package com.tyss.optimize.data.models.db.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.result.UpdateResult;
import com.tyss.optimize.common.model.auth.AccessTokenMapper;
import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.common.util.CommonUtil;
import com.tyss.optimize.common.util.PlatformTypes;
import com.tyss.optimize.common.util.ProjectTypes;
import com.tyss.optimize.config.ReqHeader;
import com.tyss.optimize.config.SpringBeans;
import com.tyss.optimize.data.models.db.model.*;
import com.tyss.optimize.data.models.dto.ResourceResponse;
import com.tyss.optimize.data.models.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.*;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@Slf4j
public class MongoUtilService {

    private final int initValue = 1000;

    public String generateSequence(String seqName, MongoTemplate mongoTemplate) {

        if(Objects.nonNull(mongoTemplate)) {
            Sequence counter = mongoTemplate.findAndModify(query(where("_id").is(seqName)),
                    new Update().inc("seq",1), options().returnNew(true).upsert(true),
                    Sequence.class);
            Long seq = !Objects.isNull(counter) ? (initValue + counter.getSeq()) : initValue;
            String specifiedSeq = entitySequenceGenerated(seqName);
            return specifiedSeq + seq;
        }
        return "";
    }

    //Generate CloneID
    public long getCloneCount(String parentId, Class<?> entityClass, MongoTemplate mongoTemplate) {
        Query query = new Query(Criteria.where("clonedFromId").is(parentId));
        long nextCloneCount = mongoTemplate.count(query, entityClass) + 1;
        return nextCloneCount;
    }
    public ResponseDTO getResponseDTO(ResponseDTO responseDTO, UpdateResult updateResult) {

        if (Objects.isNull(updateResult) || updateResult.getModifiedCount() == 0 ) {
            responseDTO.setResponseCode(HttpStatus.NOT_FOUND.value());
            responseDTO.setMessage("Resource does not exist");
            responseDTO.setStatus(CommonConstants.FAILURE);
        } else {
            responseDTO.setResponseCode(HttpStatus.OK.value());
            responseDTO.setResponseObject(updateResult.getModifiedCount());
            responseDTO.setStatus(CommonConstants.SUCCESS);
        }
        return responseDTO;
    }

    public ResponseDTO getResponseDTO(ResponseDTO responseDTO, UpdateResult updateResult, Object data) {

        if (Objects.isNull(updateResult) || updateResult.getModifiedCount() == 0) {
            responseDTO.setResponseCode(HttpStatus.NOT_FOUND.value());
            responseDTO.setMessage("Resource does not exist");
            responseDTO.setStatus(CommonConstants.FAILURE);
        } else {
            responseDTO.setResponseCode(HttpStatus.OK.value());
            responseDTO.setResponseObject(data);
            responseDTO.setStatus(CommonConstants.SUCCESS);
        }
        return responseDTO;
    }



    public Map generateSearchKey(String parentId, String newId, String resourceName, String collectionName, MongoTemplate mongoTemplate)
    {
        Map<String, String> searchKeyMap = new HashMap<>();

        if(StringUtils.isNotEmpty(parentId))
        {
            Query parentQuery = Query.query(Criteria.where("_id").
                    is(parentId));
            parentQuery.fields().include("_id").include("name").include("path").include("searchKey");

            Document parentDoc = mongoTemplate.findOne(parentQuery, Document.class, collectionName);
            if(Objects.nonNull(parentDoc)) {
                String parentPath = parentDoc.getString("path");
                String parentSearchKey = parentDoc.getString("searchKey");

                searchKeyMap.put("path", parentPath + "/" + resourceName);
                searchKeyMap.put("searchKey", parentSearchKey + "/" + newId);

            }
        }
        else {
            searchKeyMap.put("path", resourceName );
            searchKeyMap.put("searchKey", newId);
        }
        return searchKeyMap;
    }

    public List<String> getSearchKeyEntries(String parentId, String collectionName, MongoTemplate mongoTemplate)
    {
            List<String> searchKeyEntries = new ArrayList<>();
            Query parentQuery = Query.query(Criteria.where("_id").
                    is(parentId));
            parentQuery.fields().include("_id").include("name").include("path").include("searchKey");

            Document parentDoc = mongoTemplate.findOne(parentQuery, Document.class, collectionName);
            if(Objects.nonNull(parentDoc)) {
                String parentSearchKey = parentDoc.getString("searchKey");
                if(Objects.nonNull(parentSearchKey))
                {
                    String [] parentModuleIds = parentSearchKey.split("/");
                    searchKeyEntries = Arrays.asList(parentModuleIds);
                }

            }
        return searchKeyEntries;
    }

    public boolean isDuplicate(String parentId, String name, String collectionName, MongoTemplate mongoTemplate) {
        Query parentQuery = null;
        if(Objects.nonNull(parentId)) {
            parentQuery = Query.query(Criteria.where("parentId").
                    is(parentId).and("name").is(name.trim()));
        }else{
            parentQuery = Query.query(Criteria.where("name").is(name.trim()));
        }

        Long count = mongoTemplate.count(parentQuery, collectionName);
        return (count > 0) ? true : false;
    }

    public boolean isDuplicateIgnoreCase(String parentId, String name, String projectId, String collectionName, MongoTemplate mongoTemplate) {
        Query parentQuery = null,parentDirectQuery = null;
        boolean unMatchedClose = false;
        if (Objects.nonNull(parentId)) {
            try {
                parentQuery = Query.query(Criteria.where("parentId").
                        is(parentId).and("name").regex(Pattern.compile(Pattern.quote(name), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)).and("projectId").is(projectId));
                parentDirectQuery = Query.query(Criteria.where("parentId").
                        is(parentId).and("name").is(name).and("projectId").is(projectId));
            } catch (PatternSyntaxException e) {
                if (e.getMessage().contains("Unmatched closing")) {
                    unMatchedClose = true;
                    parentQuery = Query.query(Criteria.where("parentId").is(parentId).and("projectId").is(projectId));
                }
            }
        } else {
            try {
                parentQuery = Query.query(Criteria.where("name").regex(Pattern.compile(Pattern.quote(name), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)).and("projectId").is(projectId));
                parentDirectQuery = Query.query(Criteria.where("name").is(name).and("projectId").is(projectId));
            } catch (PatternSyntaxException e) {
                if (e.getMessage().contains("Unmatched closing")) {
                    unMatchedClose = true;
                    parentQuery = Query.query(Criteria.where("projectId").is(projectId));
                }
            }
        }
        log.info("isDuplicateIgnoreCase Query: " + parentQuery);
        long count = 0, countDirect = 0;
        if (unMatchedClose) {
            List<Document> list = mongoTemplate.find(parentQuery, Document.class, collectionName);
            list = list.stream().filter(document -> document.get("name").toString().equals(name)).collect(Collectors.toList());
            count = list.size();
        } else {
            count = mongoTemplate.count(parentQuery, collectionName);
            countDirect = mongoTemplate.count(parentDirectQuery, collectionName);
        }
        return ((count > 0) && (countDirect > 0)) ? true : false;
    }

    public List<Page> duplicatePagesIgnoreCase(String parentId, String name, String projectId, String collectionName, MongoTemplate mongoTemplate) {
        Query parentQuery = null;
        String regexStr = "^" + name+ "$";
        boolean unMatchedClose = false;
        if(Objects.nonNull(parentId)) {
            try {
                parentQuery = Query.query(Criteria.where("parentId").
                        is(parentId).and("name").regex(regexStr, "i").and("projectId").is(projectId));
            } catch (PatternSyntaxException e) {
                if (e.getMessage().contains("Unmatched closing")) {
                    unMatchedClose = true;
                    parentQuery = Query.query(Criteria.where("parentId").is(parentId).and("projectId").is(projectId));
                }
            }
        }else{
            try {
                parentQuery = Query.query(Criteria.where("name").regex(regexStr, "i").and("projectId").is(projectId));
            } catch (PatternSyntaxException e) {
                if (e.getMessage().contains("Unmatched closing")) {
                    unMatchedClose = true;
                    parentQuery = Query.query(Criteria.where("projectId").is(projectId));
                }
            }
        }
        log.info("duplicatePagesIgnoreCase Query: " + parentQuery);
        List<Page> pageList = mongoTemplate.find(parentQuery,Page.class,collectionName);
        if (unMatchedClose) {
            return pageList.stream().filter(page -> page.getName().equals(name)).collect(Collectors.toList());
        }
        return pageList;
    }

    public List<Page> duplicatePages(String parentId, String name, String collectionName, MongoTemplate mongoTemplate) {
        Query parentQuery = null;
        if(Objects.nonNull(parentId)) {
            parentQuery = Query.query(Criteria.where("parentId").
                    is(parentId).and("name").is(name.trim()));
        }else{
            parentQuery = Query.query(Criteria.where("name").is(name.trim()));
        }
        return mongoTemplate.find(parentQuery,Page.class,collectionName);
    }

    public List<PackageModel> duplicatePackagesIgnoreCase(String parentId, String name, String projectId, String collectionName, MongoTemplate mongoTemplate) {
        Query parentQuery = null;
        String regexStr = "^" + name+ "$";
        boolean unMatchedClose = false;
        if(Objects.nonNull(parentId)) {
            try {
                parentQuery = Query.query(Criteria.where("parentId").
                        is(parentId).and("name").regex(regexStr, "i").and("projectId").is(projectId));
            } catch (PatternSyntaxException e) {
                if (e.getMessage().contains("Unmatched closing")) {
                    unMatchedClose = true;
                    parentQuery = Query.query(Criteria.where("parentId").is(parentId).and("projectId").is(projectId));
                }
            }
        }else{
            try {
                parentQuery = Query.query(Criteria.where("name").regex(regexStr, "i").and("projectId").is(projectId));
            } catch (PatternSyntaxException e) {
                if (e.getMessage().contains("Unmatched closing")) {
                    unMatchedClose = true;
                    parentQuery = Query.query(Criteria.where("projectId").is(projectId));
                }
            }
        }
        log.info("duplicatePackagesIgnoreCase Query: " + parentQuery);
        List<PackageModel> packageModelList = mongoTemplate.find(parentQuery, PackageModel.class,collectionName);
        if (unMatchedClose) {
            return packageModelList.stream().filter(packageModel -> packageModel.getName().equals(name)).collect(Collectors.toList());
        }
        return packageModelList;
    }

    public List<PackageModel> duplicatePackages(String parentId, String name, String collectionName, MongoTemplate mongoTemplate) {
        Query parentQuery = null;
        if(Objects.nonNull(parentId)) {
            parentQuery = Query.query(Criteria.where("parentId").
                    is(parentId).and("name").is(name.trim()));
        }else{
            parentQuery = Query.query(Criteria.where("name").is(name.trim()));
        }
        return mongoTemplate.find(parentQuery, PackageModel.class,collectionName);
    }

    public List<Library> duplicateLibrariesIgnoreCase(String parentId, String name, String projectId, String collectionName, MongoTemplate mongoTemplate) {
        Query parentQuery = null;
        String regexStr = "^" + name+ "$";
        boolean unMatchedClose = false;
        if (Objects.nonNull(parentId)) {
            try {
                parentQuery = Query.query(Criteria.where("parentId").
                        is(parentId).and("name").regex(regexStr, "i").and("projectId").is(projectId));
            } catch (PatternSyntaxException e) {
                if (e.getMessage().contains("Unmatched closing")) {
                    unMatchedClose = true;
                    parentQuery = Query.query(Criteria.where("parentId").is(parentId).and("projectId").is(projectId));
                }
            }
        }else{
            try {
                parentQuery = Query.query(Criteria.where("name").regex(regexStr, "i").and("projectId").is(projectId));
            } catch (PatternSyntaxException e) {
                if (e.getMessage().contains("Unmatched closing")) {
                    unMatchedClose = true;
                    parentQuery = Query.query(Criteria.where("projectId").is(projectId));
                }
            }
        }
        log.info("duplicateLibrariesIgnoreCase Query: " + parentQuery);
        List<Library> libraryList = mongoTemplate.find(parentQuery, Library.class, collectionName);
        if (unMatchedClose) {
            return libraryList.stream().filter(library -> library.getName().equals(name)).collect(Collectors.toList());
        }
        return libraryList;
    }

    public List<Library> duplicateLibraries(String parentId, String name, String collectionName, MongoTemplate mongoTemplate) {
        Query parentQuery = null;
        if(Objects.nonNull(parentId)) {
            parentQuery = Query.query(Criteria.where("parentId").
                    is(parentId).and("name").is(name.trim()));
        }else{
            parentQuery = Query.query(Criteria.where("name").is(name.trim()));
        }
        return mongoTemplate.find(parentQuery, Library.class,collectionName);
    }
    
    public long updateParentCount(String parentId, String countField, String collectionName, MongoTemplate mongoTemplate) {

        Document update = mongoTemplate.findAndModify(query(where("_id").is(parentId)),
                new Update().inc(countField,1), options().returnNew(true).upsert(false),
                Document.class, collectionName);
            if(Objects.isNull(update))
            {
                return 0;
            }

        return Long.valueOf((Objects.nonNull(update.get(countField))) ? update.get(countField).toString() : "0");
    }

    public long decrementParentCount(String parentId, String countField, String collectionName, MongoTemplate mongoTemplate) {

        Document update = mongoTemplate.findAndModify(query(where("_id").is(parentId)),
                new Update().inc(countField,-1), options().returnNew(true).upsert(false),
                Document.class, collectionName);
        if(Objects.isNull(update))
        {
            return 0;
        }

        return Long.valueOf((Objects.nonNull(update.get(countField))) ? update.get(countField).toString() : "0");
    }

    public UpdateResult updateChildCount(String parentId, String field, int count, String collectionName, MongoTemplate mongoTemplate) {

        Update update = new Update();
        update.set(field, count);

        Query query = new Query(Criteria.where("_id").is(parentId));
        return mongoTemplate.updateFirst(query, update, collectionName);
    }

    public int getChildCount(String parentId, String childField, String collectionName, MongoTemplate mongoTemplate) {

        MatchOperation matchStage = Aggregation.match(new Criteria("_id").is(parentId));
        ProjectionOperation projectStage = Aggregation.
                project().
                and(childField).size().as("count");

        Aggregation aggregation
                = Aggregation.newAggregation(matchStage, projectStage);

        AggregationResults<Document> output
                = mongoTemplate.aggregate(aggregation, collectionName, Document.class);
        Integer count = Integer.valueOf(output.getMappedResults().get(0).get("count").toString());
        return count;
    }

    public boolean isDocumentExists(String projectId, String collectionName, MongoTemplate mongoTemplate) {

        Query query = Query.query(Criteria.where("_id").
                is(projectId));

        Long count = mongoTemplate.count(query, collectionName);
        return (count > 0) ? true : false;
    }

    public boolean isRoot(String id, String collectionName, MongoTemplate mongoTemplate) {

        Query query = Query.query(Criteria.where("_id").
                is(id).and("name").regex("^root$",  "i"));

        Long count = mongoTemplate.count(query, collectionName);
        return (count > 0) ? true : false;
    }

    public boolean isScript(String id) {
        return Pattern.compile("^SCR").matcher(id).find();
    }

    public boolean isModule(String id) {
        return Pattern.compile("^MOD").matcher(id).find();
    }

    public boolean isFile(String id) {
        return Pattern.compile("^FILE").matcher(id).find();
    }

    public boolean isFolder(String id) {
        return Pattern.compile("^FLDR").matcher(id).find();
    }

    public boolean isParentExists(String parentId, String projectId, String collectionName, MongoTemplate mongoTemplate) {

        Query query = Query.query(Criteria.where("projectId").
                is(projectId).and("_id").is(parentId));

        Long count = mongoTemplate.count(query, collectionName);
        return (count > 0) ? true : false;
    }

    public boolean isInnerDocumentExists(String parentId, String childKey, String childId, String collectionName, MongoTemplate mongoTemplate) {

        Query query = new Query(Criteria.where("_id").is(parentId)
                .and(childKey).is(childId)
        );
        return mongoTemplate.exists(query, collectionName);
    }

    private String entitySequenceGenerated(String seqName) {
        return CommonConstants.sequenceGenMap.get(seqName);
    }

    public Map<String, Object> convertObjectToMap(Object obj)
    {
        ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> map = oMapper.convertValue(obj, Map.class);
        map.remove("id");
        return map;
    }

    public Update convertObjectToUpdate(String searchKey, Object obj)  {
        ObjectMapper oMapper = new ObjectMapper();
        String name = obj.getClass().getName();
        String[] split = name.split("\\.");
        String className = split[split.length - 1];
        Map<String, Object> map = oMapper.convertValue(obj, Map.class);
        map.remove("id");
        if(!className.equalsIgnoreCase("LicenseDetails")) {
            map.put("modifiedOn" , CommonUtil.getCurrentDate());
        }
        map.remove("createdOn");
        if(obj instanceof User){
            if(Objects.isNull(((User) obj).getLicenses())) {
                map.remove("licenses");
            }
        }
        Update update = new Update();
        map.forEach((k, v) -> {
            update.set(searchKey+k, v);
        });
        return update;
    }

    public Update convertObjectArrayToUpdate(String searchKey, Object obj)
    {
        ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> map = oMapper.convertValue(obj, Map.class);
        map.remove("id");
        map.put("modifiedOn" , CommonUtil.getCurrentDate());
        map.remove("createdOn");
        if(obj instanceof User){
            if(Objects.isNull(((User) obj).getLicenses())) {
                map.remove("licenses");
            }
        }
       List<Object> projectTypes = (List<Object>) map.get("projectTypes");
        List projectTypeList =new ArrayList<>();
        for (Object object : projectTypes) {
            Map<String, Object> internalMap = oMapper.convertValue(object, Map.class);

            internalMap.put("_id",internalMap.get("id"));
            internalMap.remove("id");
            projectTypeList.add(internalMap);
        }
        map.put("projectTypes",projectTypeList);
        Update update = new Update();
        map.forEach((k, v) -> {
            update.set(searchKey+k, v);
        });
        return update;
    }

    public AccessTokenMapper fetchUserAuthData() {
        String userId = null;
        String licenseId = null;
        String privilege = null;
        String userName = null;
        String name = null;
        AccessTokenMapper accessTokenMapper = new AccessTokenMapper();

        KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        if(Objects.isNull(authentication)){
            ReqHeader reqHeaderBean = SpringBeans.getReqHeaderBean();
            accessTokenMapper.setLicenseId(reqHeaderBean.getLicenseId());
            accessTokenMapper.setUserName(reqHeaderBean.getUserEmail());
        }
        else if(Objects.nonNull(authentication)) {
            Principal principal = (Principal) authentication.getPrincipal();
            KeycloakPrincipal<KeycloakSecurityContext> kp = (KeycloakPrincipal<KeycloakSecurityContext>) principal;
            AccessToken token = kp.getKeycloakSecurityContext().getToken();
            String accessToken = kp.getKeycloakSecurityContext().getTokenString();

            accessTokenMapper.setAccess_token(accessToken);

            Map<String, Object> otherClaims = token.getOtherClaims();
            if (otherClaims.containsKey("id")) {
                userId = (String) otherClaims.get("id");
            }
            accessTokenMapper.setId(userId);

            if (otherClaims.containsKey("licenseId")) {
                licenseId = (String) otherClaims.get("licenseId");
            }
            accessTokenMapper.setLicenseId(licenseId);

            if (otherClaims.containsKey("currentPrivilege")) {
                privilege = (String) otherClaims.get("currentPrivilege");
            }
            accessTokenMapper.setPrivilege(privilege);

            if (otherClaims.containsKey("userName")) {
                userName = (String) otherClaims.get("userName");
            }
            accessTokenMapper.setUserName(userName);

            if (otherClaims.containsKey("fullName")) {
                name = (String) otherClaims.get("fullName");
            }
            log.info("fetchKeycloakUserAuthData() otherClaims={}, userId={}, licenseId={},privilege={},fullName={}"
                    , otherClaims,userId,licenseId,privilege,name);
            accessTokenMapper.setName(name);

            if (Objects.nonNull(accessTokenMapper) && StringUtils.isNotEmpty(accessTokenMapper.getId())) {
                accessTokenMapper.setValid(true);
            } else {
                accessTokenMapper.setValid(false);
            }
        }
        return accessTokenMapper;
    }



    public AccessTokenMapper fetchUserAuthDataWithoutLicense() {

        AccessTokenMapper accessTokenMapper = new AccessTokenMapper();
        KeycloakAuthenticationToken authentication =
                (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Principal principal = (Principal) authentication.getPrincipal();
        String accessToken = null;
        String userId = null;

        if (principal instanceof KeycloakPrincipal) {
            KeycloakPrincipal<KeycloakSecurityContext> kp = (KeycloakPrincipal<KeycloakSecurityContext>) principal;
            AccessToken token = kp.getKeycloakSecurityContext().getToken();
            accessToken = kp.getKeycloakSecurityContext().getTokenString();
            accessTokenMapper.setAccess_token(accessToken);

            Map<String, Object> otherClaims = token.getOtherClaims();
            log.info("Inside fetchUserAuthDataWithoutLicense() otherClaims: "+otherClaims);
            if(otherClaims.containsKey("id")){
                userId = (String) otherClaims.get("id");
            }
            accessTokenMapper.setId(userId);
        }

        if(Objects.nonNull(accessTokenMapper) && StringUtils.isNotEmpty(accessTokenMapper.getId())) {
            accessTokenMapper.setValid(true);
        } else {
            accessTokenMapper.setValid(false);
        }

        return accessTokenMapper;
    }

    public  ResponseDTO inValidTokenResponse(ResponseDTO responseDTO) {
        responseDTO.setResponseCode(HttpStatus.UNAUTHORIZED.value());
        responseDTO.setMessage(CommonConstants.TOKEN_INVALID);
        responseDTO.setStatus(CommonConstants.FAILURE);
        return responseDTO;
    }


    public ResourceResponse inValidTokenResourceResponse(ResourceResponse resourceResponse) {
        resourceResponse.setResponseCode(HttpStatus.UNAUTHORIZED.value());
        resourceResponse.setMessage(CommonConstants.TOKEN_INVALID);
        resourceResponse.setStatus(CommonConstants.FAILURE);
        return resourceResponse;
    }

    public  ResponseDTO alreadyExistResponse(ResponseDTO responseDTO) {
        String msg = responseDTO.getMessage();
        responseDTO.setResponseCode(HttpStatus.FOUND.value());
        responseDTO.setMessage(msg+CommonConstants.ALREADY_EXIST);
        responseDTO.setStatus(CommonConstants.FAILURE);
        return responseDTO;
    }
    
    public ResponseDTO prepareResponseEntity(int responseCode,String message,String status){
        var responseDTO = new ResponseDTO();
        responseDTO.setResponseCode(responseCode);
        responseDTO.setMessage(message);
        responseDTO.setStatus(status);
        return responseDTO;
    }

    public ResponseDTO prepareResponse(ResponseDTO responseDTO,int responseCode,String message,String status){
        responseDTO.setResponseCode(responseCode);
        responseDTO.setMessage(message);
        responseDTO.setStatus(status);
        return responseDTO;
    }
    
    public List<MultipartFile> filterApkFiles(List<MultipartFile> files) throws IOException {
        List<MultipartFile> apkList = new ArrayList<>();
        if(!files.isEmpty()) {
            for(MultipartFile file: files) {
                ByteArrayResource fileAsResource = new ByteArrayResource(file.getBytes()) {

                    @Override
                    public String getFilename() {
                        return file.getOriginalFilename();
                    }

                    @Override
                    public long contentLength() {
                        return file.getSize();
                    }
                };
               	String fileName = fileAsResource.getFilename();
                int index = fileName.lastIndexOf(".");
                String extension = fileName.substring(index+1);
                if(extension.equalsIgnoreCase("apk")) {
                   apkList.add(file);
                }
            }
        }
        return apkList;
    }

    public List<MultipartFile> filterIpaFiles(List<MultipartFile> files) throws IOException {
        List<MultipartFile> ipaList = new ArrayList<>();
        if(!files.isEmpty()) {
            for(MultipartFile file: files) {

                ByteArrayResource fileAsResource = new ByteArrayResource(file.getBytes()) {

                    @Override
                    public String getFilename() {
                        return file.getOriginalFilename();
                    }

                    @Override
                    public long contentLength() {
                        return file.getSize();
                    }
                };

                String fileName = file.getOriginalFilename();
                int index = fileName.lastIndexOf(".");
                String extension = fileName.substring(index+1);
                if(extension.equalsIgnoreCase("ipa")) {
                    ipaList.add(file);
                }
            }
        }
        return ipaList;
    }

    public Folder createDefaultFolder(String type, String parentId){
        Folder folderDefault = new Folder();
        if(type.equalsIgnoreCase(PlatformTypes.ANDROID)){
            folderDefault.setName(CommonConstants.DEFAULT_ANDROID_FLD);
            folderDefault.setDesc("Android Default folder");
            folderDefault.setParentId(parentId);
            folderDefault.setDefaultFolder(true);
        }
        if(type.equalsIgnoreCase(PlatformTypes.IOS)){
            folderDefault.setName(CommonConstants.DEFAULT_IOS_FLD);
            folderDefault.setDesc("iOS Default folder");
            folderDefault.setParentId(parentId);
            folderDefault.setDefaultFolder(true);
        }
        if(type.equalsIgnoreCase(CommonConstants.DEFAULT_JAR_FLD)){
            folderDefault.setName(CommonConstants.DEFAULT_JAR_FLD);
            folderDefault.setDesc("PE Jar Default folder");
            folderDefault.setParentId(parentId);
            folderDefault.setDefaultFolder(true);
        }
        if(type.equalsIgnoreCase(CommonConstants.FINAL_JAR_FLD)){
            folderDefault.setName(CommonConstants.FINAL_JAR_FLD);
            folderDefault.setDesc("Final PE Jar Default folder");
            folderDefault.setParentId(parentId);
            folderDefault.setDefaultFolder(true);
        }
        return folderDefault;
    }

    public Library createDefaultLibrary(String type, String parentId){
        Library defaultLibrary = new Library();
        if(type.equalsIgnoreCase(CommonConstants.DEFAULT_LIBRARY_WEB)) {
            defaultLibrary.setName(CommonConstants.DEFAULT_LIBRARY_WEB);
            defaultLibrary.setDesc(CommonConstants.DEFAULT_LIBRARY_WEB + " Library");
            defaultLibrary.setExecutionOrder(1.0);
            defaultLibrary.setParentId(parentId);
            defaultLibrary.setDefaultLibrary(true);
        }
        if(type.equalsIgnoreCase(CommonConstants.DEFAULT_LIBRARY_MOBILE)) {
            defaultLibrary.setName(CommonConstants.DEFAULT_LIBRARY_MOBILE);
            defaultLibrary.setDesc(CommonConstants.DEFAULT_LIBRARY_MOBILE + " Library");
            defaultLibrary.setExecutionOrder(1.0);
            defaultLibrary.setParentId(parentId);
            defaultLibrary.setDefaultLibrary(true);
        }
        return defaultLibrary;
    }

    public static boolean isValidPlatForm(String projectType,String platForm){
        WeakHashMap<String,String> weakHashMap = new WeakHashMap();
        if(projectType.equalsIgnoreCase("Web") && Objects.isNull(platForm)){return  true;}

        weakHashMap.put(ProjectTypes.MOBILE,PlatformTypes.ANDROID+" or "+PlatformTypes.IOS);
        weakHashMap.put(ProjectTypes.WEB_AND_MOBILE,PlatformTypes.WEB+" or "+PlatformTypes.ANDROID+" or "+PlatformTypes.IOS+" or "+PlatformTypes.MOBILE_WEB);
        weakHashMap.put(ProjectTypes.WEB_SERVICES,CommonConstants.NA);
        weakHashMap.put(ProjectTypes.WEB,CommonConstants.NA+" or "+PlatformTypes.WEB);
        weakHashMap.put(ProjectTypes.SALES_FORCE,PlatformTypes.WEB+" or "+PlatformTypes.ANDROID+" or "+PlatformTypes.IOS+" or "+PlatformTypes.MOBILE_WEB+" or "+PlatformTypes.SFDC);

       Object value = weakHashMap.get(projectType);if(value == null){ return false;}

        return weakHashMap.get(projectType).contains(platForm);
    }

    public ResponseDTO validateProjectId(String projectId, String collectionName, MongoTemplate mongoTemplate) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (StringUtils.isEmpty(projectId)) {
            responseDTO.setResponseCode(HttpStatus.BAD_REQUEST.value());
            responseDTO.setMessage("Project Id is mandatory");
            responseDTO.setStatus(CommonConstants.FAILURE);
            return responseDTO;
        } else {
            boolean isProjectExists = isDocumentExists(projectId, collectionName, mongoTemplate);
            if (!isProjectExists) {
                responseDTO.setResponseCode(HttpStatus.BAD_REQUEST.value());
                responseDTO.setMessage("Project Id " + projectId + " does not exist");
                responseDTO.setStatus(CommonConstants.FAILURE);
                return responseDTO;
            }
        }
        responseDTO.setResponseCode(HttpStatus.OK.value());
        responseDTO.setMessage("Valid Project Id " + projectId);
        responseDTO.setStatus(CommonConstants.SUCCESS);
        return responseDTO;
    }

    public boolean isSameFileName(String fileName, MultipartFile file) throws IOException {
        boolean isSame = false;
        ByteArrayResource fileAsResource = new ByteArrayResource(file.getBytes()) {

            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }

            @Override
            public long contentLength() {
                return file.getSize();
            }
        };
        String inputFileName = fileAsResource.getFilename();
        if(fileName.equalsIgnoreCase(inputFileName)) {
            isSame = true;
        }
        return isSame;
    }

    public static ResponseDTO findDuplicateLocatorValues(RecordedElement recordedElement){
        ResponseDTO responseDTO = new ResponseDTO();

        List<Locator>  duplicates = recordedElement.getLocators().stream()
                .collect(Collectors.groupingBy(Locator::getName))
                .entrySet().stream()
                .filter(e->e.getValue().size() > 1)
                .flatMap(e->e.getValue().stream())
                .collect(Collectors.toList());

        if (duplicates.size() > 1) {
            responseDTO.setResponseCode(HttpStatus.BAD_REQUEST.value());
            responseDTO.setMessage("Duplicate Locator values are not allowed");
            responseDTO.setStatus(CommonConstants.FAILURE);
        }

        return responseDTO;
    }
    
    public String getAccessToken() {
		return ((OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()).getTokenValue();
    }

  public static Map<String,String> notAllowedLocators(){

      Map<String,String> notAllowedLocators= new HashMap<>();
      notAllowedLocators.put("id","id");
      notAllowedLocators.put("name","name");
      notAllowedLocators.put("className","className");
      notAllowedLocators.put("tagName","tagName");
      notAllowedLocators.put("linkText","linkText");
      notAllowedLocators.put("accessibilityID","accessibilityID");

      return notAllowedLocators;
  }

    public static Map<String,String> dynamicLocators(){

        Map<String,String> dynamicLocators= new HashMap<>();
        dynamicLocators.put("partialLinkText","partialLinkText");
        dynamicLocators.put("cssSelector","cssSelector");
        dynamicLocators.put("xpath","xpath");
        dynamicLocators.put("AndroidUiAutomator","AndroidUiAutomator");
        dynamicLocators.put("Class Chain","Class Chain");
        dynamicLocators.put("Predicate String","Predicate String");

        return dynamicLocators;
    }

    public static Map<String,String> AllLocators(){

        Map<String,String> AllLocators= new HashMap<>();

        AllLocators.put("id","id");
        AllLocators.put("name","name");
        AllLocators.put("className","className");
        AllLocators.put("tagName","tagName");
        AllLocators.put("linkText","linkText");
        AllLocators.put("partialLinkText","partialLinkText");
        AllLocators.put("cssSelector","cssSelector");
        AllLocators.put("xpath","xpath");

        return AllLocators;
    }

   public static  <Target> Consumer<Target> handleCheckedExceptionConsumer(CheckedExceptionHandlerConsumer<Target,Exception> handlerConsumer){
        return obj -> {
            try {
                handlerConsumer.accept(obj);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        };
    }
    public Update convertConditionToUpdate(Conditions conditionDB, Conditions conditionReq)  {
        Map<String, Object> conditionDBMap = convertObjectToMap(conditionDB);
        Map<String, Object> conditionReqMap = convertObjectToMap(conditionReq);
        conditionReqMap.remove("_id");
        Update update = new Update();
        conditionReqMap.forEach((reqKey, reqValue)->{
            conditionDBMap.forEach((dbKey, dbValue)->{
                if (Objects.equals(reqKey,dbKey)){
                    conditionDBMap.put(reqKey,reqValue);
                }
                update.set(dbKey,dbValue);
            });
        });
        return update;
    }

    public ResponseDTO conditionResponseDTO(ResponseDTO responseDTO, Conditions conditionsResult) {

        if (Objects.isNull(conditionsResult)) {
            responseDTO.setResponseCode(HttpStatus.NOT_FOUND.value());
            responseDTO.setMessage("Resource does not exist");
            responseDTO.setStatus(CommonConstants.FAILURE);
        } else {
            responseDTO.setResponseCode(HttpStatus.OK.value());
            responseDTO.setResponseObject(1);
            responseDTO.setStatus(CommonConstants.SUCCESS);
        }
        return responseDTO;
    }

    public ResponseDTO createConditionResponseDTO(ResponseDTO responseDTO, Conditions condition2, Object data) {

        if (Objects.isNull(condition2) ) {
            responseDTO.setResponseCode(HttpStatus.NOT_FOUND.value());
            responseDTO.setMessage("Resource does not exist");
            responseDTO.setStatus(CommonConstants.FAILURE);
        } else {
            responseDTO.setResponseCode(HttpStatus.OK.value());
            responseDTO.setResponseObject(data);
            responseDTO.setStatus(CommonConstants.SUCCESS);
        }
        return responseDTO;
    }

}

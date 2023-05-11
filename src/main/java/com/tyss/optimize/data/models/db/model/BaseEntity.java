package com.tyss.optimize.data.models.db.model;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.common.util.CommonUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Data
public class BaseEntity {

    String createdBy;
    String modifiedBy;
    String createdByUname;
    String modifiedByUname;
    String createdOn;
    String modifiedOn;
    String state;
    String refId;
    String path;
    String searchKey;
    String parentId;
    String parentName;
    Float ver;
    String clonedFromId;

    public  void createEntity(String userId, String userName)
    {
        this.createdOn = CommonUtil.getCurrentDate();
        this.modifiedOn = CommonConstants.DEFAULT_ICON;
        this.modifiedBy = CommonConstants.DEFAULT_ICON;
        this.createdBy = userId;
        this.createdByUname = userName;
        this.modifiedByUname = CommonConstants.DEFAULT_ICON;
    }

    public  void updateEntity(String userId, String userName)
    {  
        this.modifiedOn = CommonUtil.getCurrentDate();
        this.modifiedBy = userId;
        this.modifiedByUname = userName;
    }

    public String getParentId() {
        if(Objects.nonNull(this.parentId) && StringUtils.isEmpty(this.parentId)) {
            this.parentId = null;
        }
        return parentId;
    }

    public String getCreatedOn() {
        return CommonUtil.getFormattedDate(createdOn);
    }

    public String getModifiedOn() {
        return CommonUtil.getFormattedDate(modifiedOn);
    }

    public void setParentId(String parentId) {
        if(Objects.nonNull(parentId) && StringUtils.isEmpty(parentId)) {
            this.parentId = null;
        }
        else {
            this.parentId = parentId;
        }
    }


    public String unFormattedCreatedDate(){
        return this.createdOn;
    }

    public String unFormattedModifiedDate(){
        return this.modifiedOn;
    }



}

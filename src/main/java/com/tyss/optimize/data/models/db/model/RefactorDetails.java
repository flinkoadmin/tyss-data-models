package com.tyss.optimize.data.models.db.model;

import lombok.Data;

import java.util.List;

@Data
public class RefactorDetails {

    List<String> dbNames;   // provide ALL if all the DBs needs to be updated
    List<String> emailIds; // provide ALL if all the documents needs to be updated
    List<String> licenseIds; // provide ALL if all the documents needs to be updated
    String attributeName;
    String collectionName;
    String dependentColl;
    String operation;
    Object defaultAttributeValue;
    boolean overrideRequired = false;
    String refactorHandler;
}

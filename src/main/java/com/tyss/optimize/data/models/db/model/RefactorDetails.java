package com.tyss.optimize.data.models.db.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefactorDetails {

    @NonNull
    List<String> dbNames;   // provide ALL if all the DBs needs to be updated
    @NonNull
    List<String> emailIds; // provide ALL if all the documents needs to be updated
    @NonNull
    List<String> licenseIds; // provide ALL if all the documents needs to be updated
    @NonNull
    List<String> ids; // provide ALL if all the documents needs to be updated
    String attributeName;
    String collectionName;
    String dependentColl;
    String operation;
    Object attributeValue;
    String attributeValueType;
    boolean isNested;
    boolean overrideRequired = false;
    String refactorHandler;
}

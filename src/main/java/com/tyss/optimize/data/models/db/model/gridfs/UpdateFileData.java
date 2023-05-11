package com.tyss.optimize.data.models.db.model.gridfs;

import lombok.Data;
import org.bson.Document;
import org.springframework.data.mongodb.core.query.Query;

import java.io.File;


@Data
public class UpdateFileData {

    private File actualDataToStore;
    private Document gridFSDbInfo;
    private Document metaData;
    private Query query;
    private String collectionName;

}

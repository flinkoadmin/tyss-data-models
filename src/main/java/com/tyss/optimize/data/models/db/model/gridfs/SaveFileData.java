package com.tyss.optimize.data.models.db.model.gridfs;

import lombok.Data;
import org.bson.Document;

import java.io.File;

@Data
public class SaveFileData {

    private File actualDataToStore;
    private Document gridFSDbInfo;
    private Document metaData;

}

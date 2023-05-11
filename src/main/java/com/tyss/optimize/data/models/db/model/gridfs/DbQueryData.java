package com.tyss.optimize.data.models.db.model.gridfs;

import lombok.Data;
import org.bson.Document;
import org.json.simple.JSONObject;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;

@Data
public class DbQueryData {

    private Document gridFSDbInfo;
    private Object dbQuery;

}

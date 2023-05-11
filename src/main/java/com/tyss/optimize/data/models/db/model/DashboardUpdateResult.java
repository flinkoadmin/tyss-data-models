package com.tyss.optimize.data.models.db.model;

import com.mongodb.client.result.UpdateResult;
import lombok.Data;

@Data
public class DashboardUpdateResult {
    private UpdateResult updateResult;
    private String updateFileResult;
}


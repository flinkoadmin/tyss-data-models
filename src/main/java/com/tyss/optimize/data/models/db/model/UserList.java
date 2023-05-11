package com.tyss.optimize.data.models.db.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;

import java.util.List;

@Data
@NoArgsConstructor
public class UserList {
    List<JSONObject> users;
}

package com.tyss.optimize.data.models.db.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dashboard {

    private List<Object> projects;

    private List<Object> users;
}

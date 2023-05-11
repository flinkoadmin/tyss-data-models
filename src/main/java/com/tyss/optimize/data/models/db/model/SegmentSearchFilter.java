package com.tyss.optimize.data.models.db.model;

import lombok.Data;

import java.util.List;

@Data
public class SegmentSearchFilter {
    private List<String> severity;
    private List<String> priority;
    private List<String> modules;
    private List<String> locations;
    private List<String> createdOn;
}

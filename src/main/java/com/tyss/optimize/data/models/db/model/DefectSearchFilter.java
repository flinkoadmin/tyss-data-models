package com.tyss.optimize.data.models.db.model;

import lombok.Data;

@Data
public class DefectSearchFilter {
    private SegmentSearchFilter segmentSearchFilters;
    private WebSearchFilter webSearchFilters;
    private MobileSearchFilter mobileSearchFilters;
}

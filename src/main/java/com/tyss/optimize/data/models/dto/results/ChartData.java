package com.tyss.optimize.data.models.dto.results;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChartData {
	
	 private String result;
	    private String category;
	    private Integer value;
	    private Integer total;



}

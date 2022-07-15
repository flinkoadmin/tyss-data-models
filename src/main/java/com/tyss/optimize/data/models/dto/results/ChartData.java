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

	   /* public ChartData(String serie, String category, Integer value) {
	        super();
	        this.result = serie;
	        this.category = category;
	        this.value = value;
	    }

		public String getResult() {
			return result;
		}

		public void setResult(String serie) {
			this.result = serie;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}*/
	    

}

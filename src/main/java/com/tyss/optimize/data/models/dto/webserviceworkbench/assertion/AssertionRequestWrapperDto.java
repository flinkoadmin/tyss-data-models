package com.tyss.optimize.data.models.dto.webserviceworkbench.assertion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssertionRequestWrapperDto {

    private String inputString; //assumption, validation
    private List<AssertionRequestDto> assertions;
}

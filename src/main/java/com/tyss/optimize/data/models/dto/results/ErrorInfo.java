package com.tyss.optimize.data.models.dto.results;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ErrorInfo{
    public String cause;
    public List<String> suggestions;
    public String name;
}

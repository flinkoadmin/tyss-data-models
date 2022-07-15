package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ElementType extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "ELEMENT_TYPE";

    @Id
    private String id;
    private String elementType;
    private String description;
    private Boolean isDefault;
    // The type property is used for 'Android & iOS' platform type
    private String type;
}

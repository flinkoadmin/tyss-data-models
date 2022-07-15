package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "date_time_configs")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class DateAndTimeConfig extends BaseEntity{

    @Transient
    public static final String SEQUENCE_NAME = "DATE_TIME_CONFIG";

    @Id
    private String id;
    private String dateFormat;
    private String dateSeparator;
    private String timeFormat;
    private String timeZone;
}

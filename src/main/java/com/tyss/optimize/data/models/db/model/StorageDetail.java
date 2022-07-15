package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "storage_detail")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StorageDetail extends BaseEntity{

    @Transient
    public static final String SEQUENCE_NAME = "STORAGE_DETAIL";

    @Id
    String id;
    String name;
    String size;
    List<StorageDetail> folders;
}

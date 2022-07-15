package com.tyss.optimize.data.models.db.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "sequences")
@Data
public class Sequence {

    @Id
    private String id;

    private long seq;

}

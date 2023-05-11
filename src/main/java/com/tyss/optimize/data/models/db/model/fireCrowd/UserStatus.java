package com.tyss.optimize.data.models.db.model.fireCrowd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserStatus {
    private String emailId;
    private String status;
}

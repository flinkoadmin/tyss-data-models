package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DefectEmailConfig {

    String lifeCycleTemplateId;
    String emailAllUsers;
    List<String> additionalRecipients;
    List<String> additionalRecipientGroups;
    List<DefectStateTransitions> stateTransitions;

}

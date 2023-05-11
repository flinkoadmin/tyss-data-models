package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmailData {
    private String emailTheReportAfterExecution;
    private List<String> recipientGroup;
    private List<EmailRecipients> emailRecipient;
    private String emailOnSuiteExecutionStart;
    private String emailOnSuiteExecutionCompletion;
    private String attachPdfReport;
    private String attachHtmlReport;
    private String attachExecutionLogs;
    private String emailManuallyTriggerEmailNotification;
}

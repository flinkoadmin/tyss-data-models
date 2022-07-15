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
@Document(value = "screenshot_video_config")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ScreenShotVideoConfig extends BaseEntity{

    @Transient
    public static final String SEQUENCE_NAME = "SCREEN_SHOT_VIDEO_CONFIG";

    @Id
    public String id;
    private String captureScreenShotFor;
    private Integer noOfDaysForScreenShot;
    private String captureVideosFor;
    private Integer noOfDaysForVideo;
    private String projectId;
}

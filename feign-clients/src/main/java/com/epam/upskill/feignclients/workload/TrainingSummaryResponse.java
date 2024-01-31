package com.epam.upskill.feignclients.workload;

import lombok.*;

/**
 * @className: SpecializationRecord  $
 * @description: TODO
 * @date: 09 November 2023 $
 * @time: 3:00 PM 28 $
 * @author: Qudratjon Komilov
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainingSummaryResponse {
    private String trainerUsername;
    private String trainerFirstName;
    private String trainerLastName;
    private Boolean trainerStatus;
    private Integer totalTrainingDuration;
}



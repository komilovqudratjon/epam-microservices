package com.epam.upskill.feignclients.workload;

/**
 * @className: SpecializationRecord  $
 * @description: TODO
 * @date: 09 November 2023 $
 * @time: 3:00 PM 28 $
 * @author: Qudratjon Komilov
 */

import javax.validation.constraints.*;
import java.util.Date;

public record ResWorkloadDTO(
        @NotNull(message = "Trainer Username should not be null")
        @NotBlank(message = "Trainer Username should not be blank")
        String trainerUsername,

        @NotNull(message = "Trainer First Name should not be null")
        @NotBlank(message = "Trainer First Name should not be blank")
        @Pattern(regexp = "^[a-zA-Z\\s,.'-]{1,30}$", message = "Trainer First Name should be between 1 and 30 characters long and can include alphabetic characters and ,.'-")
        String trainerFirstName,

        @NotNull(message = "Trainer Last Name should not be null")
        @NotBlank(message = "Trainer Last Name should not be blank")
        @Pattern(regexp = "^[a-zA-Z\\s,.'-]{1,30}$", message = "Trainer Last Name should be between 1 and 30 characters long and can include alphabetic characters and ,.'-")
        String trainerLastName,

        @NotNull(message = "IsActive should not be null")
        Boolean isActive,

        @NotNull(message = "Training date should not be null")
        Date trainingDate,

        @NotNull(message = "Training duration should not be null")
        @Min(value = 1, message = "Training duration should be at least 1 minute")
        @Max(value = 480, message = "Training duration should not exceed 480 minutes")
        Integer trainingDuration,

        @NotNull(message = "Action Type should not be null")
        @Pattern(regexp = "ADD|DELETE", message = "Action Type should be either ADD or DELETE")
        String actionType) {
}



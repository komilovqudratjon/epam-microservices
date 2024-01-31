package com.epam.upskill.authenticationservice.service.impl.mapper;


import com.epam.upskill.authenticationservice.model.TrainingType;
import com.epam.upskill.authenticationservice.model.dtos.TrainingTypeDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * @description: Mapper class for TrainingTypeDTO.
 * @date: 09 November 2023 $
 * @time: 3:37 PM 25 $
 * @author: Qudratjon Komilov
 */
@Service
public class TrainingTypeDTOMapper implements Function<TrainingType, TrainingTypeDTO> {
    @Override
    public TrainingTypeDTO apply(TrainingType training) {
        return new TrainingTypeDTO(
                training.getId(),
                training.getTrainingTypeName()
        );
    }
}

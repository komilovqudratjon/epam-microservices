package com.epam.upskill.workload.service.impl;

import com.epam.upskill.feignclients.workload.ResWorkloadDTO;
import com.epam.upskill.workload.model.TrainingSummaryResponse;
import com.epam.upskill.workload.model.Workload;
import com.epam.upskill.workload.service.WorkloadService;
import com.epam.upskill.workload.service.db.WorkloadDatabase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

/**
 * @description: Service class for managing Workload entities.
 * @date: 08 November 2023 $
 * @time: 5:43 AM 47 $
 * @author: Qudratjon Komilov
 */

@Service
@AllArgsConstructor
@Slf4j
public class WorkloadServiceImpl implements WorkloadService {

    private final WorkloadDatabase workloadRepository;

    /**
     * Creates or updates a Workload record based on the provided ResWorkloadDTO.
     * If a Workload with the given ID exists, it updates the record; otherwise, it creates a new one.
     * It also handles the association of workload, trainee, and Workload type based on their identifiers.
     *
     * @param workloadDTO The Workload data transfer object containing the information for the Workload.
     * @return The created or updated WorkloadDTO.
     * @throws EntityNotFoundException if the workload, trainee, or Workload type is not found.
     */
    @Override
    public Workload createOrUpdate(ResWorkloadDTO workloadDTO, Long id) {
        log.debug("Request to create or update Workload: {}", workloadDTO);

        Workload workload;
        if (id != null) {
            workload = workloadRepository.findById(id);
        } else {
            workload = new Workload();
        }


        workload.setTrainerUsername(workloadDTO.trainerUsername());
        workload.setTrainerFirstName(workloadDTO.trainerFirstName());
        workload.setTrainerLastName(workloadDTO.trainerLastName());
        workload.setIsActive(workloadDTO.isActive());
        workload.setTrainingDate(workloadDTO.trainingDate());
        workload.setTrainingDuration(workloadDTO.trainingDuration());

        Workload apply = workloadRepository.save(workload);
        log.info("Trainer created or updated successfully: {}", apply);
        return apply;
    }

    @Override
    public void deleteWorkload(Long id) {
        log.debug("Request to delete Workload: {}", id);
        workloadRepository.deleteById(id);
    }

    @Override
    public TrainingSummaryResponse calculateMonthlySummary(String username, Date startDate, Date endDate) {
        return workloadRepository.findByTrainerUsernameAndTrainingDateBetween(username, startDate, endDate);
    }


}
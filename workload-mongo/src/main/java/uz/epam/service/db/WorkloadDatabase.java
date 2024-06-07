package uz.epam.service.db;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.epam.model.Workload;
import uz.epam.repository.WorkloadRepository;

import java.util.Date;
import java.util.List;

/**
 * @description: Service class for managing Workload entities.
 * This class provides functionalities to save, find, delete, and list Workload entities
 * by interacting with both a DAO (Data Access Object) and a JPA repository.
 * @date: 09 November 2023 $
 * @time: 7:36 PM 17 $
 * @author: Qudratjon Komilov
 */


@Service
@AllArgsConstructor
@Slf4j
public class WorkloadDatabase {

    private final WorkloadRepository workloadRepository;

    /**
     * Saves a Workload entity.
     * The method first saves the entity using the JPA repository,
     * then saves the same entity in the DAO, and logs the process.
     *
     * @param entity The Workload entity to be saved.
     * @return The saved Workload entity.
     */
    public Workload save(Workload entity) {
        log.debug("Saving a Workload entity");
        Workload savedEntity = workloadRepository.save(entity);
        log.info("Workload saved to PostgreSQL: {}", savedEntity);
        return savedEntity;
    }

    /**
     * Deletes a Workload entity.
     * The method first deletes the entity using the JPA repository,
     * then deletes the same entity in the DAO, and logs the process.
     *
     * @param entity The Workload entity to be deleted.
     */
    public void delete(Workload entity) {
        log.debug("Deleting a Workload entity");
        workloadRepository.delete(entity);
        log.info("Workload deleted from PostgreSQL: {}", entity);
    }

    /**
     * Deletes a Workload entity by its ID.
     * The method first deletes the entity using the JPA repository,
     */
    public void deleteById(Long id) {
        log.debug("Deleting a Workload entity by its ID");
        workloadRepository.deleteByWorkloadId(id);
        log.info("Workload deleted from PostgreSQL: {}", id);
    }

    /**
     * Finds a Workload entity by its ID.
     * The method first finds the entity using the JPA repository,
     * then finds the same entity in the DAO, and logs the process.
     *
     * @param id The ID of the Workload entity to be found.
     * @return The found Workload entity.
     */
    public Workload findById(Long id) {
        log.debug("Finding a Workload entity by its ID");
        Workload foundEntity = workloadRepository.findByWorkloadId(id).orElse(null);
        log.info("Workload found by ID: {}", foundEntity);
        return foundEntity;

    }

    /**
     * calculates workload summary duration findByTrainerUsernameAndTrainingDateBetween
     **/
    public List<Workload> findByTrainerUsernameAndTrainingDateBetween(String trainerUsername, Date startDate, Date endDate) {
        log.info("Finding a Workload entity by its trainerUsername: {}, and dates between: {} and {}", trainerUsername, startDate, endDate);

        // Fetch the workloads for the given trainer and date range
        List<Workload> workloads = workloadRepository.findByTrainerUsername(trainerUsername, startDate, endDate);

        return workloads;
    }


}

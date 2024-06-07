package uz.najot.service.impl;

import com.epam.upskill.feignclients.workload.ResWorkloadDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.najot.model.MonthSummary;
import uz.najot.model.TrainingSummaryResponse;
import uz.najot.model.Workload;
import uz.najot.model.YearSummary;
import uz.najot.service.WorkloadService;
import uz.najot.service.db.WorkloadDatabase;

import java.util.*;

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
        List<Workload> workloads = workloadRepository.findByTrainerUsernameAndTrainingDateBetween(username, startDate, endDate);

        TrainingSummaryResponse summary = new TrainingSummaryResponse();
        summary.setTrainerUsername(username);
        summary.setTrainerFirstName(workloads.get(0).getTrainerFirstName());
        summary.setTrainerLastName(workloads.get(0).getTrainerLastName());
        summary.setTrainerStatus(workloads.get(0).getIsActive());

        List<YearSummary> yearSummaries = new ArrayList<>();

        Map<Integer, Map<Integer, Integer>> yearlyMonthlyDurations = new HashMap<>();

        for (Workload workload : workloads) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(workload.getTrainingDate());
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1; // Calendar.MONTH is zero-based

            yearlyMonthlyDurations
                    .computeIfAbsent(year, y -> new HashMap<>())
                    .merge(month, workload.getTrainingDuration(), Integer::sum);
        }

        for (Map.Entry<Integer, Map<Integer, Integer>> yearEntry : yearlyMonthlyDurations.entrySet()) {
            YearSummary yearSummary = new YearSummary();
            yearSummary.setYear(yearEntry.getKey());

            List<MonthSummary> monthSummaries = new ArrayList<>();
            for (Map.Entry<Integer, Integer> monthEntry : yearEntry.getValue().entrySet()) {
                MonthSummary monthSummary = new MonthSummary();
                monthSummary.setMonth(monthEntry.getKey());
                monthSummary.setTrainingSummaryDuration(monthEntry.getValue());
                monthSummaries.add(monthSummary);
            }
            yearSummary.setMonths(monthSummaries);
            yearSummaries.add(yearSummary);
        }

        summary.setYearSummaries(yearSummaries);


        return summary;
    }


}
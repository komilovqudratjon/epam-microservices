package uz.epam.service;

import com.epam.upskill.feignclients.workload.ResWorkloadDTO;
import uz.epam.model.TrainingSummaryResponse;
import uz.epam.model.Workload;

import java.util.Date;

/**
 * @description: Service interface for Workload entity.
 * @date: 08 November 2023 $
 * @time: 5:43 AM 30 $
 * @author: Qudratjon Komilov
 */


public interface WorkloadService {
    Workload createOrUpdate(ResWorkloadDTO trainer, Long id);

    void deleteWorkload(Long id);

    TrainingSummaryResponse calculateMonthlySummary(String username, Date startDate, Date endDate);
}


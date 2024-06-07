package com.epam.upskill.workload.repository;

import com.epam.upskill.workload.model.TrainingSummaryResponse;
import com.epam.upskill.workload.model.Workload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * @description: Repository interface for Workload entity.
 * @date: 08 November 2023 $
 * @time: 5:38 AM 25 $
 * @author: Qudratjon Komilov
 */

public interface WorkloadRepository extends JpaRepository<Workload, Long> {
//    @Query("SELECT new com.epam.upskill.workload.model.TrainingSummaryResponse(" +
//            "w.trainerUsername, w.trainerFirstName, w.trainerLastName, w.isActive, " +
//            "CAST(SUM(w.trainingDuration) AS integer)) " +
//            "FROM Workload w " +
//            "WHERE w.trainerUsername = :trainerUsername AND " +
//            "w.trainingDate BETWEEN :startDate AND :endDate " +
//            "GROUP BY w.trainerUsername, w.trainerFirstName, w.trainerLastName, w.isActive")
//    List<TrainingSummaryResponse> findByTrainerUsernameAndTrainingDateBetween(
//            @Param("trainerUsername") String trainerUsername,
//            @Param("startDate") Date startDate,
//            @Param("endDate") Date endDate);

    List<Workload> findByTrainerUsernameAndTrainingDateBetween(String trainerUsername, Date startDate, Date endDate);
}


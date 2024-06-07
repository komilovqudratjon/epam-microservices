package uz.najot.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import uz.najot.model.Workload;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @description: Repository interface for Workload entity.
 * @date: 08 November 2023 $
 * @time: 5:38 AM 25 $
 * @author: Qudratjon Komilov
 */
@Repository
public interface WorkloadRepository extends MongoRepository<Workload, String> {
    List<Workload> findByTrainerUsername(String trainerUsername, Date startDate, Date endDate);
    void deleteByWorkloadId(Long id);
    Optional<Workload> findByWorkloadId(Long id);
}

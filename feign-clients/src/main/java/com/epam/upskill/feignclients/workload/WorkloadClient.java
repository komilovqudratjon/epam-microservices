package com.epam.upskill.feignclients.workload;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * @description: TODO
 * @date: 28 January 2024 $
 * @time: 7:06 PM 05 $
 * @author: Qudratjon Komilov
 */

@FeignClient(name = "workload", url = "${clients.workload.url}")
public interface WorkloadClient {

    @PostMapping(path = "/workload/v1/workloads")
    void createOrUpdate(@RequestBody ResWorkloadDTO workload, @RequestParam(required = false,value = "id") Long id);

    @GetMapping(path = "/workload/v1/workloads/summary")
    TrainingSummaryResponse getTrainingSummary(@RequestParam(value = "trainerUsername") String trainerUsername,
                                               @RequestParam(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date startDate,
                                               @RequestParam(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date endDate);



}

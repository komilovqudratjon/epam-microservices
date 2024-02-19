package com.epam.upskill.workload.rabbitmq;

import com.epam.upskill.feignclients.workload.ResWorkloadDTO;
import com.epam.upskill.workload.service.WorkloadService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class WorkloadConsumer {

    private final WorkloadService workloadService;

    @RabbitListener(queues = "${rabbitmq.queues.workload}")
    public void consumer(ResWorkloadDTO workload) {
        log.info("Consumed {} from queue", workload);
        switch (workload.actionType()) {
            case "ADD":
                workloadService.createOrUpdate(workload, workload.id());
                break;
            case "DELETE":
                workloadService.deleteWorkload(workload.id());
                break;
            default:
                throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}

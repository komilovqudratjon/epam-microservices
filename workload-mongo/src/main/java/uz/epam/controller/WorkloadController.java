package uz.epam.controller;


import com.epam.upskill.feignclients.workload.ResWorkloadDTO;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uz.epam.model.TrainingSummaryResponse;
import uz.epam.service.WorkloadService;

import javax.validation.Valid;
import java.util.Date;

/**
 * WorkloadController is responsible for handling HTTP requests related to Workload operations.
 * It uses WorkloadService to perform the business logic associated with these operations.
 *
 * @date: 10 November 2023
 * @time: 1:10 AM 30
 * @author: Qudratjon Komilov
 */

@RestController
@RequestMapping("/v1/workloads")
@AllArgsConstructor
@Api(tags = "Workload Management", value = "Endpoints for managing Workloads")
@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Access Token", paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")})
public class WorkloadController {

    private final WorkloadService workloadService;

    /**
     * Creates a new Workload or updates an existing one.
     *
     * @param workload The Workload data transfer object.
     * @return The created or updated WorkloadDTO.
     */
    @ApiOperation(value = "Create or Update a Workload", notes = "Creates a new Workload or updates an existing one.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully created or updated the Workload")})
    @ResponseStatus(HttpStatus.OK)
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Access Token", paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")})
    @PostMapping()
    public void createOrUpdate(@ApiParam(value = "The Workload data transfer object", required = true) @Valid @RequestBody ResWorkloadDTO workload, @RequestParam(required = false) Long id) {
        switch (workload.actionType()) {
            case "ADD":
                workloadService.createOrUpdate(workload, id);
                break;
            case "DELETE":
                workloadService.deleteWorkload(id);
                break;
            default:
                throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    /**
     * Calculates the monthly summary of the Workload.
     *
     * @param trainerUsername The username of the trainer.
     * @param startDate       The start date of the summary.
     * @param endDate         The end date of the summary.
     * @return The TrainingSummaryResponse data transfer object.
     */
    @ApiOperation(value = "Calculate the monthly summary of the Workload", notes = "Calculates the monthly summary of the Workload.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully calculated the monthly summary of the Workload")})
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/summary")
    public TrainingSummaryResponse getTrainingSummary(@ApiParam(value = "The username of the trainer.", defaultValue = "JohnDoe") @RequestParam String trainerUsername,
                                                      @ApiParam(value = "The start date of the summary in 'yyyy-MM-dd HH:mm:ss.SSS' format.", defaultValue = "2023-01-01 00:00:00.000") @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS") Date startDate,
                                                      @ApiParam(value = "The end date of the summary in 'yyyy-MM-dd HH:mm:ss.SSS' format.", defaultValue = "2023-01-31 23:59:59.999") @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS") Date endDate) {
        return workloadService.calculateMonthlySummary(trainerUsername, startDate, endDate);
    }


}
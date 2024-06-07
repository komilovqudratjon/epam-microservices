package uz.najot.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @description: Entity class for Workload.
 * @date: 08 November 2023 $
 * @time: 5:20 AM 58 $
 * @author: Qudratjon Komilov
 */

@Document(collection = "workload")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Workload  implements Serializable {

    @Id
    private String id;

    private Long workloadId;
    private String trainerUsername;

    private String trainerFirstName;

    private String trainerLastName;

    private Boolean isActive;

    private Date trainingDate;

    private Integer trainingDuration;
}

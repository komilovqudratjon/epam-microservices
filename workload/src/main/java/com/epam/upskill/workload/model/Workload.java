package com.epam.upskill.workload.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * @description: Entity class for Workload.
 * @date: 08 November 2023 $
 * @time: 5:20 AM 58 $
 * @author: Qudratjon Komilov
 */

@Entity
@Table(name = "workload")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Workload extends AbsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String trainerUsername;

    private String trainerFirstName;

    private String trainerLastName;

    private Boolean isActive;

    private Date trainingDate;

    private Integer trainingDuration;
}

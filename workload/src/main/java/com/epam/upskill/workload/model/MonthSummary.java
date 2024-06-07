package com.epam.upskill.workload.model;

import lombok.*;

/**
 * @description: TODO
 * @date: 07 June 2024 $
 * @time: 11:36 PM 05 $
 * @author: Qudratjon Komilov
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MonthSummary {
    private Integer month;
    private Integer trainingSummaryDuration;
}

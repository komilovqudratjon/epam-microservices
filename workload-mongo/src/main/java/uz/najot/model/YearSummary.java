package uz.najot.model;

import lombok.*;

import java.util.List;

/**
 * @description: TODO
 * @date: 07 June 2024 $
 * @time: 11:35 PM 40 $
 * @author: Qudratjon Komilov
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class YearSummary {
    private Integer year;
    private List<MonthSummary> months;
}

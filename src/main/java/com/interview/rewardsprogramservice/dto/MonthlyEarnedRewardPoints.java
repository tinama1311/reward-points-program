package com.interview.rewardsprogramservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyEarnedRewardPoints {
    /**
     * a string in format of "YYYY-MM".
     */
    private String time;

    /**
     * records the monthly earned reward points of a specific customer.
     */
    private Integer rewardPoints;

}

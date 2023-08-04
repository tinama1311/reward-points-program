package com.interview.rewardsprogramservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RewardPointsRecords {

    /**
     * unique customer id for each customer.
     */
    private Long customerId;

    /**
     * a String of customer name.
     */
    private String customerName;

    /**
     * the customer's total reward points earned of a chosen period of time.
     */
    private Integer totalRewardPoints;

    /**
     * a list of the customer's monthly earned reward points of a chosen period of time.
     */
    private List<MonthlyEarnedRewardPoints> monthlyEarnedRewardPointsList;

}

package com.interview.rewardsprogramservice.service;

import com.interview.rewardsprogramservice.dto.RewardPointsRecords;

import java.time.LocalDateTime;
import java.util.List;

public interface RewardPointsService {

    /**
     * calculate a single customer's reward points (total & monthly) for recent 3-month period of time.
     * @param customerId an unique id identifying a customer.
     * @return a RewardPointsRecords object recording customer's id, name, total & monthly earned reward points.
     */
    RewardPointsRecords getRecentRewardPointsRecordsByCustomerId(String customerId);

    /**
     * calculate a single customer's reward points (total & monthly) for a chosen period of time.
     * @param customerId an unique id identifying a customer.
     * @param startTime the start time of the chosen period.
     * @param endTime the end time of the chosen period.
     * @return a RewardPointsRecords object recording customer's id, name, total & monthly earned reward points.
     */
    RewardPointsRecords getRewardPointsRecordsByCustomerIdAndTime(String customerId, LocalDateTime startTime, LocalDateTime endTime);

    /**
     * calculate all customers' reward points (total & monthly) for recent 3-month period of time.
     * @return a list of RewardPointsRecords objects.
     */
    List<RewardPointsRecords> getAllCustomersRecentRewardPointsRecords();

    /**
     * calculate all customers' reward points (total & monthly) for a chosen period of time.
     * @param startTime the start time of the chosen period.
     * @param endTime the end time of the chosen period.
     * @return a list of RewardPointsRecords objects.
     */
    List<RewardPointsRecords> getAllCustomersRewardPointsRecordsByTime(LocalDateTime startTime, LocalDateTime endTime);
}

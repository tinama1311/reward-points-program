package com.interview.rewardsprogramservice.controller;

import com.interview.rewardsprogramservice.dto.RewardPointsRecords;
import com.interview.rewardsprogramservice.entity.Customer;
import com.interview.rewardsprogramservice.entity.Transaction;
import com.interview.rewardsprogramservice.service.RewardPointsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class RewardPointsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RewardPointsService rewardPointsService;

    /**
     * unit testing endpoint: /rewards/{customer_id}?startTime=XXX&endTime=YYY
     * @throws Exception
     */
    @Test
    public void testGetSingleCustomerRewardPointsWithTimeRange() throws Exception {
        when(rewardPointsService.getRewardPointsRecordsByCustomerIdAndTime(any(),any(),any()))
                .thenReturn(new RewardPointsRecords());
        mockMvc.perform(get("/rewards/1?startTime=2022-08-02T00:00:00.00&endTime=2023-08-03T00:00:00.00"))
                .andExpect(status().isOk());
    }

    /**
     * unit testing endpoint: /rewards/{customer_id}
     * @throws Exception
     */
    @Test
    public void testGetSingleCustomerRewardPointsWithoutTimeRange() throws Exception {
        when(rewardPointsService.getRecentRewardPointsRecordsByCustomerId(any()))
                .thenReturn(new RewardPointsRecords());
        mockMvc.perform(get("/rewards/1"))
                .andExpect(status().isOk());
    }

    /**
     * unit testing endpoint: /rewards?startTime=XXX&endTime=YYY
     * @throws Exception
     */
    @Test
    public void testGetAllCustomersRewardPointsWithTimeRange() throws Exception{
        when(rewardPointsService.getAllCustomersRewardPointsRecordsByTime(any(),any()))
                .thenReturn(new ArrayList<>());
        mockMvc.perform(get("/rewards?startTime=2022-08-02T00:00:00.00&endTime=2023-08-03T00:00:00.00"))
                .andExpect(status().isOk());
    }

    /**
     * unit testing endpoint: /rewards
     * @throws Exception
     */
    @Test
    public void testGetAllCustomersRewardPointsWithoutTimeRange() throws Exception{
        when(rewardPointsService.getAllCustomersRecentRewardPointsRecords())
                .thenReturn(new ArrayList<>());
        mockMvc.perform(get("/rewards"))
                .andExpect(status().isOk());
    }

}
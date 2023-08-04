package com.interview.rewardsprogramservice.service;

import com.interview.rewardsprogramservice.dao.CustomerRepository;
import com.interview.rewardsprogramservice.dao.TransactionRepository;
import com.interview.rewardsprogramservice.dto.RewardPointsRecords;
import com.interview.rewardsprogramservice.entity.Customer;
import com.interview.rewardsprogramservice.entity.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class RewardPointsServiceTest {
    @Mock
    CustomerRepository customerRepository;

    @Mock
    TransactionRepository transactionRepository;

    @InjectMocks
    RewardPointsServiceImpl rewardPointsService;

    /**
     * set up some mock data for unit testing
     */
    @BeforeEach
    public void setUpMockData() {
        List<Transaction> customer1Transactions = new ArrayList<>();
        customer1Transactions.add(new Transaction(1, 50, LocalDateTime.now(), new Customer()));
        customer1Transactions.add(new Transaction(2, 100, LocalDateTime.now(), new Customer()));
        customer1Transactions.add(new Transaction(3, 120, LocalDateTime.now(), new Customer()));

        List<Transaction> customer2Transactions = new ArrayList<>();
        customer2Transactions.add(new Transaction(4, 0, LocalDateTime.now(), new Customer()));
        customer2Transactions.add(new Transaction(5, 200, LocalDateTime.now(), new Customer()));
        customer2Transactions.add(new Transaction(6, 400, LocalDateTime.now(), new Customer()));

        List<Transaction> customer3Transactions = new ArrayList<>();

        List<Customer> customersList = new ArrayList<>();
        customersList.add(new Customer(1, "customer1"));
        customersList.add(new Customer(2, "customer2"));
        customersList.add(new Customer(3, "customer3"));

        when(transactionRepository.findAllByTransactionTimeBetweenAndCustomerId(any(), any(), eq(1L)))
                .thenReturn(customer1Transactions);
        when(transactionRepository.findAllByTransactionTimeBetweenAndCustomerId(any(), any(), eq(2L)))
                .thenReturn(customer2Transactions);
        when(transactionRepository.findAllByTransactionTimeBetweenAndCustomerId(any(), any(), eq(3L)))
                .thenReturn(customer3Transactions);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(new Customer(1, "customer1")));
        when(customerRepository.findById(2L)).thenReturn(Optional.of(new Customer(2, "customer2")));
        when(customerRepository.findById(3L)).thenReturn(Optional.of(new Customer(3, "customer3")));

        when(customerRepository.findAll()).thenReturn(customersList);
    }

    /**
     * unit testing feature: get single customer's reward points with time param
     */
    @Test
    public void testCalculateSingleCustomerRewardPoints(){
        RewardPointsRecords rewardPointsRecords;

        rewardPointsRecords = rewardPointsService.getRewardPointsRecordsByCustomerIdAndTime("1", LocalDateTime.now(), LocalDateTime.now());
        assertEquals(140, rewardPointsRecords.getTotalRewardPoints());

        rewardPointsRecords = rewardPointsService.getRewardPointsRecordsByCustomerIdAndTime("2", LocalDateTime.now(), LocalDateTime.now());
        assertEquals(900, rewardPointsRecords.getTotalRewardPoints());

        rewardPointsRecords = rewardPointsService.getRewardPointsRecordsByCustomerIdAndTime("3", LocalDateTime.now(), LocalDateTime.now());
        assertEquals(0, rewardPointsRecords.getTotalRewardPoints());
    }

    /**
     * unit testing feature: get single customer's reward points without time param
     */
    @Test
    public void testCalculateSingleCustomerRecentRewardPoints(){
        RewardPointsRecords rewardPointsRecords;
        rewardPointsRecords = rewardPointsService.getRecentRewardPointsRecordsByCustomerId("1");
        assertEquals(140, rewardPointsRecords.getTotalRewardPoints());

        rewardPointsRecords = rewardPointsService.getRecentRewardPointsRecordsByCustomerId("2");
        assertEquals(900, rewardPointsRecords.getTotalRewardPoints());

        rewardPointsRecords = rewardPointsService.getRecentRewardPointsRecordsByCustomerId("3");
        assertEquals(0, rewardPointsRecords.getTotalRewardPoints());


    }

    /**
     * unit testing feature: get all customers' reward points with time param
     */
    @Test
    public void testCalculateAllCustomersRewardPoints(){
        List<RewardPointsRecords> rewardPointsRecordsList = rewardPointsService.getAllCustomersRewardPointsRecordsByTime(LocalDateTime.now(), LocalDateTime.now());
        List<RewardPointsRecords> expectedList = new ArrayList<>();
        expectedList.add(new RewardPointsRecords(1L, "customer1", 140, new ArrayList<>()));
        expectedList.add(new RewardPointsRecords(2L, "customer2", 900, new ArrayList<>()));
        expectedList.add(new RewardPointsRecords(3L, "customer3", 0, new ArrayList<>()));

        for (int i = 0; i < expectedList.size(); i++) {
            assertEquals(expectedList.get(i).getTotalRewardPoints(), rewardPointsRecordsList.get(i).getTotalRewardPoints());
        }
    }

    /**
     * unit testing feature: get all customers' reward points without time param
     */
    @Test
    public void testCalculateALLCustomersRecentRewardPoints(){
        List<RewardPointsRecords> rewardPointsRecordsList = rewardPointsService.getAllCustomersRecentRewardPointsRecords();
        List<RewardPointsRecords> expectedList = new ArrayList<>();
        expectedList.add(new RewardPointsRecords(1L, "customer1", 140, new ArrayList<>()));
        expectedList.add(new RewardPointsRecords(2L, "customer2", 900, new ArrayList<>()));
        expectedList.add(new RewardPointsRecords(3L, "customer3", 0, new ArrayList<>()));

        for (int i = 0; i < expectedList.size(); i++) {
            assertEquals(expectedList.get(i).getTotalRewardPoints(), rewardPointsRecordsList.get(i).getTotalRewardPoints());
        }
    }
}

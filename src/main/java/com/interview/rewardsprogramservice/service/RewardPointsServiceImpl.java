package com.interview.rewardsprogramservice.service;

import com.interview.rewardsprogramservice.dao.CustomerRepository;
import com.interview.rewardsprogramservice.dao.TransactionRepository;
import com.interview.rewardsprogramservice.dto.MonthlyEarnedRewardPoints;
import com.interview.rewardsprogramservice.dto.RewardPointsRecords;
import com.interview.rewardsprogramservice.entity.Customer;
import com.interview.rewardsprogramservice.entity.Transaction;
import com.interview.rewardsprogramservice.exception.CustomerIdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class RewardPointsServiceImpl implements RewardPointsService {

    private CustomerRepository customerRepository;
    private TransactionRepository transactionRepository;

    RewardPointsServiceImpl(CustomerRepository customerRepository, TransactionRepository transactionRepository) {
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
    }

    /**
     * calculate a single customer's reward points (total & monthly) for recent 3-month period of time.
     * @param customerId an unique id identifying a customer.
     * @return a RewardPointsRecords object recording customer's id, name, total & monthly earned reward points.
     */
    @Override
    public RewardPointsRecords getRecentRewardPointsRecordsByCustomerId(String customerId) {
        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = endTime.minusMonths(3);
        return getRewardPointsRecordsByCustomerIdAndTime(customerId, startTime, endTime);
    }

    /**
     * calculate a single customer's reward points (total & monthly) for a chosen period of time.
     * @param customerId an unique id identifying a customer.
     * @param startTime the start time of the chosen period.
     * @param endTime the end time of the chosen period.
     * @return a RewardPointsRecords object recording customer's id, name, total & monthly earned reward points.
     */
    @Override
    public RewardPointsRecords getRewardPointsRecordsByCustomerIdAndTime(String customerId, LocalDateTime startTime, LocalDateTime endTime) {
        Long customerIdLong = Long.valueOf(customerId);
        List<Transaction> transactions = transactionRepository.findAllByTransactionTimeBetweenAndCustomerId(startTime, endTime, customerIdLong);

        Map<String, Integer> monthlyRewardPointsMap = new HashMap<>();
        Integer totalRewardPoints = 0;

        for (Transaction transaction : transactions) {
            // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
            String transactionFormattedTime = transaction.getTransactionTime().format(DateTimeFormatter.ofPattern("yyy-MM"));
            Integer rewardPoints = this.calculateRewardPointsByTransaction(transaction);
            totalRewardPoints += rewardPoints;

            monthlyRewardPointsMap.compute(transactionFormattedTime, (k, v) -> {
                if (v == null) {
                    return rewardPoints;
                } else {
                    return rewardPoints + v;
                }
            });
        }

        List<MonthlyEarnedRewardPoints> monthlyEarnedRewardPointsList = monthlyRewardPointsMap.entrySet().stream()
                .map(kVPair -> new MonthlyEarnedRewardPoints(kVPair.getKey(), kVPair.getValue())).collect(Collectors.toList());

        Optional<Customer> customerOptional = customerRepository.findById(customerIdLong);
        String customerName = customerOptional.orElseThrow(CustomerIdNotFoundException::new).getName();
        return new RewardPointsRecords(customerIdLong, customerName, totalRewardPoints, monthlyEarnedRewardPointsList);
    }

    /**
     * calculate all customers' reward points (total & monthly) for recent 3-month period of time.
     * @return a list of RewardPointsRecords objects.
     */
    @Override
    public List<RewardPointsRecords> getAllCustomersRecentRewardPointsRecords() {
        List<Customer> customers = customerRepository.findAll();
        List<RewardPointsRecords> rewardPointsRecordsList = new ArrayList<>();
        for (Customer customer : customers) {
            rewardPointsRecordsList.add(this.getRecentRewardPointsRecordsByCustomerId(String.valueOf(customer.getId())));
        }
        return rewardPointsRecordsList;
    }

    /**
     * calculate all customers' reward points (total & monthly) for a chosen period of time.
     * @param startTime the start time of the chosen period.
     * @param endTime the end time of the chosen period.
     * @return a list of RewardPointsRecords objects.
     */
    @Override
    public List<RewardPointsRecords> getAllCustomersRewardPointsRecordsByTime(LocalDateTime startTime, LocalDateTime endTime) {
        List<Customer> customers = customerRepository.findAll();
        List<RewardPointsRecords> rewardPointsRecordsList = new ArrayList<>();
        for (Customer customer : customers) {
            rewardPointsRecordsList.add(this.getRewardPointsRecordsByCustomerIdAndTime(String.valueOf(customer.getId()), startTime, endTime));
        }
        return rewardPointsRecordsList;
    }

    /**
     * a helper function calculating earned reward points for a single transaction based on the following rule:
     * a customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every
     * dollar spent over $50 in each transaction
     * (e.g. a $120 purchase = 2 * $20 + 1 * $50 = 90 points)
     * @param transaction a transaction record, including its transaction id, value, time, and customer id
     * @return the calculated reward points earned for this transaction.
     */
    private Integer calculateRewardPointsByTransaction(Transaction transaction) {
        Integer transactionValue = transaction.getTransactionValue();
        if (50 < transactionValue && transactionValue <= 100) {
            return transactionValue - 50;
        }
        if (transactionValue > 100) {
            return (transactionValue - 100) * 2 + 50;
        }
        return 0;
    }
}

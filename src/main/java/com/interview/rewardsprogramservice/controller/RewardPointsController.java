package com.interview.rewardsprogramservice.controller;

import com.interview.rewardsprogramservice.dto.ExceptionResponse;
import com.interview.rewardsprogramservice.dto.RewardPointsRecords;
import com.interview.rewardsprogramservice.exception.CustomerIdNotFoundException;
import com.interview.rewardsprogramservice.service.RewardPointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/rewards")
public class RewardPointsController {

    private RewardPointsService rewardPointsService;

    RewardPointsController(RewardPointsService rewardPointsService) {
        this.rewardPointsService = rewardPointsService;
    }

    /**
     * Get for a single customer the monthly & total reward points earned during an indicated time period
     * based on the provided transaction history data. By default, it will calculate reward points of the
     * recent 3 months' transaction.
     *
     * @param customer_id a String uniquely identifying each customer.
     * @param startTime a LocalDateTime Object specifying the start time of the chosen time period.
     * @param endTime a LocalDateTime Object specifying the end time of the chosen time period.
     * @return a RewardsPointsRecords Object recording monthly & total reward points earned.
     */
    @GetMapping("/{customer_id}")
    public ResponseEntity<RewardPointsRecords> getRewardPointsRecordsByCustomerId(@PathVariable String customer_id,
                                                                                  @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
                                                                                  @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {

        if (startTime == null && endTime == null) {
            return ResponseEntity.ok(rewardPointsService.getRecentRewardPointsRecordsByCustomerId(customer_id));
        }

        if (startTime != null && endTime != null) {
            return ResponseEntity.ok(rewardPointsService.getRewardPointsRecordsByCustomerIdAndTime(customer_id, startTime, endTime));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    /**
     * Get for all customers the monthly & total reward points earned during an indicated time period
     * based on the provided transaction history data. By default, it will calculate reward points of the
     * recent 3 months' transaction.
     *
     * @param startTime a LocalDateTime Object specifying the start time of the chosen time period.
     * @param endTime a LocalDateTime Object specifying the end time of the chosen time period.
     * @return a List of RewardsPointsRecords Objects recording monthly & total reward points earned.
     */
    @GetMapping
    public ResponseEntity<List<RewardPointsRecords>> getAllCustomersRewardPointsRecords(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
                                                                                        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {

        if (startTime == null && endTime == null) {
            return ResponseEntity.ok(rewardPointsService.getAllCustomersRecentRewardPointsRecords());
        }

        if (startTime != null && endTime != null) {
            return ResponseEntity.ok(rewardPointsService.getAllCustomersRewardPointsRecordsByTime(startTime, endTime));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    /**
     * handles the customer id not exist exceptions
     * @param e the exception to be handled
     * @return an ExceptionResponse Object recoding the exception message and status code
     */
    @ExceptionHandler(CustomerIdNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleUserIdNotFoundException(Exception e) {
        ExceptionResponse resp = new ExceptionResponse();
        resp.setExceptionMessage("Customer ID doesn't exist.");
        resp.setStatusCode(404);
        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }

}

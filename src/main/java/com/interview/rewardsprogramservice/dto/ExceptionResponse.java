package com.interview.rewardsprogramservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    /**
     * a string of exception message.
     */
    String exceptionMessage;

    /**
     * http status code.
     */
    int statusCode;
}

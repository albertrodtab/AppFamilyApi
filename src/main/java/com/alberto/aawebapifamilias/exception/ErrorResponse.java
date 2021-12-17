package com.alberto.aawebapifamilias.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String internalError;
    private String message;

}

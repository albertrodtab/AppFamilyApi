package com.alberto.aawebapifamilias.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {
    private String internalCode;
    private String message;

}

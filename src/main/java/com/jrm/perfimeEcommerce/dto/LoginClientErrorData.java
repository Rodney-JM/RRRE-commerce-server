package com.jrm.perfimeEcommerce.dto;

public record LoginClientErrorData(
        boolean status,
        String errorMessage
) {
}

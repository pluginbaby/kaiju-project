package com.kaiju.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class StatResponseDto {

    public Double percentage;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String message;

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(final Double percentage) {
        this.percentage = percentage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}

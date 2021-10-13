package com.kaiju.dto;


import com.kaiju.utils.validator.SampleConstraint;

public class DnaDTO {

    @SampleConstraint
    private String sample;

    public String getSample() {
        return sample;
    }

    public void setSample(final String sample) {
        this.sample = sample;
    }
}

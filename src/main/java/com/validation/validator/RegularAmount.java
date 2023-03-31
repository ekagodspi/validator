package com.validation.validator;

import javax.validation.constraints.NotNull;

public class RegularAmount {
    @NotNull
    private Frequency frequency;

    @NotNull
    private String amount;

    public Frequency getFrequency() {
    return frequency;
    }

    public void setFrequency(Frequency frequency) {
    this.frequency = frequency;
    }

    public String getAmount() {
    return amount;
    }

    public void setAmount(String amount) {
    this.amount = amount;
    }
    }

package com.kaiju.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DnaType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private Integer max;

    private Integer min;

    private Integer total = 0;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(final Integer max) {
        this.max = max;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(final Integer min) {
        this.min = min;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(final Integer total) {
        this.total = total;
    }

    public boolean isBetweenRange(int sum) {
        return sum >= min && sum <= max;
    }
}

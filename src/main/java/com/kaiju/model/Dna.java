package com.kaiju.model;

import javax.persistence.*;

@Entity
public class Dna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sample;

    @ManyToOne
    @JoinColumn(name="dna_type_id")
    private DnaType dnaType;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getSample() {
        return sample;
    }

    public void setSample(final String sample) {
        this.sample = sample;
    }

    public DnaType getDnaType() {
        return dnaType;
    }

    public void setDnaType(final DnaType dnaType) {
        this.dnaType = dnaType;
    }
}

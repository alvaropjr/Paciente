package com.healthheart.jessica.hh.Activity;

/**
 * Created by Jessica on 11/10/2017.
 */

public enum FaixasImc {
    ABAIXO (18.5),
    ACIMA (20.4);
    // in kilograms
    private final double faixa;

    FaixasImc(double faixa) {
        this.faixa = faixa;
    }
    public double getFaixa() {
        return faixa;
    }

}

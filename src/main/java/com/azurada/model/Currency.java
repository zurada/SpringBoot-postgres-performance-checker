package com.azurada.model;


public enum Currency {
    BTC, USD;

    private static Currency[] allValues = values();

    public static Currency fromOrdinal(int n) {
        if (n < 0 || n >= allValues.length) {
            throw new RuntimeException();
        } else {
            return allValues[n];
        }
    }
}

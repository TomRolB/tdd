package com.example;

public record Price(String currency, double value) {
    private static final Price ZERO = new Price("", 0);
    public static Price zero() {
        return ZERO;
    }

    public Price add(Price otherPrice) {
        return new Price(this.currency,this.value + otherPrice.value);
    }

    public Price multiply(int amount) {
        return new Price(this.currency, this.value * amount);
    }
}

package com.cbsystematics.edu.homework05.testers;

public enum RealizationVariants {
    JDBC("JDBC"),
    TINY_HIBERNATE("TINY_HIBERNATE"),
    JPA_SQL("JPA_SQL"),
    JPA_JPQL("JPA_JPQL"),
    JPA_CRITERIA("JPA_CRITERIA"),
    JPA_METHODS("JPA_METHODS"),
    HIBERNATE_SESSION("HIBERNATE_SESSION");


    private String variant;

    RealizationVariants(String variant) {
        this.variant = variant;
    }

    public String getVariant() {
        return variant;
    }
}

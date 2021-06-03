package com.cbsystematics.edu.homework05.testers;


public class MainTester {
    public static void main(String[] args) {
        TesterChooser testerChooser = new TesterChooser();
        testerChooser.setRealizationVariant(RealizationVariants.JPA_METHODS);
        testerChooser.run();
    }
}

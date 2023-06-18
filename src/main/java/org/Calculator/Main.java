package org.Calculator;

public class Main {
    public static void main(String[] args) {
        CalculableFactoryInterface calculableFactory = new CalculableFactoryClass();
        ViewCalculator view = new ViewCalculator(calculableFactory);
        view.run();
        }
    }

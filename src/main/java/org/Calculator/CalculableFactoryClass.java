package org.Calculator;

public class CalculableFactoryClass implements CalculableFactoryInterface{

    @Override
    public Calculable createArg(ComplexNumber number) {
        return (Calculable) new ComputingModuleClass(number);
    }

    @Override
    public Calculable CreateArgForConvertation(ComplexNumber number) {
        return null;
    }
}

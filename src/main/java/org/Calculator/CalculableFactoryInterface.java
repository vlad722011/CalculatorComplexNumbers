package org.Calculator;

public interface CalculableFactoryInterface {
    Calculable createArg(ComplexNumber number);
    Calculable CreateArgForConvertation(ComplexNumber number);
}

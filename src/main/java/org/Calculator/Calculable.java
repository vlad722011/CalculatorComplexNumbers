package org.Calculator;

public interface Calculable {
    Calculable sum(ComplexNumber first, ComplexNumber second);
    Calculable multi(ComplexNumber first, ComplexNumber second);
    Calculable division(ComplexNumber first, ComplexNumber second);
    Calculable subtraction(ComplexNumber first, ComplexNumber second);
    ComplexNumber getResult();
    ComplexNumber ConverterResultToTrigonometric(ComplexNumber number);

}

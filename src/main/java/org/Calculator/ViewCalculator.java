package org.Calculator;

import java.util.Scanner;

public class ViewCalculator {
    private CalculableFactoryInterface calculableFactory;

    public ViewCalculator(CalculableFactoryInterface calculableFactory) {
        this.calculableFactory = calculableFactory;
    }

    public void run() {
        ComplexNumber resultNumber = new ComplexNumber(0, 0);
        while (true) {

            ComplexNumber first = GetFirstComplexNumber();
            Calculable calculator = calculableFactory.createArg(first);

            while (true) {
                String cmd = prompt("Выберите какое действие между числами необходимо произвести -> (+, -, *, /, =): ");
                if (cmd.equals("+")) {
                    ComplexNumber second = GetSecondComplexNumber();
                    calculator.sum(first, second);
                    continue;
                }
                if (cmd.equals("-")) {
                    ComplexNumber second = GetSecondComplexNumber();
                    calculator.subtraction(first, second);
                    continue;
                }
                if (cmd.equals("*")) {
                    ComplexNumber second = GetSecondComplexNumber();
                    calculator.multi(first, second);
                    continue;
                }
                if (cmd.equals("/")) {
                    ComplexNumber second = GetSecondComplexNumber();
                    calculator.division(first, second);
                    continue;
                }
                if (cmd.equals("=")) {
                    resultNumber = calculator.getResult();
                    System.out.printf("Результат в алгебраической форме: %s\n", resultNumber);
                    calculator.ConverterResultToTrigonometric(resultNumber);
                    break;
                }
            }
            String cmd = prompt("Еще хотите выполнить какие либо действия? (Y/N)?");
            if (cmd.equalsIgnoreCase("Y")) {
                continue;
            }
            break;
        }
    }

    private ComplexNumber GetFirstComplexNumber() {
        int realPartsFirstNumber = promptInt("Введите действительную часть первого комплексного числа: ");
        int imaginaryPartFirstNumber = promptInt("Введите мнимую часть первого комплексного числа: ");
        ComplexNumber first = new ComplexNumber(realPartsFirstNumber, imaginaryPartFirstNumber);
        first.setRealPart(realPartsFirstNumber);
        first.setImaginaryPart(imaginaryPartFirstNumber);
        return first;
    }

    private ComplexNumber GetSecondComplexNumber() {
        int realPartsSecondNumber = promptInt("Введите действительную часть второго комплексного числа: ");
        int imaginaryPartSecondNumber = promptInt("Введите мнимую часть второго комплексного числа: ");
        ComplexNumber second = new ComplexNumber(realPartsSecondNumber, imaginaryPartSecondNumber);
        second.setRealPart(realPartsSecondNumber);
        second.setImaginaryPart(imaginaryPartSecondNumber);
        return second;
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    private int promptInt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return Integer.parseInt(in.nextLine());
    }
}

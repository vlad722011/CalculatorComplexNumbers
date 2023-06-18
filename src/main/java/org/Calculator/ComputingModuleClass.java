package org.Calculator;

public class ComputingModuleClass implements CalculableFactoryInterface, Calculable {
    ComplexNumber resultNumber = new ComplexNumber(0, 0);
    String result;
    ComplexNumber number = new ComplexNumber(0.0,0.0);

    public ComputingModuleClass(ComplexNumber number) {
    }

    public Calculable sum(ComplexNumber first, ComplexNumber second) {
        // первое комплексное число -> (a + bi)
        // второе комплексное число -> (c + di)
        // формула сложения чисел
        // (a + bi) + (c + di) = (a + c) + (b + d)i
        DataToCalculateSum ResultDataForSum = getDataToCalculateSum(first, second);
        double realParts = ResultDataForSum.a() + ResultDataForSum.c();
        double imaginaryParts = ResultDataForSum.b() + ResultDataForSum.d();
        resultNumber.setRealPart(realParts);
        resultNumber.setImaginaryPart(imaginaryParts);
        System.out.printf("Промежуточный результат: -> %s\n", resultNumber);
        return (Calculable) this;
    }

    @Override
    public Calculable subtraction(ComplexNumber first, ComplexNumber second) {
        // первое комплексное число -> (a + bi)
        // второе комплексное число -> (c + di)
        // формула вычитания чисел
        // (a + bi) - (c + di) = (a - c) + (b - d)i
        DataToCalculate ResultData = getDataToCalculateSub(first, second);
        double realParts = ResultData.a() - ResultData.c();
        double imaginaryParts = ResultData.b() - ResultData.d();
        resultNumber.setRealPart(realParts);
        resultNumber.setImaginaryPart(imaginaryParts);
        System.out.printf("Промежуточный результат: -> %s\n", resultNumber);
        return (Calculable) this;
    }

    @Override
    public Calculable multi(ComplexNumber first, ComplexNumber second) {
        // первое комплексное число -> (a + bi)
        // второе комплексное число -> (c + di)
        // формула умножения чисел
        // (a + bi) · (c + di) = ac + bci + adi + bdi2 = (ac - bd) + (bc + ad)i
        DataForMulti resultForMulti = getDataForMulti(first, second);
        double realParts = resultForMulti.ac() - resultForMulti.bd();
        double imaginaryParts = resultForMulti.bc() + resultForMulti.ad();
        resultNumber.setRealPart(realParts);
        resultNumber.setImaginaryPart(imaginaryParts);
        System.out.printf("Промежуточный результат: -> %s\n", resultNumber);
        return (Calculable) this;
    }


    @Override
    public Calculable division(ComplexNumber first, ComplexNumber second) {
        // первое комплексное число -> (a + bi)
        // второе комплексное число -> (c + di)
        // формула деления чисел
        // (a + bi) / (c + di) = (ac + bd) / (c * c + d * d) + (bc - ad)i / (c * c + d * d)
        ResultDataForDivision resultForDivision = getResultDataForDivision(first, second);
        resultNumber.setRealPart(resultForDivision.realParts());
        resultNumber.setImaginaryPart(resultForDivision.imaginaryParts());
        System.out.printf("Промежуточный результат: -> %s\n", resultNumber);
        return (Calculable) this;
    }

    public String ConverterResultToTrigonometric(ComplexNumber number) {
        this.number = number;
        StringBuilder builder = new StringBuilder();
        double r;
        double argZ;
        double realParts = number.getRealPart();
        double imaginaryParts = number.getImaginaryPart();
        r = Math.sqrt(((double) (realParts * realParts) + (imaginaryParts * imaginaryParts)));

        if (realParts > 0 && imaginaryParts > 0) {
            argZ = Math.atan((double) (imaginaryParts / realParts));
            СollectResultString(builder, r, argZ);
            return this.toString();
        }
        if (realParts > 0 && imaginaryParts < 0) {
            argZ = -Math.atan((double) (imaginaryParts / realParts));
            СollectResultString(builder, r, argZ);
            return this.toString();
        }
        if (realParts < 0 && imaginaryParts > 0) {
            argZ = Math.PI - Math.atan((double) (imaginaryParts / realParts));
            СollectResultString(builder, r, argZ);
            return this.toString();
        }
        if (realParts < 0 && imaginaryParts < 0) {
            argZ = -Math.PI + Math.atan((double) (imaginaryParts / realParts));
            СollectResultString(builder, r, argZ);
            return this.toString();
        }
        if (realParts == 0 && imaginaryParts > 0) {
            argZ = Math.PI / 2;
            СollectResultString(builder, r, argZ);
            return this.toString();
        }
        if (realParts == 0 && imaginaryParts < 0) {
            argZ = -Math.PI / 2;
            СollectResultString(builder, r, argZ);
            return this.toString();
        }
        if (realParts > 0 && imaginaryParts == 0) {
            argZ = 0;
            СollectResultString(builder, r, argZ);
            return this.toString();
        }

        else if (realParts < 0 && imaginaryParts == 0) {
            argZ = Math.PI;
            СollectResultString(builder, r, argZ);
            return this.toString();
        }
       return "";
    }

    private void СollectResultString(StringBuilder builder, double r, double argZ) {
        result = String.valueOf(builder.append(String.format("%.5f", r)).append(" - ").append("cos(")
                .append(String.format("%.5f", argZ)).append(") + ").append("i").append(" - sin(")
                .append(String.format("%.5f", argZ)).append("))"));
        System.out.printf("Результат в тригонометрической форме: %s\n", result);
    }

    @Override
    public Calculable createArg(ComplexNumber number) {
        return this;
    }

    @Override
    public ComplexNumber getResult() {
        return resultNumber;
    }

    private static DataToCalculateSum getDataToCalculateSum(ComplexNumber first, ComplexNumber second) {
        double a = first.getRealPart();
        double b = first.getImaginaryPart();
        double c = second.getRealPart();
        double d = second.getImaginaryPart();
        DataToCalculateSum ResultDataForSum = new DataToCalculateSum(a, b, c, d);
        return ResultDataForSum;
    }

    private record DataToCalculateSum(double a, double b, double c, double d) {
    }

    private static DataToCalculate getDataToCalculateSub(ComplexNumber first, ComplexNumber second) {
        double a = first.getRealPart();
        double b = first.getImaginaryPart();
        double c = second.getRealPart();
        double d = second.getImaginaryPart();
        DataToCalculate ResultDataForSub = new DataToCalculate(a, b, c, d);
        return ResultDataForSub;
    }

    private record DataToCalculate(double a, double b, double c, double d) {
    }

    private static DataForMulti getDataForMulti(ComplexNumber first, ComplexNumber second) {
        double a = first.getRealPart();
        double b = first.getImaginaryPart();
        double c = second.getRealPart();
        double d = second.getImaginaryPart();
        double ac = a * c;
        double bd = b * d;
        double substractionAcAndBd = ac - bd;
        double bc = b * c;
        double ad = a * d;
        double sumBcAndAd = bc + ad;
        DataForMulti resultForMulti = new DataForMulti(ac, bd, bc, ad);
        return resultForMulti;
    }

    private record DataForMulti(double ac, double bd, double bc, double ad) {
    }

    private static ResultDataForDivision getResultDataForDivision(ComplexNumber first, ComplexNumber second) {
        double a = first.getRealPart();
        double b = first.getImaginaryPart();
        double c = second.getRealPart();
        double d = second.getImaginaryPart();
        double ac = a * c;
        double bd = b * d;
        double bc = b * c;
        double ad = a * d;
        double sumAcAndBd = ac + bd;
        double substractionBcAndAd = bc - ad;
        Double realParts = (double) (ac + bd) / ((c * c) + (d * d));
        Double imaginaryParts = (double) (bc - ad) / ((c * c) + (d * d));
        ResultDataForDivision resultForDivision = new ResultDataForDivision(realParts, imaginaryParts);
        return resultForDivision;
    }

    private record ResultDataForDivision(Double realParts, Double imaginaryParts) {
    }
}

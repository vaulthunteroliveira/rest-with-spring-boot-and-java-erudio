package com.example.restwithspringbootandjavaerudio.math;

public class SimpleMath {

    public static Double addition(Double numberOne, Double numberTwo) {
        return numberOne + numberTwo;
    }

    public static Double subtraction(Double numberOne, Double numberTwo) {
        return numberOne - numberTwo;
    }

    public static Double multiplication(Double numberOne, Double numberTwo) {
        return numberOne * numberTwo;
    }

    public static Double division(Double numberOne, Double numberTwo) {
        return numberOne / numberTwo;
    }

    public static Double squareRoot(Double numberOne) {
        return Math.sqrt(numberOne);
    }

    public static Double average(Double numberOne, Double numberTwo) {
        return (numberOne + numberTwo) / 2;
    }
}

package com.example.effectivejava.chapter6.item34;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 열거 타입 상수별로 다르게 동작하는 코드를 구현하기 위해 추상 메서드를 선언하고 각 상수에서 자신에 맞게 재정의한다.
 * 상수별 메서드 구현을 상수별 데이터와 결합할 수 있다.
 */
public enum Operation {
    PLUS("+") {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        @Override
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        @Override
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        @Override
        public double apply(double x, double y) {
            return x / y;
        }
    };

    private final String symbol;
    private static final Map<String, Operation> stringToEnum = Stream.of(values())
            .collect(Collectors.toMap(Operation::toString, e -> e));

    public static Optional<Operation> fromString(String symbol) {
        return Optional.ofNullable(stringToEnum.get(symbol));
    }

    Operation(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

    public abstract double apply(double x, double y);

    public static void main(String[] args) {
        double x = 1.5;
        double y = 2.5;
        for (Operation op : Operation.values())
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
    }
}

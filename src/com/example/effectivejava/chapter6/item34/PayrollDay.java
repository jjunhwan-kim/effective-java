package com.example.effectivejava.chapter6.item34;

import static com.example.effectivejava.chapter6.item34.PayrollDay.PayType.WEEKDAY;
import static com.example.effectivejava.chapter6.item34.PayrollDay.PayType.WEEKEND;

/**
 * 새로운 상수를 추가할 때 잔업수당 '전략'을 선택하도록 강제한다.
 * 잔업수당 계산을 private 중첩 열거 타입(PayType)으로 정의하고, PayrollDay 열거 타입 생성자에서 이 중 적당한 것을 선택한다.
 * PayrollDay 열거 타입은 잔업 수당 계산을 전략 열거 타입에 위임하여 switch 문이나 상수별 메서드 구현이 필요 없게 된다.
 */

public enum PayrollDay {
    MONDAY(WEEKDAY),
    TUESDAY(WEEKDAY),
    WEDNESDAY(WEEKDAY),
    THURSDAY(WEEKDAY),
    FRIDAY(WEEKDAY),
    SATURDAY(WEEKEND),
    SUNDAY(WEEKEND);

    private final PayType payType;

    PayrollDay(PayType payType) {
        this.payType = payType;
    }

    int pay(int minutesWorked, int payRate) {
        return payType.pay(minutesWorked, payRate);
    }

    enum PayType {
        WEEKDAY {
            @Override
            int overtimePay(int minsWorked, int payRate) {
                return minsWorked <= MINS_PER_SHITT ? 0 : (minsWorked - MINS_PER_SHITT) * payRate / 2;
            }
        },
        WEEKEND {
            @Override
            int overtimePay(int minsWorked, int payRate) {
                return minsWorked * payRate / 2;
            }
        };

        abstract int overtimePay(int minsWorked, int payRate);

        private static final int MINS_PER_SHITT = 8 * 60;

        int pay(int minsWorked, int payRate) {
            int basePay = minsWorked * payRate;
            return basePay + overtimePay(minsWorked, payRate);
        }
    }

    public static void main(String[] args) {
        for (PayrollDay day : values())
            System.out.printf("%-10s%d%n", day, day.pay(8 * 60, 1));
    }
}

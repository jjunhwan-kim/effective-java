package com.example.effectivejava.chapter5.item26;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();

        // 1. 제네릭의 하위 타입 규칙
        // String은 Object의 하위 타입이지만, List<String>은 List<Object>의 하위 타입이 아니다.
        // 따라서 strings를 파라미터로 넘길 수 없다.
        // unsafeAdd(strings, Integer.valueOf(42));
        // String s = strings.get(0);

        Set<String> s1 = new LinkedHashSet<>();
        s1.add("1");
        s1.add("2");
        s1.add("3");

        Set<Object> s2 = new LinkedHashSet<>();
        s2.add("1");

        int answer = numElementsInCommon(s1, s2);
        System.out.println(answer);

        Set<?> wildcardSet = instanceOfSet(s1);
        wildcardSet.add(null);
    }

    private static void unsafeAdd(List<Object> list, Object o) {
        list.add(o);
    }


    // 2. 비한정적 와일드카드 타입
    // 비한정적 와일드카드 타입: "?"만 사용할 때 비한정적 와읻드카드라고 한다.
    // 제네릭 타입을 쓰고 싶지만 실제 타입 매개 변수가 무엇인지 신경 쓰고 싶지 않을 때 비한정적 와일드카드 타입을 사용한다.
    // Set<E>의 비한정적 와일드카드 타입은 Set<?>이다.
    // Set<?>와 로 타입인 Set와의 차이점은, 로 타입 컬렉션에는 아무 원소나 넣을 수 있으나 Set<?>에는 null 외에는 어떤 원소도 넣읋 수 없다.
    private static int numElementsInCommon(Set<?> s1, Set<?> s2) {
        int result = 0;

        // Set<?> 에는 null만 담을 수 있다. 따라서 타입 불변식을 훼손하지 못하므로 안전하다.
        s1.add(null);
        //s1.add(1);

        for (Object o1 : s1) {
            if (s2.contains(o1)) {
                result++;
            }
        }
        return result;
    }

    // 3. instanceof 연산자
    // 런타입에는 제네릭 타입 정보가 지워지므로 instanceof 연산자는 비한정적 와일드카드 타입에만 적용할 수 있다. Set<?>
    // 로 타입이든 비한정적 와일드카드 타입이든 instanceof 연산자는 완전히 똑같이 동작한다.
    // 따라서 비한정적 와일드카드 타입의 꺽쇠괄호와 물음표는 코드만 지저분하게 만드므로, 로 타입을 쓰는 것이 깔끔하다.
    // 제네릭 타입에 instanceof 연산자를 사용할 때는 로 타입을 사용하자.
    private static Set<?> instanceOfSet(Object o) {
        if (o instanceof Set) {
            Set<?> s = (Set<?>) o;
            return s;
        }
        return null;
    }
}

package com.osrs.util;

@FunctionalInterface
public interface Condition<T> {

    boolean test(T t);

}

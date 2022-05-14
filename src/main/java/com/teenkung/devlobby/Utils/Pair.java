package com.teenkung.devlobby.Utils;

import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.K;

public class Pair {

    private K FirstValue;
    private A SecondValue;

    public Pair(K first, A second) {
        this.FirstValue = first;
        this.SecondValue = second;
    }

    public K getFirstValue() { return FirstValue; }
    public A getSecondValue() { return SecondValue; }

    public void setFirstValue(K firstValue) { this.FirstValue = firstValue;}
    public void setSecondValue(A secondValue) { this.SecondValue = secondValue; }

}

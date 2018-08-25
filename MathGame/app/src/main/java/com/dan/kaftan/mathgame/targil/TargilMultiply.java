package com.dan.kaftan.mathgame.targil;

import com.dan.kaftan.mathgame.targil.AbstractTargil;

public class TargilMultiply extends AbstractTargil {

    public TargilMultiply() {
        super();
    }

    public TargilMultiply(int firstnum, int secondNum, String operator) {
        super(firstnum, secondNum, operator);
    }
    @Override
    public int calc() {
        return firstnum * secondNum;
    }
}

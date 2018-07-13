package com.dan.kaftan.mathgame.targil;

import com.dan.kaftan.mathgame.targil.AbstractTargil;

public class TargilMultiply extends AbstractTargil {

    public TargilMultiply() {
        super();
    }

    @Override
    public int calc() {
        return firstnum * secondNum;
    }
}

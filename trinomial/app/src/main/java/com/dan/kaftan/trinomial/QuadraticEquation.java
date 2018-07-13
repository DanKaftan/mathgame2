package com.dan.kaftan.trinomial;

/**
 * Created by alonk on 22/03/2018.
 */

public class QuadraticEquation {

    double a;
    double b;
    double c;
    double Answer1;
    double Answer2;

    public QuadraticEquation (double a, double b, double c){
        this.a = a;
        this.b = b;
        this.c = c;
    }


    public double getAnswer1 (){

        Answer1 = (-1 *b + Math.sqrt(b * b -4 * a * c ))/(2 * a);

        return Answer1;
    }
    public double getAnswer2 (){
        Answer2 = (-1 *b - Math.sqrt(b * b -4 * a * c ))/(2 * a);
        return Answer2;
    }
}

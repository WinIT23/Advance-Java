/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javabeans.model;

/**
 *
 * @author Win_It
 */
public class Person {

    private String name;
    private Dog doggo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDoggo(Dog doggo) {
        this.doggo = doggo;
    }

    public Dog getDoggo() {
        return doggo;
    }

}

package org.sopt.controller;

public class IdGenerator {
    private int id = 1;

    public int generateId() {
        return id++;
    }
}

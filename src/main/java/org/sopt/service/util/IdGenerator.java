package org.sopt.service.util;

public class IdGenerator {
    private int id = 1;

    public int generateId() {
        return id++;
    }
}

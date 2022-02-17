package com.o4.microservices.util;

import java.util.UUID;

public class IDUtils {

    public static String nextID() {
        return UUID.randomUUID().toString();
    }
}

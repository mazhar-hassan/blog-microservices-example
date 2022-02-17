package com.o4.microservices.util;

import java.util.UUID;

public interface IDUtils {

    static String nextID() {
        return UUID.randomUUID().toString();
    }
}

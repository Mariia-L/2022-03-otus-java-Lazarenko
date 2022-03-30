package ru.otus;

import com.google.common.base.Joiner;

public class HelloOtus {

    public static void main(String[] args) {
        System.out.println(Joiner.on("; ").skipNulls()
                .join("Run", null, "Otus", "Run"));
    }
}

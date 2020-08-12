package com.atlassian.oauth.client.example;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Command {
    REQUEST_TOKEN("9gcRw6S2eRhgXrujp1bvuFcmmsRUM2Mb"),
    ACCESS_TOKEN("M3qYg7keANAXorCUwKnfwcSA9VtDElRI"),
    REQUEST("https://priya006.atlassian.net/rest/api/3/issue/HAC-1");

    private final String name;

    Command(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static String names() {
        return Arrays.asList(values()).stream()
                .map(Command::getName)
                .collect(Collectors.toList())
                .toString();
    }

    public static Command fromString(String name) {
        if (name != null) {
            for (Command b : Command.values()) {
                if (name.equalsIgnoreCase(b.name)) {
                    return b;
                }
            }
        }
        return null;
    }
}
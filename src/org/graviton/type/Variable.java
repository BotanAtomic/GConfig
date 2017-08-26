package org.graviton.type;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

public class Variable {
    private String name, value, type;

    private char openValue;

    public Variable(String name, String value, String type) {
        this.name = getterName(name);
        this.value = value;
        this.type = parseType(type);
    }

    public String getterName(String name) {
        StringBuilder finalName = new StringBuilder();
        AtomicBoolean capitalizeNext = new AtomicBoolean(false);
        IntStream.range(0, name.length()).forEach(i -> {
            char current = name.charAt(i);
            if (current != '.')
                finalName.append(capitalizeNext.getAndSet(false) ? Character.toUpperCase(current) : current);
            else capitalizeNext.set(true);
        });
        return finalName.toString();
    }

    private String parseType(String type) {
        switch (type.toLowerCase()) {
            case "string":
                openValue = '\"';
                break;
            case "char":
                openValue = '\'';
                break;
        }
        return type;
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

    public String getType() {
        return this.type;
    }

    public String openingCharValue() {
        return openValue == '\u0000' ? "" : String.valueOf(openValue);
    }


}

package org.graviton.type;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Class {
    private String variableName, packageName;

    private List<Pair<String, String>> variables = new ArrayList<>();

    public Class(String variableName) {
        this.variableName = variableName;
    }

    public String getVariableName() {
        return Character.toLowerCase(variableName.charAt(0)) + variableName.substring(1);
    }

    public void addConstructorVar(String name, String type) {
        variables.add(new Pair<>(name, type));
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getType() {
        String[] info = packageName.split("\\.");
        return info[info.length - 1];
    }

    private String openingValue(String type) {
        switch (type.toLowerCase()) {
            case "string":
                return  "\"";
            case "character":
            case "char":
                return  "\'";
        }
        return "";
    }

    public String buildConstructor() {
        if (variables.isEmpty()) return "";
        StringBuilder constructor = new StringBuilder();
        variables.forEach(var ->{
            String openingValue = openingValue(var.getValue());
            constructor.append(openingValue).append(var.getKey()).append(openingValue).append(",");
        });
        return constructor.substring(0, constructor.length() - 1);
    }
}

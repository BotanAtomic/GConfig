package org.graviton.writer;

import org.graviton.reader.ConfigurationReader;

import static com.sun.xml.internal.ws.util.StringUtils.capitalize;

public class ConfigurationWriter {
    private final StringBuilder builder = new StringBuilder();


    public ConfigurationWriter(ConfigurationReader reader, String packageName) {
        builder.append("package ").append(packageName).append(";\n\n");
        reader.getClasses().forEach(aClass -> builder.append("import " + aClass.getPackageName() + ";"));
        builder.append("\n\n");
        builder.append("public class Configuration {\n\n");
        reader.getVariables().forEach(variable ->
                builder.append("    private final static ").append(variable.getType()).append(" ").append(variable.getName())
                        .append(" = ").append(variable.openingCharValue()).append(variable.getValue()).append(variable.openingCharValue())
                        .append(";\n"));

        reader.getClasses().forEach(aClass ->
                builder.append("    private final static ").append(aClass.getType()).append(" ").append(aClass.getVariableName())
                        .append(" = new ").append(aClass.getType()).append("(").append(aClass.buildConstructor()).append(");\n"));

        reader.getVariables().forEach(variable ->
                builder.append("\n\n    public static ").append(variable.getType()).append(" get")
                        .append(capitalize(variable.getName())).append("() {\n").append("        return ")
                        .append(variable.getName()).append(";\n").append("    }"));

        builder.append("\n}");
    }

    @Override
    public String toString() {
        return builder.toString();
    }

}

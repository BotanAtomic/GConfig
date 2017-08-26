package org.graviton.reader;

import org.graviton.type.Class;
import org.graviton.type.Variable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class ConfigurationReader {
    private List<String> lines;
    private List<Variable> variables = new ArrayList<>();
    private List<Class> classes = new ArrayList<>();

    public ConfigurationReader(File file) {
        try {
            lines = Files.readAllLines(file.toPath());


            AtomicReference<Class> currentClassBuild = new AtomicReference<>();

            lines.stream().map(String::trim).filter(line -> !line.isEmpty()).forEach(line -> {
                if (line.trim().charAt(0) == '}') {
                    classes.add(currentClassBuild.getAndSet(null));
                } else if (currentClassBuild.get() != null) {
                    parseClass(currentClassBuild.get(), line);
                } else if (line.contains("=")) {
                    parseVariable(line);
                } else if (line.contains("{")) {
                    currentClassBuild.set(new Class(line.split("\\{")[0]));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseClass(Class clazz, String line) {
        String[] params = line.split(":");
        if (params[0].contains("class")) {
            clazz.setPackageName(params[1]);
        } else if (params[0].contains("constructor")) {
            Stream.of(line.split("\\(")[1].split(",")).forEach(var -> {
                String[] varParams = var.replace(")","").split(":");
                clazz.addConstructorVar(varParams[0], varParams[1]);
            });
        }
    }

    private void parseVariable(String line) {
        try {
            String name = line.split("=")[0];
            String value = line.split("=")[1].trim();
            variables.add(new Variable(name, value.split(":")[0], value.split(":")[1]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Variable> getVariables() {
        return this.variables;
    }

    public List<Class> getClasses() {
        return this.classes;
    }

}

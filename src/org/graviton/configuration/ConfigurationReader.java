package org.graviton.configuration;

import org.graviton.type.Variable;
import org.graviton.writer.ConfigurationWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationReader {
    private List<String> lines;
    private List<Variable> variables = new ArrayList<>();

    public ConfigurationReader(File file) {
        try {
            lines = Files.readAllLines(file.toPath());

            lines.stream().map(String::trim).filter(line -> !line.isEmpty()).forEach(line -> {
                if (line.contains("=")) {
                    parseVariable(line);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseVariable(String line) {
        try {
            String name = line.split("=")[0];
            String value = line.split("=")[1].trim();

            variables.add(new Variable(name, value.split(":")[0], value.split(":")[1]));

        } catch (Exception e) {

        }
    }

    public List<Variable> getVariables() {
        return this.variables;
    }

}

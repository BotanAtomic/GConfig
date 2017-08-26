package org.graviton.configuration;

import org.graviton.writer.ConfigurationWriter;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigurationBuilder {
    private String path, exportName, exportPath, packageName;

    private ConfigurationBuilder(String path) {
        this.path = path;
    }

    public static ConfigurationBuilder newInstance(String path) {
        return new ConfigurationBuilder(path);
    }

    public ConfigurationBuilder exportName(String exportName) {
        this.exportName = exportName + ".java";
        return this;
    }

    public ConfigurationBuilder exportPath(String exportPath) {
        this.exportPath = exportPath;
        return this;
    }

    public ConfigurationBuilder packageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public void build() {
        new File(exportPath).mkdirs();
        try {
            Path path = Files.createFile(Paths.get(exportPath + "/" + exportName));
            Files.write(path, new ConfigurationWriter(new ConfigurationReader(new File(this.path)), packageName).toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

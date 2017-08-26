package org.graviton.test;

import org.graviton.configuration.ConfigurationBuilder;

public class Main {

    public static void main(String[] args) {
        ConfigurationBuilder.newInstance("configuration.grv")
                .exportName("Configuration").exportPath("test/org/graviton/configuration")
                .packageName("org.graviton.configuration").build();

    }

}

package com.repeta.numerical_analysis.lab1;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

public final class ConfigurationSingleton {
    private static Configuration CONFIGURATION;

    private ConfigurationSingleton(){}

    public static Configuration getConfiguration(){
        if(CONFIGURATION==null){
            Parameters params = new Parameters();
            FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                    new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                            .configure(params.properties()
                                    .setFileName("app.properties"));
            try
            {
                CONFIGURATION = builder.getConfiguration();
            }
            catch(ConfigurationException cex)
            {
               cex.printStackTrace();
            }
        }

        return CONFIGURATION;
    }
}

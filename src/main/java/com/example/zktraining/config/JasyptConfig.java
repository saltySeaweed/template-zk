/**
 * Config decode file application.properties.
 * @author  Vinhcv
 * @version 1.0
 * @since   2020-08-28
 */

package com.example.zktraining.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {

    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("CNC-EMS-admin-secret-pass");
        config.setAlgorithm("PBEWithMD5AndTripleDES");
        config.setPoolSize(1);
        encryptor.setConfig(config);
        return encryptor;
    }
}

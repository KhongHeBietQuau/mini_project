package com.ms.tourist_app.application.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component

public class AppEnv {

    public static String jwtConfigSecretKey ="cuong" ;

    public static Integer jwtConfigTimeExpiration=864000 ;




    public static final class Swagger {
        public Swagger() {
        }

        public static final String pathRegex = "/.*";
        public static final String basePackage = "com.ms.tourist_app.adapter.web.v1.controller";
        public static final String title = "Ngo Ngoc Sang";
        public static final String description = "TouristApp";
        public static final String nameContact = "Ngo Ngoc Sang";
        public static final String urlContact = "...";
        public static final String emailContact = "ngocsangair01@gmail.com";
        public static final String license = "Apache 2.0";
        public static final String licenseUrl = "";
        public static final String version = "1.0.0";
    }

    public static final class MessageSourceConfig {
        public static final String baseName = "classpath:i18n/messages";
        public static final String utf8 = "UTF-8";
    }

}

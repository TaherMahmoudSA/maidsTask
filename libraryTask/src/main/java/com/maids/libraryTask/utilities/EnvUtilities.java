package com.maids.libraryTask.utilities;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
@Getter
public class EnvUtilities {

    private static EnvUtilities instance;

    private final Environment env;

    @PostConstruct
    public void init() {
        instance = this;
    }

    public String getPropertyByEnv(String key){

        return env.getProperty(key);
    }


}

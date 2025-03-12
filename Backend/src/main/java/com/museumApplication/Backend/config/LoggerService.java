package com.museumApplication.Backend.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class LoggerService {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(LoggerService.class);

    public void info(String message)
    {
        logger.info(message);
    }

    public void error(String message)
    {
        logger.error(message);
    }

    public void warning(String message)
    {
        logger.warn(message);
    }


}

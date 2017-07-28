package com.tiger.config;

import akka.actor.ActorSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * Package: com.tiger.config
 * ClassName: BaseConfig
 * Author: Tiger
 * Description:
 * CreateDate: 2016/10/8
 * Version: 1.0
 */

@Configuration
@ComponentScan(basePackages  = "com.tiger",excludeFilters={
        @ComponentScan.Filter(type= FilterType.REGEX, pattern="com.tiger.model")})
public class AkkaConfig {

    private static Logger logger = LoggerFactory.getLogger(AkkaConfig.class);

    @Bean(name = "actorSystem")
    public ActorSystem getActorSystem(){
        return ActorSystem.create("actorSystem");
    }

}

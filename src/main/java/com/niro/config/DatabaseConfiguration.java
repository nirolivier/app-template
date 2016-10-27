/*
 * Copyright 2016 Olivier nirina
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.niro.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoFactoryBean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import java.util.Set;

/**
 * @author Olivier nirina
 * @since 1.0
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.niro.repository")
@EnableSpringDataWebSupport
public class DatabaseConfiguration extends AbstractMongoConfiguration {

    @Autowired private Mongo mongo;

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getDatabaseName() {
        return "appTmpdb";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mongo mongo() throws Exception {
        return mongo;
    }

    @Bean public MongoClientFactoryBean mongoClient(){
        MongoClientFactoryBean mongoClient = new MongoClientFactoryBean();
        mongoClient.setHost("localhost");
       // MongoCredential mongoCredential = MongoCredential.createCredential("admin", "appTmpdb", "admin".toCharArray());
        //mongoClient.setCredentials(new MongoCredential[]{mongoCredential});
        return mongoClient;
    }


    @Override
    protected String getMappingBasePackage() {
        return "com.niro.domain";
    }


}

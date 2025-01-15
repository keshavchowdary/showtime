package com.showtime.showtime.utils;

import org.springframework.data.mongodb.core.query.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class MongoHelper {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    public <T> Object find(Query query, Class<T> model) {
        return mongoTemplate.find(query, model);
    }

    public <T> Object findOne(Query query, Class<T> model) {
        return mongoTemplate.findOne(query, model);
    }

    public <T> void save(T document) {
        mongoTemplate.save(document);
    }

    public <T> Integer count(Query query, Class<T> model) {
        return (int) mongoTemplate.count(query, model);
    }

}

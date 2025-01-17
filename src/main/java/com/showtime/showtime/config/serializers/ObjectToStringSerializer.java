package com.showtime.showtime.config.serializers;

import java.io.IOException;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ObjectToStringSerializer extends JsonSerializer<ObjectId> {
    @Override
    public void serialize(ObjectId id,  JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(id.toString());
    }
}

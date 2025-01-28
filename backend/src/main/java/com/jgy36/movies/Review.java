package com.jgy36.movies;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "reviews")
@Data
@AllArgsConstructor @NoArgsConstructor
public class Review {
    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public ObjectId getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    @JsonSerialize(using = ToStringSerializer.class)
    @Getter
    private ObjectId id;
    private String body;
    private LocalDateTime created;
    private LocalDateTime updated;

    public Review(String body, LocalDateTime created, LocalDateTime updated) {
        this.body = body;
        this.created = created;
        this.updated = updated;
    }
}

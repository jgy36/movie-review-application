package com.jgy36.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Review createReview(String reviewBody, String imdbId) {
        if (reviewBody == null || reviewBody.isEmpty()) {
            throw new IllegalArgumentException("Review body cannot be empty");
        }
        // Create the review and save it to the repository
        Review review = new Review(reviewBody, LocalDateTime.now(), LocalDateTime.now());
        review = repository.insert(review);

        if (review.getId() == null) {
            throw new RuntimeException("Failed to create Review");
        }

        System.out.println("Review created with ID: " + review.getId());

        // Update the Movie document with the review ID
        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review.getId().toHexString())) // Convert ObjectId to String
                .first();

        return review; // Ensure the review is returned after being created
    }
}

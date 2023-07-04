package dev.torres.movieprojectapi.services;

import dev.torres.movieprojectapi.entities.Movie;
import dev.torres.movieprojectapi.entities.Review;
import dev.torres.movieprojectapi.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {


    private ReviewRepository reviewRepository;
    private MongoTemplate mongoTemplate;

    public ReviewService(){}

    @Autowired
    public ReviewService(ReviewRepository reviewRepo, MongoTemplate mongoTemp){
        this.reviewRepository = reviewRepo;
        this.mongoTemplate = mongoTemp;
    }

    public Review createReview(String reviewBody, String imdbId){
        Review review = reviewRepository.insert(new Review(reviewBody));

        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review))
                .first();

        return review;
    }
}

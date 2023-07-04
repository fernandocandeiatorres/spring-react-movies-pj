package dev.torres.movieprojectapi.controllers;

import dev.torres.movieprojectapi.entities.Review;
import dev.torres.movieprojectapi.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/reviews")
@CrossOrigin(origins = "http://localhost:3000/")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController() {
    }

    @Autowired
    public ReviewController(ReviewService rs) {
        this.reviewService = rs;
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Review>(reviewService.createReview(payload.get("reviewBody"), payload.get("imdbId")), HttpStatus.CREATED);
    }
}

package umc.spring.converter;

import umc.spring.domain.Review;
import umc.spring.web.dto.ReviewDTO.ReviewRequestDTO;
import umc.spring.web.dto.ReviewDTO.ReviewResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReviewConverter {

    public static ReviewResponseDTO.addReviewResponseDTO addReviewResponseDTO(Review review){
        return ReviewResponseDTO.addReviewResponseDTO.builder()
                .reviewId(review.getId())
                .memberId(review.getMember().getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
    public static Review toReview(ReviewRequestDTO.addReviewDTO request) {
        return Review.builder()
                .title(request.getTitle())
                .score(request.getScore())
                .contents(request.getContents())
                .reviewImageList(new ArrayList<>())
                .build();
    }
}

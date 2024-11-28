package umc.spring.service.ReviewService;

import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.web.dto.ReviewDTO.ReviewRequestDTO;

public interface ReviewCommandService {

    Review addReview(Long missionId, Member member, ReviewRequestDTO.addReviewDTO request);
}

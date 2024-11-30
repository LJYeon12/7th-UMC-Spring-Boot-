package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.service.ReviewService.ReviewCommandService;
import umc.spring.web.dto.ReviewDTO.ReviewRequestDTO;
import umc.spring.web.dto.ReviewDTO.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewRestController {
    private final ReviewCommandService reviewCommandService;

    // @PathVariable Spring MVC에서 URL 경로에 포함된 값을 추출하여 메서드의 파라미터로 전달할 때 사용
    @PostMapping("/{missionId}/review")
    public ApiResponse<ReviewResponseDTO.addReviewResponseDTO> addReview(
            @PathVariable("missionId") Long missionId,
            @RequestBody @Valid ReviewRequestDTO.addReviewDTO request) {
        // 임시 객체 생성
        Member member = Member.builder()
                .id(1L)
                .name("Test User")
                .email("test@example.com")
                .build();
        Review newReview = reviewCommandService.addReview(missionId, member, request);
        return ApiResponse.onSuccess(ReviewConverter.addReviewResponseDTO(newReview));
    }
}

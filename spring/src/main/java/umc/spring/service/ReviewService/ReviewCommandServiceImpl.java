package umc.spring.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MissionHandler;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.*;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.ReviewImageRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.web.dto.ReviewDTO.ReviewRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements  ReviewCommandService{

    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;
    private final ReviewImageRepository reviewImageRepository;
    @Override
    @Transactional
    public Review addReview(Long missionId , Member member, ReviewRequestDTO.addReviewDTO request) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));
        Store store = mission.getStore();
        if (store == null) {
            throw new MissionHandler(ErrorStatus.MISSION_NOT_ASSOCIATED_WITH_STORE);
        }

        Review newReview = ReviewConverter.toReview(request);
        newReview.setMemberAndStore(member, store);

        if (request.getReviewImageList() != null) {
            List<ReviewImage> reviewImages = request.getReviewImageList().stream()
                    .map(url -> new ReviewImage(null, newReview, url))
                    .collect(Collectors.toList());
            reviewImageRepository.saveAll(reviewImages);
            newReview.setReviewImageList(reviewImages);
        }
        return reviewRepository.save(newReview);
    }
}

package umc.spring.web.dto.ReviewDTO;

import lombok.Getter;
import umc.spring.domain.ReviewImage;

import java.util.List;

public class ReviewRequestDTO {

    @Getter
    public static class addReviewDTO{
        String title;
        Float score;
        String contents;
        List<String> reviewImageList;
    }
}

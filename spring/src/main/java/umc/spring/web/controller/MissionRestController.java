package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.service.ReviewService.ReviewCommandService;
import umc.spring.web.dto.MissionDTO.MissionRequestDTO;
import umc.spring.web.dto.MissionDTO.MissionResponseDTO;
import umc.spring.web.dto.ReviewDTO.ReviewRequestDTO;
import umc.spring.web.dto.ReviewDTO.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionRestController {
    private final MissionCommandService missionCommandService;
    @PostMapping("/{storeId}")
    public ApiResponse<MissionResponseDTO.createMissionResponseDTO> createMission(
            @PathVariable("storeId") Long storeId,
            @RequestBody @Valid MissionRequestDTO.createMissionRequestDTO requestDTO) {
        Mission mission = missionCommandService.createMission(storeId, requestDTO);
        return ApiResponse.onSuccess(MissionConverter.createMissionResponseDTO(mission));
    }
}

package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.service.MemberMissionService.MemberMissionCommandService;
import umc.spring.web.dto.MemberMissionDTO.MemberMissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member-missions")
public class MemberMissionRestController {
    private final MemberMissionCommandService memberMissionCommandService;

    @PostMapping("/{memberId}/missions/{missionId}")
    public ApiResponse<MemberMissionResponseDTO.addMissionToMemberListResponseDTO> addMissionToMemberList(
            @PathVariable("memberId") Long memberId,
            @PathVariable("missionId") Long missionId
    ) {
        memberMissionCommandService.addMissionToMemberList(memberId, missionId);
        return ApiResponse.onSuccess(
                MemberMissionConverter.addMissionToMemberListResponseDTO(memberId, missionId));
    }

}

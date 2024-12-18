package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MemberMissionService.MemberMissionCommandService;
import umc.spring.service.MemberMissionService.MemberMissionQueryService;
import umc.spring.web.dto.MemberMissionDTO.MemberMissionResponseDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member-missions/{memberId}")
public class MemberMissionRestController {
    private final MemberMissionCommandService memberMissionCommandService;
    private final MemberMissionQueryService memberMissionQueryService;
    @PostMapping("/missions/{missionId}")
    public ApiResponse<MemberMissionResponseDTO.addMissionToMemberListResponseDTO> addMissionToMemberList(
            @PathVariable("memberId") Long memberId,
            @PathVariable("missionId") Long missionId
    ) {
        memberMissionCommandService.addMissionToMemberList(memberId, missionId);
        return ApiResponse.onSuccess(
                MemberMissionConverter.addMissionToMemberListResponseDTO(memberId, missionId));
    }

    @GetMapping("/in-progress")
    @Operation(summary = "진행 중인 미션 목록 조회 API",description = "진행 중인 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "멤버 Id, path variable 입니다!!")
    })
    public ApiResponse<MemberMissionResponseDTO.progressMissionListDTO> getProgressMissionList(
            @PathVariable("memberId") Long memberId,
            @RequestParam(name = "page") Integer page)
    {
        Page<Mission> progressMissionList = memberMissionQueryService.getProgressMissionList(memberId, page);
        return ApiResponse.onSuccess(MemberMissionConverter.progressMissionListDTO(progressMissionList));
    }

    @PatchMapping("/to-completed/{missionId}")
    @Operation(summary = "진행 중인 미션 완료 미션으로 변환 API",description = "진행 중인 미션을 완료 미션으로 변환하는 API 이다.")
    public ApiResponse<MemberMissionResponseDTO.changeToCompletedMissionDTO> patchChangeToCompletedMissionDTO(
            @PathVariable("missionId") Long missionId, @PathVariable("memberId") Long memberId) {
        MemberMission memberMission = memberMissionCommandService.patchToCompletedMissionDTO(memberId, missionId);
        return ApiResponse.onSuccess(MemberMissionConverter.changeToCompletedMissionDTO(memberMission));
    }
}

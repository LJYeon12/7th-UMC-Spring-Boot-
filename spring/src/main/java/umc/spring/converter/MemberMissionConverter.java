package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.web.dto.MemberMissionDTO.MemberMissionResponseDTO;

import java.time.LocalDateTime;

public class MemberMissionConverter {
    public static MemberMissionResponseDTO.addMissionToMemberListResponseDTO addMissionToMemberListResponseDTO(
            Long memberId, Long missionId) {
        return MemberMissionResponseDTO.addMissionToMemberListResponseDTO.builder()
                .memberId(memberId)
                .missionId(missionId)
                .createdAt(LocalDateTime.now())
                .message("미션이 성공적으로 추가되었습니다")
                .build();
    }
}

package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberMissionDTO.MemberMissionResponseDTO;
import umc.spring.web.dto.StoreDTO.StoreResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class MemberMissionConverter {

    public static MemberMissionResponseDTO.progressMissionDTO progressMissionDTO(Mission progressMission) {
        return MemberMissionResponseDTO.progressMissionDTO.builder()
                .mission_spec(progressMission.getMissionSpec())
                .reward(progressMission.getReward())
                .deadline(progressMission.getDeadline())
                .createdAt(progressMission.getCreatedAt().toLocalDate())
                .build();
    }

    public static MemberMissionResponseDTO.progressMissionListDTO progressMissionListDTO(Page<Mission> missionList) {
        List<MemberMissionResponseDTO.progressMissionDTO> progressMissionDTOList = missionList.stream()
                .map(MemberMissionConverter::progressMissionDTO).collect(Collectors.toList());
        return MemberMissionResponseDTO.progressMissionListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(progressMissionDTOList.size())
                .progressMissionList(progressMissionDTOList)
                .build();
    }

    public static MemberMissionResponseDTO.addMissionToMemberListResponseDTO addMissionToMemberListResponseDTO(
            Long memberId, Long missionId) {
        return MemberMissionResponseDTO.addMissionToMemberListResponseDTO.builder()
                .memberId(memberId)
                .missionId(missionId)
                .createdAt(LocalDateTime.now())
                .message("미션이 성공적으로 추가되었습니다")
                .build();
    }

    public static MemberMissionResponseDTO.changeToCompletedMissionDTO changeToCompletedMissionDTO(MemberMission memberMission) {

        return MemberMissionResponseDTO.changeToCompletedMissionDTO.builder()
                .missionId(memberMission.getMission().getId())
                .missionStatus(memberMission.getStatus())
                .build();
    }
}

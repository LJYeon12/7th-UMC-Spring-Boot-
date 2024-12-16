package umc.spring.service.MemberMissionService;

import umc.spring.domain.mapping.MemberMission;

public interface MemberMissionCommandService {
    void addMissionToMemberList(Long memberId, Long missionId);

    MemberMission patchToCompletedMissionDTO(Long memberId, Long missionId);
}

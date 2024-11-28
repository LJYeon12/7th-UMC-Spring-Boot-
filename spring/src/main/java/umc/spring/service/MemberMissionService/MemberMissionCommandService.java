package umc.spring.service.MemberMissionService;

import umc.spring.domain.Mission;

public interface MemberMissionCommandService {
    void addMissionToMemberList(Long memberId, Long missionId);
}

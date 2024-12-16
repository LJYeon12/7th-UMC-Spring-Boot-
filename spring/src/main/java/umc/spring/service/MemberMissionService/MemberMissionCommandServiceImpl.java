package umc.spring.service.MemberMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.apiPayload.exception.handler.MemberMissionHandler;
import umc.spring.apiPayload.exception.handler.MissionHandler;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;

@Service
@RequiredArgsConstructor
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService{
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    @Override
    @Transactional
    public void addMissionToMemberList(Long memberId, Long missionId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));
        // 중복 처리
        boolean alreadyExists = memberMissionRepository.existsByMemberAndMission(member, mission);
        if (alreadyExists) {
            throw new MemberMissionHandler(ErrorStatus.MISSION_ALREADY_ADD_MEMBER);
        }
        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGING)
                .build();
        member.addMemberMission(memberMission);
        memberMissionRepository.save(memberMission);
    }

    @Override
    @Transactional
    public MemberMission patchToCompletedMissionDTO(Long memberId, Long missionId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));
        // 중복 처리
        boolean alreadyExists = memberMissionRepository.existsByMemberAndMission(member, mission);
        if (!alreadyExists) {
            throw new MemberMissionHandler(ErrorStatus.MEMBERMISSION_NOT_FOUND);
        }

        MemberMission memberMission = memberMissionRepository.findByMemberAndMission(member, mission);
        memberMission.setStatus(MissionStatus.COMPLETE);
        return memberMission;
    }

}

package umc.spring.service.MemberMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberMissionQueryServiceImpl implements MemberMissionQueryService{
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    @Override
    public Page<Mission> getProgressMissionList(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId).get();

        List<MemberMission> progressMemberMissionList = memberMissionRepository.findAllByMemberAndStatus(member, MissionStatus.CHALLENGING);

        List<Mission> missionList = progressMemberMissionList.stream()
                .map(MemberMission::getMission)
                .collect(Collectors.toList());
        Page<Mission> missionPage = new PageImpl<>(missionList, PageRequest.of(page, 10), missionList.size());
        return missionPage;
    }
}

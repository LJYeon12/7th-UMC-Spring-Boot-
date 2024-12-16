package umc.spring.service.MemberMissionService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;

public interface MemberMissionQueryService {
    Page<Mission> getProgressMissionList(Long memberId, Integer page);
}

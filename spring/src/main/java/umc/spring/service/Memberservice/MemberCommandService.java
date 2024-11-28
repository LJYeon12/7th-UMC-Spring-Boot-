package umc.spring.service.Memberservice;

import umc.spring.domain.Member;
import umc.spring.web.dto.MemberDTO.MemberRequestDTO;

public interface MemberCommandService {

    public Member joinMember(MemberRequestDTO.JoinDto request);
}

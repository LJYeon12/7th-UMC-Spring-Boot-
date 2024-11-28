package umc.spring.service.MissionService;

import umc.spring.domain.Mission;
import umc.spring.repository.MissionRepository;
import umc.spring.web.dto.MissionDTO.MissionRequestDTO;

public interface MissionCommandService {
    Mission createMission(Long storeId,MissionRequestDTO.createMissionRequestDTO request);
}

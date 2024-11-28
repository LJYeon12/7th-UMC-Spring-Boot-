package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.web.dto.MissionDTO.MissionRequestDTO;
import umc.spring.web.dto.MissionDTO.MissionResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MissionConverter {
    public static MissionResponseDTO.createMissionResponseDTO createMissionResponseDTO(Mission mission) {
        return MissionResponseDTO.createMissionResponseDTO.builder()
                .missionId(mission.getId())
                .missionSpec(mission.getMissionSpec())
                .createdAt(LocalDateTime.now())
                .build();
    }
    public static Mission toMission(MissionRequestDTO.createMissionRequestDTO requestDTO) {
        return Mission.builder()
                .reward(requestDTO.getReward())
                .deadline(requestDTO.getDeadline())
                .missionSpec(requestDTO.getMissionSpec())
                .memberMissionList(new ArrayList<>())
                .build();
    }
}

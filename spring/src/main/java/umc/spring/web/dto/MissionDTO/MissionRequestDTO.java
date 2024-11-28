package umc.spring.web.dto.MissionDTO;

import lombok.Getter;

import java.time.LocalDate;

public class MissionRequestDTO {

    @Getter
    public static class createMissionRequestDTO{
        Integer reward;
        LocalDate deadline;
        String missionSpec;
    }
}

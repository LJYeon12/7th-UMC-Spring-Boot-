package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.StoreHandler;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.MissionDTO.MissionRequestDTO;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService{
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    @Override
    public Mission createMission(Long storeId ,MissionRequestDTO.createMissionRequestDTO request) {
        Mission newMission = MissionConverter.toMission(request);
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        newMission.setStore(store);
        return missionRepository.save(newMission);
    }
}

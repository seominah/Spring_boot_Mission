package dev.sma.basic.service;

import dev.sma.basic.jpa.entity.AreaEntity;
import dev.sma.basic.jpa.repository.AreaRepository;
import dev.sma.basic.model.AreaDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class AreaService {
    private static final Logger logger = LoggerFactory.getLogger(AreaService.class);
    private AreaRepository areaRepository;

    public AreaService(@Autowired AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
        String regionMajor = "서울시";
        String[] regionMinors = {" 서초구", "강남구", "강남구"};
        String[] regionPatches = {"서초동","역삼동", "삼성동"};
        Double[] latitudes = {37.4877,37.4999, 37.5140};
        Double[] longitudes = {127.0174, 127.0374, 127.0565};
        final List<AreaEntity> areaEntityList = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            AreaEntity testAreaEntity = new AreaEntity();
            testAreaEntity.setRegionMajor(regionMajor);
            testAreaEntity.setRegionMinor(regionMinors[i]);
            testAreaEntity.setRegionPatch(regionPatches[i]);
            testAreaEntity.setLatitude(latitudes[i]);
            testAreaEntity.setLongitude(longitudes[i]);
            areaEntityList.add(testAreaEntity);
        }
        this.areaRepository.saveAll(areaEntityList);
    }

    public AreaDto createArea(AreaDto dto) {
        AreaEntity areaEntity = new AreaEntity();
        areaEntity.setRegionMajor(dto.getRegionMajor());
        areaEntity.setRegionMinor(dto.getRegionMinor());
        areaEntity.setRegionPatch(dto.getRegionPatch());
        areaEntity.setLatitude(dto.getLatitude());
        areaEntity.setLongitude(dto.getLongitude());
        areaEntity = this.areaRepository.save(areaEntity);
        return new AreaDto(
                areaEntity.getId(),
                areaEntity.getRegionMajor(),
                areaEntity.getRegionMinor(),
                areaEntity.getRegionPatch(),
                areaEntity.getLatitude(),
                areaEntity.getLongitude()
        );
    }

    public AreaDto readArea(Long id) {
        Optional<AreaEntity> areaEntityOptional = this.areaRepository.findById(id);
        if (areaEntityOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new AreaDto(areaEntityOptional.get());
    }

    public Collection<AreaDto> readAllArea() {
        List<AreaDto> areaDtoList = new ArrayList<>();
        this.areaRepository.findAll().forEach(areaEntity -> areaDtoList.add(
                new AreaDto(areaEntity)
        ));
        return areaDtoList;
    }
}

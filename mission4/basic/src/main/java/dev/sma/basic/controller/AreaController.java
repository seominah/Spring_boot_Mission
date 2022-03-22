package dev.sma.basic.controller;

import dev.sma.basic.model.AreaDto;
import dev.sma.basic.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/area")
public class AreaController {
    private static final Logger logger = LoggerFactory.getLogger(AreaController.class);
    private final AreaService areaService;

    public AreaController(@Autowired AreaService areaService) {
        this.areaService = areaService;
    }

    @PostMapping
    public ResponseEntity<AreaDto> createArea (@RequestBody AreaDto dto) {
        return ResponseEntity.ok(this.areaService.createArea(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AreaDto> readArea (@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.areaService.readArea(id));
    }

    @GetMapping
    public ResponseEntity<Collection<AreaDto>> readAllArea () {
        return ResponseEntity.ok(this.areaService.readAllArea());
    }
}

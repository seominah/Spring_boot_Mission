package dev.sma.basic.controller;

import dev.sma.basic.model.ShopDto;
import dev.sma.basic.service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/shop")
public class ShopController {
    private static final Logger logger = LoggerFactory.getLogger(ShopController.class);
    private final ShopService shopService;

    public ShopController(@Autowired ShopService shopService) {
        this.shopService = shopService;
    }

    // shop
    @PostMapping
    public ResponseEntity<ShopDto> createShop(@RequestBody ShopDto shopDto) {
        return ResponseEntity.ok(this.shopService.createShop(shopDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShopDto> readShop(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.shopService.readShop(id));
    }

    @GetMapping
    public ResponseEntity<Collection<ShopDto>> readAllShop() {
        return ResponseEntity.ok(this.shopService.readAllShop());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateShop(@PathVariable("id") Long id,
                                        @RequestBody ShopDto shopDto) {
        this.shopService.updateShop(id, shopDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteShop(@PathVariable("id") Long id) {
        this.shopService.deleteShop(id);
        return ResponseEntity.noContent().build();
    }

    // shopPost

    // shopReview

}

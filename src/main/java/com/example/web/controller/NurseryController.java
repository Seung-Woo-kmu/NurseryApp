package com.example.web.controller;

import com.example.dto.GetNurseryListResponseDto;
import com.example.dto.NurseryDto;
import com.example.service.NurseryService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class NurseryController {

    private final NurseryService nurseryService;

    @GetMapping("/api/nursery")
    public ResponseEntity<GetNurseryListResponseDto> showNurseryList() {
        return ResponseEntity
                .ok()
                .body(new GetNurseryListResponseDto(nurseryService.findAll().stream().map(NurseryDto::new).toList()));
    }

}
package com.example.api.controller;

import com.example.dto.GetNurseryListResponseDto;
import com.example.dto.NurseryDto;
import com.example.service.NurseryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@Api(tags = {"어린이집 API"})
public class NurseryController {

    private final NurseryService nurseryService;

    @ApiOperation(value = "어린이집 목록 조회")
    @GetMapping("/api/nursery")
    public ResponseEntity<GetNurseryListResponseDto> showNurseryList() {
        return ResponseEntity
                .ok()
                .body(new GetNurseryListResponseDto(nurseryService.findAll().stream().map(NurseryDto::new).toList()));
    }

}
package com.mhyusuf.hr.controller;

import com.mhyusuf.hr.dto.TimeRecordDto;
import com.mhyusuf.hr.service.TimeRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/time-records")
@RequiredArgsConstructor
public class TimeRecordController {
    private final TimeRecordService timeRecordService;

    @PostMapping
    public ResponseEntity<TimeRecordDto.Response> create(@RequestBody @Valid TimeRecordDto.Request dto) {
        return ResponseEntity.ok(timeRecordService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeRecordDto.Response> getById(@PathVariable Long id) {
        return ResponseEntity.ok(timeRecordService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<TimeRecordDto.Response>> getAll() {
        return ResponseEntity.ok(timeRecordService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TimeRecordDto.Response> update(@PathVariable Long id, @RequestBody @Valid TimeRecordDto.Request dto) {
        return ResponseEntity.ok(timeRecordService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        timeRecordService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

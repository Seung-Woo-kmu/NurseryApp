package com.example.service;

import com.example.domain.nursery.Nursery;
import com.example.repository.NurseryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NurseryService {
    private final NurseryRepository nurseryRepository;

    public List<Nursery> findAll(){
        return nurseryRepository.findAll();
    }
    @Transactional
    public Long addNursery(Nursery nursery) {
        nurseryRepository.save(nursery);
        return nursery.getId();
    }
}

package com.convention_store.service.impl;

import com.convention_store.domain.Franchise;
import com.convention_store.dto.FranchiseDto;
import com.convention_store.repository.FranchiseRepository;
import com.convention_store.service.FranchiseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FranchiseServiceImpl implements FranchiseService {
    
    private final FranchiseRepository franchiseRepository;
    
    @Override
    public List<FranchiseDto> getAllFranchises() {
        return franchiseRepository.findAll().stream().map(FranchiseDto::from).toList();
    }
}

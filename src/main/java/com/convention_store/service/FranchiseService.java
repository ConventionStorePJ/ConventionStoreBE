package com.convention_store.service;

import com.convention_store.dto.FranchiseDto;
import java.util.List;
import org.springframework.stereotype.Service;

public interface FranchiseService {
    
    public List<FranchiseDto> getAllFranchises();
}

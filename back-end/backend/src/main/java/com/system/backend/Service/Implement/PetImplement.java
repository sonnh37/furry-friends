package com.system.backend.Service.Implement;

import com.system.backend.Repository.PetRepository;
import com.system.backend.Repository.UserRepository;
import com.system.backend.Service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetImplement implements PetService {
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private UserRepository userRepository;
}

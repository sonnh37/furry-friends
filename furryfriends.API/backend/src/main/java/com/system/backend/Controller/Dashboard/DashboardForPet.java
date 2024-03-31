package com.system.backend.Controller.Dashboard;

import com.system.backend.Dto.Pet.PetResponse;
import com.system.backend.Service.PetService;
import com.system.backend.util.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(Link.CORS)
@RequestMapping(Link.API_ROOT + Link.USER.STATISTIC)
public class DashboardForPet {
    @Autowired
    private PetService petService;
    @GetMapping(Link.USER.STATISTICCRUD.STATISTICPET)
    public Integer getAllPets_asc()
    {
        List<PetResponse> list = petService.getAllPets_asc();
        return list.size();
    }
}

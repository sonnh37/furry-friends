package com.system.backend.Dto.Pet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetRequest {
    private String pet_name;
    private Float pet_price;
    private String development_stage;
    private String type;
    @Lob
    private List<String> img_petList = new ArrayList<>();
    private String description;
    private String date;
    private String phone;
    private String title;
    private String address;
    private boolean status;
}

package com.mr2.zaiko.zaiko2.useCase;

import com.mr2.zaiko.zaiko2.domain.inhouse.equipment.Equipment;
import com.mr2.zaiko.zaiko2.domain.inhouse.equipment.EquipmentId;
import com.mr2.zaiko.zaiko2.domain.inhouse.equipment.EquipmentRepository;
import com.mr2.zaiko.zaiko2.domain.inhouse.equipment.Photo;

public class ImagePersistenceService {
    private EquipmentRepository repository;

    public ImagePersistenceService(EquipmentRepository repository) {
        this.repository = repository;
    }

    public void addPhoto(EquipmentId equipmentId, String fileName){
        Equipment equipment = repository.get(equipmentId);
        equipment.addPhoto(new Photo(fileName));
        repository.save(equipment);
    }
}

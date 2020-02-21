package com.mr2.zaiko.zaiko2.useCase;

import androidx.annotation.NonNull;

import com.mr2.zaiko.zaiko2.domain.inhouse.equipment.Equipment;
import com.mr2.zaiko.zaiko2.domain.inhouse.equipment.EquipmentId;
import com.mr2.zaiko.zaiko2.domain.inhouse.equipment.EquipmentRepository;
import com.mr2.zaiko.zaiko2.domain.inhouse.equipment.Keyword;
import com.mr2.zaiko.zaiko2.domain.inhouse.equipment.Photo;
import com.mr2.zaiko.zaiko2.domain.inhouse.orderSlip.OrderSlip;
import com.mr2.zaiko.zaiko2.domain.inhouse.orderSlip.OrderSlipRepository;
import com.mr2.zaiko.zaiko2.domain.inhouse.storageLocation.StorageLocation;
import com.mr2.zaiko.zaiko2.domain.inhouse.storageLocation.StorageLocationId;
import com.mr2.zaiko.zaiko2.domain.inhouse.storageLocation.StorageLocationRepository;
import com.mr2.zaiko.zaiko2.domain.inhouse.user.User;
import com.mr2.zaiko.zaiko2.domain.outside.commodity.CommodityId;

import java.util.List;

public class InventoryApplicationService {
    @NonNull private EquipmentRepository equipmentRepository;
    @NonNull private OrderSlipRepository orderSlipRepository;
    @NonNull private StorageLocationRepository storageLocationRepository;

    public InventoryApplicationService(@NonNull EquipmentRepository equipmentRepository, @NonNull OrderSlipRepository orderSlipRepository, @NonNull StorageLocationRepository storageLocationRepository) {
        this.equipmentRepository = equipmentRepository;
        this.orderSlipRepository = orderSlipRepository;
        this.storageLocationRepository = storageLocationRepository;
    }

    //getEquipmentList
    public List<Equipment> getEquipmentList(){
        return equipmentRepository.get();
    }

    public List<Equipment> getEquipmentList(Keyword keyword){
        return equipmentRepository.findByKeyword(keyword);
    }


    //カートに入れる throwIntoShoppingCart
    // --> 購入依頼を掛ける
    public boolean requestBuyCommodity(User loginUser, CommodityId commodityId, int quantity, String note){
        List<OrderSlip> slipList = orderSlipRepository.findUncompletedByUser(loginUser.id());
        OrderSlip orderSlip;
        if (0 >= slipList.size()){
            orderSlip = new OrderSlip(loginUser.id());
        }else {
            orderSlip = slipList.get(0); //とりあえず一つ目
        }

        orderSlip.addRequest(commodityId, quantity, orderSlip.userId(), note);
        return orderSlipRepository.save(orderSlip);
    }

    //出庫する
    public boolean pickOut(User loginUser, EquipmentId equipmentId, StorageLocationId storageLocationId, int quantity, String note){
        StorageLocation storageLocation = storageLocationRepository.get(storageLocationId);
        if (null == storageLocation) return false;
        try {
            storageLocation.pickOut(loginUser, equipmentId, quantity, note);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return false;
        }
        return storageLocationRepository.save(storageLocation);
    }

    //廃棄する
    public boolean discard(User loginUser, EquipmentId equipmentId, StorageLocationId storageLocationId, int quantity, String note){
        StorageLocation storageLocation = storageLocationRepository.get(storageLocationId);
        if (null == storageLocation) return false;
        try {
            storageLocation.warehousing(loginUser, equipmentId, quantity, note);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return false;
        }
        return storageLocationRepository.save(storageLocation);
    }

    //写真を登録する
    public boolean registerPhoto(EquipmentId equipmentId, String address){
        Equipment equipment = equipmentRepository.get(equipmentId);
        if (null == equipment) return false;
        equipment.addPhoto(new Photo(address));
        return equipmentRepository.save(equipment);
    }

    //主写真を変更する
    public boolean changePrimaryPhoto(EquipmentId equipmentId, String address){
        Equipment equipment = equipmentRepository.get(equipmentId);
        if (null == equipment) return false;
        List<Photo> photos = equipment.photos();
        for (Photo p: photos){
            if (p.address().equals(address)){
                equipment.insertPhotoToTop(p);
                return equipmentRepository.save(equipment);
            }
        }
        return false;
    }

    //写真を削除する
    public boolean deletePhoto(EquipmentId equipmentId, String address){
        Equipment equipment = equipmentRepository.get(equipmentId);
        if (null == equipment) return false;
        List<Photo> photos = equipment.photos();
        for (Photo p: photos){
            if (p.address().equals(address)){
                equipment.removePhoto(p);
                return equipmentRepository.save(equipment);
            }
        }
        return false;
    }

}

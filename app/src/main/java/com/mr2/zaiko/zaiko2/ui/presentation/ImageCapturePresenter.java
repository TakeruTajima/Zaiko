package com.mr2.zaiko.zaiko2.ui.presentation;

import androidx.annotation.NonNull;

import com.mr2.zaiko.zaiko2.domain.inhouse.equipment.EquipmentId;
import com.mr2.zaiko.zaiko2.ui.contractor.ContractImageCapture;
import com.mr2.zaiko.zaiko2.useCase.ImagePersistenceService;

public class ImageCapturePresenter implements ContractImageCapture.Presenter {
    private ContractImageCapture.View view;
    private ImagePersistenceService service;
    private EquipmentId targetEquipmentId;

    public ImageCapturePresenter(ImagePersistenceService service, EquipmentId targetEquipmentId) {
        this.service = service;
        this.targetEquipmentId = targetEquipmentId;
    }

    @Override
    public void onCreate(ContractImageCapture.View view) {
        this.view = view;
    }

    @Override
    public void onDestroy(@NonNull ContractImageCapture.View view) {
        this.view = null;
    }

    @Override
    public void onCaptureResult(String fileName) {
        service.addPhoto(targetEquipmentId, fileName);
    }
}

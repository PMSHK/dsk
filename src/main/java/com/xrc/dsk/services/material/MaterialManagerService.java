package com.xrc.dsk.services.material;

import com.xrc.dsk.connection.ConnectionService;
import com.xrc.dsk.dto.medicine.MaterialInfoDataDto;
import javafx.scene.control.Button;

public class MaterialManagerService {
    private final ConnectionService connectionService = new ConnectionService();

    public void addMaterial(MaterialInfoDataDto sourceMaterial, MaterialInfoDataDto targetMaterial) {
        if (sourceMaterial.getMaterialName() != null && !sourceMaterial.getMaterialName().isBlank() && targetMaterial.filled()) {
            connectionService.addNewMaterial(targetMaterial, sourceMaterial);
        }
    }

    public void deleteMaterial(MaterialInfoDataDto material) {
        if (material.filled()) {
            connectionService.deleteMaterial(material);
        }
    }

    public void updateMaterial(MaterialInfoDataDto material, String materialName, float materialDensity) {
        if (material.filled()) {
            connectionService.updateMaterial(material, materialName, materialDensity);
        }
    }
}

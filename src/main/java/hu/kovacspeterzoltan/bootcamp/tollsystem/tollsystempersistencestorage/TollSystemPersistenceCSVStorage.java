package hu.kovacspeterzoltan.bootcamp.tollsystem.tollsystempersistencestorage;

import hu.kovacspeterzoltan.bootcamp.tollsystem.tollsystempersistencestorage.storage.CSVStorageManager;
import hu.kovacspeterzoltan.bootcamp.tollsystem.tollsystempersistencestorage.parser.StorageParser;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.entity.MotorwayVignetteEntity;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.storage.MotorwayVignetteStorageInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TollSystemPersistenceCSVStorage implements MotorwayVignetteStorageInterface {
    private List<MotorwayVignetteEntity> motorwayVignetteEntities;
    private final StorageParser parser;
    private final CSVStorageManager csvManager;

    public TollSystemPersistenceCSVStorage() {
        parser = new StorageParser();
        csvManager = new CSVStorageManager("./motorwayVignetteStorage.csv");
    }

    @Override
    public List<MotorwayVignetteEntity> findByRegistrationNumber(String registrationNumber) {
        List<MotorwayVignetteEntity> motorwayVignettes = new ArrayList<>();
        loadCSV();
        motorwayVignetteEntities.forEach(entity -> {
            motorwayVignettes.add(entity);
        });
        return motorwayVignettes;
    }

    private void loadCSV() {
        List<String[]> csv = csvManager.load();
        motorwayVignetteEntities = parser.csvToMotorwayVignettes(csv);
    }

    private MotorwayVignetteEntity getLastMotorwayVignetteEntity() {
        MotorwayVignetteEntity mv = new MotorwayVignetteEntity();
        mv.setRegistrationNumber("AA-BB-123");
        mv.setVehicleCategory("d1");
        mv.setMotorwayVignetteType("yearly");
        mv.setPrice(10000);
        mv.setValidFrom(new Date(2021 - 1900, 0, 1));
        mv.setValidTo(new Date(2022 - 1900, 0, 31, 23, 59, 59));
        mv.setDateOfPurchase(new Date(2021 - 1900, 0, 10, 10, 25, 38));
        return mv;
    }

    private static MotorwayVignetteEntity getCurrentMotorwayVignetteEntity() {
        MotorwayVignetteEntity mv = new MotorwayVignetteEntity();
        mv.setRegistrationNumber("AA-BB-123");
        mv.setVehicleCategory("d1");
        mv.setMotorwayVignetteType("yearly");
        mv.setPrice(15000);
        mv.setValidFrom(new Date(2023 - 1900, 0, 1));
        mv.setValidTo(new Date(2024 - 1900, 0, 31, 23, 59, 59));
        mv.setDateOfPurchase(new Date(2023 - 1900, 0, 12, 15, 30, 52));
        return mv;
    }
}
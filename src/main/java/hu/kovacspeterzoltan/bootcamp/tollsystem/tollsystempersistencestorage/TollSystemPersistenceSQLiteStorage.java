package hu.kovacspeterzoltan.bootcamp.tollsystem.tollsystempersistencestorage;

import hu.kovacspeterzoltan.bootcamp.tollsystem.tollsystempersistencestorage.dto.MotorwayVignetteRecordDTO;
import hu.kovacspeterzoltan.bootcamp.tollsystem.tollsystempersistencestorage.parser.StorageParser;
import hu.kovacspeterzoltan.bootcamp.tollsystem.tollsystempersistencestorage.storage.SQLiteStorageManager;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.entity.MotorwayVignetteEntity;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.storage.MotorwayVignetteStorageInterface;

import java.util.ArrayList;
import java.util.List;

public class TollSystemPersistenceSQLiteStorage implements MotorwayVignetteStorageInterface {
    private final StorageParser parser;
    private final SQLiteStorageManager storageManager;

    public TollSystemPersistenceSQLiteStorage() {
        parser = new StorageParser();
        storageManager = new SQLiteStorageManager();
    }

    @Override
    public List<MotorwayVignetteEntity> findByRegistrationNumber(String registrationNumber) {
        List<MotorwayVignetteRecordDTO> motorwayVignetteRecordDTOs = storageManager.findByRegistrationNumber(registrationNumber);
        return parser.recordDTOListToEntityList(motorwayVignetteRecordDTOs);
    }
}
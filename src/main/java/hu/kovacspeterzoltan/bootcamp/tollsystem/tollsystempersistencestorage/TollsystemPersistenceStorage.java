package hu.kovacspeterzoltan.bootcamp.tollsystem.tollsystempersistencestorage;

import hu.kovacspeterzoltan.bootcamp.tollsystemapp.entity.MotorwayVignetteEntity;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.storage.MotorwayVignetteStorageInterface;

import java.util.List;

public class TollsystemPersistenceStorage implements MotorwayVignetteStorageInterface {
    @Override
    public List<MotorwayVignetteEntity> findByRegistrationNumber(String s) {
        return null;
    }
}
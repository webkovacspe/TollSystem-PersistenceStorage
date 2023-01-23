package hu.kovacspeterzoltan.bootcamp.tollsystem.tollsystempersistencestorage;

import hu.kovacspeterzoltan.bootcamp.tollsystemapp.entity.MotorwayVignetteEntity;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.storage.MotorwayVignetteStorageInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TollSystemPersistenceStorage implements MotorwayVignetteStorageInterface {
    @Override
    public List<MotorwayVignetteEntity> findByRegistrationNumber(String registrationNumber) {
        MotorwayVignetteEntity lastMV = getLastMotorwayVignetteEntity();
        MotorwayVignetteEntity currentMV = getCurrentMotorwayVignetteEntity();

        List<MotorwayVignetteEntity> motorwayVignettes = new ArrayList<>();
        motorwayVignettes.add(lastMV);
        motorwayVignettes.add(currentMV);
        return motorwayVignettes;
    }

    private MotorwayVignetteEntity getLastMotorwayVignetteEntity() {
        MotorwayVignetteEntity mv = new MotorwayVignetteEntity();
        mv.setRegistrationNumber("AA-BB-123");
        mv.setVehicleCategory("d1");
        mv.setMotorwayVignetteType("yearly");
        mv.setPrice(10000);
        mv.setValidFrom(new Date("2021.01.01 0:00"));
        mv.setValidTo(new Date("2022.01.31 23:59"));
        mv.setDateOfPurchase(new Date("2021.01.10 10:25"));
        return mv;
    }

    private static MotorwayVignetteEntity getCurrentMotorwayVignetteEntity() {
        MotorwayVignetteEntity mv = new MotorwayVignetteEntity();
        mv.setRegistrationNumber("AA-BB-123");
        mv.setVehicleCategory("d1");
        mv.setMotorwayVignetteType("yearly");
        mv.setPrice(15000);
        mv.setValidFrom(new Date("2023.01.01 0:00"));
        mv.setValidTo(new Date("2024.01.31 23:59"));
        mv.setDateOfPurchase(new Date("2023.01.12 15:30"));
        return mv;
    }
}
package hu.kovacspeterzoltan.bootcamp.tollsystem.tollsystempersistencestorage.parser;

import hu.kovacspeterzoltan.bootcamp.tollsystemapp.entity.MotorwayVignetteEntity;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.entity.VehicleEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StorageParser {
    SimpleDateFormat formatter;

    public StorageParser() {
        formatter = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
    }

    public List<MotorwayVignetteEntity> csvToMotorwayVignettes(List<String[]> csv) {
        List<MotorwayVignetteEntity> motorwayVignetteEntities = new ArrayList<>();
        csv.forEach(line -> {
            MotorwayVignetteEntity mv = csvLineToMotorwayVignetteEntity(line);
            motorwayVignetteEntities.add(mv);
        });
        return motorwayVignetteEntities;
    }

    public MotorwayVignetteEntity csvLineToMotorwayVignetteEntity(String[] vehicleColumn) {
        MotorwayVignetteEntity v = new MotorwayVignetteEntity();
        v.registrationNumber = parseString(vehicleColumn[0]);
        v.vehicleCategory = parseString(vehicleColumn[1]);
        v.motorwayVignetteType = parseString(vehicleColumn[2]);
        v.price = Float.parseFloat(vehicleColumn[3]);
        v.validFrom = dateStringToDate(vehicleColumn[4]);
        v.validTo = dateStringToDate(vehicleColumn[5]);
        v.dateOfPurchase = dateStringToDate(vehicleColumn[6]);
        return v;
    }

    private Date dateStringToDate(String dateString) {
        Date d = null;
        try {
            d = formatter.parse(parseString(dateString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    private String parseString(String s) {
        return (Objects.equals(s.trim(), "") ? null : s);
    }
}

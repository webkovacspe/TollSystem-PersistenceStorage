package hu.kovacspeterzoltan.bootcamp.tollsystem.tollsystempersistencestorage.parser;

import hu.kovacspeterzoltan.bootcamp.tollsystem.tollsystempersistencestorage.dto.MotorwayVignetteRecordDTO;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.entity.MotorwayVignetteEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StorageParser {
    SimpleDateFormat formatter;

    public StorageParser() {
        formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
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
        MotorwayVignetteEntity e = new MotorwayVignetteEntity();
        e.registrationNumber = parseString(vehicleColumn[0]);
        e.vehicleCategory = parseString(vehicleColumn[1]);
        e.motorwayVignetteType = parseString(vehicleColumn[2]);
        e.price = Float.parseFloat(vehicleColumn[3]);
        e.validFrom = dateStringToDate(vehicleColumn[4]);
        e.validTo = dateStringToDate(vehicleColumn[5]);
        e.dateOfPurchase = dateStringToDate(vehicleColumn[6]);
        return e;
    }

    public MotorwayVignetteRecordDTO resultSetToMotorwayVignetteDTO(ResultSet rs) {
        MotorwayVignetteRecordDTO dto = new MotorwayVignetteRecordDTO();
        try {
            dto.registrationNumber = rs.getString("registrationNumber");
            dto.vehicleCategory = rs.getString("vehicleCategory");
            dto.motorwayVignetteType = rs.getString("motorwayVignetteType");
            dto.price = rs.getFloat("price");
            dto.validFrom = dateStringToDate(rs.getString("validFrom"));
            dto.validTo = dateStringToDate(rs.getString("validTo"));
            dto.dateOfPurchase = dateStringToDate(rs.getString("dateOfPurchase"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dto;
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

    public List<MotorwayVignetteEntity> recordDTOListToEntityList(List<MotorwayVignetteRecordDTO> recordDTOList) {
        List<MotorwayVignetteEntity> motorwayVignettes = new ArrayList<>();
        if (recordDTOList != null) {
            recordDTOList.forEach(dto -> {
                motorwayVignettes.add(recordDTOToEntity(dto));
            });
        }
        return motorwayVignettes;
    }

    private MotorwayVignetteEntity recordDTOToEntity(MotorwayVignetteRecordDTO dto) {
        MotorwayVignetteEntity e = new MotorwayVignetteEntity();
        e.registrationNumber = dto.registrationNumber;
        e.vehicleCategory = dto.vehicleCategory;
        e.motorwayVignetteType = dto.motorwayVignetteType;
        e.price = dto.price;
        e.validFrom = dto.validFrom;
        e.validTo = dto.validTo;
        e.dateOfPurchase = dto.dateOfPurchase;
        return e;
    }
}

package com.fis.java.finaltest.utils;

import com.fis.java.finaltest.constant.MyConstant;
import com.fis.java.finaltest.dto.CriminalCaseDto;
import com.fis.java.finaltest.entity.CriminalCase;

import java.time.LocalDateTime;

public class CriminalCaseUtils {
    public static CriminalCase convertFromDto(CriminalCaseDto criminalCaseDto){
        CriminalCase criminalCase = new CriminalCase();
        criminalCase.setNumber(criminalCaseDto.getNumber());
        criminalCase.setType(criminalCaseDto.getType());
        criminalCase.setShortDescription(criminalCaseDto.getShortDescription());
        criminalCase.setDetailedDescription(criminalCaseDto.getDetailedDescription());
        criminalCase.setStatus(criminalCaseDto.getStatus());
        criminalCase.setNotes(criminalCaseDto.getNotes());
        return criminalCase;
    }
}

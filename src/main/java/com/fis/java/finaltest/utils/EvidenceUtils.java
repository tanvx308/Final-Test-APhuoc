package com.fis.java.finaltest.utils;

import com.fis.java.finaltest.constant.MyConstant;
import com.fis.java.finaltest.dto.EvidenceDto;
import com.fis.java.finaltest.entity.Evidence;

import java.time.LocalDateTime;

public class EvidenceUtils {
    public static Evidence convertFromDto(EvidenceDto evidenceDto){
        Evidence evidence = new Evidence();
        evidence.setNumber(evidenceDto.getNumber());
        evidence.setItemName(evidenceDto.getItemName());
        evidence.setNotes(evidenceDto.getNotes());
        evidence.setArchived(evidenceDto.getArchived());
        return evidence;
    }
}

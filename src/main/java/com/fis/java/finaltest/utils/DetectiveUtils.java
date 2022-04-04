package com.fis.java.finaltest.utils;

import com.fis.java.finaltest.constant.MyConstant;
import com.fis.java.finaltest.dto.DetectiveDto;
import com.fis.java.finaltest.entity.Detective;
import com.fis.java.finaltest.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class DetectiveUtils {

    public static Detective convertFromDto(DetectiveDto detectiveDto){
        Detective detective = new Detective();
        detective.setBadgeNumber(detectiveDto.getBadgeNumber());
        detective.setRanking(detectiveDto.getRanking());
        detective.setArmed(detectiveDto.getArmed());
        detective.setStatus(detectiveDto.getStatus());
        detective.setPerson(detectiveDto.getPerson());
        return detective;
    }
}

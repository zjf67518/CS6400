package com.cs6400.carshop.controller;

import com.cs6400.carshop.utils.converter.SearchInfoConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class VehicleController {

    @ResponseBody
    @PostMapping("/SaveSearchInfo")
    public Map saveSearchInfo(SearchInfoConverter searchInfo){
        Map<String, Object> map = new HashMap<>();
        log.info("searchInfo:{}", searchInfo.toString());
        map.put("searchInfo", searchInfo);
        return map;
    }
}

package com.example.jennie.airquality.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.jennie.airquality.service.AirQualityService;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class AirQualityController {

    //생성자 주입 방식으로 서비스 객체 생성
    private final AirQualityService airQualityService;

    public AirQualityController(AirQualityService airQualityService) {
        this.airQualityService = airQualityService;
    }




    @GetMapping("/basic")
    public String basic(@RequestParam(defaultValue = "서울") String sidoName ,Model model) throws IOException {
        String result = airQualityService.getAirQualityDataBasic(sidoName);
        model.addAttribute("airQualityData", result);
        return "airQuality";

    }
}

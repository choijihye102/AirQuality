package com.example.jennie.airquality;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AirQualityControllerTest {

    // MVC 테스트를 위한 가짜 객체 생성
    @Autowired private MockMvc mockMvc;

    // 올바른 JSON 데이터인지 확인하기 위해
    // ObjectMapper 객체 선언
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("testAirQualityController")
    public void testAirQualityController() throws Exception {
        //테스트 실행
        MvcResult result = mockMvc.perform(get("/basic"))
                .andExpect(status().isOk()) // basic 앤드포인트 접속 여부
                .andExpect(view().name("airQuality")) // 올바른 뷰가 리턴되는지 여부
                .andExpect(model().attributeExists("airQualityData")) // 모델에 객체가 존재하는지 여부 확인
                .andReturn();

        // 모델에서 airQualityData 추출
        String json = result.getModelAndView().getModel()
                    .get("airQualityData").toString();

        System.out.println(json);

        // JSON 유효성 검사
        assertDoesNotThrow(()-> mapper.readTree(json));
    }

}



















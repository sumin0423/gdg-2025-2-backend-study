package com.example.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * 통합 테스트 예시
 * - 컨텍스트 로딩 확인
 * - Member 도메인 CRUD 흐름 간단 검증
 *
 * 실행 위치:
 * src/test/java/com/example/shop/ShopApplicationTest.java
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ShopApplicationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("스프링 부트 컨텍스트가 정상적으로 로드된다")
    void contextLoads() {
        // 단순 구동 테스트 (예외 없이 로드되면 성공)
    }

    @Test
    @DisplayName("Member CRUD 플로우가 정상 동작한다")
    void memberCrudFlow() throws Exception {
        // 1) 회원 생성 (POST /members)
        Map<String, Object> create = new HashMap<>();
        create.put("loginId", "hong");
        create.put("password", "pw1234");
        create.put("phoneNumber", "010-1234-5678");
        create.put("address", "서울시 마포구");

        MvcResult createResult = mockMvc.perform(post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(create)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", startsWith("/members/")))
                .andReturn();

        // 생성된 Location 헤더에서 ID 추출
        String location = createResult.getResponse().getHeader("Location");
        assertThat(location).isNotBlank();
        Long createdId = Long.valueOf(location.substring(location.lastIndexOf('/') + 1));

        // 2) 단건 조회 (GET /members/{id})
        mockMvc.perform(get("/members/{id}", createdId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(createdId.intValue())))
                .andExpect(jsonPath("$.loginId", is("hong")))
                .andExpect(jsonPath("$.phoneNumber", is("010-1234-5678")))
                .andExpect(jsonPath("$.address", is("서울시 마포구")))
                .andExpect(jsonPath("$.point", is(0)));

        // 3) 전체 조회 (GET /members)
        mockMvc.perform(get("/members"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))));

        // 4) 수정 (PATCH /members/{id})
        Map<String, Object> update = new HashMap<>();
        update.put("password", "newpw");
        update.put("phoneNumber", "010-0000-0000");
        update.put("address", "서울시 강남구");

        mockMvc.perform(patch("/members/{id}", createdId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isOk());

        // 수정 확인
        mockMvc.perform(get("/members/{id}", createdId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phoneNumber", is("010-0000-0000")))
                .andExpect(jsonPath("$.address", is("서울시 강남구")));

        // 5) 삭제 (DELETE /members/{id})
        mockMvc.perform(delete("/members/{id}", createdId))
                .andExpect(status().isNoContent());

        // 삭제 후 조회 시 404 기대 (컨트롤러가 notFound로 매핑하므로)
        mockMvc.perform(get("/members/{id}", createdId))
                .andExpect(status().isNotFound());
    }
}

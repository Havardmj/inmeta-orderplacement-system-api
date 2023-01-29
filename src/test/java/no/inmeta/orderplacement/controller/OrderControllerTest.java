package no.inmeta.orderplacement.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import no.inmeta.orderplacement.TestData;
import no.inmeta.orderplacement.order.AddOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles({"local", "testdata"})
@DirtiesContext
public class OrderControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void contextLoads() throws Exception {
        assertThat(OrderController.class).isNotNull();
    }

    private String sendRequest(
            MockHttpServletRequestBuilder request,
            Object content,
            ResultMatcher status
    ) throws Exception {
        if (content != null) {
            request.content(objectMapper.writeValueAsString(content));
        }
        return mockMvc.perform(
                        request
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status)
                .andReturn()
                .getResponse().getContentAsString(StandardCharsets.UTF_8);

    }

    private String sendRequest(
            MockHttpServletRequestBuilder request,
            ResultMatcher status
    ) throws Exception {
        return sendRequest(request,null, status);
    }

    private void verifyGetRequest(
            MockHttpServletRequestBuilder request,
            ResultMatcher resultMatcher
    ) throws Exception {
        mockMvc.perform(
                request.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
        ).andExpect(resultMatcher);
    }

    @Test
    public void verify_that_add_order_request() throws Exception {
        AddOrder addOrder = TestData.aAddDetailTestCaseWithPredefinedConsultantId(TestData.A_CONSULTANT_TEST_ID);
        sendRequest(post("/register-order"), addOrder, status().isOk());
    }

    @Test
    public void edit_order_request() throws Exception {
        sendRequest(get("/find-all-order-consultant"), /* TODO: ADD id obj */ status().isOk());

    }


}

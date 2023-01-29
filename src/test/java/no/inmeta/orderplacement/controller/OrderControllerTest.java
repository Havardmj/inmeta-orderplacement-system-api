package no.inmeta.orderplacement.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import no.inmeta.orderplacement.Consultant.Consultant;
import no.inmeta.orderplacement.Consultant.FindConsultant;
import no.inmeta.orderplacement.TestData;
import no.inmeta.orderplacement.order.AddOrder;
import no.inmeta.orderplacement.order.Order;
import no.inmeta.orderplacement.repository.ConsultantRepository;
import no.inmeta.orderplacement.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import com.fasterxml.jackson.core.type.TypeReference;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

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

    Consultant consultant;

    @Autowired
    private ConsultantRepository consultantRepository;

    @Autowired
    private OrderRepository orderRepository;


  public void addOrderConsultantAndOrders() {
        final UUID aConsultantTestId = UUID.randomUUID();
        final String PASSWORD = "password123";
        final String EMAIL_ADDRESS = "test@testRepository.no";
        final String FIRST_NAME = "Ivar";
        final String LAST_NAME = "Åsen";
        consultant = new Consultant(
                aConsultantTestId,
                FIRST_NAME,
                LAST_NAME,
                EMAIL_ADDRESS,
                PASSWORD,
                "+4744556677"
        );
        consultantRepository.save(consultant);
        orderRepository.save(TestData.oneConsultantTestOrderWithPredefinedConsultantId(aConsultantTestId));
        orderRepository.save(TestData.oneConsultantTestOrderWithPredefinedConsultantId(aConsultantTestId));
        orderRepository.save(TestData.oneConsultantTestOrderWithPredefinedConsultantId(aConsultantTestId));
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

    private HttpHeaders addHttpHeaders(Consultant consultant){
        HttpHeaders headers = new HttpHeaders();
        headers.add("email-address", consultant.getEmailAddress());
        headers.add("password", consultant.getConsultantPassword());
        return headers;
    }

    @Test
    public void contextLoads() throws Exception {
        assertThat(OrderController.class).isNotNull();
    }

    @Test
    public void verify_that_add_order_request() throws Exception {
        addOrderConsultantAndOrders();
        AddOrder addOrder = TestData.aAddDetailTestCaseWithPredefinedConsultantId(TestData.A_CONSULTANT_TEST_ID);
        HttpHeaders httpHeaders = addHttpHeaders(consultant);
        sendRequest(post("/order/register-order").headers(httpHeaders), addOrder, status().isOk());
    }

    @Test
    public void get_all_order_consultant() throws Exception {
        addOrderConsultantAndOrders();
        HttpHeaders httpHeaders = addHttpHeaders(consultant);
        FindConsultant findConsultant = new FindConsultant(consultant.getId().toString());
        String response = sendRequest(
                get("/order/find-all-order-consultant").headers(httpHeaders),
                findConsultant,
                status().isOk()
        );
        List<Order> orders = objectMapper.readValue(response, new TypeReference<List<Order>>(){});

        Assertions.assertEquals(orders.size(), 3);

    }


}

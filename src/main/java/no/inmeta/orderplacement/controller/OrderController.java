package no.inmeta.orderplacement.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.inmeta.orderplacement.Consultant.FindConsultant;
import no.inmeta.orderplacement.dto.LoginService;
import no.inmeta.orderplacement.dto.OrderDto;
import no.inmeta.orderplacement.order.AddOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/order")
public class OrderController {

    private final OrderDto orderDto;

    private final LoginService loginService;

    private final ObjectMapper objectMapper;


    @GetMapping(value = "/is-alive")
    public ResponseEntity<?> controllerIsAlive() { return new ResponseEntity<>(HttpStatus.valueOf(200)); }

    @Transactional
    @PostMapping("/register-order")
    public ResponseEntity<?> registerAOrderTransaction(
            @RequestBody AddOrder addOrder,
            @RequestHeader(value = "email-address", required = true) String loginEmail,
            @RequestHeader(value = "password", required = true) String loginPassword
    ) {
        loginService.findSalesRepresentative(loginEmail, loginPassword);
        orderDto.addConsultantOrder(addOrder);
        return new ResponseEntity<>(HttpStatus.valueOf(200));
    }

    @GetMapping("/get-order")
    public ResponseEntity<?> getOrder(@RequestBody String a) {
        return new ResponseEntity<>(HttpStatus.valueOf(200));
    }

    @GetMapping("/find-all-order-consultant")
    public ResponseEntity<?> getAllOrderConsultant(
            @RequestBody FindConsultant findConsultant,
            @RequestHeader(value = "email-address", required = false) String loginEmail,
            @RequestHeader(value = "password", required = false) String loginPassword
    ) {
        log.info("test");
        loginService.findSalesRepresentative(loginEmail, loginPassword);
        return new ResponseEntity<>(
                orderDto.findAllConsultantOrders(
                        UUID.fromString(
                                findConsultant.getConsultantId()
                        )
                ),
                HttpStatus.valueOf(200)
        );
    }
}

package no.inmeta.orderplacement.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.inmeta.orderplacement.consultantOrderDto.ConsultantOrderDto;
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

    private final ConsultantOrderDto consultantOrderPlacement;

    private final ObjectMapper objectMapper;


    @GetMapping(value = "/is-alive")
    public ResponseEntity<?> controllerIsAlive() { return new ResponseEntity<>(HttpStatus.valueOf(200)); }

    @Transactional
    @PostMapping("/register-order")
    public ResponseEntity<?> registerAOrderTransaction(@RequestBody AddOrder addOrder) {
        consultantOrderPlacement.addConsultantOrder(addOrder);
        return new ResponseEntity<>(HttpStatus.valueOf(200));
    }

    @GetMapping("/get-order")
    public ResponseEntity<?> getOrder(@RequestBody String a) {
        return new ResponseEntity<>(HttpStatus.valueOf(200));
    }

    @GetMapping("/find-all-order-consultant")
    public ResponseEntity<?> getAllOrderConsultant(@RequestBody UUID consultantId) {
        return new ResponseEntity<>(
                consultantOrderPlacement.findAllConsultantOrders(consultantId), HttpStatus.valueOf(200)
        );
    }
}

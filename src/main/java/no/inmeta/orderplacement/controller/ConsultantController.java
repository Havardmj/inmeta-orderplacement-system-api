package no.inmeta.orderplacement.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.inmeta.orderplacement.Consultant.AddConsultant;
import no.inmeta.orderplacement.dto.ConsultantDto;
import no.inmeta.orderplacement.dto.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/consultant")
public class ConsultantController {

    private final ConsultantDto consultantDto;

    private final LoginService loginService;


    @Transactional
    @PostMapping("/add-consultant")
    public ResponseEntity<?> addConsultant(@RequestBody AddConsultant addConsultant) {
        consultantDto.addConsultant(addConsultant);
        return new ResponseEntity<>(HttpStatus.valueOf(200));
    }

    @GetMapping("/login-consultant")
    public ResponseEntity<?> loginConsultant(
            @RequestHeader(value = "email-address", required = false) String loginEmail,
            @RequestHeader(value = "password", required = false) String loginPassword
    ) throws JsonProcessingException {
        String json = loginService.loginConsultant(loginEmail, loginPassword);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }
}

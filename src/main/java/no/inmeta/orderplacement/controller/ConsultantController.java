package no.inmeta.orderplacement.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.inmeta.orderplacement.Consultant.AddConsultant;
import no.inmeta.orderplacement.dto.ConsultantDto;
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


    @Transactional
    @PostMapping("/add-consultant")
    public ResponseEntity<?> addConsultant(@RequestBody AddConsultant addConsultant) {
        consultantDto.addConsultant(addConsultant);
        return new ResponseEntity<>(HttpStatus.valueOf(200));
    }
}

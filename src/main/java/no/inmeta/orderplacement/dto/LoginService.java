package no.inmeta.orderplacement.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.inmeta.orderplacement.Consultant.Consultant;
import no.inmeta.orderplacement.Consultant.LoginConsultant;
import no.inmeta.orderplacement.repository.ConsultantRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final ConsultantRepository consultantRepository;

    private final ObjectMapper objectMapper;


    public String loginConsultant(String emailAddress, String consultantPassword) throws JsonProcessingException {
        Consultant user = findSalesRepresentative(emailAddress, consultantPassword);
        return objectMapper.writeValueAsString(
                LoginConsultant.builder()
                        .id(user.getId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .emailAddress(user.getEmailAddress())
                        .phoneNumber(user.getPhoneNumber()).build()
        );
    }

    public Consultant findSalesRepresentative(String emailAddress, String consultantPassword) {
        Consultant consultant = consultantRepository.findConsultantByEmailAddressAndConsultantPassword(
                emailAddress,
                consultantPassword
        );
        assert consultant != null;
        return consultant;
    }
}

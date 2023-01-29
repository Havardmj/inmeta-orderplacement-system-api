package no.inmeta.orderplacement.dto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.inmeta.orderplacement.Consultant.Consultant;
import no.inmeta.orderplacement.repository.ConsultantRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final ConsultantRepository consultantRepository;

    public Consultant findSalesRepresentative(String emailAddress, String consultantPassword) {
        Consultant consultant = consultantRepository.findConsultantByEmailAddressAndConsultantPassword(
                emailAddress,
                consultantPassword
        );
        assert consultant != null;
        return consultant;
    }
}

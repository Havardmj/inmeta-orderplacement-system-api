package no.inmeta.orderplacement.dto;


import lombok.RequiredArgsConstructor;
import no.inmeta.orderplacement.Consultant.AddConsultant;
import no.inmeta.orderplacement.Consultant.Consultant;
import no.inmeta.orderplacement.repository.ConsultantRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsultantDto {

    private final ConsultantRepository consultantRepository;

    public void addConsultant(AddConsultant addConsultant) {
        Consultant consultant = new Consultant(
                addConsultant.getFirstName(),
                addConsultant.getLastName(),
                addConsultant.getEmailAddress(),
                addConsultant.getConsultantPassword(),
                addConsultant.getPhoneNumber()
        );
        consultantRepository.save(consultant);
    }

}

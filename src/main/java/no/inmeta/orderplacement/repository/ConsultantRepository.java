package no.inmeta.orderplacement.repository;

import no.inmeta.orderplacement.Consultant.Consultant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ConsultantRepository extends JpaRepository<Consultant, UUID>, JpaSpecificationExecutor {

    Consultant save(Consultant consultant);

    Consultant findConsultantByEmailAddressAndConsultantPassword(String emailAddress, String password);


}

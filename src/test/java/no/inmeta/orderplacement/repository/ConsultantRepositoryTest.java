package no.inmeta.orderplacement.repository;

import no.inmeta.orderplacement.Consultant.Consultant;
import no.inmeta.orderplacement.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles({"local", "testdata"})
@DirtiesContext
public class ConsultantRepositoryTest {

    @Autowired
    private ConsultantRepository consultantRepository;

    @Test
    public void confim_add_new_consultant() {
        Consultant consultant = TestData.addConsultant();
        Assertions.assertDoesNotThrow(() -> {
            consultantRepository.save(consultant);
        });
    }

    @Test
    public void confirm_add_new_consultant_with_predefined_id() {
        Consultant consultant = TestData.addConsultantPredefinedConsultantId(TestData.A_CONSULTANT_TEST_ID);
        Assertions.assertDoesNotThrow(() -> {
            consultantRepository.save(consultant);
        });
    }

    @Test
    public void find_consultant_by_id_and_password() {
        final String PASSWORD = "password123";
        final String EMAIL_ADDRESS = "test@testRepository.no";
        final String FIRST_NAME = "Ivar";
        final String LAST_NAME = "Åsen";
        Consultant consultant = new Consultant(
                TestData.A_CONSULTANT_TEST_ID,
                FIRST_NAME,
                LAST_NAME,
                EMAIL_ADDRESS,
                PASSWORD,
                "+4744556677"
        );
        consultantRepository.save(consultant);
        Consultant consultantFound = consultantRepository.findConsultantByEmailAddressAndConsultantPassword(
                EMAIL_ADDRESS,
                PASSWORD
        );
        Assertions.assertEquals(consultantFound.getFirstName(), FIRST_NAME);
        Assertions.assertEquals(consultantFound.getLastName(), LAST_NAME);
        Assertions.assertEquals(consultantFound.getEmailAddress(), EMAIL_ADDRESS);
    }

}

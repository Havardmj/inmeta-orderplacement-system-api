package no.inmeta.orderplacement;

import lombok.AllArgsConstructor;
import lombok.Data;
import no.inmeta.orderplacement.order.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
public class TestData {

    public static final UUID A_CONSULTANT_TEST_ID = UUID.randomUUID();

    public static AddOrder aAddDetailTestCase()  {
        return aAddDetailTestCaseWithPredefinedConsultantId(A_CONSULTANT_TEST_ID);
    }

    public static AddOrder aAddDetailTestCaseWithPredefinedConsultantId(UUID consultantId) {
      return new AddOrder(
              consultantId,
              "mr.salesGuy",
              LocalDate.now().plusMonths(2),
              TestData.addOrderDetailTestCase()
      );
    }

    public static AddOrderDetail addOrderDetailTestCase() {
        return new AddOrderDetail(
                "Ola",
                "Norman",
                "+4744449999",
                "newCostumer@testcase.com",
                "Storgata 1",
                "Storgata 10",
                TypeOfService.MOVING
        );
    }

    public static Order oneConsultantTestOrder() {
        return oneConsultantTestOrderWithPredefinedConsultantId(A_CONSULTANT_TEST_ID);
    }

    public static Order oneConsultantTestOrderWithPredefinedConsultantId(UUID consultantId) {
        return new Order(
                consultantId,
                "",
                TestData.oneOrderDetailsTestUser(),
                LocalDate.now().plusMonths(1)
        );
    }

    public static OrderDetatail oneOrderDetailsTestUser() {
        return new OrderDetatail(
                "Kari",
                "Jensen",
                "333 44 555",
                "test@testclass.com",
                "storgata 4",
                "storgata 40", TypeOfService.CLEANING
        );
    }
}

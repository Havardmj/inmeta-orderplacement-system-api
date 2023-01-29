package no.inmeta.orderplacement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.inmeta.orderplacement.order.Order;
import no.inmeta.orderplacement.repository.ConsultantRepository;
import no.inmeta.orderplacement.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@Profile("testdata")
public class LoadingTestData implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ConsultantRepository consultantRepository;

    public void loadTestData() {
        loadOrderTestData();
    }

    public void loadOrderTestData() {
        orderRepository.save(TestData.oneConsultantTestOrder());
        orderRepository.save(new Order(TestData.aAddDetailTestCase()));
    }

    public void loadConsultantData() {
        consultantRepository.save(TestData.addConsultantPredefinedConsultantId(TestData.A_CONSULTANT_TEST_ID));
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        event.getApplicationContext().getBean(LoadingTestData.class).loadTestData();

    }
}


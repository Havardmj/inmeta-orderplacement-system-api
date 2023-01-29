package no.inmeta.orderplacement;

import org.springframework.boot.builder.SpringApplicationBuilder;

public class LocalOrderplacementApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(OrderplacementApplication.class)
                .profiles("local", "testdata")
                .build()
                .run();
    }
}
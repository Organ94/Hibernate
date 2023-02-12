package com.example.hibernate;

import com.example.hibernate.entity.enums.Customers;
import com.example.hibernate.entity.enums.Orders;
import com.example.hibernate.entity.enums.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class CommandLineApp implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var random = new Random();

        var names = List.of("Ivan", "Petr", "Boris", "Olga", "Alex");
        var surname = List.of("Ivanov(a)", "Petrov(a)", "Sidorov(a)", "Smirnov(a)");
        var products = List.of("phone", "cup", "flower", "steak");
        var numberPhone = 0000;

        IntStream.range(0, 100)
                .forEach(i -> {
                    var customers = Customers.builder()
                            .name(names.get(random.nextInt(names.size())))
                            .surname(surname.get(random.nextInt(surname.size())))
                            .age(random.nextInt(18, 45))
                            .phoneNumber("+7(999)123 " + random.nextInt(1000, 10000))
                            .build();

                    entityManager.persist(customers);
                });

        IntStream.range(0, 100)
                .forEach(i -> {
                    var orders = Orders.builder()
                            .date(new Date())
                            .customerId(i)
                            .product(Product.builder()
                                    .productName(products.get(random.nextInt(products.size())))
                                    .amount(random.nextInt(1, 3))
                                    .build())
                            .build();
                    entityManager.persist(orders);
                });

    }
}

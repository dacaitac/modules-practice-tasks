package com.epam.training.app;

import com.epam.training.bank.Bank;
import com.epam.training.dto.*;
import com.epam.training.service.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        // Cargar las implementaciones de Bank y Service usando ServiceLoader
        ServiceLoader<Bank> bankLoader = ServiceLoader.load(Bank.class);
        ServiceLoader<Service> serviceLoader = ServiceLoader.load(Service.class);

        Bank bank = bankLoader.findFirst().orElseThrow(() -> new RuntimeException("Bank implementation not found"));
        Service service = serviceLoader.findFirst().orElseThrow(() -> new RuntimeException("Service implementation not found"));

        // Crear un usuario
        User user = new User("Pepito", "Galletas", LocalDate.of(1990, 5, 15));
        System.out.println("User created: " + user.getName() + " " + user.getSurname());

        // Crear una tarjeta bancaria
        BankCard card = bank.createBankCard(user, BankCardType.CREDIT);
        System.out.println("BankCard created: " + card.getNumber());

        // Suscribir la tarjeta
        service.subscribe(card);
        System.out.println("Subscription added for card: " + card.getNumber());

        // Buscar la suscripción por número de tarjeta
        Optional<Subscription> subscription = service.getSubscriptionByBankCardNumber(card.getNumber());
        subscription.ifPresentOrElse(
                sub -> System.out.println("Subscription found for card: " + sub.getBankcardNumber()),
                () -> System.out.println("No subscription found.")
        );

        // Obtener todos los usuarios
        List<User> users = service.getAllUsers();
        System.out.println("All users count: " + users.size());

        // Calcular el promedio de edad de los usuarios
        double averageAge = service.getAverageUsersAge();
        System.out.println("Average users age: " + averageAge);

        // Verificar si el usuario es mayor de 18 años
        boolean isPayable = Service.isPayableUser(user);
        System.out.println("Is user payable (over 18)? " + isPayable);

        // Filtrar suscripciones según una condición (ejemplo: suscripciones creadas hoy)
        Predicate<Subscription> createdToday = sub -> ChronoUnit.DAYS.between(sub.getStartDate(), LocalDate.now()) == 0;
        List<Subscription> todaySubscriptions = service.getAllSubscriptionsByCondition(createdToday);
        System.out.println("Subscriptions created today: " + todaySubscriptions.size());
    }
}
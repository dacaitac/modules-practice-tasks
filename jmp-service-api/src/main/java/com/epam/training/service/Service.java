package com.epam.training.service;

import com.epam.training.dto.BankCard;
import com.epam.training.dto.Subscription;
import com.epam.training.dto.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Service {
    void subscribe(BankCard bankCard);
    Optional<Subscription> getSubscriptionByBankCardNumber(String number);
    List<User> getAllUsers();

    default double getAverageUsersAge() {
        return getAllUsers().stream()
                .mapToLong(user -> ChronoUnit.YEARS.between(user.getBirthday(), LocalDate.now()))
                .average()
                .orElse(0);
    }

    static boolean isPayableUser(User user) {
        return ChronoUnit.YEARS.between(user.getBirthday(), LocalDate.now()) >= 18;
    }

    List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> condition);
}
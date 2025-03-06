package com.epam.training.service.impl;

import com.epam.training.dto.*;
import com.epam.training.service.Service;
import com.epam.training.service.impl.exceptions.SubscriptionNotFoundException;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ServiceImpl implements Service {
    private final List<Subscription> subscriptions = new ArrayList<>();
    private final Set<User> users = new HashSet<>();

    @Override
    public void subscribe(BankCard bankCard) {
        subscriptions.add(new Subscription(bankCard.getNumber(), LocalDate.now()));
        users.add(bankCard.getUser());
    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String number) {
        return Optional.ofNullable(subscriptions.stream()
                .filter(sub -> sub.getBankcardNumber().equals(number))
                .findFirst()
                .orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found")));
    }

    @Override
    public List<User> getAllUsers() {
        return users.stream().collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> condition) {
        return subscriptions.stream()
                .filter(condition)
                .collect(Collectors.toList());
    }
}
package com.epam.training.bank.impl;

import com.epam.training.bank.Bank;
import com.epam.training.dto.*;

import java.util.UUID;
import java.util.function.BiFunction;

public class BankImpl implements Bank {
    @Override
    public BankCard createBankCard(User user, BankCardType type) {
        BiFunction<String, User, BankCard> constructor =
                type == BankCardType.CREDIT ? CreditBankCard::new : DebitBankCard::new;
        return constructor.apply(generateCardNumber(), user);
    }

    private String generateCardNumber() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 10);
    }
}
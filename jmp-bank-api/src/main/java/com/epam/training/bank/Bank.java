package com.epam.training.bank;

import com.epam.training.dto.BankCard;
import com.epam.training.dto.BankCardType;
import com.epam.training.dto.User;

public interface Bank {
    BankCard createBankCard(User user, BankCardType type);
}
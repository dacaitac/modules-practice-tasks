module jmp.cloud.bank.impl {
    requires transitive jmp.bank.api;
    requires jmp.dto;
    exports com.epam.training.bank.impl;
    provides com.epam.training.bank.Bank with com.epam.training.bank.impl.BankImpl;
}
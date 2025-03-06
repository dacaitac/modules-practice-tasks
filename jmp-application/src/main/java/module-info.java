module jmp.application {
    requires jmp.cloud.bank.impl;
    requires jmp.cloud.service.impl;
    requires jmp.dto;

    uses com.epam.training.bank.Bank;
    uses com.epam.training.service.Service;
}
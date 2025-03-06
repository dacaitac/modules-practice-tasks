module jmp.cloud.service.impl {
    requires transitive jmp.service.api;
    requires jmp.dto;
    exports com.epam.training.service.impl;
    provides com.epam.training.service.Service with com.epam.training.service.impl.ServiceImpl;
}
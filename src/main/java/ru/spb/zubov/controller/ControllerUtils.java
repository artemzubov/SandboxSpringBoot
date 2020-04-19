package ru.spb.zubov.controller;

import org.springframework.validation.BindingResult;

import java.util.Map;
import java.util.stream.Collectors;

class ControllerUtils {
    static Map<String, String> getErrors(BindingResult bindingResult) {
        Map<String, String> errorMap = bindingResult.getFieldErrors().stream().collect(Collectors.toMap(
                fieldError -> fieldError.getField() + "Error",
                fieldError -> fieldError.getDefaultMessage()
        ));
        return errorMap;
    }
}

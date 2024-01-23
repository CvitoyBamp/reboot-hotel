package ru.reboot.hotel.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping(value = "/api/validation")
public class ValidationController {


    @PostMapping(value = "/name")
    @ResponseBody
    public String checkName(@RequestParam String name) {
        log.info(name);
        return name.length() < 3 ? "Error" : "";
    }

}

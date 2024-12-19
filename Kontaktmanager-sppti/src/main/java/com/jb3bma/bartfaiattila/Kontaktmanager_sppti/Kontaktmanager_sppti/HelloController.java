package com.jb3bma.bartfaiattila.Kontaktmanager_sppti.Kontaktmanager_sppti;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Tesztelésre létrehozva

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Helló, működik!";
    }
}

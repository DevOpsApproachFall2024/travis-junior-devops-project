package fr.epita.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackendController {

    @GetMapping("/")
    public String index() {
        return "Let's get rolling!!!";
    }

}
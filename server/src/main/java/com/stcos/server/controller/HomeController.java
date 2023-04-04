package com.stcos.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RestController
public class HomeController {

    @GetMapping("/hello")
    public String welcome() {
        log.info("用户访问主界面");
        return new Date() + "\n"
                + """
                 <div>
                 <textarea rows="12" cols="128">
                 ___  ___  _______   ___       ___       ________  ___       __   ________  ________  ___       ________  ___      \s
                |\\  \\|\\  \\|\\  ___ \\ |\\  \\     |\\  \\     |\\   __  \\|\\  \\     |\\  \\|\\   __  \\|\\   __  \\|\\  \\     |\\   ___ \\|\\  \\     \s
                \\ \\  \\\\\\  \\ \\   __/|\\ \\  \\    \\ \\  \\    \\ \\  \\|\\  \\ \\  \\    \\ \\  \\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\    \\ \\  \\_|\\ \\ \\  \\    \s
                 \\ \\   __  \\ \\  \\_|/_\\ \\  \\    \\ \\  \\    \\ \\  \\\\\\  \\ \\  \\  __\\ \\  \\ \\  \\\\\\  \\ \\   _  _\\ \\  \\    \\ \\  \\ \\\\ \\ \\  \\   \s
                  \\ \\  \\ \\  \\ \\  \\_|\\ \\ \\  \\____\\ \\  \\____\\ \\  \\\\\\  \\ \\  \\|\\__\\_\\  \\ \\  \\\\\\  \\ \\  \\\\  \\\\ \\  \\____\\ \\  \\_\\\\ \\ \\__\\  \s
                   \\ \\__\\ \\__\\ \\_______\\ \\_______\\ \\_______\\ \\_______\\ \\____________\\ \\_______\\ \\__\\\\ _\\\\ \\_______\\ \\_______\\|__|  \s
                    \\|__|\\|__|\\|_______|\\|_______|\\|_______|\\|_______|\\|____________|\\|_______|\\|__|\\|__|\\|_______|\\|_______|   ___\s
                                                                                                                               |\\__\\
                                                                                                                               \\|__|
                    
                </textarea>
                </div>
                """;
    }

}

package br.com.cardoso.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SecurityController {

    @GetMapping("/security/hello")
    fun hello() = "Se viu essa mensagem, você está liberado."
}
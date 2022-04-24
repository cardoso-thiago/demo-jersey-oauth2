package br.com.cardoso.controller

import br.com.cardoso.service.HelloService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController(private val helloService: HelloService) {

    @GetMapping("/hello")
    fun hello() = helloService.getHello()
}
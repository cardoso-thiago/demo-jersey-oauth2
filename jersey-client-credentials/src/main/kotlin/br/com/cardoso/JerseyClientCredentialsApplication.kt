package br.com.cardoso

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JerseyClientCredentialsApplication

fun main(args: Array<String>) {
	runApplication<JerseyClientCredentialsApplication>(*args)
}

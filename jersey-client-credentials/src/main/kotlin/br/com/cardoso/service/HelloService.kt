package br.com.cardoso.service

import br.com.cardoso.model.Token
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.Form
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.MediaType


@Service
class HelloService(private val environment: Environment) {

    companion object {
        private const val BASE_URL = "http://localhost:8080/security"
        private const val PATH = "hello"

        private const val BASE_URL_KEYCLOAK = "http://localhost:9090/auth/realms/"
        private const val REALM = "security-api"
        private const val PATH_TOKEN = "protocol/openid-connect/token"
    }

    fun getHello(): String {
        val client: Client = ClientBuilder.newBuilder().build()
        val token = getToken(client)

        val webTarget: WebTarget = client.target(BASE_URL)
        val responseHello = webTarget.path(PATH).request().accept(MediaType.TEXT_PLAIN)
            .header(HttpHeaders.AUTHORIZATION, "Bearer ${token.accessToken}").get()
        if (responseHello.status != 200) {
            throw ResponseStatusException(HttpStatus.valueOf(responseHello.status), "Erro ao obter o olá!")
        }
        return responseHello.readEntity(String::class.java)
    }

    private fun getToken(client: Client): Token {
        val form = Form()
        form.param("grant_type", "client_credentials")
        form.param("client_id", environment.getProperty("client.id"))
        form.param("client_secret", environment.getProperty("client.secret"))
        val webTargetToken: WebTarget = client.target(BASE_URL_KEYCLOAK)
        val responseToken = webTargetToken.path(REALM).path(PATH_TOKEN).request().accept(MediaType.APPLICATION_JSON)
            .post(Entity.form(form))
        if (responseToken.status != 200) {
            throw ResponseStatusException(HttpStatus.valueOf(responseToken.status), "Erro ao obter o token!")
        }
        return responseToken.readEntity(Token::class.java)
    }
}
package br.com.cardoso.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Token(

	@field:JsonProperty("access_token")
	val accessToken: String? = null,

	@field:JsonProperty("refresh_expires_in")
	val refreshExpiresIn: Int? = null,

	@field:JsonProperty("not-before-policy")
	val notBeforePolicy: Int? = null,

	@field:JsonProperty("scope")
	val scope: String? = null,

	@field:JsonProperty("token_type")
	val tokenType: String? = null,

	@field:JsonProperty("expires_in")
	val expiresIn: Int? = null
)

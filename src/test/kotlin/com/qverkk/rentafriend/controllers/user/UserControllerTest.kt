package com.qverkk.rentafriend.controllers.user

import io.restassured.RestAssured
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

import io.restassured.RestAssured.*
import io.restassured.matcher.RestAssuredMatchers.*
import org.hamcrest.Matchers.*
import io.restassured.module.jsv.JsonSchemaValidator.*
import org.junit.Before
import org.junit.jupiter.api.BeforeAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.server.LocalServerPort
import org.assertj.core.api.Assertions.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class UserControllerTest {

    @LocalServerPort
    var randomPort = 0

    @Test
    fun `all users is empty`() {
        val list: MutableList<UserDTO> =
                given()
                        .port(randomPort)
                .`when`()
                        .get("/users/all")
                .then()
                        .extract().body().jsonPath().getList(".", UserDTO::class.java)
        assertThat(list).isEmpty()
    }
}
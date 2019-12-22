package com.qverkk.rentafriend.controllers.user

import com.qverkk.rentafriend.controllers.user.information.UserInformationDTO
import io.restassured.RestAssured.given
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.context.annotation.PropertySource
import java.math.BigDecimal

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@PropertySource("classpath:UserControllerTest.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
internal class UserControllerTest {

    @LocalServerPort
    var randomPort = 0

    @Test
    @Order(1)
    fun `all users is empty`() {
        val list: MutableList<UserDTO> =
                given()
                        .port(randomPort)
                .`when`()
                        .get("/users/all")
                .then()
                        .extract().body().jsonPath().getList(".", UserDTO::class.java)
        assertThat(list.isEmpty()).isEqualTo(true)
    }

    @Test
    @Order(2)
    fun `register user`() {
        val userWithInformation = UserWithInformation(
                UserDTO(0, "John", "Depp", "1992-12-10", "test@test.com", "testing123"),
                UserInformationDTO(0, 0, "Some description hehe", BigDecimal.TEN, "Poland", "Warsaw")
        )

        given()
                .port(randomPort)
                .body(userWithInformation)
                .contentType("application/json")
                .accept("application/json")
        .`when`()
                .post("/users/register")
        .then()
                .assertThat()
                .statusCode(200)
    }

    @Test
    @Order(3)
    fun `all users is not empty`() {
        val list: MutableList<UserDTO> =
                given()
                        .port(randomPort)
                .`when`()
                        .get("/users/all")
                .then()
                        .extract().body().jsonPath().getList(".", UserDTO::class.java)

        assertThat(list.isEmpty()).isEqualTo(false)
    }
}
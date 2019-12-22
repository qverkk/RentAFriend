package com.qverkk.rentafriend

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RentafriendApplication

fun main(args: Array<String>) {
    runApplication<RentafriendApplication>(*args)
//    SpringApplication.run(RentafriendApplication::class.java, *args)
}

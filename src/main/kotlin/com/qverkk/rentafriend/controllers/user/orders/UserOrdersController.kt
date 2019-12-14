package com.qverkk.rentafriend.controllers.user.orders

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
class UserOrdersController {

    @Autowired
    private lateinit var service: JpaUserOrderService

    @PostMapping(
            value = ["/create"],
            headers = ["rentingId", "rentedId"]
    )
    fun rent(@RequestHeader("rentingId") rentingId: Int, @RequestHeader("rentedId") rentedId: Int): Boolean {
        return service.addOrderBetween(rentingId, rentedId)
    }

    @GetMapping(
            value = ["/get/for"],
            headers = ["userId"]
    )
    fun getAllForUser(@RequestHeader("userId") userId: Int): List<UserOrdersDTO> {
        return service.getAllOrdersByUser(userId)
    }
}

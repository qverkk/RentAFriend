package com.qverkk.rentafriend.controllers.user.orders

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
}

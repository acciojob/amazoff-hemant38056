package com.driver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
public class OrderController {

    @Autowired
    OrderService orderService = new OrderService();


//    1. Add Order
    @PostMapping("/add-order")
    public ResponseEntity<String> addOrder(@RequestBody Order order){
        orderService.addOrderService(order);
        return new ResponseEntity<>("New order added successfully", HttpStatus.CREATED);
    }

//    2. Add partner

    @PostMapping("/add-partner/{partnerId}")
    public ResponseEntity<String> addPartner(@PathVariable String partnerId){
        orderService.addPartnerService(partnerId);
        return new ResponseEntity<>("New delivery partner added successfully", HttpStatus.CREATED);
    }


//    3. Add Order and Partner Pair
    @PutMapping("/add-order-partner-pair")
    public ResponseEntity<String> addOrderPartnerPair(@RequestParam String orderId, @RequestParam String partnerId){
        orderService.addOrderPartnerPairService(orderId, partnerId);
        //This is basically assigning that order to that partnerId
        return new ResponseEntity<>("New order-partner pair added successfully", HttpStatus.CREATED);
    }


//    4. Get Order by Order Id

    @GetMapping("/get-order-by-id/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable String orderId){

        Order order= null;
        //order should be returned with an orderId.
        order = orderService.getOrderByIdService(orderId);

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

//    5. Get Partner by Partner Id
    @GetMapping("/get-partner-by-id/{partnerId}")
    public ResponseEntity<DeliveryPartner> getPartnerById(@PathVariable String partnerId){

        DeliveryPartner deliveryPartner = null;

        //deliveryPartner should contain the value given by partnerId
        deliveryPartner = orderService.getPartnerByIdService(partnerId);

        return new ResponseEntity<>(deliveryPartner, HttpStatus.CREATED);
    }

//    6. Get Order Count By PartnerId

    @GetMapping("/get-order-count-by-partner-id/{partnerId}")
    public ResponseEntity<Integer> getOrderCountByPartnerId(@PathVariable String partnerId){

        Integer orderCount = 0;

        //orderCount should denote the orders given by a partner-id
        orderCount = orderService.getOrderCountByPartnerIdService(partnerId);

        return new ResponseEntity<>(orderCount, HttpStatus.CREATED);
    }

//    7. Get Orders By Partner Id
    @GetMapping("/get-orders-by-partner-id/{partnerId}")
    public ResponseEntity<List<String>> getOrdersByPartnerId(@PathVariable String partnerId){
        List<String> orders = null;

        //orders should contain a list of orders by PartnerId
        orders = orderService.getOrdersByPartnerIdService(partnerId);

        return new ResponseEntity<>(orders, HttpStatus.CREATED);
    }

//    8. Get All Orders
    @GetMapping("/get-all-orders")
    public ResponseEntity<List<String>> getAllOrders(){
        List<String> orders = null;

        //Get all orders
        orders = orderService.getAllOrdersService();
        return new ResponseEntity<>(orders, HttpStatus.CREATED);
    }

//    9. Get count of unassigned  orders
    @GetMapping("/get-count-of-unassigned-orders")
    public ResponseEntity<Integer> getCountOfUnassignedOrders(){
        Integer countOfOrders = 0;

        //Count of orders that have not been assigned to any DeliveryPartner
        countOfOrders = orderService.getCountOfUnassignedOrdersService();

        return new ResponseEntity<>(countOfOrders, HttpStatus.CREATED);
    }

//    10. Get count of order left after time
    @GetMapping("/get-count-of-orders-left-after-given-time/{partnerId}")
    public ResponseEntity<Integer> getOrdersLeftAfterGivenTimeByPartnerId(@PathVariable String time, @PathVariable String partnerId){

        Integer countOfOrders = 0;

        //countOfOrders that are left after a particular time of a DeliveryPartner
        countOfOrders = orderService.getOrdersLeftAfterGivenTimeByPartnerIdService(time, partnerId);

        return new ResponseEntity<>(countOfOrders, HttpStatus.CREATED);
    }

//    11. Get last Delivery Time
    @GetMapping("/get-last-delivery-time/{partnerId}")
    public ResponseEntity<String> getLastDeliveryTimeByPartnerId(@PathVariable String partnerId){
        String time = null;

        //Return the time when that partnerId will deliver his last delivery order.
        time = orderService.getLastDeliveryTimeByPartnerIdService(partnerId);

        return new ResponseEntity<>(time, HttpStatus.CREATED);
    }

//    12. Delete partner by id

    @DeleteMapping("/delete-partner-by-id/{partnerId}")
    public ResponseEntity<String> deletePartnerById(@PathVariable String partnerId){

        //Delete the partnerId
        //And push all his assigned orders to unassigned orders.

        orderService.deletePartnerByIdService(partnerId);

        return new ResponseEntity<>(partnerId + " removed successfully", HttpStatus.CREATED);
    }

//    13. Delete order by id

    @DeleteMapping("/delete-order-by-id/{orderId}")
    public ResponseEntity<String> deleteOrderById(@PathVariable String orderId){

        //Delete an order and also
        // remove it from the assigned order of that partnerId
        orderService.deleteOrderByIdService(orderId);

        return new ResponseEntity<>(orderId + " removed successfully", HttpStatus.CREATED);
    }
}

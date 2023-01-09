package com.driver;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository = new OrderRepository();

//    1. Add Order
    public void addOrderService(Order order){
        orderRepository.addOrderRepo(order);
    }

    //    2. Add partner
    public void addPartnerService(String partnerId){
        orderRepository.addPartnerRepo(partnerId);
    }

    //    3. Add Order and Partner Pair
    public void addOrderPartnerPairService(String orderId, String partnerId){
        orderRepository.addOrderPartnerPairRepo(orderId, partnerId);
    }

    //    4. Get Order by Order Id
    public Order getOrderByIdService(String orderId){
        return orderRepository.getOrderByIdRepo(orderId);
    }

    //    5. Get Partner by Partner Id
    public DeliveryPartner getPartnerByIdService(String partnerId){
        return orderRepository.getPartnerByIdRepo(partnerId);
    }

    //    6. Get Order Count By PartnerId
    public int getOrderCountByPartnerIdService(String partnerId){
        return orderRepository.getOrderCountByPartnerIdRepo(partnerId);
    }

    //    7. Get Orders By Partner Id

    public List<String> getOrdersByPartnerIdService(String partnerId){
        return orderRepository.getOrdersByPartnerIdRepo(partnerId);
    }


    //    8. Get All Orders
    public List<String> getAllOrdersService(){
        return orderRepository.getAllOrdersRepo();
    }

    //    9. Get count of unassigned  orders
    public int getCountOfUnassignedOrdersService(){
        return orderRepository.getCountOfUnassignedOrdersRepo();
    }


    //    10. Get count of order left after time
    public int getOrdersLeftAfterGivenTimeByPartnerIdService(String time, String partnerId){
        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerIdRepo(time, partnerId);
    }


    //    11. Get last Delivery Time
    public String getLastDeliveryTimeByPartnerIdService(String partnerId){
        return orderRepository.getLastDeliveryTimeByPartnerIdRepo(partnerId);
    }


    //    12. Delete partner by id
    public void deletePartnerByIdService(String partnerId){
        orderRepository.deletePartnerByIdRepo(partnerId);
    }


    //    13. Delete order by id
    public void deleteOrderByIdService(String orderId){
        orderRepository.deleteOrderByIdRepo(orderId);
    }

}

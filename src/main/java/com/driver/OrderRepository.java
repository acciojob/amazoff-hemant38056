package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository {

    HashMap<String, Order> orderMap = new HashMap<>();
    HashMap<String, DeliveryPartner> partnerMap = new HashMap<>();
    HashMap<String, ArrayList<String>> pairPartnerToOrder = new HashMap<>();
    HashMap<String, String> pairOrderToPartner = new HashMap<>();



    //    1. Add Order
    public void addOrderRepo(Order order){
        orderMap.put(order.getId(), order);
        pairOrderToPartner.put(order.getId(), "-1");
    }

    //    2. Add partner
    public void addPartnerRepo(String partnerId){
        DeliveryPartner deliveryPartner = new DeliveryPartner(partnerId);
        partnerMap.put(partnerId, deliveryPartner);
    }

    //    3. Add Order and Partner Pair
    public void addOrderPartnerPairRepo(String orderId, String partnerId){
        if(orderMap.containsKey(orderId) && partnerMap.containsKey(partnerId)){
            if(!pairPartnerToOrder.containsKey(partnerId)){
                pairPartnerToOrder.put(partnerId, new ArrayList<>());
            }
            pairPartnerToOrder.get(partnerId).add(orderId);
            DeliveryPartner temp = partnerMap.get(partnerId);
            int num = temp.getNumberOfOrders();
            num++;
            temp.setNumberOfOrders(num);
            pairOrderToPartner.put(orderId, partnerId);
        }
    }

    //    4. Get Order by Order Id
    public Order getOrderByIdRepo(String orderId){
        return orderMap.get(orderId);

    }

    //    5. Get Partner by Partner Id
    public DeliveryPartner getPartnerByIdRepo(String partnerId){
        return partnerMap.get(partnerId);
    }

    //    6. Get Order Count By PartnerId
    public int getOrderCountByPartnerIdRepo(String partnerId){
        if(pairPartnerToOrder.containsKey(partnerId)){
            return pairPartnerToOrder.get(partnerId).size();
        }
        return 0;
    }

    //    7. Get Orders By Partner Id

    public List<String> getOrdersByPartnerIdRepo(String partnerId){
        List<String> listOfOrderByPartner = new ArrayList<>();
        if(pairPartnerToOrder.containsKey(partnerId)){
            listOfOrderByPartner.addAll(pairPartnerToOrder.get(partnerId));
        }
        return listOfOrderByPartner;
    }


    //    8. Get All Orders
    public List<String> getAllOrdersRepo(){
        List<String> listOfOrders = new ArrayList<>();
        for(String orderId : orderMap.keySet()){
            listOfOrders.add(orderId);
        }
        return listOfOrders;
    }

    //    9. Get count of unassigned  orders
    public int getCountOfUnassignedOrdersRepo(){
        int count = 0;
        for (String orderIds : orderMap.keySet()){
            String assign = pairOrderToPartner.get(orderIds);
            if(assign.equals("-1")){
                count++;
            }
        }
        return count;
    }


    //    10. Get count of order left after time
    public int getOrdersLeftAfterGivenTimeByPartnerIdRepo(String time, String partnerId){
        String hour = time.substring(0, 2);
        String minute = time.substring(3, 5);

        int hh = Integer.parseInt(hour);
        int mm = Integer.parseInt(minute);
        int lastTime = (hh * 60) + mm;

        int count = 0;
        if(!pairPartnerToOrder.containsKey(partnerId)){
            return 0;
        }
        List<String> listOfOrder = pairPartnerToOrder.get(partnerId);
        for(int i = 0; i < listOfOrder.size(); i++){
            Order temp = orderMap.get(listOfOrder.get(i));
            if(temp.getDeliveryTime() > lastTime){
                count++;
            }
        }
        return count;
    }


    //    11. Get last Delivery Time
    public String getLastDeliveryTimeByPartnerIdRepo(String partnerId){
        if(!pairPartnerToOrder.containsKey(partnerId)){
            return null;
        }
        if(pairPartnerToOrder.get(partnerId).size() == 0){
            return null;
        }
        List<String> listOfOrder = new ArrayList<>();
        List<Integer> times = new ArrayList<>();
        listOfOrder.addAll(pairPartnerToOrder.get(partnerId));

        for(int i = 0; i < listOfOrder.size(); i++){
            String id = listOfOrder.get(i);
            int temp = orderMap.get(id).getDeliveryTime();
            times.add(temp);
        }

        int max = times.get(0);
        for(int i = 1; i < times.size(); i++){
            if(times.get(i) > max){
                max = times.get(i);
            }
        }

        int mm = max % 60;
        int hh = max / 60;

        String ans = "";
        ans += Integer.toString(hh);
        ans += " : ";
        ans += Integer.toString(mm);
        return ans;


    }


    //    12. Delete partner by id
    public void deletePartnerByIdRepo(String partnerId){
        List<String> ordersAssigned = new ArrayList<>();
        if(pairPartnerToOrder.containsKey(partnerId)){
            ordersAssigned.addAll(pairPartnerToOrder.get(partnerId));
        }
        pairPartnerToOrder.remove(partnerId);
        for(int i = 0; i < ordersAssigned.size(); i++){
            pairOrderToPartner.put(ordersAssigned.get(i),"-1");
        }
    }


    //    13. Delete order by id
    public void deleteOrderByIdRepo(String orderId){
        String temp = pairOrderToPartner.get(orderId);
        if(orderMap.containsKey(orderId)){
            orderMap.remove(orderId);
        }
        String partnerId = "";

        if(pairOrderToPartner.containsKey(orderId)){
            partnerId += pairOrderToPartner.get(orderId);
            pairOrderToPartner.remove(orderId);
        }

        if(!partnerId.equals("-1")){
            pairPartnerToOrder.get(partnerId).remove(orderId);
            DeliveryPartner dtemp = partnerMap.get(partnerId);
            int num = dtemp.getNumberOfOrders();
            num--;
            dtemp.setNumberOfOrders(num);
        }


    }

}

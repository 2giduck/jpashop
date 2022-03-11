package com.jpashop.api;

import com.jpashop.domain.Address;
import com.jpashop.domain.Order;
import com.jpashop.domain.OrderStatus;
import com.jpashop.repository.OrderRepository;
import com.jpashop.repository.OrderSearch;
import com.jpashop.repository.SimpleOrderQueryDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * xToOne
 * Order
 * Order -> Member
 * Order -> Delivery
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {
    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1(){
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        return all;
    }

    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderQueryDto> ordersV2(){
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        List<SimpleOrderQueryDto> result = orders.stream()
                .map(o->new SimpleOrderQueryDto(o))
                .collect(Collectors.toList());

        return result;
    }

    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderQueryDto> ordersV3(){
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        List<SimpleOrderQueryDto> result = orders.stream()
                .map(o->new SimpleOrderQueryDto(o))
                .collect(Collectors.toList());

        return result;
    }

    @GetMapping("/ap4/v3/simple-orders")
    public List<SimpleOrderQueryDto> ordersV4(){
        List<SimpleOrderQueryDto> orders = orderRepository.findOrderDtos();
        return orders;
    }

}

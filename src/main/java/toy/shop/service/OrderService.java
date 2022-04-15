package toy.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import toy.shop.entity.Item;
import toy.shop.entity.Order;

import toy.shop.entity.OrderItem;
import toy.shop.entity.User;
import toy.shop.repository.item.ItemRepository;
import toy.shop.repository.order.OrderRepository;
import toy.shop.repository.UserRepository;
import toy.shop.web.dto.dtoresponse.OrderResponseDto;


import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;


    public Long orderOne(String deliveryAddress,Long itemId, int quantity, String username) {
        User findUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("사용자가 존제하지 않습니다. " + username));
        Item findItem = itemRepository.getById(itemId);

        OrderItem orderItem = OrderItem.orderItem(findItem, findItem.getPrice(), quantity);

        Order order = Order.order(findUser, deliveryAddress, List.of(orderItem));
        orderRepository.save(order);

        return order.getId();
    }


    public Long cancel(Long orderId) {
        Order findOrder = orderRepository.findById(orderId).
                orElseThrow(() -> new IllegalStateException("주문 정보가 없습니다"));
        findOrder.orderCancel();

        return findOrder.getId();
    }

    public List<OrderResponseDto> getOrders(Long userId) {
        return orderRepository.findByUserId(userId);

    }


}

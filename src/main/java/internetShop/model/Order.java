package internetShop.model;

import internetShop.service.OrderService;
import internetShop.web.IdGenerator;

import java.util.List;

public class Order {
    private Long orderId;
    private Long userId;
    private List<Item> items;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Order(Long userId, List<Item> items) {
        this.userId = userId;
        this.items = items;

    }

    private Double countAmount(List<Item> items) {
        return Double.valueOf(items.stream()
                .map(item -> item.getPrice())
                .count());
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", items=" + items +
                '}';
    }
}

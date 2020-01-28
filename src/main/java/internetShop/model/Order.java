package internetShop.model;

import java.util.List;

public class Order {
    private Long orderId;
    private Long userId;
    private List<Item> items;

    public Order() {

    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    private Double amount;

    public Order(List<Item> items, Long userId) {
        this.userId = userId;
        this.items = items;
    }

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

package internetShop.model;

public class Item {
    private static Long idGenerator=0L;

    private Long itemId;
    private String name;
    private Double price;


    public Item(String name, Double price) {
        this.name = name;
        this.price = price;
        itemId=idGenerator++;
    }
    public Item(){
        itemId=idGenerator++;
    }

    public Item(Long itemId) {
        this.itemId=itemId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

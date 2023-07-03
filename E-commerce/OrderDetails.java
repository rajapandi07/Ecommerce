public class OrderDetails {
    private int id;
    private String brandName;
    private String modelName;
    private int price;
    private int quantity;
    private String orderDate;
    private String status;

    public OrderDetails(int id, String brandName, String modelName, int price, int quantity, String orderDate, String status) {
        this.id = id;
        this.brandName = brandName;
        this.modelName = modelName;
        this.price = price;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
public String toString() {
    return "OrderDetails{" +
            "id=" + id +
            ", brandName='" + brandName + '\'' +
            ", modelName='" + modelName + '\'' +
            ", price=" + price +
            ", quantity=" + quantity +
            ", orderDate='" + orderDate + '\'' +
            ", status='" + status + '\'' +
            '}';
}

}


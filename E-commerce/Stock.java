public class Stock {
    private int id;
    private String brandName;
    private String modelName;
    private int price;
    private int stock;
    private int available;
    
    public Stock(int id, String brandName, String modelName, int price, int stock, int available) {
        this.id = id;
        this.brandName = brandName;
        this.modelName = modelName;
        this.price = price;
        this.stock = stock;
        this.available = available;
    }
    
    // Getters and Setters
    
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
    
    public int getStock() {
        return stock;
    }
    
    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public int getAvailable() {
        return available;
    }
    
    public void setAvailable(int available) {
        this.available = available;
    }
    
    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", brandName='" + brandName + '\'' +
                ", modelName='" + modelName + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", available=" + available +
                '}';
    }
}


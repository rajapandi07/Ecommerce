public class Cart {
    private int id;
    private String brandName;
    private String modelName;
    private int price;
    private int quantity;
    private int rating;
    private int productId;
    
    public Cart(int id, String brandName, String modelName, int price, int quantity, int rating,int productId) {
        this.id = id;
        this.brandName = brandName;
        this.modelName = modelName;
        this.price = price;
        this.quantity = quantity;
        this.rating = rating;
        this.productId=productId;
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
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public int getRating() {
        return rating;
    }
    
    public void setRating(int rating) {
        this.rating = rating;
    }
    
     public int getproductId() {
        return productId;
    }

    public void setproductId(int productId) {
        this.productId = productId;
    }
   
    
    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", brandName='" + brandName + '\'' +
                ", modelName='" + modelName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", rating=" + rating +
                '}';
    }
}


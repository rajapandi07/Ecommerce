import java.util.ArrayList;

public class ProductDetails {
    private int id;
    private String brandName;
    private String modelName;
    private int price;
    private int rating;
    private String description;
    private String vendor;
    private ArrayList<Rating> ratings;
    
    public ProductDetails(int id, String brandName, String modelName, int price, int rating, String description,String vendor, ArrayList<Rating> ratings) {
        this.id = id;
        this.brandName = brandName;
        this.modelName = modelName;
        this.price = price;
        this.rating = rating;
        this.description = description;
        this.vendor=vendor;
        this.ratings = ratings;
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
    
    public int getRating() {
        return rating;
    }
    
    public void setRating(int rating) {
        this.rating = rating;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
     public String getVendor() {
        return vendor;
    }
    
     public void setVendor(String vendor) {
        this.vendor = vendor;
    }
    
    public ArrayList<Rating> getRatings() {
        return ratings;
    }
    
    public void setRatings(ArrayList<Rating> ratings) {
        this.ratings = ratings;
    }
    
    @Override
    public String toString() {
        return "ProductDetails{" +
                "id=" + id +
                ", brandName='" + brandName + '\'' +
                ", modelName='" + modelName + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", Vendor='" +vendor+ '\''+
                ", ratings=" + ratings +
                '}';
    }
}


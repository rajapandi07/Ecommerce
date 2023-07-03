public class Rating {
    private String customerName;
    private int rating;
    private String review;
    
    public Rating(String customerName, int rating, String review) {
        this.customerName = customerName;
        this.rating = rating;
        this.review = review;
    }
    
    // Getters and Setters
    
    public String getCustomerName() {
        return customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public int getRating() {
        return rating;
    }
    
    public void setRating(int rating) {
        this.rating = rating;
    }
    
    public String getReview() {
        return review;
    }
    
    public void setReview(String review) {
        this.review = review;
    }
    
    @Override
    public String toString() {
        return "Rating{" +
                "customerName='" + customerName + '\'' +
                ", rating=" + rating +
                ", review='" + review + '\'' +
                '}';
    }
}


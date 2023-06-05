package lag311.pa;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**A class that creates a Product object. The class contains an observable list to hold associated Part objects,
 * class variables, a constructor method and getter and setter methods.
 * @author Leigh Grover*/
public class Product {
    /**An observable list to hold Part objects. The observable list will contain associated Part objects of the Product.*/
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    /**A method that adds a Part object to the associatedParts observable list.
     * @param part */
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }
/**A method that returns the associated parts observable list.
 * @return associatedParts*/
    public  ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }
/**A method that deletes an associated part.
 * @return boolean*/
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
       return associatedParts.remove(selectedAssociatedPart);
    };
    /**A variable of the Product object.*/
    private int id;
    /**A variable of the Product object.*/
    private String name;
    /**A variable of the Product object.*/
    private Double price;
    /**A variable of the Product object.*/
    private int stock;
    /**A variable of the Product object.*/
    private int min;
    /**A variable of the Product object.*/
    private int max;
    /**A constructor method for the Product object.*/
    public Product(int id, String name, Double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
/**A method that sets the id variable.
 * @param id The variable to set*/
    public void setId(int id) {

        this.id = id;
    }
    /**A method that sets the name variable.
     * @param name The variable to set.*/
    public void setName(String name) {
        this.name = name;
    }
    /**A method that sets the price variable.
     * @param price The variable to set.*/
    public void setPrice(Double price) {

        this.price = price;
    }
    /**A method that sets the stock variable.
     * @param stock The variable to set.*/
    public void setStock(int stock) {

        this.stock = stock;
    }
    /**A method that sets the min variable.
     * @param min The variable to set.*/
    public void setMin(int min) {

        this.min = min;
    }
    /**A method that sets the max variable.
     * @param max The variable to set.*/
    public void setMax(int max) {

        this.max = max;
    }
    /**A method that gets the id variable.
     * @return id The variable to get.*/
    public int getId() {

        return id;
    }
    /**A method that gets the name variable.
     * @return name The variable to get.*/
    public String getName() {

        return name;
    }
    /**A method that gets the price variable.
     * @return price The variable to get.*/
    public Double getPrice() {

        return price;
    }
    /**A method that gets the stock variable.
     * @return stock The variable to get.*/
    public int getStock() {
        return stock;
    }
    /**A method that gets the min variable.
     * @return min The variable to get.*/
    public int getMin() {
        return min;
    }
    /**A method that gets the max variable.
     * @return maxThe variable to get.*/
    public int getMax() {
        return max;
    }
}

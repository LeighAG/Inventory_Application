package lag311.pa;

/**
 *This abstract class creates the Part object.  The class contains class variables, a constructor method and getter and setter methods.
 * @author WGU Computer Science professors
 */
public abstract class Part {
    /**A variable of the Part object.*/
    private int id;
    /**A variable of the Part object.*/
    private String name;
    /**A variable of the Part object.*/
    private double price;
    /**A variable of the Part object.*/
    private int stock;
    /**A variable of the Part object.*/
    private int min;
    /**A variable of the Part object.*/
    private int max;

    /**A constructor method for the Part object.*/
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**A method to retrieve the id.
     * @return The variable to get.
     */
    public int getId() {
        return id;
    }

    /**A method to set the id.
     * @param id The variable to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**A method to retrieve the name.
     * @return name The variable to get
     */
    public String getName() {
        return name;
    }

    /**A method to set the name.
     * @param name The variable to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**A method to retrieve the price.
     * @return price The variable to get
     */
    public double getPrice() {
        return price;
    }

    /**A method to set the price.
     * @param price The variable to set.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**A method to retrieve the stock.
     * @return stock The variable to get.
     */
    public int getStock() {
        return stock;
    }

    /**A method to set the stock.
     * @param stock The variable to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**A method to retrieve the min.
     * @return The variable to get.
     */
    public int getMin() {
        return min;
    }

    /**A method to set the min.
     * @param min The variable to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**A method to retrieve the max.
     * @return The variable to get.
     */
    public int getMax() {
        return max;
    }

    /**A method to set the max variable.
     * @param max The variable to set.
     */
    public void setMax(int max) {
        this.max = max;
    }

}
package lag311.pa;
/**This class extends the Part class and creates an Outsourced object.
 * The class creates the String variable companyName and holds constructor, getter and setter methods.*/
public class Outsourced  extends Part {
    String companyName;
/**A constructor method for an instance of Outsourced.*/
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }
/*A set method for the variable companyName.**/
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
/**A get method for the variable, companyName.*/
    public String getCompanyName() {
        return companyName;
    }

}


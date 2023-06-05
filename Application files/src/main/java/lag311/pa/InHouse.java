package lag311.pa;
/**A class that creates an InHouse instance and extends the Part class.
 *It contains a class variable, a constructor method and setter and getter methods.
 * @author Leigh Grover */
public class InHouse extends Part{
    /**An variable of the InHouse Class*/
    int machineId;
    /**Constructor method for InHouse.*/
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }
/**A setter method for the class variable, machineID.*/
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
    /**A getter method for the class variable, machineID.*/
    public int getMachineId() {
        return machineId;
    }
}

package lag311.pa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
/**A class that controls the ModifyPart GUI.
 * This class allows the user to modify a Part object or cancel and return to the Main GUI.
 * @author Leigh Grover */
public class ModifyPartController implements Initializable {
        Stage stage;
        Parent scene;
 /**An instance of DecimalFormat.
  * This is used to constrain double variables to only have up to two decimal places.*/
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");
/**An element of the ModifyPart GUI*/
        @FXML
        private Button btn_ModifyPart_Cancel;
    /**An element of the ModifyPart GUI*/
        @FXML
        private Button btn_ModifyPart_Save;
    /**An element of the ModifyPart GUI*/
    @FXML
    private Label exceptionErrorTextModPart;
    /**An element of the ModifyPart GUI*/
        @FXML
        private Label lblModifyPart_ID;
    /**An element of the ModifyPart GUI*/
        @FXML
        private Label lblModifyPart_Inv;
    /**An element of the ModifyPart GUI*/
        @FXML
        private Label lblModifyPart_Machine_Company;
    /**An element of the ModifyPart GUI*/
        @FXML
        private Label lblModifyPart_Max;
    /**An element of the ModifyPart GUI*/
        @FXML
        private Label lblModifyPart_Min;
    /**An element of the ModifyPart GUI*/
        @FXML
        private Label lblModifyPart_Name;
    /**An element of the ModifyPart GUI*/
        @FXML
        private Label lblModifyPart_PriceCost;
    /**An element of the ModifyPart GUI*/
        @FXML
        private ToggleGroup modifyPart_Toggle;
    /**An element of the ModifyPart GUI*/
        @FXML
        private RadioButton radioModifyPart_InHouse;
    /**An element of the ModifyPart GUI*/
        @FXML
        private RadioButton radioModifyPart_OutSourced;
    /**An element of the ModifyPart GUI*/
        @FXML
        private TextField txt_ModifyPart_ID;
    /**An element of the ModifyPart GUI*/
        @FXML
        private TextField txt_ModifyPart_Name;
    /**An element of the ModifyPart GUI*/
        @FXML
        private TextField txt_ModifyPart_Inv;
    /**An element of the ModifyPart GUI*/
        @FXML
        private TextField txt_ModifyPart_MachineID_CompName;
    /**An element of the ModifyPart GUI*/
        @FXML
        private TextField txt_ModifyPart_Max;
    /**An element of the ModifyPart GUI*/
        @FXML
        private TextField txt_ModifyPart_Min;
    /**An element of the ModifyPart GUI*/
        @FXML
        private TextField txt_ModifyPart_PriceCost;
/**An action event method that sets text of a label.
 * The selection of the "In House" radio button sets the label text to "Machine ID."
 * @param event On selecting a radio button.*/
        @FXML
        void onClick_InHouse_ModifyPart(ActionEvent event) {

            lblModifyPart_Machine_Company.setText("Machine ID");
        }
    /**An action event method that sets text of a label.
     * The selection of the "Outsource" radio button sets the label text to "Company Name."
     * @param event On selecting a radio button.*/
        @FXML
        void onClick_Outsource_ModifyPart(ActionEvent event) {

            lblModifyPart_Machine_Company.setText("Company Name");
        }
/**An action event method that loads the Main GUI upon clicking a button.
 * The method does not save any modifiable changes to the product.
 * @param event A button click.*/
        @FXML
        void onAction_Cancel_ModifyPart(ActionEvent event) throws IOException{
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/lag311/pa/main.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
        }

    /**A method that returns an integer of a Part index.  The parameter of passedPart matches to the equivalent Part in the Inventory.getAllParts observable list using the getID method.
     * If the method does not return an index, then the method will return -1.
     * @param passPart A Part instance.*/
        public int getIndex(Part passPart) {
                for (Part part : Inventory.getAllParts()) {
                        if (part.getId() == passPart.getId()) {
                                int index = Inventory.getAllParts().indexOf(part);
                                return index;
                        }
                }
                return -1;
        }
/**An action event method the saves the modified Part and then loads the Main GUI.
 * The method error checks the input and will give an error message to the user if an exception is encountered.
 * If all data is the correct type, the method will save the modified Part to the Inventory Parts observable list.
 * @param event An event of a button press.*/
        @FXML
        void onAction_Save_ModifyPart(ActionEvent event) throws IOException {
            exceptionErrorTextModPart.setText("");
            boolean loadMain = false;
            try {
                int id = Integer.parseInt(txt_ModifyPart_ID.getText());
                String name = txt_ModifyPart_Name.getText();
                try {
                    Integer.parseInt(txt_ModifyPart_Inv.getText());
                } catch (NumberFormatException e) {
                    exceptionErrorTextModPart.setText("Inventory must be an integer\n");
                }
                try {
                    Double.parseDouble(txt_ModifyPart_PriceCost.getText());
                } catch (NumberFormatException e) {
                    exceptionErrorTextModPart.setText(exceptionErrorTextModPart.getText() + "Price must be an double\n");
                }
                try {
                    Integer.parseInt(txt_ModifyPart_Max.getText());
                } catch (Exception e) {
                    exceptionErrorTextModPart.setText(exceptionErrorTextModPart.getText() + "Max must be an integer\n");
                }
                try {
                    Integer.parseInt(txt_ModifyPart_Min.getText());
                } catch (Exception e) {
                    exceptionErrorTextModPart.setText(exceptionErrorTextModPart.getText() + "Min must be an integer\n");
                }
                int machineID;
                String companyName;
                if (radioModifyPart_InHouse.isSelected()) {
                    try {
                        Integer.parseInt(txt_ModifyPart_MachineID_CompName.getText());
                    } catch (NumberFormatException e) {
                        exceptionErrorTextModPart.setText(exceptionErrorTextModPart.getText() + "Machine ID must be an integer");
                    }
                }
                if (name.isEmpty()) {
                    exceptionErrorTextModPart.setText("Name must have input\n" + exceptionErrorTextModPart.getText());
                } else {
                    int index = getIndex(passedPart);
                    int inventory = Integer.parseInt(txt_ModifyPart_Inv.getText());
                    double price = Double.parseDouble(decimalFormat.format(Double.parseDouble(txt_ModifyPart_PriceCost.getText())));
                    int max = Integer.parseInt(txt_ModifyPart_Max.getText());
                    int min = Integer.parseInt(txt_ModifyPart_Min.getText());
                    if (index != -1) {
                        if (radioModifyPart_InHouse.isSelected()) {
                            machineID = Integer.parseInt(txt_ModifyPart_MachineID_CompName.getText());
                            if (inventory < 0 || price < 0 || max < 0 || min < 0||machineID<0) {
                                exceptionErrorTextModPart.setText("Please enter non negative numbers.");
                            } else {
                                if (max <= min) {
                                    exceptionErrorTextModPart.setText("Max must be bigger than min");
                                } else {
                                    if (inventory > max || inventory < min) {
                                        exceptionErrorTextModPart.setText("Inventory must be between max and min");
                                    } else {

                                        InHouse partModified = new InHouse(id, name, price, inventory, min, max, machineID);
                                        Inventory.updatePart(index, partModified);
                                        loadMain=true;
                                    }
                                }
                            }
                        } else if (!radioModifyPart_InHouse.isSelected()) {
                            companyName = txt_ModifyPart_MachineID_CompName.getText();
                            if (companyName.isEmpty()) {
                                exceptionErrorTextModPart.setText(exceptionErrorTextModPart.getText() + "Fill in company name\n");
                            } else if
                            (inventory < 0 || price < 0 || max < 0 || min < 0) {
                                exceptionErrorTextModPart.setText("Please enter non negative numbers.");
                            } else {
                                if (max <= min) {
                                    exceptionErrorTextModPart.setText("Max must be bigger than min");
                                } else {
                                    if (inventory > max || inventory < min) {
                                        exceptionErrorTextModPart.setText("Inventory must be between max and min");
                                    } else {
                                        Outsourced partModified = new Outsourced(id, name, price, inventory, min, max, companyName);
                                        Inventory.updatePart(index, partModified);
                                        loadMain=true;
                                    }
                                }
                            }
                        }
                    }
                }
                }
            catch(NumberFormatException e){
                System.out.println("error: " + e.getMessage());
            }
            if(loadMain){
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/lag311/pa/main.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        }
/**A Part instance used to hold the passed Part object from the Main GUI.*/
        private static Part passedPart= null;

/**A method that receives the Part object passed from the Main GUI.
 * It assigns that passed Part object to the class variable, passedPart.
 * @param pass A Part instance.*/
        public static void passPart (Part pass){

            passedPart=pass;
        }

/**A method that returns the String name of the passed Part.
 * The name is obtained using the getName() method from Inventory.
 * @return A string of the name.*/
        public String name(){
            return passedPart.getName();
        }

        /**A method that returns the String ID of the passed Part.
         *The ID is obtained using the getID() method from Inventory.
     * @return String of the ID.*/
        public String id(){
                int i =passedPart.getId();
                return Integer.toString(i);
        }
    /**A method that returns the String inventory of the passed Part.
     * The inv variable is obtained using the getStock method from Inventory.
     * @return String of the inventory number.*/
        public String inv(){
                int i =passedPart.getStock();
                return Integer.toString(i);
        }
    /**A method that returns the String max of the passed Part.
     * The max variable is obtained using the getMax() method from Inventory.
     * @return String of the max number.*/
        public String max(){
                int i =passedPart.getMax();
                return Integer.toString(i);
        }
    /**A method that returns the String min of the passed Part.
     * The min variable is obtained using the getMin() method from Inventory.
     * @return String of the min number.*/
        public String min(){
                int i =passedPart.getMin();
                return Integer.toString(i);
        }
    /**A method that returns the String price of the passed Part.
     * The price variable is obtained using the getPrice() method from Inventory.
     * @return String of the price number.*/
        public String price(){
                double i =passedPart.getPrice();
                return Double.toString(i);
        }
/**The method that initializes the ModifyPartController class.
 * It loads the text fields for the passed Part object in the text fields.
 * @param resourceBundle An instance of the ResourceBundle class.
 * @param url An instance of the URL class*/
        @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(passedPart instanceof Outsourced){
                lblModifyPart_Machine_Company.setText("Company Name");
                radioModifyPart_OutSourced.setSelected(true);
                txt_ModifyPart_MachineID_CompName.setText( ((Outsourced)passedPart).getCompanyName());
        } else {
                lblModifyPart_Machine_Company.setText("Machine ID");
                radioModifyPart_InHouse.setSelected(true);
                txt_ModifyPart_MachineID_CompName.setText(Integer.toString(((InHouse)passedPart).getMachineId()));
        }

        txt_ModifyPart_ID.setText(id());
        txt_ModifyPart_Name.setText(name());
        txt_ModifyPart_Inv.setText(inv());
        txt_ModifyPart_PriceCost.setText(price());
        txt_ModifyPart_Max.setText(max());
        txt_ModifyPart_Min.setText(min());
            }

}
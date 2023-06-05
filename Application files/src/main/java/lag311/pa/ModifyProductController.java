package lag311.pa;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.ResourceBundle;
/**A class that controls the Modify Product GUI. It allows the user to modify an existing Product and update the Product.
 * @author Leigh Grover */
public class ModifyProductController implements Initializable {
        Stage stage;
        Parent scene;
        /**An instance of DecimalFormat.
         * This is used to constrain double variables to only have up to two decimal places.*/
        private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");
/**An observable list that holds Part objects of the associated parts of the passed Product from the Main GUI.*/
        ObservableList<Part> associatedPartsList = passedProduct.getAssociatedParts();
        /**An element from the ModifyProduct GUI*/
        @FXML
        private Button btn_ModifyProd_Add;
        /**An element from the ModifyProduct GUI*/
        @FXML
        private Button btn_ModifyProd_Cancel;
        /**An element from the ModifyProduct GUI*/
        @FXML
        private Button btn_ModifyProd_Remove;
        /**An element from the ModifyProduct GUI*/
        @FXML
        private Button btn_ModifyProd_Save;
        /**An element from the ModifyProduct GUI*/
        @FXML
        private TableView<Part> ModifyProduct_AddedPartTable;
        /**An element from the ModifyProduct GUI*/
        @FXML
        private TableView<Part> ModifyProduct_PartTable;
        /**An element from the ModifyProduct GUI*/
        @FXML
        private TableColumn<Part, Integer> col_ModifyProduct_Inventory;
        /**An element from the ModifyProduct GUI*/
        @FXML
        private TableColumn<Part, Integer> col_ModifyProduct_Inventory_Remove;
        /**An element from the ModifyProduct GUI*/
        @FXML
        private TableColumn<Part, Integer> col_ModifyProduct_PartID;
        /**An element from the ModifyProduct GUI*/
        @FXML
        private TableColumn<Part, Integer> col_ModifyProduct_PartID_Remove;
        /**An element from the ModifyProduct GUI*/
        @FXML
        private TableColumn<Part, String> col_ModifyProduct_PartName;
        /**An element from the ModifyProduct GUI*/
        @FXML
        private TableColumn<Part, String> col_ModifyProduct_PartNameAdded;
        /**An element from the ModifyProduct GUI*/
        @FXML
        private TableColumn<Part, Double> col_ModifyProduct_Price;
        /**An element from the ModifyProduct GUI*/
        @FXML
        private TableColumn<Part, Double> col_ModifyProduct_Price_Remove;
        /**An element from the ModifyProduct GUI*/
        @FXML
        private Label lbl_ModifyProd_ID;
        /**An element from the ModifyProduct GUI*/
        @FXML
        private Label lbl_ModifyProd_Inv;
        /**An element from the ModifyProduct GUI*/
        @FXML
        private Label lbl_ModifyProd_Max;
        /**An element from the ModifyProduct GUI*/
        @FXML
        private Label lbl_ModifyProd_Min;
        /**An element from the ModifyProduct GUI*/
        @FXML
        private Label lbl_ModifyProd_Name;
        /**An element from the ModifyProduct GUI*/
        @FXML
        private Label lbl_ModifyProd_PriceCost;
        /**An element from the ModifyProduct GUI*/
        @FXML
        private Label dataExceptionWarning;
        /**An element from the ModifyProduct GUI*/
        @FXML
        private TextField txt_ModifyProduct_ID;
        /**An element from the ModifyProduct GUI*/
        @FXML
        private TextField txt_ModifyProduct_Inv;
        /**An element from the ModifyProduct GUI*/
        @FXML
        private TextField txt_ModifyProduct_Max;
        /**An element from the ModifyProduct GUI*/
        @FXML
        private TextField txt_ModifyProduct_Min;
        /**An element from the ModifyProduct GUI*/
        @FXML
        private TextField txt_ModifyProduct_Name;
        /**An element from the ModifyProduct GUI*/
        @FXML
        private TextField txt_ModifyProduct_PriceCost;
        /**An element from the ModifyProduct GUI*/
        @FXML
        private TextField search_PartName;

        /**An action event method that adds an associated Part object.
         * The Part is added to the observable list of associated Parts and are shown in the associated Parts table.
         * @param event Button click.*/
        @FXML
        void onClick_AddAssociatedPart(ActionEvent event)  throws IOException{
                if(ModifyProduct_PartTable.getSelectionModel().getSelectedItem()==null){
                        System.out.println("no item selected");
                } else
                {
                        associatedPartsList.add(ModifyProduct_PartTable.getSelectionModel().getSelectedItem());
                        ModifyProduct_AddedPartTable.setItems(associatedPartsList);
                }
        }
        /**An action event method that loads the Main GUI.
         * No modified elements are saved to the product.
         * @param event Button click.*/
        @FXML
        void onClick_CancelModifyProduct(ActionEvent event) throws IOException {
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/lag311/pa/main.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
        }
/**An action event method that disassociates a Part from the Product.
 * The user is asked to confirm removing the Part, and if they answer in the affirmative, the Part is removed from the Associated Parts observable list.
 * @param event Button click*/
        @FXML
        void onClick_RemoveAssociatedPart(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure that you want to remove this associated part?");
                alert.setTitle("Associated Parts");
                alert.setHeaderText("Remove");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.isPresent() && result.get()== ButtonType.OK) {
                        int index_remove;
                        index_remove = ModifyProduct_AddedPartTable.getSelectionModel().getSelectedIndex();
                        associatedPartsList.remove(index_remove);
                }
                ModifyProduct_AddedPartTable.setItems(associatedPartsList);
        }
        /**A boolean method that updates the associated Parts and saves the modified Product.
       * The existing associated Parts are cleared. The modified associated Parts are added to a local observable list of Parts and then added to the updated product.
         * The updated product then replaces the original product using the Inventory.updateProduct method.
         * @param productReplace
         * @return boolean result*/
        public boolean update(Product productReplace)
        {
               // ObservableList<Part> associatedPartsList = passedProduct.getAssociatedParts();
                for(Product product: Inventory.getAllProducts())
                {
                      if(product.getId() == passedProduct.getId()){
                              int index = Inventory.getAllProducts().indexOf(product);
                              ObservableList<Part> holderParts = FXCollections.observableArrayList();
                              for(Part part: associatedPartsList){
                                      holderParts.add(part);
                              }
                              Inventory.getAllProducts().get(index).getAssociatedParts().clear();
                              int size = holderParts.size();
                              for (Part part : holderParts) {
                                      productReplace.addAssociatedPart(part);
                              }
                              Inventory.updateProduct(index, productReplace);
                              return true;
                      }
                }
                return false;
        }
        /**An action event method that saves the modified product and then loads the Main GUI.
         * The method error checks the inputted data, and if correct, creates a new instance of Product that passes into the update method.
         * If the Update method returns true, the Main GUI loads.
         * RUNTIME ERROR: Initially I had created local variables to hold the data in the try/catch blocks of error checking the input data.
         * I found that I had to create the variables again to create the new Product object, which seemed like a factor that could slow down the execution time.
         *I changed this by only having the error check try/catch blocks test the input, while not creating variables to hold them. This initially worked,
         * but then my program stopped catching letters in the "price" text field, so I switched it the "try" blocks holding variables.
         * @param event Button click*/
        @FXML
        void onClick_SaveModifyProduct(ActionEvent event) throws IOException{
                dataExceptionWarning.setText("");
                try {
                        int id = Integer.parseInt(txt_ModifyProduct_ID.getText());
                        String name = txt_ModifyProduct_Name.getText();
                        try {
                               int inv= Integer.parseInt(txt_ModifyProduct_Inv.getText());
                        } catch (NumberFormatException e) {
                                dataExceptionWarning.setText(dataExceptionWarning.getText() + "Inventory must be an int\n");
                        }
                        try {
                              double price=  Double.parseDouble(txt_ModifyProduct_PriceCost.getText());
                        } catch (NumberFormatException e) {
                                dataExceptionWarning.setText(dataExceptionWarning.getText() + "Price must be an double\n");
                        }
                        try {
                               int max= Integer.parseInt(txt_ModifyProduct_Max.getText());
                        } catch (NumberFormatException e) {
                                dataExceptionWarning.setText(dataExceptionWarning.getText() + "Max must be an int\n");
                        }
                        try {
                               int min= Integer.parseInt(txt_ModifyProduct_Min.getText());
                        } catch (NumberFormatException e) {
                                dataExceptionWarning.setText(dataExceptionWarning.getText() + "Min must be an int");
                        }
                        if (name.isEmpty()) {
                                dataExceptionWarning.setText("Input needed in name\n" + dataExceptionWarning.getText());
                        } else {

                                double price = Double.parseDouble(decimalFormat.format(Double.parseDouble(txt_ModifyProduct_PriceCost.getText())));
                                int inventory = Integer.parseInt(txt_ModifyProduct_Inv.getText());
                                int max = Integer.parseInt(txt_ModifyProduct_Max.getText());
                                int min = Integer.parseInt(txt_ModifyProduct_Min.getText());
                                if (inventory < 0 || price < 0 || max < 0 || min < 0) {
                                        dataExceptionWarning.setText("Please enter non negative numbers.");

                                } else {
                                        if (max <= min) {
                                                dataExceptionWarning.setText("Maximum must be bigger or equal to Minimum");
                                        } else {
                                                if (inventory > max || inventory < min) {
                                                        dataExceptionWarning.setText("Inventory must be between max and min");
                                                } else {
                                                        Product product = new Product(id, name, price, inventory, min, max);
                                                        if (update(product)) {

                                                                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                                                                scene = FXMLLoader.load(getClass().getResource("/lag311/pa/main.fxml"));
                                                                stage.setScene(new Scene(scene));
                                                                stage.show();
                                                        }
                                                }
                                        }
                                }
                        }
                }catch (NumberFormatException e) {
                    System.out.println("error: "+e.getMessage());
                }
        }
        /**An on action event method that searches for a Part by ID or name.
         * If a matching part is not found, an error message will appear.
         * @param event Enter key press.*/
        @FXML
        void onEnter_SearchPart(KeyEvent event) {
                boolean partFound=false;
                if (event.getCode().equals(KeyCode.ENTER)) {
                        String input = search_PartName.getText();
                        if (input.matches("[0-9]+")) {
                                int input_Number = Integer.parseInt(input);
                                        for (int i = 0; i < ModifyProduct_PartTable.getItems().size(); i++) {
                                                if (ModifyProduct_PartTable.getItems().get(i).getId() == input_Number)
                                                {
                                                        ModifyProduct_PartTable.getSelectionModel().select(i);
                                                        partFound=true;
                                                }
                                        }
                        } else {
                                ObservableList<Part>parts = Inventory.lookupPart(input);
                                if(!parts.isEmpty()) {
                                        ModifyProduct_PartTable.setItems(parts);
                                        partFound=true;
                                }
                        }
                        if(!partFound) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Error Dialogue");
                                alert.setContentText("Part not found");
                                alert.showAndWait();

                }

                }
        }

        /**A Product instance to hold the selected product passed from the main GUI.*/
        private static Product passedProduct= null;
/**A method that assigns the product passed from the main GUI to a local instance of Product.
 * @param pass */
        public static void passProduct (Product pass){

                passedProduct=pass;
        }
/**A String method that assigns the result of getId of the passedProduct to a local variable.
 * @return String id*/
        String id(){

                return Integer.toString(passedProduct.getId());
        }
        /**A String method that assigns the result of getName() of the passedProduct name to a local variable.
         * @return String name*/
        public String name(){

                return passedProduct.getName();
        }
        /**A String method that assigns the result of getStock() of the passedProduct to a local variable.
         * @return String inv*/
        public String inv(){
                int inv =passedProduct.getStock();
                return Integer.toString(inv);
        }
        /**A String method that assigns the result of getMax() of the passedProduct to a local variable.
         * @return String max*/
        public String max(){
                int i =passedProduct.getMax();
                return Integer.toString(i);
        }
        /**A String method that assigns the result of getMin() of the passedProduct to a local variable.
         * @return String min*/
        public String min(){
                int i =passedProduct.getMin();
                return Integer.toString(i);
        }
        /**A String method that assigns the result of getPrice() of the passedProduct to a local variable.
         * @return String price*/
        public String price(){
                double i =passedProduct.getPrice();
                return Double.toString(i);
        }
/**A method that initializes the ModifyProductController.
 * It sets the input data for the Product to be modified through calling the local methods that return this data from the passed Product. The method populates the table view of all Parts
 * utilizing an observable list of Inventory.getAllParts().  The method populates the associated parts table through
 * adding each associated Part from the passed Product to a local observable list.
 * @param url Instance of the URL class.
 * @param resourceBundle Instance of the ResourceBundle class. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            txt_ModifyProduct_ID.setText(id());
            txt_ModifyProduct_Inv.setText(inv());
            txt_ModifyProduct_Max.setText(max());
            txt_ModifyProduct_Min.setText(min());
            txt_ModifyProduct_Name.setText(name());
            txt_ModifyProduct_PriceCost.setText(price());

            ObservableList<Part> partsList = Inventory.getAllParts();
            ModifyProduct_PartTable.setItems(partsList);
            col_ModifyProduct_PartID.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_ModifyProduct_PartName.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_ModifyProduct_Price.setCellValueFactory(new PropertyValueFactory<>("price"));
            col_ModifyProduct_Inventory.setCellValueFactory(new PropertyValueFactory<>("stock"));

            ObservableList<Part>associatedParts = FXCollections.observableArrayList();
            ObservableList<Part> associatedPartsList = passedProduct.getAssociatedParts();
        for(Part part : associatedPartsList){
                ObservableList<Part> allParts = Inventory.getAllParts();
                for (Part part_compare : allParts){
                        if(part == part_compare){
                                associatedParts.add(part);
                        }
                }
        }
         ModifyProduct_AddedPartTable.setItems(associatedParts);
        col_ModifyProduct_PartID_Remove.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_ModifyProduct_PartNameAdded.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_ModifyProduct_Inventory_Remove.setCellValueFactory(new PropertyValueFactory<>("stock"));
        col_ModifyProduct_Price_Remove.setCellValueFactory(new PropertyValueFactory<>("price"));


    }
}
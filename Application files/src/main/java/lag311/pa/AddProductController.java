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

/**A class that controls the Add Product GUI.
 * This class allows the user to modify a Product object or cancel and return to the Main GUI.
 * @author Leigh Grover
 */
public class AddProductController implements Initializable{
    Stage stage;
    Parent scene;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    /**An observable list of selected Part objects.
     * This observable list holds the Part objects that the user selects to save to the new Product's associated list.*/
    private  ObservableList<Part> namedPartsAdded = FXCollections.observableArrayList();

    /**An element in the AddProduct GUI*/
    @FXML
    private Button btn_toAddedPartList;
    /**An element in the AddProduct GUI*/
    @FXML
    private Button btnAddProductCancel;
    /**An element in the AddProduct GUI*/
    @FXML
    private Button btnAddProductRemovePart;
    /**An element in the AddProduct GUI*/
    @FXML
    private Button btnAddProductSave;
    /**An element in the AddProduct GUI*/
    @FXML
    private TableView <Part> AddPartstoProductTable;
    /**An element in the AddProduct GUI*/
    @FXML
    private TableView<Part> AddedPartstoProductTable;
    /**An element in the AddProduct GUI*/
    @FXML
    private TableColumn<Part, Integer> colAddProductID;
    /**An element in the AddProduct GUI*/
    @FXML
    private TableColumn<Part, Integer> colAddProductIDRemove;
    /**An element in the AddProduct GUI*/
    @FXML
    private TableColumn<Part, Integer> colAddProductInventoryLevel;
    /**An element in the AddProduct GUI*/
    @FXML
    private TableColumn<Part, Integer> colAddProductInventoryRemove;
    /**An element in the AddProduct GUI*/
    @FXML
    private TableColumn<Part, String> colAddProductNameRemove;
    /**An element in the AddProduct GUI*/
    @FXML
    private TableColumn<Part, String> colAddProductPartName;
    /**An element in the AddProduct GUI*/
    @FXML
    private TableColumn<Part, Double> colAddProductPrice;
    /**An element in the AddProduct GUI*/
    @FXML
    private TableColumn<Part, Double> colAddProductPriceRemove;
    /**An element in the AddProduct GUI*/
    @FXML
    private Label lblAddProductID;
    /**An element in the AddProduct GUI*/
    @FXML
    private Label lblAddProductInv;
    /**An element in the AddProduct GUI*/
    @FXML
    private Label lblAddProductMax;
    /**An element in the AddProduct GUI*/
    @FXML
    private Label lblAddProductMin;
    /**An element in the AddProduct GUI*/
    @FXML
    private Label lblAddProductName;
    /**An element in the AddProduct GUI*/
    @FXML
    private Label lblAddProductPrice;
    /**An element in the AddProduct GUI*/
    @FXML
    private Label lblAddProductTitle;
    /**An element in the AddProduct GUI*/
    @FXML
    private Label label_warning_1;
    /**An element in the AddProduct GUI*/
    @FXML
    private TextField txtAddProductID;
    /**An element in the AddProduct GUI*/
    @FXML
    private TextField txtAddProductInv;
    /**An element in the AddProduct GUI*/
    @FXML
    private TextField txtAddProductMax;
    /**An element in the AddProduct GUI*/
    @FXML
    private TextField txtAddProductMin;
    /**An element in the AddProduct GUI*/
    @FXML
    private TextField txtAddProductName;
    /**An element in the AddProduct GUI*/
    @FXML
    private TextField txtAddProductPrice;
    /**An element in the AddProduct GUI*/
    @FXML
    private TextField txtAddProductSearch;
    /**An element in the AddProduct GUI*/

/**This method adds a part to the associated parts list. The selected part is added to the observable list for associated parts.
 * @param event A button click*/
    @FXML
    void onClickAddAssociatedPart(ActionEvent event) throws IOException{
        System.out.println(AddPartstoProductTable.getSelectionModel().getSelectedItem());
        if(AddPartstoProductTable.getSelectionModel().getSelectedItem()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
                       alert.setTitle("Error Dialogue");
                       alert.setContentText("No Part Selected");
                       alert.showAndWait();
        } else
        {
            namedPartsAdded.add(AddPartstoProductTable.getSelectionModel().getSelectedItem());
        AddedPartstoProductTable.setItems(namedPartsAdded);
        }
    }
/**A method that navigates out of the AddProduct GUI. It returns the user to the main menu with no data saved.
 * @param event A button click.*/
    @FXML
    void onClickAddProductCancel(ActionEvent event) throws IOException{
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/lag311/pa/main.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


/**This method returns a unique product ID.
 * A nested loop is used to determine the highest product ID and then creates an ID by adding 1 to the highest ID value.
 * @return An integer which is the new product ID.*/
    public int incrementID(){
        int productIdCounter = 0;
        ObservableList<Product> productsList = Inventory.getAllProducts();

        for(Product product: productsList){
            productIdCounter = product.getId();
            for(Product product_search : productsList){
                if(productIdCounter<product_search.getId()){
                    productIdCounter= product_search.getId();
                }
            }
        }
        return ++productIdCounter;
    };
    /**This method saves a new Product instance. It error checks the user inputted data, creates a new instance of Product, adds
     * the associated Parts and adds the Product to Inventory class observable list of Products. The user is then returned to the main menu.
     * @param event Button click.*/
    @FXML
    void onClickAddProductSave(ActionEvent event) throws IOException {
        label_warning_1.setText("");
        try {
            int id = incrementID();
                String name = txtAddProductName.getText();
                try {
                     Integer.parseInt(txtAddProductInv.getText());
                } catch (NumberFormatException e){
                    label_warning_1.setText(label_warning_1.getText()+"Inventory must be an int\n");
            }
                try{
                Double.parseDouble(txtAddProductPrice.getText());} catch (NumberFormatException e){
                    label_warning_1.setText(label_warning_1.getText()+"Price must be an double\n");
                }
                try{
                Integer.parseInt(txtAddProductMax.getText());} catch(NumberFormatException e){
                    label_warning_1.setText(label_warning_1.getText()+"Max must be an int\n");
                }
                try{
                Integer.parseInt(txtAddProductMin.getText());} catch (NumberFormatException e){
                    label_warning_1.setText(label_warning_1.getText()+"Min must be an int\n");
                }
            if (name.isEmpty()) {
                label_warning_1.setText("Input needed in name\n"+ label_warning_1.getText());
            } else {
                int inventory = Integer.parseInt(txtAddProductInv.getText());
                double price = Double.parseDouble(df.format(Double.parseDouble(txtAddProductPrice.getText())));
                int max = Integer.parseInt(txtAddProductMax.getText());
                int min = Integer.parseInt(txtAddProductMin.getText());
                if (inventory < 0 || price < 0 || max < 0 || min < 0) {
                    label_warning_1.setText("Please enter non negative numbers.\n");
                } else {
                    if (max <= min) {
                        label_warning_1.setText("Max must be bigger than min.\n");
                    } else {
                        if (inventory > max || inventory < min) {
                            label_warning_1.setText("Inventory must be between max and min.\n");
                        } else {
                            Product product = new Product(id, name, price, inventory, min, max);
                            for (Part part : namedPartsAdded) {
                                product.addAssociatedPart(part);
                            }
                            Inventory.addProduct(product);
                            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                            scene = FXMLLoader.load(getClass().getResource("/lag311/pa/main.fxml"));
                            stage.setScene(new Scene(scene));
                            stage.show();
                        }
                    }
                }
            }

            } catch(NumberFormatException e){
                System.out.println("error: " + e);
            }
        }
    /**The method removes a selected associated Part. On the click event, an alert pops up to verify this action, and if confirmed,
     * the method removes the Part from the added associated parts list.
     * @param event Button click.*/
    @FXML
    void onClickRemoveAssociatedPart(ActionEvent event) {
        int index_remove;
        index_remove = AddedPartstoProductTable.getSelectionModel().getSelectedIndex();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure that you want to remove this associated part?");
        alert.setTitle("Associated Parts");
        alert.setHeaderText("Remove");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get()== ButtonType.OK) {
       namedPartsAdded.remove(index_remove);
            }
    }
    /**The method searches for a Part.
     *It captures the text input, and searches a match for either the ID or name.
     * @param event Enter key click.*/
    @FXML
    void onEnter_SearchPart_toAdd(KeyEvent event) {
        boolean partFound=false;
        if (event.getCode().equals(KeyCode.ENTER)) {
            String capt = txtAddProductSearch.getText();
            if (capt.matches("[0-9]+")) {
                int capt_1 = Integer.parseInt(capt);
                    for (int i = 0; i < AddPartstoProductTable.getItems().size(); i++) {
                        if (AddPartstoProductTable.getItems().get(i).getId() == capt_1) {
                            //System.out.println("Selected Index : " + i);
                            partFound=true;
                            AddPartstoProductTable.getSelectionModel().select(i);

                        }
                    }

            } else {
                ObservableList<Part>parts = Inventory.lookupPart(capt);
                if(!parts.isEmpty()) {
                    partFound=true;
                    AddPartstoProductTable.setItems(parts);
               }
                }
            if(!partFound){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialogue");
                alert.setContentText("Part not found");
                alert.showAndWait();
            }
            }
        }

    /**This method initializes the AddProductController class.
     * It creates the observable lists to populate the tables of parts in the GUI.
     * @param url Instance of the URL class.
     * @param resourceBundle Instance of ResourceBundle class.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Part> partsList = Inventory.getAllParts();
        AddPartstoProductTable.setItems(partsList);
        colAddProductID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAddProductPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddProductPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colAddProductInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));

        ObservableList<Part> namedPartsAdded = FXCollections.observableArrayList();
        AddedPartstoProductTable.setItems(namedPartsAdded);
        colAddProductIDRemove.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAddProductNameRemove.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddProductInventoryRemove.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colAddProductPriceRemove.setCellValueFactory(new PropertyValueFactory<>("price"));

    }
}

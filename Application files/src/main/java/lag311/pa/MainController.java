
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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**This class controls the Main GUI page.
 * @author Leigh Grover */
public class MainController implements Initializable {
    Stage stage;
    Parent scene;

    /**An element of the Main GUI*/
    @FXML
    private Button btnAddPart;
    /**An element of the Main GUI*/
    @FXML
    private Button btnAddProduct;
    /**An element of the Main GUI*/
    @FXML
    private Button btnDeletePart;
    /**An element of the Main GUI*/
    @FXML
    private Button btnDeleteProduct;
    /**An element of the Main GUI*/
    @FXML
    private Button btnExitMain;
    /**An element of the Main GUI*/
    @FXML
    private Button btnModifyPart;
    /**An element of the Main GUI*/
    @FXML
    private Button btnModifyProduct;
    /**An element of the Main GUI*/
    @FXML
    private TableColumn<Part, Integer> partID;
    /**An element of the Main GUI*/
    @FXML
    private TableColumn<Part, Integer> partInventoryLevel;
    /**An element of the Main GUI*/
    @FXML
    private TableColumn<Part, String> partName;
    /**An element of the Main GUI*/
    @FXML
    private TableColumn<Part, Double> partPricePerUnit;
    /**An element of the Main GUI*/
    @FXML
    private TableView<Part> partsTableMain;
    /**An element of the Main GUI*/
    @FXML
    private TableColumn<Product, Integer> productID;
    /**An element of the Main GUI*/
    @FXML
    private TableColumn<Product, Integer> productInventoryLevel;
    /**An element of the Main GUI*/
    @FXML
    private TableColumn<Product, String> productName;
    /**An element of the Main GUI*/
    @FXML
    private TableColumn<Product, Double> productPricePerUnit;
    /**An element of the Main GUI*/
    @FXML
    private TableView<Product> productsTableMain;
    /**An element of the Main GUI*/
    @FXML
    private TextField txtSearchPart;
    /**An element of the Main GUI*/
    @FXML
    private TextField txtSearchProducts;

//
//    @FXML
//    private Label warning_deleteProduct;
    /**An action event method that loads the AddPart GUI.
     * @param actionEvent Button click.*/
    @FXML
    public void onActionAddPart(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/lag311/pa/AddPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
/**An action event method that loads the AddProduct GUI.
 * @param actionEvent Button click.*/
    @FXML
    public void onActionAddProduct(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/lag311/pa/AddProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**A method that updates a product's associated parts observable list based off of the ID of a recently deleted part.
     * A nested for loop is used to run through products and locate associated parts with a matching ID of the deleted part. The product is updated
     * if an associated part is deleted.
     * @param ID
     * @return boolean*/
public boolean updateAssociatedParts(int ID){
        boolean success=false;
        int index=0;
        Product product_alter=null;
        Part part_delete = null;
    for(Product product : Inventory.getAllProducts()){
                            for(Part partSearch : product.getAssociatedParts()){
                                if( partSearch.getId() == ID){
                                    part_delete=partSearch;
                                    product_alter=product;
                                    index= Inventory.getAllProducts().indexOf(product);

                                }
                            }
                        }

    if(product_alter!=null) {
        success = product_alter.getAssociatedParts().remove(part_delete);
    }
    if(success) {
        Inventory.updateProduct(index, product_alter);
    }
    return success;
}
    /**An action event method that deletes a selected part. A warning window is triggered to verify the delete request. If
     * confirmed, the selected part is deleted using the Inventory deletePart method.  If the deletePart boolean result is true, then
     * the updatedAssociatedParts function is called to update all Products that use the deleted part using a while loop.
     * LOGIC ERROR: Initially, I had assumed that upon deleting a Part from the Inventory observable list of Parts, the Part would no
     * longer show up as an Associated Part when modifying a Product.  However, I found that this was not the case, and so I had to
     * create a method to update the Associated Parts of all Products after a Part is deleted. This is the updateAssociatedParts method above.
     * @param actionEvent Button click.*/
    @FXML
    public void onActionDeletePart(ActionEvent actionEvent) throws IOException {
        Part partSelected = partsTableMain.getSelectionModel().getSelectedItem();
        int ID = partSelected.getId();
            if(partSelected!=null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure that you want to delete this part?");
                alert.setTitle("Parts");
                alert.setHeaderText("Delete");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                   boolean deleteOK= Inventory.deletePart(partSelected);
                    if(deleteOK){
                        System.out.println("delete was ok");
                        boolean tracker=true;
                       while(tracker){
                           tracker = updateAssociatedParts(ID);
                       }
                        partsTableMain.setItems(Inventory.getAllParts());
                    }
                }
            }else{
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setTitle("Error Dialogue");
                alertError.setContentText("Please select a part to delete.");
                alertError.showAndWait();
            }
    }
    /**An action even method that deletes a product. The method checks whether a product has associated parts prior
     * to deleting.  If no associated parts, the method verifies user intent to delete and then calls the Inventory delete product method.
     * If no product is selected, an error message will pop up.
     * @param actionEvent Button click.*/
    @FXML
    public void onActionDeleteProduct(ActionEvent actionEvent) {
        try {
            Product productSelected = null;
            productSelected = productsTableMain.getSelectionModel().getSelectedItem();
            int index = Inventory.getAllProducts().indexOf(productSelected);
            ObservableList<Part> associatedParts = FXCollections.observableArrayList();
            associatedParts = Inventory.getAllProducts().get(index).getAssociatedParts();
            if (!associatedParts.isEmpty()) {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setTitle("Error Dialogue");
                alertError.setContentText("Remove associated parts before deleting.");
                alertError.showAndWait();
            } else if (productSelected!=null){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure that you want to delete this product?");
                alert.setTitle("Products");
                alert.setHeaderText("Delete");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    if(!Inventory.deleteProduct(productSelected)){
                        System.out.println("Delete product failed.");
                    } else {
                        productsTableMain.setItems(Inventory.getAllProducts());
                    };
                }
            } else {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setTitle("Error Dialogue");
                alertError.setContentText("Please select a product to delete.");
                alertError.showAndWait();
            }
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());

        }
    }
/**A method that exits the inventory application..
 * @param actionEvent  Button click.*/
    @FXML
    public void onActionExitInvMgmtSystem(ActionEvent actionEvent) {
        System.exit(0);
    }
    /**An action event that loads the Modify Part GUI. It captures and passes the selected part's ID to the
     * passPart method in the ModifyPartController.
     * @param actionEvent Button click.*/
    @FXML
    public void onActionModifyPart(ActionEvent actionEvent) throws IOException {
        try {
            int idPart;
            if(partsTableMain.getSelectionModel().getSelectedItem()!=null) {
                idPart = partsTableMain.getSelectionModel().getSelectedItem().getId();
                ModifyPartController.passPart(Inventory.lookupPart(idPart));
                stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/lag311/pa/ModifyPart.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialogue");
                alert.setContentText("Please select a part to modify.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }
/**An action event method that loads the ModifyProduct GUI. It passes the selected product's ID to the passProduct
 * method in the ModifyProduct Controller.  If no product is selected, a warning message pops up.
 * @param actionEvent Button click.*/
    @FXML
    public void onActionModifyProduct(ActionEvent actionEvent) throws IOException {
        try {
            int idProduct;
            if(productsTableMain.getSelectionModel().getSelectedItem()!=null) {
                idProduct = productsTableMain.getSelectionModel().getSelectedItem().getId();
                ModifyProductController.passProduct(Inventory.lookupProduct(idProduct));
                stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/lag311/pa/ModifyProduct.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialogue");
                alert.setContentText("Please select a product to modify.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }
    /**An action event method that searches for a matching Part.
    *The method looks up the part either using the numeric input to search a match for ID versus a string input
    * for whether the input is contained within the name.
    * @param event Enter key press. */
    @FXML
    void onEnter_Pressed_SearchParts(KeyEvent event) {
        boolean partFound=false;
        try {
            if (event.getCode().equals(KeyCode.ENTER)) {
                String input = txtSearchPart.getText();
                if (input.matches("[0-9]+")) {
                    int numericInput = Integer.parseInt(input);
                    for (int i = 0; i < partsTableMain.getItems().size(); i++) {
                        if (partsTableMain.getItems().get(i).getId() == numericInput) {
                            //System.out.println("Selected Index : " + i);
                            partsTableMain.getSelectionModel().select(i);
                            partFound = true;
                        }
                    }

                } else {
                    ObservableList<Part> parts = Inventory.lookupPart(input);
                    if (!parts.isEmpty()) {
                        partFound = true;
                        partsTableMain.setItems(parts);
                    }
                }

                if (!partFound) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialogue");
                    alert.setContentText("Part not found");
                    alert.showAndWait();
                }
            }
        } catch (Exception e){
            System.out.println("Error: "+e);
        }
    }
    /**An action event method that searches for a matching product.
     *The method looks up the product either using the numeric input to search a match for ID versus a string input
     * for whether the input is contained within the name.
     * @param event Enter key press.*/
    @FXML
  void onEnter_SearchProducts(KeyEvent event) {
        boolean productFound=false;
        if (event.getCode().equals(KeyCode.ENTER)) {
            String input = txtSearchProducts.getText();
            if (input.matches("[0-9]+")) {
                int input_numeric = Integer.parseInt(input);
                    for (int i = 0; i < productsTableMain.getItems().size(); i++) {
                        if (productsTableMain.getItems().get(i).getId() == input_numeric) {
                            productsTableMain.getSelectionModel().select(i);
                            productFound = true;
                        }
                    }
                }
             else {
                ObservableList<Product> products = Inventory.lookupProduct(input);
                if (!products.isEmpty()) {
                    productFound = true;
                    productsTableMain.setItems(products);
                }
            }
            if (!productFound) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialogue");
                alert.setContentText("Product not found");
                alert.showAndWait();
            }
        }
    }


/**A method that starts and loads the MainController GUI. It loads the table views through calling the getAllParts and getAllProducts
 * methods in the Inventory class to return observable lists of Parts and Products, and then populates the table cells by calling the
 * get methods for Parts and Products.
 * @param url An instance of the URL class.
 * @param resourceBundle An instance of the ResourceBundle class.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Part> partsList = Inventory.getAllParts();
        partsTableMain.setItems(partsList);
        partID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
        partInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));

        productsTableMain.setItems(Inventory.getAllProducts());
        productID.setCellValueFactory(new PropertyValueFactory<>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

}

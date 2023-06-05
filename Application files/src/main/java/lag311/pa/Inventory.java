package lag311.pa;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**The inventory class has a dependency on the Part and Product classes.
 * The inventory class creates the observable lists of Parts and Products and contains methods to access and manipulate those observable lists.
 * @author Leigh Grover */
public class Inventory {
/**An observable list to hold all Part objects.*/
 private static ObservableList<Part> allParts = FXCollections.observableArrayList();

 /**An observable list to hold all Product objects.*/
 private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

/**A method to add a Part object to the observable list allParts.
 * @param part An instance of Part.
 * */
    public static void addPart(Part part){

        allParts.add(part);
    }

    /**A method to add a Part object to the observable list allParts.
     * @param product An instance of Product.*/
    public static void addProduct(Product product){

        allProducts.add(product);
    }
    /**A method to look up an instance of Part. The partID is used as a parameter to find
     * the matching Part object in allParts.
     * @param partID The ID of a Part object.
     * @return The method returns a Part object.*/
    public static Part lookupPart (int partID) {
        Part part = null;
        for (Part partSearch : allParts) {
            if (partSearch.getId() == partID) {
                part = allParts.get(allParts.indexOf(partSearch));
                return part;
            }
        }
        return part;
    }

/**A method to look up a product by productID.
 * The productID is used as a parameter to find the matching product object in allProducts.
 * @param productId The ID of a Product object.
 * @return The method returns a Product object.*/
    public static Product lookupProduct(int productId) {
        Product product = null;

        for (Product productSearch : allProducts) {
            if (productSearch.getId() == productId){
                product = allProducts.get(allProducts.indexOf(productSearch));
                return product;
            }
        }
        return product;
    }
    /**A method to look up a Part by Part name. Part name is used as a parameter to find the matching Part in allParts.
     * @param partName A name of a Part object.
     * @return An observable list called namedParts.*/
public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> namedParts = FXCollections.observableArrayList();
        ObservableList<Part> allPartsList = Inventory.getAllParts();
       for(Part part : allPartsList){
           if (part.getName().toLowerCase().contains(partName.toLowerCase())) {
               namedParts.add(part);
           }
       }
        return namedParts;
    }

/**A method to looks up a Product by Product name. Product name is used as a parameter to find the matching Product in allProducts.
 * @param productName A name of a Product object.
 * @return An observable list called namedProduct.*/
    public static ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> namedProduct = FXCollections.observableArrayList();
        ObservableList<Product> allProductsList = Inventory.getAllProducts();
        for(Product product : allProductsList){
            if (product.getName().toLowerCase().contains(productName.toLowerCase())) {
                namedProduct.add(product);
            }
        }
        return namedProduct;
    }
/**A method that updates a Part object. The parameters of index and selected part are passed to update the
 * particular Part object that is stored in allParts.
 * @param index The index of a Part object.
 * @param selectedPart The Part object*/
    public static void updatePart(int index, Part selectedPart){

        allParts.set(index, selectedPart);
    }

/**A method that updates a Product object. The parameters of index and selected part are passed to update the
 * particular Product object that is stored in allProducts.
 * @param index The index of a Product object.
 * @param newProduct The Product object*/
public static void updateProduct(int index, Product newProduct){

    allProducts.set(index, newProduct);
}

/**A method that deletes a Part object. It uses the Part object parameter to remove the matching Part by
 * calling the remove method of getAllParts().
 * @param partSelected An instance of Part.
 * @return boolean foundPart*/
public static boolean deletePart (Part partSelected) {

    return getAllParts().remove(partSelected);
}

/**This method deletes a selected Product object. It uses the Product object parameter to remove the matching Product by
 * calling the remove method of getAllProducts().
 * @param selectedProduct An instance of Product.
 * @return boolean */
public static boolean deleteProduct(Product selectedProduct) {

    return getAllProducts().remove(selectedProduct);

}

/**This method returns an observable list of all Part objects.
 * @return allParts observable list.*/
 public static ObservableList<Part> getAllParts(){

     return allParts;
 };

 /**This method returns an observable list of all Product objects.
     * @return allProducts observable list.*/
 public static ObservableList<Product> getAllProducts(){
        return allProducts;
    };
}

package models;

import java.util.ArrayList;

public class Inventario {
    ArrayList<Producto> productList = new ArrayList<>();

    public ArrayList<Producto> getProductList() {
        return productList;
    }
    public boolean setProductList(Producto newProduct) {
        return productList.add(newProduct);
    }
}
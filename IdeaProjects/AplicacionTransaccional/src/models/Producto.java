package models;

public class Producto {
    String name;
    float priece;
    int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPriece() {
        return priece;
    }

    public void setPriece(float priece) {
        this.priece = priece;
    }

    @Override
    public String toString() {
        return  name +" "+
                "{ Precio=" + priece +
                ", Cantidad=" + quantity +
                " }";
    }

}
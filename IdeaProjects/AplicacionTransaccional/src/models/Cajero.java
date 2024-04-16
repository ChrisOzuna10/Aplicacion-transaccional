package models;

public class Cajero {
    float totalVendido;

    public float getTotalVendido() {
        return totalVendido;
    }
    public void realizarVenta(float total){
        this.totalVendido = this.totalVendido + total;
    }
    public void reiniciarTotalVendido (){
        totalVendido = 0;
    }
}
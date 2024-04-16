package models;

public class Administrador {
    String name;
    String password;
    Cajero newCajero = new Cajero();
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Cajero getNewCajero() {
        return newCajero;
    }
    public float realizarArqueo(){
        return newCajero.getTotalVendido();
    }
}
package Ventanas;
import javax.swing.*;

public class Componentes {
    private String usuario;
    private String contraseña;

    String[] Burgger = {
            "Hamburguesa clásica",
            "Hamburguesa de carne",
            "Hamburguesa de pollo",
            "Hamburguesa vegeteriana",
            "Hamburguesa de pollo y verduras"
    };

    public String[] Lista() {
        return Burgger;
    }

    public void Mensaje() {
        JOptionPane.showMessageDialog(null, "¡Cocinando!");
        JOptionPane.showMessageDialog(null, "El precio es s/.3.50");
    }

    public void Mensaje2() {
        String op = String.valueOf(JOptionPane.showConfirmDialog(null, "¿Estas seguro que quieres confirmar?", "Confirmación", JOptionPane.YES_NO_OPTION));
    }
    public String getUsuario(){
        usuario = "user";
        return usuario;
    }
    public  String getContraseña(){
        contraseña = "user";
        return contraseña;
    }
}

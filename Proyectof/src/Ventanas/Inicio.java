package Ventanas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent; //Importación de ActionEvent para la creación de eventos.
import java.awt.event.ActionListener; //Importación de ActionListener para el oyente de acción
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//Creación del menú inicio
public class Inicio extends JFrame{
    //Instanciando objeto de tipo panel
    JPanel panel = new JPanel();//creación de objeto de tipo panel.
//Método de ubicación y tamaño de la ventana.
    public Inicio(){
        setSize(700,500);
        setResizable(false); //bloqueo de ventana para evitar redimensión.
        setLocationRelativeTo(null);
        setTitle("Inicio");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Components();
    }
//Método de inicialización de componentes de la ventana.
    private void Components(){
        Panel_1();
        Buttons();
        Tittle();
    }
//Método de ajuste del panel de ventana.
    private void Panel_1(){
        panel.setLayout(null);//Desbloqueo de Layout para el ajuste libre de los componentes.
        this.getContentPane().add(panel);//Agregando el panel a la ventana Gestor.
        panel.setBackground(Color.DARK_GRAY);//Agregando Color al panel.

    }
// Método de ubicación de botones.
    private void Tittle(){
        //Label de Título.
        JLabel label = new JLabel();
        label.setText("El Glotón");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(200, 30, 300, 80);
        label.setFont(new Font("Bauhaus 93", 1, 60));
        label.setForeground(Color.WHITE);
        panel.add(label);
        //Label de Selecciones.
        JLabel label1 = new JLabel();
        label1.setText("Ver nuestras Bebidas");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setBounds(100, 300, 200, 100);
        label1.setFont(new Font("Berlin Sans FB Demi", 1, 18));
        label1.setForeground(Color.WHITE);
        panel.add(label1);
        //Label de Selección 2.
        JLabel label2 = new JLabel();
        label2.setText("Ver nuestro menú");
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setBounds(380,300,200,100);
        label2.setFont(new Font("Berlin Sans FB Demi", 1, 18));
        label2.setForeground(Color.WHITE);
        panel.add(label2);
    }
//Método de ubicación de botones.
    private void Buttons(){
        //Botón 1 encargado de dirigir a la sección de elección de ingredientes.
        ImageIcon image = new ImageIcon("bebida.png");
        JButton button1 = new JButton();
        button1.setBounds(90, 140, 220, 190);
        button1.setIcon(new ImageIcon(image.getImage().getScaledInstance(250,250, Image.SCALE_SMOOTH)));
        button1.setBackground(Color.DARK_GRAY);
        button1.setBorderPainted(false);
        panel.add(button1);
        //Botón 2 encargado de dirigir a la sección de menú de comida.
        ImageIcon image_2 = new ImageIcon("Menu.png");
        JButton button2 = new JButton();
        button2.setBounds(370, 140, 220, 190);
        button2.setIcon(new ImageIcon(image_2.getImage().getScaledInstance(240,220, Image.SCALE_SMOOTH)));
        button2.setBackground(Color.DARK_GRAY);
        button2.setBorderPainted(false);
        panel.add(button2);
        //Botón de ajustes encargado de dirigir al gestor de base de datos.
        ImageIcon image3 = new ImageIcon("ajustes.png");
        JButton button3 = new JButton();
        button3.setBounds(640, 420, 30, 30);
        button3.setIcon(new ImageIcon(image3.getImage().getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING)));
        button3.setBackground(Color.DARK_GRAY);
        button3.setBorderPainted(false);
        panel.add(button3);

//OYENTES DE BOTONES.
        //Oyente del botón 1 encargado de dirigir a ventana elección de ingrediente.
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Bebidas bebidas = new Bebidas();
                bebidas.setVisible(true);
                dispose();
            }
        };
        button1.addActionListener(actionListener);

        //Oyente del botón 2 encargado de dirigir a ventana de selección de menú de comida.
        ActionListener actionListener1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu();
                menu.setVisible(true);
                dispose();
            }
        };
        button2.addActionListener(actionListener1);

        //Oyente del botón 3 encargado de dirigir al gestor de base de datos.
        ActionListener actionListener2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Acceso acceso = new Acceso();
                acceso.setVisible(true);
                dispose();
            }
        };
        button3.addActionListener(actionListener2);

        button1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                button1.setBackground(Color.GRAY);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                button1.setBackground(Color.DARK_GRAY);
            }
        });
        button2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                button2.setBackground(Color.GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button2.setBackground(Color.DARK_GRAY);
            }
        });
    }
}

package Ventanas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

public class GestorMenu extends JFrame{
    JPanel panel = new JPanel(); //creación de objeto de tipo panel.
    private JTable table;
    private JTextField textField ;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private Connection con;
    private DefaultTableModel modelo;
    private JTextField textField4;
    private JComboBox comboBox;

    //Método de ubicación y tamaño de la ventana.
    public GestorMenu(){
        setSize(900,800);
        setBackground(Color.DARK_GRAY);
        setResizable(false); //bloqueo de ventana para evitar redimensión.
        setLocationRelativeTo(null);
        setTitle("Gestionar");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Components();
    }
    //Método de inicialización de componentes de la ventana.
    private void Components(){
        Panel();
        Tables();
        Button();
        TextField();
        Labels();
        Actualizar();

        Fondo();
    }
    private void Fondo(){
        JLabel fondo = new JLabel();
        fondo.setBounds(0,0,700,300);
        fondo.setBackground(Color.gray);
        fondo.setOpaque(true);
        panel.add(fondo);
    }
    private void Panel(){
        panel.setLayout(null); //Desbloqueo de Layout para el ajuste libre de los componentes
        panel.setBounds(0,300,700,600);
        panel.setSize(700,600);
        this.getContentPane().add(panel);//Agregando el panel a la ventana Gestor.
        panel.setBackground(Color.DARK_GRAY); //Agregando Color al panel .
    }

    //CREACIÓN DE TEXTFIELD PARA LA ENTRADA DE TEXTO, PARA AGREGAR O MODIFICAR DATOS DE LA BASE DE DATOS.
    private void TextField(){
        //textfield de Producto.
        textField = new JTextField();
        textField.setBounds(200,350,200,25);
        textField.setFont(new Font("Arial",1,15));
        panel.add(textField);
        //textfield de Cantidad.
        textField1 = new JTextField();
        textField1.setBounds(200,400,200,25);
        textField1.setFont(new Font("Arial",1,15));
        panel.add(textField1);
        //textfield de Precio.
        textField2 = new JTextField();
        textField2.setBounds(200,450,200,25);
        textField2.setFont(new Font("Arial",1,15));
        panel.add(textField2);
    }
    private void Labels(){
        JLabel label = new JLabel();
        label.setBounds(20,350,200,30);
        label.setText("Número: ");
        label.setFont(new Font("Arial",1,16));
        label.setForeground(Color.WHITE);
        panel.add(label);

        JLabel label1 = new JLabel();
        label1.setBounds(20,400,200,30);
        label1.setText("Nombre: ");
        label1.setFont(new Font("Arial",1,16));
        label1.setForeground(Color.WHITE);
        panel.add(label1);

        JLabel label2 = new JLabel();
        label2.setBounds(20,450,200,30);
        label2.setText("Precio: ");
        label2.setFont(new Font("Arial",1,16));
        label2.setForeground(Color.WHITE);
        panel.add(label2);
    }

    //CREACIÓN DE UNA TABLA QUE MUESTRA UNA TABLA DE BASE DE DATOS MYSQL
    private void Tables(){
        //Creación de un try para crear una excepción.
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/productos","root","");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from menu");
            modelo = new DefaultTableModel();
            table = new JTable(modelo);
            modelo.addColumn("N°");
            modelo.addColumn("Hamburguesas");
            modelo.addColumn("Precio");
            while (rs.next()){
                Object [] fila = new Object[3];
                for (int i = 0; i<3;i++){
                    fila[i] = rs.getObject(i+1);
                }
                modelo.addRow(fila);
            }

            //ESTILO Y AJUSTES DEL NOMBRE DE LAS COLUMNAS.
            add(new JScrollPane(table),BorderLayout.BEFORE_LINE_BEGINS);//Se agrega una Scroll para poder ver los nombres de las columnas y un Scroll
            JTableHeader th = table.getTableHeader();//Se crea un objeto de cabecera de la tabla que contiene el título de las columnas.
            th.setFont(new Font("arial",1,15));//Se le agrega un estilo de fuente a los nombres de las columnas.
            th.setForeground(Color.WHITE);
            th.setBackground(Color.DARK_GRAY);
            //ESTILO Y AJUSTES DE LA TABLA.
            table.setFont(new Font("arial",1,13));
            TableColumn column = table.getColumn("N°");
            TableColumn column1 = table.getColumn("Precio");
            column1.setMaxWidth(60);
            column.setMaxWidth(40);

            //table.setBackground(Color.GRAY);

            //CREACIÓN DE EXCEPCIÓN A LA HORA DE LA EJECUCIÓN DEL PROGRAMA JDBC
        }catch (Exception exception){
            System.out.println(exception);
        }
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable table1 = (JTable) e.getSource();
                Point point = new Point();
                table1.rowAtPoint(point);
                if (e.getClickCount()==1){
                    textField.setText(table.getValueAt(table.getSelectedRow(),0).toString());
                    textField1.setText((table.getValueAt(table.getSelectedRow(),1).toString()));
                    textField2.setText((table.getValueAt(table.getSelectedRow(),2).toString()));
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {

            }
            @Override
            public void mouseReleased(MouseEvent e) {

            }
            @Override
            public void mouseEntered(MouseEvent e) {

            }
            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }

    //CREACIÓN DE BOTONES CON DISTINTAS ACCIONES EN LA TABLA DE BASE DE DATOS Y JTABLE.
    private void Button(){
        JButton button = new JButton();
        button.setBounds(80, 200, 100,30);
        button.setText("Regresar");
        panel.add(button);

        JButton button1 = new JButton();
        button1.setBounds(250, 200,100,30);
        button1.setText("Actualizar");
        panel.add(button1);

        JButton button2 = new JButton("Registrar");
        button2.setBounds(70,700,100,30);
        panel.add(button2);

        JButton button3 = new JButton("Eliminar");
        button3.setBounds(170,700,100,30);
        panel.add(button3);

        JButton button4 = new JButton("Modificar");
        button4.setBounds(270,700,100,30);
        panel.add(button4);

        button.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indice = comboBox.getSelectedIndex();
                Gestor gestor = new Gestor();
                Empleados empleados = new Empleados();
                GestorBebida gestorBebida = new GestorBebida();
                if (indice == 1){
                    //empleados.setVisible(true);
                    empleados.show(true);
                    dispose();
                }
                if (indice == 2){
                    gestor.show(true);
                    dispose();
                }
                if (indice == 3){
                    gestorBebida.show(true);
                    dispose();
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/productos","root","");
                    CallableStatement pa = con.prepareCall("CALL registro_menu(?,?,?)");
                    pa.setString(1, textField.getText());
                    pa.setString(2, textField1.getText());
                    pa.setString(3, textField2.getText());
                    pa.execute();
                    JOptionPane.showMessageDialog(null,"Comida registrada");
                }catch (Exception exception){
                    System.out.println("Error "+exception);
                    JOptionPane.showMessageDialog(null,exception);
                }
                dispose();
                GestorMenu gestorMenu = new GestorMenu();
                gestorMenu.setVisible(true);
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "DELETE FROM menu WHERE num = ?;";
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/productos","root","");
                    PreparedStatement ps = con.prepareStatement(sql);
                    String eliminar = textField.getText();
                    ps.setString(1,eliminar);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Comida eliminada");

                }catch (Exception exception){
                    System.out.println("Error");
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(null,exception);
                }
                dispose();
                GestorMenu gestorMenu = new GestorMenu();
                gestorMenu.setVisible(true);
            }
        });

        button4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String id = table.getValueAt(table.getSelectedRow(),0).toString();
                String sql = "update menu set num = '"+textField.getText()+"', hamburquesas = '"+textField1.getText()+
                        "', precio = '"+textField2.getText()+"' where menu.num = '"+id+"'";
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/productos","root","");
                    Statement st = con.createStatement();
                    st.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Datos modificados");
                }catch (Exception exception){
                    System.out.println(exception);
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(null,exception);
                }
                dispose();
                GestorMenu gestorMenu = new GestorMenu();
                gestorMenu.setVisible(true);
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
                button2.setBackground(Color.PINK);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                button2.setBackground(Color.white);
            }
        });
    }

    private void Actualizar(){
        JLabel label = new JLabel("Registros existentes: ");
        label.setBounds(40,50,180,30);
        label.setFont(new Font ("Arial",1,14));
        label.setForeground(Color.white);
        panel.add(label);

        comboBox = new JComboBox();
        comboBox.addItem("Lista de Menú");
        comboBox.addItem("Lista de Empleados");
        comboBox.addItem("Lista de Productos");
        comboBox.addItem("Lista de Bebidas");
        comboBox.setBounds(230,50,150,30);
        panel.add(comboBox);
    }
}

package Ventanas;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

//CREACIÓN DE VENTANA DE GESTOR DE BASE DE DATOS.
public class Gestor extends JFrame{
    JPanel panel = new JPanel(); //creación de objeto de tipo panel.
    private JTable table;
    private JTextField textField ;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private Connection con;
    private DefaultTableModel modelo;
    private JComboBox<String> comboBox;

    //Método de ubicación y tamaño de la ventana.
    public Gestor(){
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
        Combobox();
        Fondo();
    }
    private void Panel(){
        panel.setLayout(null); //Desbloqueo de Layout para el ajuste libre de los componentes
        panel.setBounds(0,300,700,600);
        panel.setSize(700,600);
        this.getContentPane().add(panel);//Agregando el panel a la ventana Gestor.
        panel.setBackground(Color.DARK_GRAY); //Agregando Color al panel .
    }

    private void Fondo(){
        JLabel fondo = new JLabel();
        fondo.setBounds(0,0,700,300);
        fondo.setBackground(Color.gray);
        fondo.setOpaque(true);
        panel.add(fondo);
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
        //textfield de Precio por kilo.
        textField3 = new JTextField();
        textField3.setBounds(200,500,200,25);
        textField3.setFont(new Font("Arial",1,15));
        panel.add(textField3);
    }
    private void Labels(){
        JLabel label = new JLabel();
        label.setBounds(20,350,200,30);
        label.setText("Producto: ");
        label.setFont(new Font("Arial",1,16));
        label.setForeground(Color.WHITE);
        panel.add(label);

        JLabel label1 = new JLabel();
        label1.setBounds(20,400,200,30);
        label1.setText("Cantidad en Kg: ");
        label1.setFont(new Font("Arial",1,16));
        label1.setForeground(Color.WHITE);
        panel.add(label1);

        JLabel label2 = new JLabel();
        label2.setBounds(20,450,200,30);
        label2.setText("Precio Total: ");
        label2.setFont(new Font("Arial",1,16));
        label2.setForeground(Color.WHITE);
        panel.add(label2);

        JLabel label3 = new JLabel();
        label3.setBounds(20,500,200,30);
        label3.setText("Precio por Kg: ");
        label3.setFont(new Font("Arial",1,16));
        label3.setForeground(Color.WHITE);
        panel.add(label3);
    }


//CREACIÓN DE UNA TABLA QUE MUESTRA UNA TABLA DE BASE DE DATOS MYSQL
    private void Tables(){
        //Creación de un try para crear una excepción.
        try {
            //creación de una conexión con la base de datos de productos.
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/productos","root","");
            //Creación de Statement y ResultSet para la ejecución de una consulta.
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery("select * from productos");
            //Creación de una tabla modelo.
            modelo = new DefaultTableModel();
            //Creación de una tabla.
            table = new JTable(modelo);
            //Agregando columnas al modelo de table.
            modelo.addColumn("Producto");
            modelo.addColumn("Cantidad Kilos");
            modelo.addColumn("Precio Total");
            modelo.addColumn("Precio/Kilo");
            //Inicialización de recorrido y agredado de una tabla
            while (rs.next()){
                Object[] fila = new Object[4]; //Creación de un objeto de tipo Object para las filas del JTable dentro de las "3" columnas dadas.
                for( int i = 0; i< 4; i++){ //Bucle para llenar cada columna del JFrame con los datos de las columnas de la base de datos.
                    fila[i] = rs.getObject(i+1); //se suma + 1 porque el primer índice del resultset es 1 y no 0
                }
                modelo.addRow(fila);//Se añade al modelo la fila creada.
            }

            //ESTILO Y AJUSTES DEL NOMBRE DE LAS COLUMNAS.
            add(new JScrollPane(table),BorderLayout.BEFORE_LINE_BEGINS); //Se agrega una Scroll para poder ver los nombres de las columnas y un Scroll
            JTableHeader th = table.getTableHeader(); //Se crea un objeto de cabecera de la tabla que contiene el título de las columnas.
            th.setFont(new Font("arial",1,14)); //Se le agrega un estilo de fuente a los nombres de las columnas.
            th.setForeground(Color.WHITE);
            th.setBackground(Color.DARK_GRAY);

            //ESTILO Y AJUSTES DE LA TABLA.
            table.setFont(new Font("arial",1,13));
            //table.setBackground(Color.GRAY);

        //CREACIÓN DE EXCEPCIÓN A LA HORA DE LA EJECUCIÓN DEL PROGRAMA JDBC
        }catch (SQLException e){
            System.out.println("ERROR");
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Hubo un error con la base de datos."+e,"ERROR",JOptionPane.YES_OPTION);
        }
//Oyente de mouse para agregar datos de la tabla a los textfields.
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable table1 = (JTable) e.getSource();
                Point point = e.getPoint();
                table1.rowAtPoint(point);
                if(e.getClickCount() == 1){
                    textField.setText(table.getValueAt(table.getSelectedRow(),0).toString());
                    textField1.setText(table.getValueAt(table.getSelectedRow(),1).toString());
                    textField2.setText(table.getValueAt(table.getSelectedRow(),2).toString());
                    textField3.setText(table.getValueAt(table.getSelectedRow(),3).toString());
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
        button.setBackground(Color.WHITE);
        panel.add(button);

        JButton button1 = new JButton();
        button1.setBounds(250, 200,100,30);
        button1.setBackground(Color.WHITE);
        button1.setText("Aceptar");
        panel.add(button1);

        JButton button2 = new JButton("Registrar");
        button2.setBounds(70,700,100,30);
        button2.setBackground(Color.WHITE);
        panel.add(button2);

        JButton button3 = new JButton("Eliminar");
        button3.setBounds(170,700,100,30);
        button3.setBackground(Color.WHITE);
        panel.add(button3);

        JButton button4 = new JButton("Modificar");
        button4.setBounds(270,700,100,30);
        button4.setBackground(Color.WHITE);
        panel.add(button4);

        JButton button5 = new JButton("Ver los Pedidos");
        button5.setBounds(150,120,130,30);
        panel.add(button5);

        button.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Inicio inicio = new Inicio();
                //inicio.show(true);
                dispose();
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indice = comboBox.getSelectedIndex();
                Empleados empleados = new Empleados();
                GestorMenu gestorMenu = new GestorMenu();
                GestorBebida gestorBebida = new GestorBebida();
                if (indice == 1){
                    //empleados.setVisible(true);
                    empleados.show(true);
                    dispose();
                }
                if (indice == 2){
                    gestorMenu.show(true);
                    dispose();
                }
                if ( indice == 3){
                    gestorBebida.show(true);
                    dispose();
                }
            }
        });
        
//PROCESO DE REGISTRO DE DATOS EN LA TABLA
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/productos","root","");
                    CallableStatement pa = con.prepareCall("CALL registrar(?,?,?,?)");
                    pa.setString(1, textField.getText());
                    pa.setString(2, textField1.getText());
                    pa.setString(3, textField2.getText());
                    pa.setString(4, textField3.getText());
                    pa.execute();
                    JOptionPane.showMessageDialog(null,"Producto registrado");
                }catch (Exception exception){
                    System.out.println("Error "+exception);
                    JOptionPane.showMessageDialog(null,exception);
                }
                dispose();
                Gestor gestor = new Gestor();
                gestor.setVisible(true);
            }
        });
//PROCESO DE ELIMINACIÓN DE DATOS DE LA TABLA
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "DELETE FROM productos WHERE producto = ?;";
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/productos","root","");
                    PreparedStatement ps = con.prepareStatement(sql);
                    String eliminar = textField.getText();
                    ps.setString(1,eliminar);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Producto eliminado");

                }catch (Exception exception){
                    System.out.println("Error");
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(null,exception);
                }
                dispose();
                Gestor gestor = new Gestor();
                gestor.setVisible(true);
            }
        });
//PROCESO DE ACTUALIZACIÓN DE DATOS DE LA TABLA
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = table.getValueAt(table.getSelectedRow(),0).toString();
                String sql = "update productos set producto = '"+textField.getText()+"', cantidad_kilos = '"+textField1.getText()+
                        "', precio = '"+textField2.getText()+"', precio_por_kg = '"+textField3.getText()+
                        "' where productos.producto = '"+id+"'";
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/productos","root","");
                    Statement st = con.createStatement();
                    st.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Producto modificado");
                }catch (Exception exception){
                    System.out.println(exception);
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(null,exception);
                }
                dispose();
                Gestor gestor = new Gestor();
                gestor.setVisible(true);
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

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pedidos pedidos = new Pedidos();
                pedidos.setVisible(true);
            }
        });
    }

    private void Combobox(){

        JLabel label = new JLabel("Registros existentes: ");
        label.setBounds(40,50,180,30);
        label.setFont(new Font ("Arial",1,14));
        label.setForeground(Color.white);
        panel.add(label);


        comboBox = new JComboBox<>();
        comboBox.setBounds(230,50,150,30);
        comboBox.addItem("Lista de Ingredientes");
        comboBox.addItem("Lista de Empleados");
        comboBox.addItem("Lista de Menú");
        comboBox.addItem("Lista de Bebidas");
        panel.add(comboBox);
    }

}

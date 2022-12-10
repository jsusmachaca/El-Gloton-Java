package Ventanas;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent; //Importación de ActionEvent para la creación de eventos.
import java.awt.event.ActionListener; //Importación de ActionListener para el oyente de acción.
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;

import static javax.swing.JOptionPane.*;


//CREACIÓN DE VENTANA DE LISTA DE BEBIDAS.
public class Bebidas extends JFrame{
    JPanel panel = new JPanel();//creación de objeto de tipo panel.
    Componentes componentes = new Componentes();//creación de objeto de tipo Componentes perteneciente a la clase Componentes.
    private DefaultTableModel modelo;
    private JTable table;
    private JTextField textField;
    private JTextField textField1;
    private JTextField textField2;
    private Connection con;


    //Método de ubicación y tamaño de la ventana.
    public Bebidas(){
        setSize(700,800);
        setResizable(false); //bloqueo de ventana para evitar redimensión.
        setLocationRelativeTo(null);
        setTitle("Menu");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Components();
    }
    //Método de inicialización de componentes de la ventana.
    private void Components(){
        Panel();
        ListaMenu();
        Buttons();
        TextField();

    }
    //Método de ajuste del panel de ventana.
    private void Panel(){
        panel.setLayout(null);//Desbloqueo de Layout para el ajuste libre de los componentes.
        this.getContentPane().add(panel);//Agregando el panel a la ventana Gestor.
        panel.setBackground(Color.DARK_GRAY);//Agregando Color al panel .
    }
    //Creación de Botones de Confirmación y Retroceso.
    private void Buttons() {
        //Botón de retroceso.
        JButton regresar = new JButton();
        regresar.setText("REGRESAR");
        regresar.setBounds(110, 250, 150, 40);
        regresar.setBackground(Color.DARK_GRAY);
        regresar.setFont(new Font("Bahnschrift SemiBold", 1, 18));
        regresar.setForeground(Color.WHITE);
        regresar.setBorderPainted(false);
        panel.add(regresar);

        //Botón de confirmación.
        JButton button = new JButton();
        button.setText("CONFIRMAR PEDIDO");
        button.setBounds(350, 250, 230, 40);
        button.setBackground(Color.DARK_GRAY);
        button.setFont(new Font("Bahnschrift SemiBold", 1, 18));
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        panel.add(button);

//OYENTES DE BOTONES.
        //Oyente del botón encargado de regresar al menú inicio.
        ActionListener Regresar = new ActionListener() {//Creación de objeto de tipo Listener para oyente de acción
            @Override
            public void actionPerformed(ActionEvent e) {
                Inicio inicio = new Inicio();
                inicio.setVisible(true);
                dispose();
            }
        };
        regresar.addActionListener(Regresar);

        //Oyente encarado de mostrar un mensaje del método Mensaje() del objeto componentes de la clase Componentes de la clase

        ActionListener Button = new ActionListener() {//Creación de objeto de tipo Listener para oyente de acción
            @Override
            public void actionPerformed(ActionEvent e) {
                int si = JOptionPane.YES_NO_OPTION;
                Inicio inicio = new Inicio();
                Bebidas bebidas = new Bebidas();
                //Creación de una condicional que analiza la entrada de acción a la hora de mostrar mensaje de confirmación.
                if (JOptionPane.showConfirmDialog(null, "Seguro que quieres confirmar?","Confirmación",si)== YES_OPTION){
                    int mesa = Integer.parseInt(JOptionPane.showInputDialog("Número de mesa"));
                    double cant = Double.parseDouble(JOptionPane.showInputDialog("¿Qué cantidad de "+textField1.getText()+" desea?"));
                    double x = Double.parseDouble(textField2.getText());
                    if (cant > 1){
                        double res = x*cant;
                        BigDecimal db2 = new BigDecimal(res).setScale(2, RoundingMode.HALF_UP);
                        showMessageDialog(null,"El costo es S/. "+ db2);
                    }else {
                        JOptionPane.showMessageDialog(null, "El costo es S/. "+ textField2.getText());
                    }
                    //inicio.setVisible(true);
                    try{
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/productos","root","");
                        PreparedStatement pst = con.prepareStatement("INSERT INTO pedidos (pedido, mesa,cantidad, fecha) VALUES (?, ?, ?, now());");
                        pst.setString(1, textField1.getText());
                        pst.setInt(2, mesa);
                        pst.setDouble(3,cant);
                        pst.execute();
                    }catch (Exception exception){
                        System.out.println(exception);
                    }
                    //dispose();
                }else if(si == NO_OPTION){
                    bebidas.setVisible(true);
                }
            }
        };
        button.addActionListener(Button);

        regresar.addMouseListener(new MouseListener() {
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
                regresar.setBackground(Color.GRAY);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                regresar.setBackground(Color.DARK_GRAY);
            }
        });

        button.addMouseListener(new MouseListener() {
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
                button.setBackground(Color.GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(Color.DARK_GRAY);
            }
        });

        //FIN DE LOS OYENTES.
    }
    private void TextField(){
        textField = new JTextField();
        textField.setBounds(200,100,40,30);
        textField.setFont(new Font("Arial",1,16));
        panel.add(textField);

        textField1 = new JTextField();
        textField1.setBounds(241,100,300,30);
        textField1.setFont(new Font("Arial",1,16));
        panel.add(textField1);

        textField2 = new JTextField();
    }

    private void ListaMenu(){
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/productos","root","");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from bebidas");
            modelo = new DefaultTableModel();
            table = new JTable(modelo);
            modelo.addColumn("N°");
            modelo.addColumn("Bebidas");
            modelo.addColumn("Precio");
            while (rs.next()){
                Object [] fila = new Object[3];
                for (int i = 0; i<3;i++){
                    fila[i] = rs.getObject(i+1);
                }
                modelo.addRow(fila);
            }

            //ESTILO Y AJUSTES DEL NOMBRE DE LAS COLUMNAS.
            add(new JScrollPane(table),BorderLayout.PAGE_START);//Se agrega una Scroll para poder ver los nombres de las columnas y un Scroll
            JTableHeader th = table.getTableHeader();//Se crea un objeto de cabecera de la tabla que contiene el título de las columnas.
            th.setFont(new Font("arial",1,15));//Se le agrega un estilo de fuente a los nombres de las columnas
            th.setForeground(Color.WHITE);
            th.setBackground(Color.DARK_GRAY);

            //ESTILO Y AJUSTES DE LA TABLA.
            table.setFont(new Font("arial",1,13));
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
                table.setFont(new Font("arial",1,15));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                table.setFont(new Font("arial",1,13));
            }
        });

    }
//FIN DE LOS MÉTODOS.
}

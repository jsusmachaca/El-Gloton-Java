package Ventanas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.*;

public class Pedidos extends JFrame {
    JPanel panel = new JPanel();
    private JTable table;

    public Pedidos(){
        setSize(600,700);
        setResizable(false); //bloqueo de ventana para evitar redimensión.
        setLocationRelativeTo(null);
        setTitle("Pedidos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Components();
    }
    private void Components(){
        Table();
        Button();
        Panel();
    }
    private void Table(){
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/productos","root","");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from pedidos");
            DefaultTableModel model = new DefaultTableModel();
            table = new JTable(model);
            model.addColumn("Pedidos");
            model.addColumn("N° de mesa");
            model.addColumn("Cantidad");
            model.addColumn("Fecha y Hora");
            Object[] fila = new Object[4];
            while (rs.next()){
                for (int i=0;i<4;i++){
                    fila[i] = rs.getObject(i+1);
                }
                model.addRow(fila);
            }
            //ESTILO Y AJUSTES DEL NOMBRE DE LAS COLUMNAS.
            add(new JScrollPane(table), BorderLayout.NORTH);//Se agrega una Scroll para poder ver los nombres de las columnas y un Scroll
            JTableHeader th = table.getTableHeader();//Se crea un objeto de cabecera de la tabla que contiene el título de las columnas.
            th.setFont(new Font("arial",1,15));//Se le agrega un estilo de fuente a los nombres de las columnas
            th.setForeground(Color.WHITE);
            th.setBackground(Color.DARK_GRAY);
            //ESTILO Y AJUSTES DE LA TABLA.
            table.setFont(new Font("arial",1,13));
            //table.setBounds(0,700,700,600);

            TableColumn column = table.getColumn("Pedidos");
            TableColumn column1 = table.getColumn("N° de mesa");

            TableColumn column2 = table.getColumn("Fecha y Hora");

            column.setMinWidth(200);
        }catch (Exception exception){
            System.out.println(exception);
        }
    }
    private void Panel(){
        panel.setLayout(null);//Desbloqueo de Layout para el ajuste libre de los componentes.
        this.getContentPane().add(panel);//Agregando el panel a la ventana Gestor.
        panel.setBackground(Color.DARK_GRAY);//Agregando Color al panel
    }
    private void Button(){
        JButton button = new JButton("Actualizar");
        button.setBounds(350,100,100,30);
        panel.add(button);

        JButton button1 = new JButton("Limpiar");
        button1.setBounds(100,100,100,30);
        panel.add(button1);

        JButton button2 = new JButton("Salir");
        button2.setBounds(10,200,60,30);
        panel.add(button2);

        JButton button3 = new JButton("Imprimir");
        button3.setBounds(500,200,80,30);
        panel.add(button3);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pedidos pedidos = new Pedidos();
                dispose();
                pedidos.setVisible(true);
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/productos","root","");
                    Statement st = con.createStatement();
                    String sql = "TRUNCATE TABLE pedidos";
                    st.executeUpdate(sql);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                Pedidos pedidos = new Pedidos();
                dispose();
                pedidos.setVisible(true);
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    table.print();
                } catch (PrinterException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}

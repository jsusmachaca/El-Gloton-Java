package Ventanas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent; //Importación de ActionEvent para la creación de eventos.
import java.awt.event.ActionListener; //Importación de ActionListener para el oyente de acción.
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//CREACIÓN DE VENTANA DE ELECCIÓN DE MENÚ.
public class Acceso extends JFrame {
    JPanel panel = new JPanel(); //creación de objeto de tipo panel.
    Componentes comp = new Componentes(); //creación de objeto de tipo Componentes perteneciente a la clase Componentes.
    private JTextField textField; //Creación de JText fiel al inicio para una mayor manipulación.
    private JPasswordField textField1; //Creación de JText fiel al inicio para una mayor manipulación.

//Método de ubicación y tamaño de la ventana.
    public Acceso(){
        setSize(400,300);
        setResizable(false); //bloqueo de ventana para evitar redimensión.
        setLocationRelativeTo(null);
        setTitle("Acceso");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Components();
    }
//Método de inicialización de componentes de la ventana.
    private void Components(){
        Panel();
        Labels();
        Textfield();
        Buttons();
    }
//Método de ajuste del panel de ventana.
    private void Panel(){
        panel.setLayout(null); //Desbloqueo de Layout para el ajuste libre de los componentes
        this.getContentPane().add(panel);//Agregando el panel a la ventana Gestor.
        panel.setBackground(Color.DARK_GRAY); //Agregando Color al panel .
    }
//Creación de un Label que contiene el título y los textos de la ventana de elección de ingredientes.
    private void Labels(){
        //Título de la ventana
        JLabel label = new JLabel();
        label.setBounds(40,35,300,30);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setText("Acceso a la base de datos");
        label.setFont(new Font("Bahnschrift SemiBold",1,20));
        label.setForeground(Color.WHITE);
        panel.add(label);

        //Sub titulo de TextField Usuario.
        JLabel jLabel = new JLabel();
        jLabel.setBounds(20,90,200,30);
        jLabel.setText("Usuario: ");
        jLabel.setFont(new Font("Bahnschrift SemiBold",1,13));
        jLabel.setForeground(Color.WHITE);
        panel.add(jLabel);

        //Sub titulo de TextField Contraseña.
        JLabel jLabel1 = new JLabel();
        jLabel1.setBounds(20,140,200,30);
        jLabel1.setText("Contraseña: ");
        jLabel1.setFont(new Font("Bahnschrift SemiBold",1,13));
        jLabel1.setForeground(Color.WHITE);
        panel.add(jLabel1);
    }
//Creación de TextField para la entrada de usuario y contraseña.
    private void Textfield(){
        //TextField de usuario.
        textField = new JTextField();
        textField.setBounds(150,90,200,25);
        panel.add(textField);

        //TextField de contraseña.
        textField1 = new JPasswordField();
        textField1.setBounds(150,140,200,25);
        panel.add(textField1);


        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //e.getSource(char).KeyEvent.VK_CLEAR);
                textField1.requestFocus();
            }
        });
        textField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Gestor gestor = new Gestor();
                Inicio inicio = new Inicio();
                gestor.requestFocus();
                inicio.setVisible(true);
                gestor.setVisible(true);
                dispose();
            }
        });
    }
//Creación de Botones de Confirmación y Retroceso.
    private void Buttons(){
        //Botón regresar.
        JButton button = new JButton();
        button.setBounds(70,200,100,30);
        button.setText("Regresar");
        button.setFont(new Font("Bahnschrift SemiBold",1,13));
        button.setForeground(Color.WHITE);
        button.setBackground(Color.GRAY);
        panel.add(button);
        //Botón confirmar.
        JButton button1 = new JButton();
        button1.setBounds(210,200,100,30);
        button1.setText("Confirmar");
        button1.setFont(new Font("Bahnschrift SemiBold",1,13));
        button1.setForeground(Color.WHITE);
        button1.setBackground(Color.gray);
        panel.add(button1);

//OYENTES DE LOS BOTONES
        //Oyente del botón regresar.
        ActionListener actionListener = new ActionListener() {//Creación de objeto de tipo Listener para oyente de acción
            @Override
            public void actionPerformed(ActionEvent e) {
                Inicio i = new Inicio();
                i.setVisible(true);
                dispose();
            }
        };
        button.addActionListener(actionListener);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //oyente del botón confirmar, con una condicional que analiza el nombre y usuario correcto.
                String a = comp.getUsuario();//instancia de objetos a de la clase getUsuario
                String b = comp.getContraseña(); //instancia de objeto b de la clase getContraseña.
                Gestor gestor = new Gestor();
                //gestor.requestFocus();
                Inicio inicio = new Inicio();
                if (textField.getText().equals(a) && textField1.getText().equals(b)){//análisis de textfield usuario y contraseña, si estos son correctos se procede a abrir la ventana de gestor.
                    inicio.setVisible(true);
                    gestor.setVisible(true);

                }else {//Si la condición es false se procese a mostrar un mensaje de acceso denegado.
                    JOptionPane.showMessageDialog(null,"Acceso denegado, credenciales incorrectas");
                }
            }
        });

    //FIN DE LOS OYENTES.
    }
//FIN DE LOS MÉTODOS.
}
//FIN DEL PROGRAMA.
package formularios;

import javax.swing.*;

public class Menu {
    private JPanel panel1;
    private JButton btnAdd;
    private JButton btnRead;
    private JButton btnAtualizar;
    private JButton btnDeletar;
    private JTextField textField1;

    public static void main(String[] args) {
    JFrame menu = new JFrame("CRUD clientes");
    menu.setContentPane(new Menu().panel1);
    menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    menu.pack();
    menu.setVisible(true);
    }

}

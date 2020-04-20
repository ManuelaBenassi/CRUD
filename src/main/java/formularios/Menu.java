package formularios;

import bd.dao.Clientes;
import bd.dbo.Cliente;
import webServer.ClienteWS;
import webServer.Logradouro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import package.src;

public class Menu {
    private JPanel panel1;
    private JButton btnAdd;
    private JButton btnRead;
    private JButton btnAtualizar;
    private JButton btnDeletar;
    private JTextField txtEmail;
    private JTextField txtNome;
    private JTextField txtCEP;
    private JTextField txtEstado;
    private JTextField txtCidade;
    private JTextField txtBairro;
    private JTextField txtRua;
    private JTextField txtNumero;
    private JTextField txtComplemento;
    private JButton btnProcurarCEP;

    public Menu() {
        naoPodeEditar(txtEstado,txtCidade,txtBairro,txtRua);
        btnRead.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!txtEmail.isEditable()|| txtEmail.getText().equals("")) {
                naoPodeEditar(txtNome,txtCEP,txtEstado,txtCidade,txtBairro,txtRua,txtNumero,txtComplemento);
                btnAdd.setEnabled(false);
                btnAtualizar.setEnabled(false);
                btnDeletar.setEnabled(false);
                btnProcurarCEP.setEnabled(false);
                }
                else{
                    try {
                            if(Clientes.cadastrado(txtEmail.getText())){
                                Cliente cliente = Clientes.getCliente(txtEmail.getText());
                                txtNome.setText(cliente.getNome());
                                txtBairro.setText(cliente.getBairro());
                                txtCidade.setText(cliente.getCidade());
                                txtCEP.setText("");
                                txtRua.setText(cliente.getRua());
                                txtNumero.setText(cliente.getNumero() + "");
                                txtComplemento.setText(cliente.getComplemento());
                                txtEstado.setText(cliente.getEstado());
                            }

                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(null,exception.getMessage());
                        exception.printStackTrace();
                    }

                }
            }
        });
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente cliente = null;
                try {
                    if(!checaCamposVazios(txtComplemento,txtNumero,txtRua,txtBairro,txtCidade,txtEstado,txtCEP,txtNome,txtEmail))
                    try {
                        cliente = new Cliente(txtEmail.getText(),txtNome.getText(),txtEstado.getText(),txtCidade.getText(),txtBairro.getText(),
                                txtRua.getText(),txtComplemento.getText(),Integer.parseInt(txtNumero.getText()));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    else{
                        throw new Exception("todos os campos devem estar preenchidos ");
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                try {
                    Clientes.incluir(cliente);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        btnProcurarCEP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logradouro logradouro = null;
                if(!txtCEP.getText().equals("")||txtCEP.getText().length() == 8){
                    {
                        logradouro = (Logradouro) ClienteWS.getObjeto(Logradouro.class, "http://api.postmon.com.br/v1/cep", txtCEP.getText());
                        txtRua.setText(logradouro.getLogradouro());
                        txtBairro.setText(logradouro.getBairro());
                        txtCidade.setText(logradouro.getCidade());
                        txtEstado.setText(logradouro.getEstado());
                    }
                }
            }
        });
        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente cliente = null;
                try {
                    if(!checaCamposVazios(txtComplemento,txtNumero,txtRua,txtBairro,txtCidade,txtEstado,txtCEP,txtNome,txtEmail))
                        try {
                            cliente = new Cliente(txtEmail.getText(),txtNome.getText(),txtEstado.getText(),txtCidade.getText(),txtBairro.getText(),
                                    txtRua.getText(),txtComplemento.getText(),Integer.parseInt(txtNumero.getText()));
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    else{
                        throw new Exception("todos os campos devem estar preenchidos ");
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                try {
                    Clientes.alterar(cliente);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }


        });
        btnDeletar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)  {
                if(txtEmail.getText().equals(""))
                    try {
                        throw new Exception("o email deve ser fornecido");
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                else{
                    try {
                        Clientes.excluir(txtEmail.getText());
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }

            }
        });
    }

    private void podeEditar(JTextField... campos){
        for (int i = 0; i < campos.length; i++)
            campos[i].setEditable(true);

    }
    private void naoPodeEditar(JTextField... campos){
        for (int i = 0; i < campos.length; i++)
            campos[i].setEditable(false);
    }
    private boolean checaCamposVazios(JTextField... campos) throws Exception {
        for (int i = 0; i < campos.length; i++)
            if(campos[i].getText().equals(""))
                return true;
            return  false;

    }
    public static void main(String[] args) {

    JFrame menu = new JFrame("CRUD clientes");
    menu.setContentPane(new Menu().panel1);
    menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    menu.pack();
    menu.setVisible(true);

    }

}

package bd.dao;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import bd.*;
import bd.core.*;
import bd.dbo.*;


import bd.dbo.Cliente;

public class Clientes {
    public static boolean cadastrado(String email) throws Exception {
        boolean retorno = false;
        String sql;
        try {
            sql = "SELECT * " +
                    "FROM CLIENTE " +
                    "WHERE email = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setString(1, email);

            MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();

            retorno = resultado.first();
        }
        catch (SQLException erro){
            throw new Exception("Erro ao procurar cliente");

        }
        return retorno;
    }
    public void incluir(Cliente cliente) throws Exception {
        if (cliente == null)
            throw new Exception("cliente não pode ser nulo");
        if(Clientes.cadastrado(cliente.getEmail()))
            throw new Exception("Esse cliente já foi cadastrado.");
        try
        {
            String sql;
            sql = "insert into Cliente values(?, ?, ?, ?, ?,?,?,?)";
            BDSQLServer.COMANDO.prepareStatement(sql);
            BDSQLServer.COMANDO.setString(1, cliente.getEmail());
            BDSQLServer.COMANDO.setString(2, cliente.getNome());
            BDSQLServer.COMANDO.setString(3, cliente.getEstado());
            BDSQLServer.COMANDO.setString(4, cliente.getCidade());
            BDSQLServer.COMANDO.setString(5, cliente.getBairro());
            BDSQLServer.COMANDO.setString(6, cliente.getRua());
            BDSQLServer.COMANDO.setInt	 (7, cliente.getNumero());
            BDSQLServer.COMANDO.setString(8, cliente.getComplemento());

            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit();
        }
        catch(Exception ex)
        {
            throw new Exception("Erro ao cadastrar cliente");
        }
    }
    public void excluir(String email) throws Exception {
        if(!Clientes.cadastrado(email))
            throw new Exception("Esse cliente não foi cadastrado, portanto é impossivel excluir ele.");
        try
        {
            String sql;

            sql = "DELETE FROM CLIENTE " +
                    "WHERE EMAIL=?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, email);

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            //BDSQLServer.COMANDO.rollback ();
            throw new Exception ("Erro ao excluir cliente");
        }

    }
    public static void alterar (Cliente cliente) throws Exception
    {
        if (cliente==null)
            throw new Exception ("Monitor nao fornecido");

        if (!cadastrado (cliente.getEmail()))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "UPDATE CLIENTE " +
                    "SET NOME=? " +
                    "SET ESTADO=? " +
                    "SET CIDADE=? " +
                    "SET BAIRRO=? " +
                    "SET RUA=? " +
                    "SET NUMERO=? " +
                    "SET COMPLEMENTO=? " +
                    "WHERE EMAIL = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);


            BDSQLServer.COMANDO.setString(1, cliente.getNome ());
            BDSQLServer.COMANDO.setString(2, cliente.getEstado());
            BDSQLServer.COMANDO.setString(3, cliente.getCidade());
            BDSQLServer.COMANDO.setString(4, cliente.getBairro());
            BDSQLServer.COMANDO.setString(5, cliente.getRua());
            BDSQLServer.COMANDO.setInt(6, cliente.getNumero());
            BDSQLServer.COMANDO.setString(7, cliente.getComplemento());
            BDSQLServer.COMANDO.setString(8, cliente.getEmail ());


            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            //BDSQLServer.COMANDO.rollback ();
            throw new Exception ("Erro ao atualizar dados de cliente");
        }
    }
    public static List<Cliente> selecionarClientes() throws Exception
    {
        List<Cliente> ret = new ArrayList<Cliente>();
        Cliente cliente;

        try
        {
            String sql = "SELECT * FROM CLIENTE";
            BDSQLServer.COMANDO.prepareStatement(sql);

            MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
            while(resultado.next())
            {
                cliente = new Cliente(resultado.getString("email"), resultado.getString("nome"),
                        resultado.getString("estado"), resultado.getString("cidade"),
                        resultado.getString("bairro"),resultado.getString("rua"),
                        resultado.getString("complemento"),resultado.getInt("numero"));

                ret.add(cliente);
            }
        }
        catch(Exception ex)
        {
            throw new Exception("Erro ao procurar Pessoas");
        }

        return ret;
    }
}
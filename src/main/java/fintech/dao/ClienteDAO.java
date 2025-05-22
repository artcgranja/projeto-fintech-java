package fintech.dao;

import fintech.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para operações CRUD da entidade Cliente.
 */
public class ClienteDAO {

    /**
     * Insere um novo cliente no banco de dados.
     *
     * @param cliente objeto Cliente a ser inserido
     * @throws SQLException se houver erro na operação
     */
    public void insert(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO TB_CLIENTE (NOME, CPF) VALUES (?, ?)";

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(sql);

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());

            ps.executeUpdate();

        } finally {
            if (ps != null) ps.close();
            ConnectionManager.closeConnection(connection);
        }
    }

    /**
     * Busca um cliente pelo CPF.
     *
     * @param cpf CPF do cliente
     * @return Cliente encontrado ou null
     * @throws SQLException se houver erro na operação
     */
    public Cliente getByCpf(String cpf) throws SQLException {
        String sql = "SELECT * FROM TB_CLIENTE WHERE CPF = ?";

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, cpf);
            rs = ps.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("NOME");
                return new Cliente(nome, cpf);
            }

        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            ConnectionManager.closeConnection(connection);
        }

        return null;
    }

    /**
     * Lista todos os clientes.
     *
     * @return List<Cliente> lista de clientes
     * @throws SQLException se houver erro na operação
     */
    public List<Cliente> getAll() throws SQLException {
        String sql = "SELECT * FROM TB_CLIENTE ORDER BY NOME";
        List<Cliente> clientes = new ArrayList<>();

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("NOME");
                String cpf = rs.getString("CPF");
                clientes.add(new Cliente(nome, cpf));
            }

        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            ConnectionManager.closeConnection(connection);
        }

        return clientes;
    }

    /**
     * Atualiza os dados de um cliente.
     *
     * @param cliente cliente com dados atualizados
     * @throws SQLException se houver erro na operação
     */
    public void update(Cliente cliente) throws SQLException {
        String sql = "UPDATE TB_CLIENTE SET NOME = ? WHERE CPF = ?";

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.executeUpdate();

        } finally {
            if (ps != null) ps.close();
            ConnectionManager.closeConnection(connection);
        }
    }

    /**
     * Remove um cliente do banco de dados.
     *
     * @param cpf CPF do cliente a ser removido
     * @throws SQLException se houver erro na operação
     */
    public void delete(String cpf) throws SQLException {
        String sql = "DELETE FROM TB_CLIENTE WHERE CPF = ?";

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, cpf);
            ps.executeUpdate();

        } finally {
            if (ps != null) ps.close();
            ConnectionManager.closeConnection(connection);
        }
    }
}
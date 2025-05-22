package fintech.dao;

import fintech.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DAO para operações de autenticação de usuários.
 */
public class UsuarioDAO {

    /**
     * Insere um novo usuário no banco de dados.
     *
     * @param usuario objeto Usuario a ser inserido
     * @throws SQLException se houver erro na operação
     */
    public void insert(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO TB_USUARIO (LOGIN, SENHA, NOME, EMAIL) VALUES (?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(sql);

            ps.setString(1, usuario.getLogin());
            ps.setString(2, usuario.getSenha());
            ps.setString(3, usuario.getNome());
            ps.setString(4, usuario.getEmail());

            ps.executeUpdate();

        } finally {
            if (ps != null) ps.close();
            ConnectionManager.closeConnection(connection);
        }
    }

    /**
     * Autentica um usuário no sistema.
     *
     * @param login login do usuário
     * @param senha senha do usuário
     * @return Usuario se autenticação for bem-sucedida, null caso contrário
     * @throws SQLException se houver erro na operação
     */
    public Usuario autenticar(String login, String senha) throws SQLException {
        String sql = "SELECT * FROM TB_USUARIO WHERE LOGIN = ? AND SENHA = ?";

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, senha);
            rs = ps.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("NOME");
                String email = rs.getString("EMAIL");
                return new Usuario(login, senha, nome, email);
            }

        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            ConnectionManager.closeConnection(connection);
        }

        return null;
    }

    /**
     * Busca um usuário pelo login.
     *
     * @param login login do usuário
     * @return Usuario encontrado ou null
     * @throws SQLException se houver erro na operação
     */
    public Usuario getByLogin(String login) throws SQLException {
        String sql = "SELECT * FROM TB_USUARIO WHERE LOGIN = ?";

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, login);
            rs = ps.executeQuery();

            if (rs.next()) {
                String senha = rs.getString("SENHA");
                String nome = rs.getString("NOME");
                String email = rs.getString("EMAIL");
                return new Usuario(login, senha, nome, email);
            }

        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            ConnectionManager.closeConnection(connection);
        }

        return null;
    }

    /**
     * Atualiza os dados de um usuário.
     *
     * @param usuario usuário com dados atualizados
     * @throws SQLException se houver erro na operação
     */
    public void update(Usuario usuario) throws SQLException {
        String sql = "UPDATE TB_USUARIO SET SENHA = ?, NOME = ?, EMAIL = ? WHERE LOGIN = ?";

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, usuario.getSenha());
            ps.setString(2, usuario.getNome());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getLogin());
            ps.executeUpdate();

        } finally {
            if (ps != null) ps.close();
            ConnectionManager.closeConnection(connection);
        }
    }
}
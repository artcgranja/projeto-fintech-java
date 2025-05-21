package fintech.dao;

import fintech.model.Conta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para acesso à tabela CONTA usando Oracle Database.
 */
public class ContaDAO {
    // Parâmetros de conexão Oracle
    private static final String URL      = "jdbc:oracle:thin:@//HOST:PORT/SERVICE";
    private static final String USER     = "seu_usuario";
    private static final String PASSWORD = "sua_senha";

    static {
        try {
            // Carrega o driver Oracle JDBC
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver Oracle JDBC não encontrado", e);
        }
    }

    /**
     * Insere uma nova conta na tabela CONTA.
     */
    public void insert(Conta conta) {
        String sql = "INSERT INTO CONTA (NUMERO_CONTA, SALDO) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, conta.getNumeroConta());
            ps.setDouble(2, conta.getSaldo());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro no insert(): " + e.getMessage());
        }
    }

    /**
     * Recupera todas as contas da tabela CONTA.
     */
    public List<Conta> getAll() {
        String sql = "SELECT NUMERO_CONTA, SALDO FROM CONTA";
        List<Conta> lista = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int num = rs.getInt("NUMERO_CONTA");
                double sal = rs.getDouble("SALDO");
                lista.add(new Conta(num, sal));
            }
        } catch (SQLException e) {
            System.err.println("Erro no getAll(): " + e.getMessage());
        }

        return lista;
    }
}
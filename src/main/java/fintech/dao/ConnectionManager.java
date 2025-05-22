package fintech.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gerenciador de conexões com o banco de dados Oracle da FIAP.
 */
public class ConnectionManager {

    // Configurações para conexão com Oracle da FIAP
    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private static final String USER = "pf0392";
    private static final String PASSWORD = "fiap";

    static {
        try {
            // Carrega o driver Oracle JDBC
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver Oracle JDBC não encontrado", e);
        }
    }

    /**
     * Obtém uma conexão com o banco de dados.
     *
     * @return Connection objeto de conexão
     * @throws SQLException se houver erro na conexão
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Fecha a conexão de forma segura.
     *
     * @param connection conexão a ser fechada
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
}
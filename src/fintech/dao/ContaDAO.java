package fintech.dao;

import fintech.model.Conta;
import fintech.model.ContaCorrente;
import fintech.model.ContaPoupanca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para operações CRUD da entidade Conta.
 */
public class ContaDAO {

    /**
     * Insere uma nova conta no banco de dados.
     *
     * @param conta objeto Conta a ser inserido
     * @throws SQLException se houver erro na operação
     */
    public void insert(Conta conta) throws SQLException {
        String sql = "INSERT INTO TB_CONTA (NUMERO_CONTA, SALDO, TIPO_CONTA, LIMITE, TAXA_JUROS) VALUES (?, ?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(sql);

            ps.setInt(1, conta.getNumeroConta());
            ps.setDouble(2, conta.getSaldo());

            if (conta instanceof ContaCorrente) {
                ContaCorrente cc = (ContaCorrente) conta;
                ps.setString(3, "CORRENTE");
                ps.setDouble(4, cc.getLimite());
                ps.setNull(5, java.sql.Types.DOUBLE);
            } else if (conta instanceof ContaPoupanca) {
                ContaPoupanca cp = (ContaPoupanca) conta;
                ps.setString(3, "POUPANCA");
                ps.setNull(4, java.sql.Types.DOUBLE);
                ps.setDouble(5, cp.getTaxaJuros());
            } else {
                ps.setString(3, "COMUM");
                ps.setNull(4, java.sql.Types.DOUBLE);
                ps.setNull(5, java.sql.Types.DOUBLE);
            }

            ps.executeUpdate();

        } finally {
            if (ps != null) ps.close();
            ConnectionManager.closeConnection(connection);
        }
    }

    /**
     * Busca uma conta pelo número.
     *
     * @param numeroConta número da conta
     * @return Conta encontrada ou null
     * @throws SQLException se houver erro na operação
     */
    public Conta getByNumero(int numeroConta) throws SQLException {
        String sql = "SELECT * FROM TB_CONTA WHERE NUMERO_CONTA = ?";

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, numeroConta);
            rs = ps.executeQuery();

            if (rs.next()) {
                String tipoConta = rs.getString("TIPO_CONTA");
                double saldo = rs.getDouble("SALDO");

                if ("CORRENTE".equals(tipoConta)) {
                    double limite = rs.getDouble("LIMITE");
                    return new ContaCorrente(numeroConta, saldo, limite);
                } else if ("POUPANCA".equals(tipoConta)) {
                    double taxaJuros = rs.getDouble("TAXA_JUROS");
                    return new ContaPoupanca(numeroConta, saldo, taxaJuros);
                } else {
                    return new Conta(numeroConta, saldo);
                }
            }

        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            ConnectionManager.closeConnection(connection);
        }

        return null;
    }

    /**
     * Lista todas as contas.
     *
     * @return List<Conta> lista de contas
     * @throws SQLException se houver erro na operação
     */
    public List<Conta> getAll() throws SQLException {
        String sql = "SELECT * FROM TB_CONTA ORDER BY NUMERO_CONTA";
        List<Conta> contas = new ArrayList<>();

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int numeroConta = rs.getInt("NUMERO_CONTA");
                double saldo = rs.getDouble("SALDO");
                String tipoConta = rs.getString("TIPO_CONTA");

                if ("CORRENTE".equals(tipoConta)) {
                    double limite = rs.getDouble("LIMITE");
                    contas.add(new ContaCorrente(numeroConta, saldo, limite));
                } else if ("POUPANCA".equals(tipoConta)) {
                    double taxaJuros = rs.getDouble("TAXA_JUROS");
                    contas.add(new ContaPoupanca(numeroConta, saldo, taxaJuros));
                } else {
                    contas.add(new Conta(numeroConta, saldo));
                }
            }

        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            ConnectionManager.closeConnection(connection);
        }

        return contas;
    }

    /**
     * Atualiza o saldo de uma conta.
     *
     * @param numeroConta número da conta
     * @param novoSaldo novo saldo
     * @throws SQLException se houver erro na operação
     */
    public void updateSaldo(int numeroConta, double novoSaldo) throws SQLException {
        String sql = "UPDATE TB_CONTA SET SALDO = ? WHERE NUMERO_CONTA = ?";

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setDouble(1, novoSaldo);
            ps.setInt(2, numeroConta);
            ps.executeUpdate();

        } finally {
            if (ps != null) ps.close();
            ConnectionManager.closeConnection(connection);
        }
    }

    /**
     * Remove uma conta do banco de dados.
     *
     * @param numeroConta número da conta a ser removida
     * @throws SQLException se houver erro na operação
     */
    public void delete(int numeroConta) throws SQLException {
        String sql = "DELETE FROM TB_CONTA WHERE NUMERO_CONTA = ?";

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, numeroConta);
            ps.executeUpdate();

        } finally {
            if (ps != null) ps.close();
            ConnectionManager.closeConnection(connection);
        }
    }
}
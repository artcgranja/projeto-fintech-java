package fintech.dao;

import fintech.model.investimento.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para operações CRUD da entidade Investimento.
 */
public class InvestimentoDAO {

    /**
     * Insere um novo investimento no banco de dados.
     *
     * @param investimento objeto Investimento a ser inserido
     * @throws SQLException se houver erro na operação
     */
    public void insert(Investimento investimento) throws SQLException {
        String sql = "INSERT INTO TB_INVESTIMENTO (CODIGO, VALOR_APLICADO, TIPO_INVESTIMENTO, TAXA_ANUAL) VALUES (?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(sql);

            ps.setString(1, investimento.getCodigo());
            ps.setDouble(2, investimento.getValorAplicado());

            String tipoInvestimento = "";
            double taxaAnual = 0.0;

            if (investimento instanceof Cdb) {
                tipoInvestimento = "CDB";
                taxaAnual = ((Cdb) investimento).getTaxaAnual();
            } else if (investimento instanceof TesouroDireto) {
                tipoInvestimento = "TESOURO_DIRETO";
                taxaAnual = ((TesouroDireto) investimento).getTaxaAnual();
            } else if (investimento instanceof Cdi) {
                tipoInvestimento = "CDI";
                taxaAnual = ((Cdi) investimento).getTaxaAnual();
            } else if (investimento instanceof Bolsa) {
                tipoInvestimento = "BOLSA";
                taxaAnual = ((Bolsa) investimento).getTaxaMediaAnual();
            }

            ps.setString(3, tipoInvestimento);
            ps.setDouble(4, taxaAnual);

            ps.executeUpdate();

        } finally {
            if (ps != null) ps.close();
            ConnectionManager.closeConnection(connection);
        }
    }

    /**
     * Busca um investimento pelo código.
     *
     * @param codigo código do investimento
     * @return Investimento encontrado ou null
     * @throws SQLException se houver erro na operação
     */
    public Investimento getByCodigo(String codigo) throws SQLException {
        String sql = "SELECT * FROM TB_INVESTIMENTO WHERE CODIGO = ?";

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, codigo);
            rs = ps.executeQuery();

            if (rs.next()) {
                double valorAplicado = rs.getDouble("VALOR_APLICADO");
                String tipoInvestimento = rs.getString("TIPO_INVESTIMENTO");
                double taxaAnual = rs.getDouble("TAXA_ANUAL");

                switch (tipoInvestimento) {
                    case "CDB":
                        return new Cdb(codigo, valorAplicado, taxaAnual);
                    case "TESOURO_DIRETO":
                        return new TesouroDireto(codigo, valorAplicado, taxaAnual);
                    case "CDI":
                        return new Cdi(codigo, valorAplicado, taxaAnual);
                    case "BOLSA":
                        return new Bolsa(codigo, valorAplicado, taxaAnual);
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
     * Lista todos os investimentos.
     *
     * @return List<Investimento> lista de investimentos
     * @throws SQLException se houver erro na operação
     */
    public List<Investimento> getAll() throws SQLException {
        String sql = "SELECT * FROM TB_INVESTIMENTO ORDER BY CODIGO";
        List<Investimento> investimentos = new ArrayList<>();

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String codigo = rs.getString("CODIGO");
                double valorAplicado = rs.getDouble("VALOR_APLICADO");
                String tipoInvestimento = rs.getString("TIPO_INVESTIMENTO");
                double taxaAnual = rs.getDouble("TAXA_ANUAL");

                switch (tipoInvestimento) {
                    case "CDB":
                        investimentos.add(new Cdb(codigo, valorAplicado, taxaAnual));
                        break;
                    case "TESOURO_DIRETO":
                        investimentos.add(new TesouroDireto(codigo, valorAplicado, taxaAnual));
                        break;
                    case "CDI":
                        investimentos.add(new Cdi(codigo, valorAplicado, taxaAnual));
                        break;
                    case "BOLSA":
                        investimentos.add(new Bolsa(codigo, valorAplicado, taxaAnual));
                        break;
                }
            }

        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            ConnectionManager.closeConnection(connection);
        }

        return investimentos;
    }

    /**
     * Remove um investimento do banco de dados.
     *
     * @param codigo código do investimento a ser removido
     * @throws SQLException se houver erro na operação
     */
    public void delete(String codigo) throws SQLException {
        String sql = "DELETE FROM TB_INVESTIMENTO WHERE CODIGO = ?";

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, codigo);
            ps.executeUpdate();

        } finally {
            if (ps != null) ps.close();
            ConnectionManager.closeConnection(connection);
        }
    }
}
package fintech.app;

import fintech.dao.ContaDAO;
import fintech.model.Conta;
import java.util.List;
import java.sql.SQLException;

/**
 * Classe de teste para o ContaDAO usando Oracle Database.
 * Deve estar em src/fintech/app/TesteDao.java com package fintech.app;
 */
public class TesteDao {
    public static void main(String[] args) {
        try {
            ContaDAO dao = new ContaDAO();

            // 1) Insere 5 registros de teste
            dao.insert(new Conta(101, 150.0));
            dao.insert(new Conta(102, 250.5));
            dao.insert(new Conta(103, 350.75));
            dao.insert(new Conta(104, 450.25));
            dao.insert(new Conta(105, 550.0));

            // 2) Recupera todos os registros
            List<Conta> contas = dao.getAll();

            // 3) Imprime resultados
            System.out.println("=== TesteDao: Registros na tabela CONTA ===");
            for (Conta c : contas) {
                System.out.printf("Conta %d - Saldo: R$ %.2f%n", c.getNumeroConta(), c.getSaldo());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
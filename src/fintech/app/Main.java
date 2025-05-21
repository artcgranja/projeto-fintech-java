package fintech.app;

import fintech.dao.ContaDAO;
import fintech.model.Conta;
import fintech.model.ContaCorrente;
import fintech.model.ContaPoupanca;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1) Cria instâncias e faz operações de negócio
        ContaCorrente cc = new ContaCorrente(1001, 500.0, 200.0);
        ContaPoupanca cp = new ContaPoupanca(2001, 1000.0, 1.5);

        cc.depositar(150.0);
        cc.sacar(700.0);

        cp.depositar(200.0);
        cp.aplicarJuros();
        cp.sacar(50.0);

        // 2) Persiste no banco de dados
        ContaDAO dao = new ContaDAO();
        dao.insert(cc);
        dao.insert(cp);
        // Exemplo: mais 3 contas quaisquer
        dao.insert(new Conta(3001, 150.0));
        dao.insert(new Conta(3002, 350.0));
        dao.insert(new Conta(3003, 600.0));

        // 3) Recupera tudo e imprime
        List<Conta> contas = dao.getAll();
        System.out.println("=== Contas no Banco de Dados ===");
        for (Conta c : contas) {
            System.out.printf("Conta %d — Saldo: %.2f%n",
                    c.getNumeroConta(), c.getSaldo());
        }
    }
}
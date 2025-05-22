package fintech.app;

import fintech.model.Conta;
import fintech.model.ContaCorrente;
import fintech.model.ContaPoupanca;
import fintech.model.operacao.Deposito;
import fintech.model.operacao.Saque;
import fintech.model.operacao.Transferencia;
import fintech.model.cartao.CartaoDebito;
import fintech.model.cartao.CartaoCredito;
import fintech.model.cartao.CartaoOnline;
import fintech.model.investimento.Cdb;
import fintech.model.investimento.TesouroDireto;
import fintech.model.investimento.Bolsa;
import fintech.model.investimento.Cdi;

public class TesteFintech {
    public static void main(String[] args) {
        System.out.println("=== Testes de Contas e Operações ===");

        ContaCorrente cc = new ContaCorrente(1001, 500.0, 200.0);
        ContaPoupanca cp = new ContaPoupanca(2001, 1000.0, 1.5);

        // Operações genéricas na Conta Corrente
        Deposito depCC = new Deposito(150.0);
        Saque saqueCC = new Saque(600.0);
        depCC.executar(cc);
        saqueCC.executar(cc);
        System.out.printf("Saldo final ContaCorrente: R$ %.2f%n", cc.getSaldo());

        // Operações genéricas na Conta Poupança
        Deposito depCP = new Deposito(200.0);
        depCP.executar(cp);
        cp.aplicarJuros();
        Saque saqueCP = new Saque(50.0);
        saqueCP.executar(cp);
        System.out.printf("Saldo final ContaPoupanca: R$ %.2f%n", cp.getSaldo());

        // Transferência com saldo garantido
        System.out.println("\n=== Teste de Transferência ===");
        // Assegura saldo suficiente na conta de origem
        Deposito depParaTransferencia = new Deposito(100.0);
        depParaTransferencia.executar(cc);
        Transferencia tr = new Transferencia(100.0, cp);
        tr.executar(cc);
        System.out.printf("Saldo pós-transferência CC: R$ %.2f%n", cc.getSaldo());
        System.out.printf("Saldo pós-transferência CP: R$ %.2f%n", cp.getSaldo());

        // Testes de Cartões
        System.out.println("\n=== Testes de Cartões ===");
        CartaoDebito cd = new CartaoDebito("1111-2222-3333-4444", "Alice", "123");
        cd.pagar(50.0);

        CartaoCredito cc2 = new CartaoCredito("5555-6666-7777-8888", "Bruno", 1000.0);
        cc2.pagar(200.0);
        cc2.pagarFatura(150.0);

        CartaoOnline co = new CartaoOnline("9999-0000-1111-2222", "Carla", "carla@mail.com");
        co.pagar(75.0);

        // Testes de Investimentos
        System.out.println("\n=== Testes de Investimentos ===");
        Cdb cdb = new Cdb("CDB123", 1000.0, 8.0);
        TesouroDireto td = new TesouroDireto("TD456", 2000.0, 6.5);
        Bolsa bolsa = new Bolsa("PETR4", 1500.0, 10.0);
        Cdi cdi = new Cdi("CDI789", 2500.0, 12.0);

        int dias = 30;
        System.out.printf("Resgate CDB (30 dias): R$ %.2f%n", cdb.calcularResgate(dias));
        System.out.printf("Resgate TesouroDireto (30 dias): R$ %.2f%n", td.calcularResgate(dias));
        System.out.printf("Resgate Bolsa (30 dias): R$ %.2f%n", bolsa.calcularResgate(dias));
        System.out.printf("Resgate CDI (30 dias uteis): R$ %.2f%n", cdi.calcularResgate(dias));
    }
}
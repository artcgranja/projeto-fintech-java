package fintech.model.operacao;

import fintech.model.Conta;

/**
 * Representa uma operação de depósito em uma conta, usando o método público depositar da classe Conta.
 */
public class Deposito extends Operacao {

    public Deposito(double valor) {
        super(valor);
    }

    @Override
    public boolean validar(Conta conta) {
        // Valor deve ser positivo
        return valor > 0;
    }

    @Override
    public void executar(Conta conta) {
        if (!validar(conta)) {
            throw new IllegalStateException("Valor inválido para depósito");
        }
        // Usa o método público depositar() da Conta para ajustar o saldo
        conta.depositar(valor);
        System.out.println("Operação de depósito de R$ " + valor + " concluída com sucesso.");
    }
}
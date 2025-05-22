package fintech.model.operacao;

import fintech.model.Conta;

/**
 * Representa uma operação de saque em uma conta, usando o método público sacar da classe Conta.
 */
public class Saque extends Operacao {

    public Saque(double valor) {
        super(valor);
    }

    @Override
    public boolean validar(Conta conta) {
        // Valor deve ser positivo e haver saldo suficiente
        return valor > 0 && conta.getSaldo() >= valor;
    }

    @Override
    public void executar(Conta conta) {
        if (!validar(conta)) {
            throw new IllegalStateException("Saldo insuficiente ou valor inválido para saque");
        }
        // Usa o método público sacar() da Conta para ajustar o saldo
        boolean sucesso = conta.sacar(valor);
        if (!sucesso) {
            throw new IllegalStateException("Erro ao executar saque na conta");
        }
        // Mensagem de confirmação
        System.out.println("Operação de saque de R$ " + valor + " concluída com sucesso.");
    }
}
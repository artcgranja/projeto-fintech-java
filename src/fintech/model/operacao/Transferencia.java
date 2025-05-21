package fintech.model.operacao;

import fintech.model.Conta;

/**
 * Representa uma operação de transferência entre duas contas.
 */
public class Transferencia extends Operacao {
    private Conta contaDestino;

    /**
     * Construtor da transferência.
     *
     * @param valor           valor a ser transferido
     * @param contaDestino    conta que receberá o valor
     */
    public Transferencia(double valor, Conta contaDestino) {
        super(valor);
        this.contaDestino = contaDestino;
    }

    @Override
    public boolean validar(Conta contaOrigem) {
        // valor positivo e saldo suficiente na conta de origem
        return valor > 0 && contaOrigem.getSaldo() >= valor;
    }

    @Override
    public void executar(Conta contaOrigem) {
        if (!validar(contaOrigem)) {
            throw new IllegalStateException("Saldo insuficiente ou valor inválido para transferência");
        }
        // realiza o saque na conta de origem
        boolean sucessoSaque = contaOrigem.sacar(valor);
        if (!sucessoSaque) {
            throw new IllegalStateException("Falha ao debitar valor da conta de origem");
        }
        // deposita na conta de destino
        contaDestino.depositar(valor);
        System.out.println("Transferência de R$ " + valor + " da conta "
                + contaOrigem.getNumeroConta() + " para a conta "
                + contaDestino.getNumeroConta() + " concluída.");
    }
}
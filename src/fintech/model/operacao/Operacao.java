package fintech.model.operacao;

import fintech.model.Conta;

/**
 * Representa uma operação genérica sobre uma conta.
 */
public abstract class Operacao {
    protected double valor;

    /**
     * Construtor da operação.
     *
     * @param valor valor envolvido na operação
     */
    public Operacao(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    /**
     * Valida se a operação pode ser executada na conta.
     *
     * @param conta conta alvo da operação
     * @return true se válido, false caso contrário
     */
    public abstract boolean validar(Conta conta);

    /**
     * Executa a operação na conta, assumindo que ela já foi validada.
     *
     * @param conta conta alvo
     */
    public abstract void executar(Conta conta);
}
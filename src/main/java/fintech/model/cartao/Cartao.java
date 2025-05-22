package fintech.model.cartao;

/**
 * Classe abstrata que representa um cartão genérico.
 */
public abstract class Cartao {
    protected String numero;
    protected String titular;

    /**
     * Construtor do cartão.
     *
     * @param numero  número do cartão
     * @param titular nome do titular do cartão
     */
    public Cartao(String numero, String titular) {
        this.numero = numero;
        this.titular = titular;
    }

    public String getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    /**
     * Método para realizar um pagamento com o cartão.
     *
     * @param valor valor a ser pago
     */
    public abstract void pagar(double valor);
}
package fintech.model.cartao;

/**
 * Representa um cartão de crédito com limite de crédito e fatura.
 */
public class CartaoCredito extends Cartao {
    private double limiteCredito;
    private double saldoFatura;

    /**
     * Construtor do Cartão de Crédito.
     *
     * @param numero         número do cartão
     * @param titular        nome do titular
     * @param limiteCredito  limite de crédito disponível
     */
    public CartaoCredito(String numero, String titular, double limiteCredito) {
        super(numero, titular);
        this.limiteCredito = limiteCredito;
        this.saldoFatura = 0.0;
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public double getSaldoFatura() {
        return saldoFatura;
    }

    /**
     * Tentativa de pagamento na função de comprar com cartão de crédito.
     * Se o valor ultrapassar o limite disponível, lança exceção.
     */
    @Override
    public void pagar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor de pagamento deve ser positivo");
        }
        if (valor > (limiteCredito - saldoFatura)) {
            throw new IllegalStateException("Limite insuficiente para realizar a compra");
        }
        saldoFatura += valor;
        System.out.println("Compra de R$ " + valor + " lançada na fatura do cartão " + numero + ", titular " + titular + ".");
    }

    /**
     * Realiza pagamento da fatura, zerando o saldo da fatura.
     *
     * @param valorPagamento valor a ser pago da fatura
     */
    public void pagarFatura(double valorPagamento) {
        if (valorPagamento <= 0 || valorPagamento > saldoFatura) {
            throw new IllegalArgumentException("Valor de pagamento da fatura inválido");
        }
        saldoFatura -= valorPagamento;
        System.out.println("Pagamento de fatura de R$ " + valorPagamento + " para o cartão " + numero + ". Saldo restante da fatura: R$ " + saldoFatura + ".");
    }
}
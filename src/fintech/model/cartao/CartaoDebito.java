package fintech.model.cartao;

/**
 * Representa um cartão de débito, que efetua pagamentos debitando diretamente a conta vinculada.
 */
public class CartaoDebito extends Cartao {
    private String codigoSeguranca;

    /**
     * Construtor do Cartão de Débito.
     *
     * @param numero           número do cartão
     * @param titular          nome do titular
     * @param codigoSeguranca  código de segurança (CVV)
     */
    public CartaoDebito(String numero, String titular, String codigoSeguranca) {
        super(numero, titular);
        this.codigoSeguranca = codigoSeguranca;
    }

    public String getCodigoSeguranca() {
        return codigoSeguranca;
    }

    public void setCodigoSeguranca(String codigoSeguranca) {
        this.codigoSeguranca = codigoSeguranca;
    }

    @Override
    public void pagar(double valor) {
        // lógica simplificada de pagamento
        System.out.println("Pagamento de R$ " + valor + " efetuado no débito pelo cartão " + numero + ", titular " + titular + ".");
    }
}
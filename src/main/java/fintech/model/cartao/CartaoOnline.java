package fintech.model.cartao;

/**
 * Representa um cartão online, que além de débito/crédito, pode incluir autenticação adicional.
 */
public class CartaoOnline extends Cartao {
    private String emailAssociado;

    /**
     * Construtor do Cartão Online.
     *
     * @param numero         número do cartão
     * @param titular        nome do titular
     * @param emailAssociado e-mail usado para notificações e autenticação
     */
    public CartaoOnline(String numero, String titular, String emailAssociado) {
        super(numero, titular);
        this.emailAssociado = emailAssociado;
    }

    public String getEmailAssociado() {
        return emailAssociado;
    }

    public void setEmailAssociado(String emailAssociado) {
        this.emailAssociado = emailAssociado;
    }

    /**
     * Efetua um pagamento online, simulando requisição externa e autenticação via e-mail.
     */
    @Override
    public void pagar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor de pagamento deve ser positivo");
        }
        // Simula envio de token por e-mail
        System.out.println("Enviando token de autenticação para " + emailAssociado + "...");
        // Simula aprovação do token
        System.out.println("Token confirmado. Pagamento online de R$ " + valor + " realizado com sucesso no cartão " + numero + ".");
    }
}
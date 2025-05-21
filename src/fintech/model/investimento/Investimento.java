package fintech.model.investimento;

/**
 * Classe abstrata que representa um investimento genérico.
 */
public abstract class Investimento {
    protected String codigo;
    protected double valorAplicado;

    /**
     * Construtor do investimento.
     *
     * @param codigo         código ou identificador do investimento
     * @param valorAplicado  valor inicial aplicado
     */
    public Investimento(String codigo, double valorAplicado) {
        this.codigo = codigo;
        this.valorAplicado = valorAplicado;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getValorAplicado() {
        return valorAplicado;
    }

    /**
     * Calcula o valor de resgate do investimento após o período informado.
     *
     * @param dias  número de dias desde a aplicação
     * @return      valor de resgate
     */
    public abstract double calcularResgate(int dias);
}
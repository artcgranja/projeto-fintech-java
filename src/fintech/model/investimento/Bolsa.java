package fintech.model.investimento;

/**
 * Representa um investimento em Bolsa de Valores, usando rendimento composto anual.
 */
public class Bolsa extends Investimento {
    private double taxaMediaAnual;

    /**
     * Construtor para investimento em ações/bolsa.
     *
     * @param codigo             código ou identificação do ativo
     * @param valorAplicado      valor inicial investido
     * @param taxaMediaAnual     taxa média de retorno anual (% ao ano)
     */
    public Bolsa(String codigo, double valorAplicado, double taxaMediaAnual) {
        super(codigo, valorAplicado);
        this.taxaMediaAnual = taxaMediaAnual;
    }

    public double getTaxaMediaAnual() {
        return taxaMediaAnual;
    }

    public void setTaxaMediaAnual(double taxaMediaAnual) {
        this.taxaMediaAnual = taxaMediaAnual;
    }

    @Override
    public double calcularResgate(int dias) {
        // Rendimento composto anual proporcional ao período
        double periodoAnos = dias / 365.0;
        return valorAplicado * Math.pow(1 + taxaMediaAnual / 100.0, periodoAnos);
    }
}
package fintech.model.investimento;

/**
 * Representa um investimento atrelado ao CDI com rendimento composto diário.
 */
public class Cdi extends Investimento {
    private double taxaAnual;

    /**
     * Construtor do investimento em CDI.
     *
     * @param codigo         código ou identificador do investimento
     * @param valorAplicado  valor inicial aplicado
     * @param taxaAnual      taxa CDI anual (% ao ano)
     */
    public Cdi(String codigo, double valorAplicado, double taxaAnual) {
        super(codigo, valorAplicado);
        this.taxaAnual = taxaAnual;
    }

    public double getTaxaAnual() {
        return taxaAnual;
    }

    public void setTaxaAnual(double taxaAnual) {
        this.taxaAnual = taxaAnual;
    }

    @Override
    public double calcularResgate(int dias) {
        // CDI composto diário: valor * (1 + taxaAnual/100/252) ^ dias
        // Considera 252 dias úteis no ano
        double taxaDiaria = taxaAnual / 100.0 / 252.0;
        return valorAplicado * Math.pow(1 + taxaDiaria, dias);
    }
}
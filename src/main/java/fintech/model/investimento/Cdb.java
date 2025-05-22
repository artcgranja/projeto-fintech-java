package fintech.model.investimento;

/**
 * Representa um investimento em CDB (Certificado de Depósito Bancário) com taxa anual fixa.
 */
public class Cdb extends Investimento {
    private double taxaAnual;

    /**
     * Construtor do CDB.
     *
     * @param codigo          código do investimento
     * @param valorAplicado   valor inicial aplicado
     * @param taxaAnual       taxa de rendimento anual (% ao ano)
     */
    public Cdb(String codigo, double valorAplicado, double taxaAnual) {
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
        // Cálculo de juros simples como base: valorAplicado * (1 + taxaAnual/100 * dias/365)
        return valorAplicado * (1 + (taxaAnual / 100) * dias / 365.0);
    }
}
package fintech.model.investimento;

/**
 * Representa um investimento em Tesouro Direto com rendimento composto diário.
 */
public class TesouroDireto extends Investimento {
    private double taxaAnual;

    /**
     * Construtor do Tesouro Direto.
     *
     * @param codigo         código do título no Tesouro Direto
     * @param valorAplicado  valor inicial aplicado
     * @param taxaAnual      taxa de rendimento anual (% ao ano)
     */
    public TesouroDireto(String codigo, double valorAplicado, double taxaAnual) {
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
        // Juros compostos diários: valor * (1 + taxaAnual/100/365) ^ dias
        double taxaDiaria = taxaAnual / 100.0 / 365.0;
        return valorAplicado * Math.pow(1 + taxaDiaria, dias);
    }
}
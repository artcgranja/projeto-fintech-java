package fintech.model;

public class ContaPoupanca extends Conta {
    private double taxaJuros;

    public ContaPoupanca(int numeroConta, double saldoInicial, double taxaJuros) {
        super(numeroConta, saldoInicial);
        this.taxaJuros = taxaJuros;
    }

    public double getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(double taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    public void aplicarJuros() {
        double juros = getSaldo() * taxaJuros / 100;
        depositar(juros);
        System.out.println("Juros aplicados: " + juros);
    }
}
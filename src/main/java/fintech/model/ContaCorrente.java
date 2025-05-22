package fintech.model;

public class ContaCorrente extends Conta {
    private double limite;

    public ContaCorrente(int numeroConta, double saldoInicial, double limite) {
        super(numeroConta, saldoInicial);
        this.limite = limite;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    @Override
    public boolean sacar(double valor) {
        boolean result;
        if (valor > 0 && (getSaldo() + limite) >= valor) {
            double novoSaldo = getSaldo() - valor;
            setSaldo(novoSaldo);
            System.out.println("Saque na ContaCorrente realizado: " + valor);
            result = true;
        } else {
            System.out.println("Saldo e limite insuficientes para o saque!");
            result = false;
        }
        return result;
    }
}
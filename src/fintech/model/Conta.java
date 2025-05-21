package fintech.model;

public class Conta {
    private int numeroConta;
    private double saldo;

    public Conta(int numeroConta, double saldoInicial) {
        this.numeroConta = numeroConta;
        this.saldo = saldoInicial;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    // Método protegido para permitir alteração do saldo nas subclasses
    protected void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Depósito realizado: " + valor);
        } else {
            System.out.println("Valor inválido para depósito!");
        }
    }

    public boolean sacar(double valor) {
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
            System.out.println("Saque realizado: " + valor);
            return true;
        } else {
            System.out.println("Saldo insuficiente ou valor inválido!");
            return false;
        }
    }
}
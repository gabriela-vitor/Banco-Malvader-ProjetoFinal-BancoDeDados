package model;

public class Conta {
    private String numeroConta;
    private double saldo;

    public Conta(String numeroConta, double saldoInicial) {
        this.numeroConta = numeroConta;
        this.saldo = saldoInicial;
    }

    public double consultarSaldo() {
        return saldo;
    }

    public boolean sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            return true;
        }
        return false;
    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public boolean transferir(Conta contaDestino, double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            contaDestino.depositar(valor);
            return true;
        }
        return false;
    }

    public boolean solicitarEmprestimo(double valor) {
        if (valor > 0) {
            saldo += valor;
            return true;
        }
        return false;
    }
}

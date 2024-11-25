package model;

public class ContaPoupanca extends Conta{

    private double taxaRendimento;

    public ContaPoupanca(String numeroConta, double saldoInicial, String agencia, Cliente cliente , double taxaRendimento) {
        super(numeroConta, saldoInicial, agencia, cliente);
        this.taxaRendimento = taxaRendimento;
    }

    public double calcularRendimento() {
        return consultarSaldo() * taxaRendimento;
    }

    @Override
    public void depositar(double valor) {
        if (valor > 0) {
            setSaldo(consultarSaldo() + valor);
        }
    }

    @Override
    public boolean sacar(double valor) {
        if (valor > 0 && consultarSaldo() >= valor) {
            setSaldo(consultarSaldo() - valor);
            return true;
        }
        return false;
    }

    public double getTaxaRendimento() {
        return taxaRendimento;
    }

    public void setTaxaRendimento(double taxaRendimento) {
        this.taxaRendimento = taxaRendimento;
    }
}

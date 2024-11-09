//classe que herda de usuario e implementa de acordo com cliente

package model;

public class Cliente extends Usuario {
    private String senha;

    // Métodos específicos
    public double consultarSaldo() {
        // Lógica para consultar saldo
        return 0;
    }

    public void depositar(double valor) {
        // Lógica para depósito
    }

    public boolean sacar(double valor) {
        // Lógica para saque
        return true;
    }

    public String consultarExtrato() {
        // Lógica para consultar extrato
        return "";
    }

    public double consultarLimite() {
        // Lógica para consultar limite
        return 0;
    }
}


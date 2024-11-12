//classe que herda de usuario e implementa de acordo com cliente

package model;

public class Cliente extends Usuario {
    private String senha;

    // Métodos específicos como consultar, depositar e sacar
    // Lógica para consultar saldo
    public double consultarSaldo() {
        
        return 0;
    }

    // Lógica para depósito
    public void depositar(double valor) {
        
    }

    // Lógica para saque
    public boolean sacar(double valor) {
       
        return true;
    }

     // Lógica para consultar extrato
    public String consultarExtrato() {
       
        return "";
    }

    // Lógica para consultar limite
    public double consultarLimite() {
        
        return 0;
    }
}


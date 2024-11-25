<<<<<<< Updated upstream
//classe que herda de usuario e implementa funcionalidades especificas para usuario

package model;

import java.time.LocalDate;

public class Funcionario extends Usuario {
    private String codigoFuncionario;
    private String cargo;
    private String senha;

    public Funcionario(int id, String nome, String cpf, LocalDate dataNascimento, String telefone, Endereco endereco) {
        super(id, nome, cpf, dataNascimento, telefone, endereco);
    }

    // métodos específicos como abrir e encerrar conta
    // Lógica para abrir conta
    public void abrirConta(Conta conta) {
       
    }

    // Lógica para encerrar conta
    public void encerrarConta(Conta conta) {
        
    }

   
}

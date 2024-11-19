<<<<<<< Updated upstream
//classe que herda de usuario e implementa funcionalidades especificas para usuario

=======
>>>>>>> Stashed changes
package model;

public class Funcionario extends Usuario {
    private String codigoFuncionario;
    private String cargo;

    // Construtor da classe Funcionario
    public Funcionario(int id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        String telefone,
        Endereco endereco,
        String codigoFuncionario,
        String cargo) {
            
        super(id, nome, cpf, dataNascimento, telefone, endereco); 
        this.codigoFuncionario = codigoFuncionario;
        this.cargo = cargo;
    }

   
<<<<<<< Updated upstream
=======
    public String getCodigoFuncionario() {
        return codigoFuncionario;
    }

    public void setCodigoFuncionario(String codigoFuncionario) {
        this.codigoFuncionario = codigoFuncionario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }


>>>>>>> Stashed changes
}

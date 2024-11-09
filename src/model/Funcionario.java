//classe que herda de usuario e implementa funcionalidades especificas para usuario

package model;

public class Funcionario extends Usuario {
    private String codigoFuncionario;
    private String cargo;
    private String senha;

    // Métodos específicos
    public void abrirConta(Conta conta) {
        // Lógica para abrir conta
    }

    public void encerrarConta(Conta conta) {
        // Lógica para encerrar conta
    }

    // Outros métodos conforme a especificação
}

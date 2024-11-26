package controller;

import dao.FuncionarioDAO;
import model.Funcionario;
import java.util.Random;

public class FuncionarioController {

    private FuncionarioDAO funcionarioDAO;

    public FuncionarioController() {
        this.funcionarioDAO = new FuncionarioDAO();
    }

    // Autenticação de funcionário pelo código e senha
    public boolean autenticarFuncionario(String codigoFuncionario, String senha) {
        return funcionarioDAO.autenticar(codigoFuncionario, senha);
    }

    // Cadastro de um novo funcionário
    public String cadastrarFuncionario(String nome, String cpf, String dataNascimento, String telefone, String senha, 
                                       String cargo) {
        if (nome == null || nome.isEmpty() || cpf == null || cpf.isEmpty() || senha == null || senha.isEmpty()) {
            return "Dados obrigatórios ausentes (nome, CPF ou senha).";
        }
        
        // Chama o DAO para cadastrar o funcionário sem o código
        int idFuncionario = funcionarioDAO.cadastrarFuncionario(nome, cpf, dataNascimento, telefone, senha, cargo);
        
        // Verifica se o cadastro foi bem-sucedido (idFuncionario deve ser maior que 0)
        if (idFuncionario > 0) {
            // Gera o código do funcionário
            String codigoFuncionario = gerarCodigoFuncionario();
            // Atualiza o código gerado para o funcionário no banco de dados
            boolean sucesso = funcionarioDAO.atualizarCodigoFuncionario(idFuncionario, codigoFuncionario);
            return sucesso ? "Funcionário cadastrado com sucesso. Código gerado: " + codigoFuncionario
                           : "Funcionário cadastrado, mas erro ao gerar o código.";
        }
        
        return "Erro ao cadastrar o funcionário.";
    }

    // Consultar informações do funcionário pelo ID
    public String consultarFuncionario(int idFuncionario) {
        return funcionarioDAO.consultarFuncionario(idFuncionario);
    }

    // Atualizar cargo do funcionário
    public String atualizarCargoFuncionario(int idFuncionario, String novoCargo) {
        if (novoCargo == null || novoCargo.isEmpty()) {
            return "Cargo inválido.";
        }
        boolean sucesso = funcionarioDAO.atualizarCargo(idFuncionario, novoCargo);
        return sucesso ? "Cargo atualizado com sucesso." : "Erro ao atualizar o cargo.";
    }

    // Excluir funcionário pelo ID
    public String excluirFuncionario(int idFuncionario) {
        boolean sucesso = funcionarioDAO.excluirFuncionario(idFuncionario);
        return sucesso ? "Funcionário excluído com sucesso." : "Erro ao excluir o funcionário.";
    }

    // Método para gerar um código aleatório de 7 dígitos
    private String gerarCodigoFuncionario() {
        Random random = new Random();
        int codigo = 1000000 + random.nextInt(9000000); // Gera um número aleatório entre 1000000 e 9999999
        return String.valueOf(codigo);
    }

    // Método para atualizar o código do funcionário no banco
    public boolean associarCodigoFuncionario(int idFuncionario, String codigoFuncionario) {
        return funcionarioDAO.atualizarCodigoFuncionario(idFuncionario, codigoFuncionario);
    }
}

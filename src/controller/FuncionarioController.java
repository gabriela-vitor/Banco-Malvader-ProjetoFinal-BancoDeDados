package controller;

import dao.FuncionarioDAO;
import model.Funcionario;

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
                                       String codigoFuncionario, String cargo) {
        if (nome == null || nome.isEmpty() || cpf == null || cpf.isEmpty() || senha == null || senha.isEmpty()) {
            return "Dados obrigatórios ausentes (nome, CPF ou senha).";
        }
        boolean sucesso = funcionarioDAO.cadastrarFuncionario(nome, cpf, dataNascimento, telefone, senha, codigoFuncionario, cargo);
        return sucesso ? "Funcionário cadastrado com sucesso." : "Erro ao cadastrar o funcionário.";
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
}

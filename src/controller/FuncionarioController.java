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

    // Método para consultar informações do funcionário pelo código
    public String consultarFuncionarioPorCodigo(String codigoFuncionario) {
    Funcionario funcionario = funcionarioDAO.consultarFuncionarioPorCodigo(codigoFuncionario);
    if (funcionario != null) {
        return String.format("Nome: %s\nCPF: %s\nData de Nascimento: %s\nTelefone: %s\nCódigo Funcionário: %s\nCargo: %s",
                             funcionario.getNome(), funcionario.getCpf(), funcionario.getDataNascimento(),
                             funcionario.getTelefone(), funcionario.getCodigoFuncionario(), funcionario.getCargo());
    }
    return "Funcionário não encontrado.";
    }


    public String atualizarCargoFuncionario(String codigoFuncionario, String novoCargo) {
        if (novoCargo == null || novoCargo.isEmpty()) {
            return "Cargo inválido.";
        }
    
        // Consulta o funcionário pelo código
        Funcionario funcionario = funcionarioDAO.consultarFuncionarioPorCodigo(codigoFuncionario);
    
        if (funcionario == null) {
            return "Funcionário não encontrado com o código informado.";
        }
    
        boolean sucesso = funcionarioDAO.atualizarCargo(funcionario.getId(), novoCargo);
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

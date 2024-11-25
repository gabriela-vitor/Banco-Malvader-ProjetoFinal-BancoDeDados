package controller;

import dao.FuncionarioDAO;
import dao.ClienteDAO;
import model.Funcionario;
import model.Conta;
import dao.ContaDAO;

public class FuncionarioController {

    private FuncionarioDAO funcionarioDAO;

    public FuncionarioController() {
        this.funcionarioDAO = new FuncionarioDAO();
    }

    // Autenticação de funcionário pelo código e senha
    public boolean autenticarFuncionario(String codigoFuncionario, String senha) {
        return funcionarioDAO.autenticar(codigoFuncionario, senha);
    }

    // Adicionar uma nova conta para cliente
    public String adicionarConta(String nomeCliente, String cpf, String telefone, String endereco, String senha,
                                 String tipoConta, int numeroConta) {
        if (nomeCliente == null || nomeCliente.isEmpty() || cpf == null || cpf.isEmpty() || senha == null || senha.isEmpty()) {
            return "Dados obrigatórios ausentes (nome, CPF ou senha).";
        }
        if (tipoConta == null || (!tipoConta.equalsIgnoreCase("corrente") && !tipoConta.equalsIgnoreCase("poupança"))) {
            return "Tipo de conta inválido. Escolha 'corrente' ou 'poupança'.";
        }

        // Conta conta = new Conta(nomeCliente, cpf, telefone, endereco, senha, tipoConta, numeroConta);

        // // Usando DAO para persistir os dados
        // boolean sucesso = funcionarioDAO.adicionarConta(conta);
        // return sucesso ? "Conta adicionada com sucesso." : "Erro ao adicionar a conta.";
    }

    // Encerrar uma conta de cliente
    public String encerrarConta(int numeroConta, String nomeCliente) {
        if (numeroConta <= 0 || nomeCliente == null || nomeCliente.isEmpty()) {
            return "Número da conta ou nome do cliente inválido.";
        }

        // Buscar conta por número usando DAO
        // Conta conta = funcionarioDAO.buscarContaPorNumero(numeroConta);
        // if (conta != null && conta.getNomeCliente().equals(nomeCliente)) {
        //     boolean sucesso = funcionarioDAO.removerConta(numeroConta);
        //     return sucesso ? "Conta encerrada com sucesso." : "Erro ao encerrar a conta.";
        // } else {
            return "Conta não encontrada ou nome do cliente não corresponde.";
        }
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

    // Excluir funcionário
    public String excluirFuncionario(int idFuncionario) {
        boolean sucesso = funcionarioDAO.excluirFuncionario(idFuncionario);
        return sucesso ? "Funcionário excluído com sucesso." : "Erro ao excluir o funcionário.";
    }
}


package controller;

import dao.FuncionarioDAO;
import model.Funcionario;
import model.Conta;
import sql_banco_malvader.banco_malvader.sql;

public class FuncionarioController {

    private FuncionarioDAO funcionarioDAO;

    
    public FuncionarioController() {
        this.funcionarioDAO = new FuncionarioDAO();
    }

   
    public boolean autenticarFuncionario(String usuario, String senha) {
        Funcionario funcionario = funcionarioDAO.buscarFuncionarioPorId(idFuncionario);
        if (funcionario != null && funcionario.getSenha().equals(senha)) {
            return true;
        }
        return false;
    }

    
    public String adicionarConta(String nomeCliente, String cpf, String telefone, String endereco, String senha,
                                 String tipoConta, int numeroConta) {
        if (nomeCliente == null || nomeCliente.isEmpty() || cpf == null || cpf.isEmpty() || senha == null || senha.isEmpty()) {
            return "Dados obrigatórios ausentes (nome, CPF ou senha).";
        }
        if (tipoConta == null || (!tipoConta.equalsIgnoreCase("corrente") && !tipoConta.equalsIgnoreCase("poupança"))) {
            return "Tipo de conta inválido. Escolha 'corrente' ou 'poupança'.";
        }
        
        Conta conta = new Conta(nomeCliente, cpf, telefone, endereco, senha, tipoConta, numeroConta);
        
        
        boolean sucesso = funcionarioDAO.adicionarConta(conta);
        return sucesso ? "Conta adicionada com sucesso." : "Erro ao adicionar a conta.";
    }

    // Método para encerrar uma conta de um cliente
    public String encerrarConta(int numeroConta, String nomeCliente) {
        if (numeroConta <= 0 || nomeCliente == null || nomeCliente.isEmpty()) {
            return "Número da conta ou nome do cliente inválido.";
        }

        // Buscar a conta no banco de dados
        Conta conta = funcionarioDAO.buscarContaPorNumero(numeroConta);
        if (conta != null && conta.getNomeCliente().equals(nomeCliente)) {
            boolean sucesso = funcionarioDAO.removerConta(conta);
            return sucesso ? "Conta encerrada com sucesso." : "Erro ao encerrar a conta.";
        } else {
            return "Conta não encontrada ou nome do cliente não corresponde.";
        }
    }
}

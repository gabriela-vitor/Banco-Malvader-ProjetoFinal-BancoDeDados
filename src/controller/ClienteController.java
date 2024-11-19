//responsavel por controlar as acoes do cliente
package controller;

import dao.ClienteDAO;

public class ClienteController {
    private ClienteDAO clienteDAO; // Instância do DAO para acessar dados do banco

    // Construtor que inicializa o ClienteDAO
    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }

    // Método para consultar o saldo de um cliente
    public double consultarSaldo(int clienteId) {
        // Chama o método no DAO que retorna o saldo do cliente no banco de dados
        return clienteDAO.consultarSaldo(clienteId);
    }

    // Método para realizar um depósito na conta de um cliente
    public void depositar(int clienteId, double valor) {
        // Chama o método no DAO para incrementar o saldo no banco de dados
        clienteDAO.depositar(clienteId, valor);
    }

    // Método para realizar um saque na conta de um cliente
    public boolean sacar(int clienteId, double valor) {
        // Chama o método no DAO para decrementar o saldo, se houver saldo suficiente
        return clienteDAO.sacar(clienteId, valor);
    }

    // Método para consultar o extrato de um cliente
    public String consultarExtrato(int clienteId) {
        // Chama o método no DAO que retorna uma string representando o extrato
        return clienteDAO.consultarExtrato(clienteId);
    }

    // Método para consultar o limite de crédito de um cliente
    public double consultarLimite(int clienteId) {
        // Chama o método no DAO que retorna o limite de crédito do cliente
        return clienteDAO.consultarLimite(clienteId);
    }

    // Método para autenticar o cliente com usuário e senha
    public boolean autenticar(String usuario, String senha) {
        // Chama o método no DAO que verifica se o usuário e senha estão corretos no banco
        return clienteDAO.autenticar(usuario, senha);
    }
}

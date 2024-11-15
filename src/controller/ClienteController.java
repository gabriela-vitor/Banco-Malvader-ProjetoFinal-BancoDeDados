package controller;

import dao.ClienteDAO;

public class ClienteController {
    private ClienteDAO clienteDAO;

    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }

    public double consultarSaldo(int clienteId) {
        return clienteDAO.consultarSaldo(clienteId);
    }

    public void depositar(int clienteId, double valor) {
        clienteDAO.depositar(clienteId, valor);
    }

    public boolean sacar(int clienteId, double valor) {
        return clienteDAO.sacar(clienteId, valor);
    }

    public String consultarExtrato(int clienteId) {
        return clienteDAO.consultarExtrato(clienteId);
    }

    public double consultarLimite(int clienteId) {
        return clienteDAO.consultarLimite(clienteId);
    }
}

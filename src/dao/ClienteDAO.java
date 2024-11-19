//responsável por realizar operações relacionadas ao cliente
package dao;

import java.sql.*;

public class ClienteDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/bancodedados"; // nome do banco de dados
    private static final String USER = "root"; // usuário do MySQL
    private static final String PASSWORD = ""; // senha do MySQL

    // Método para autenticar o cliente pelo usuário e senha
    public boolean autenticar(String usuario, String senha) {
        String sql = "SELECT id FROM clientes WHERE usuario = ? AND senha = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, usuario);  // Define o nome de usuário na consulta
            ps.setString(2, senha);     // Define a senha na consulta
            ResultSet rs = ps.executeQuery();

            // Se houver um cliente com o usuário e senha fornecidos, retorna true
            return rs.next();  
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Caso haja erro, retorna false
        }
    }

    // Método para consultar o saldo de um cliente
    public double consultarSaldo(int clienteId) {
        double saldo = 0.0;
        String sql = "SELECT saldo FROM clientes WHERE id = ?"; // Consulta para obter o saldo

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, clienteId);  // Define o ID do cliente na consulta
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                saldo = rs.getDouble("saldo");  // Obtém o saldo do cliente
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return saldo;
    }

    // Método para depositar um valor na conta do cliente
    public void depositar(int clienteId, double valor) {
        String sql = "UPDATE clientes SET saldo = saldo + ? WHERE id = ?"; // Atualiza o saldo do cliente
    
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            
            // Verifica se o valor é positivo
            if (valor <= 0) {
                System.out.println("Valor de depósito inválido");
                return;
            }
    
            ps.setDouble(1, valor);  // Define o valor a ser depositado
            ps.setInt(2, clienteId);  // Define o ID do cliente
            
            int rowsAffected = ps.executeUpdate();  // Executa a atualização no banco de dados
    
            if (rowsAffected > 0) {
                System.out.println("Depósito realizado com sucesso!");
            } else {
                System.out.println("Cliente não encontrado ou não foi possível realizar o depósito.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao realizar depósito: " + e.getMessage());
        }
    }
    

    // Método para sacar um valor da conta do cliente
    public boolean sacar(int clienteId, double valor) {
        String sql = "UPDATE clientes SET saldo = saldo - ? WHERE id = ? AND saldo >= ?"; // Verifica se há saldo suficiente

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1, valor);  // Define o valor a ser sacado
            ps.setInt(2, clienteId);  // Define o ID do cliente
            ps.setDouble(3, valor);   // Verifica se o cliente tem saldo suficiente

            int rowsAffected = ps.executeUpdate();  // Executa a atualização no banco de dados
            return rowsAffected > 0;  // Retorna true se o saque for bem-sucedido, false caso contrário
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para consultar o extrato do cliente 
    public String consultarExtrato(int clienteId) {
        return "Extrato do Cliente " + clienteId + ": Saldo Atual = " + consultarSaldo(clienteId);
    }

    // Método para consultar o limite de crédito do cliente 
    public double consultarLimite(int clienteId) {
        return 1000.0; // Retorna 1000 como limite
    }
}

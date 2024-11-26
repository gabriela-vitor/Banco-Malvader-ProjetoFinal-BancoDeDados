package dao;

import model.Funcionario;
import model.Cliente;
import model.Usuario;
import sql_banco__malvader.DBUtil;

import java.sql.*;

public class UsuarioDAO {


    // Construtor para inicializar a conexão com o banco de dados


    // Método para autenticar um usuário (Cliente ou Funcionario)
    public Usuario autenticar(String cpf, String senha) throws SQLException {
        String queryCliente = "SELECT * FROM cliente WHERE cpf = ? AND senha = ?";
        String queryFuncionario = "SELECT * FROM funcionario f JOIN usuario u ON f.id_usuario = u.id_usuario WHERE u.cpf = ? AND u.senha = ?";

        // Verificando se o CPF e senha correspondem a um cliente
        try (
                Connection connection = DBUtil.conectar();
                PreparedStatement stmt = connection.prepareStatement(queryCliente)) {
            stmt.setString(1, cpf);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Se encontrar o cliente, cria o objeto Cliente
                return new Cliente(
                        rs.getInt("id_usuario"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getString("telefone"),
                        null, // O endereço pode ser obtido em outro método, caso necessário
                        rs.getString("senha"),
                        0.0 // O limite pode ser obtido no banco, ou em outro método
                );
            }
        }

        // Verificando se o CPF e senha correspondem a um funcionário
        try (                Connection connection = DBUtil.conectar();
                             PreparedStatement stmt = connection.prepareStatement(queryFuncionario)) {
            stmt.setString(1, cpf);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Se encontrar o funcionário, cria o objeto Funcionario
                return new Funcionario(
                        rs.getInt("id_usuario"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getString("telefone"),
                        null, // O endereço pode ser obtido em outro método, caso necessário
                        rs.getString("codigo_funcionario"),
                        rs.getString("cargo"),
                        rs.getString("senha")
                );
            }
        }

        // Se não encontrar nenhum usuário correspondente, retorna null
        return null;
    }

    // Método para buscar um usuário por ID
    public Usuario buscarPorId(int id) throws SQLException {
        String query = "SELECT * FROM usuario WHERE id_usuario = ?";

        try (                Connection connection = DBUtil.conectar();

                             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Se encontrar o usuário, retorna um novo objeto Usuario (Cliente ou Funcionario)
                if (rs.getString("tipo_usuario").equals("CLIENTE")) {
                    return new Cliente(
                            rs.getInt("id_usuario"),
                            rs.getString("nome"),
                            rs.getString("cpf"),
                            rs.getDate("data_nascimento").toLocalDate(),
                            rs.getString("telefone"),
                            null, // O endereço pode ser obtido em outro método
                            rs.getString("senha"),
                            0.0 // O limite pode ser obtido no banco
                    );
                } else {
                    return new Funcionario(
                            rs.getInt("id_usuario"),
                            rs.getString("nome"),
                            rs.getString("cpf"),
                            rs.getDate("data_nascimento").toLocalDate(),
                            rs.getString("telefone"),
                            null, // O endereço pode ser obtido em outro método
                            null, // O código do funcionário pode ser obtido
                            null, // O cargo do funcionário pode ser obtido
                            rs.getString("senha")
                    );
                }
            }
        }

        return null; // Retorna null se não encontrar o usuário
    }

    // Método para salvar um novo cliente no banco de dados
    public void salvarCliente(Cliente cliente) throws SQLException {
        String query = "INSERT INTO usuario (nome, cpf, data_nascimento, telefone, tipo_usuario, senha) VALUES (?, ?, ?, ?, 'CLIENTE', ?)";
        try (                Connection connection = DBUtil.conectar();

                             PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setDate(3, Date.valueOf(cliente.getDataNascimento()));
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getSenha());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                // Recupera o ID do usuário gerado
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int idUsuario = rs.getInt(1);

                    // Agora insere os dados específicos do cliente na tabela cliente
                    String queryCliente = "INSERT INTO cliente (id_usuario, nome, cpf, data_nascimento, telefone, tipo_usuario, senha) VALUES (?, ?, ?, ?, ?, 'CLIENTE', ?)";
                    try (PreparedStatement stmtCliente = connection.prepareStatement(queryCliente)) {
                        stmtCliente.setInt(1, idUsuario);
                        stmtCliente.setString(2, cliente.getNome());
                        stmtCliente.setString(3, cliente.getCpf());
                        stmtCliente.setDate(4, Date.valueOf(cliente.getDataNascimento()));
                        stmtCliente.setString(5, cliente.getTelefone());
                        stmtCliente.setString(6, cliente.getSenha());
                        stmtCliente.executeUpdate();
                    }
                }
            }
        }
    }

    // Método para salvar um novo funcionário no banco de dados
    public void salvarFuncionario(Funcionario funcionario) throws SQLException {
        String query = "INSERT INTO usuario (nome, cpf, data_nascimento, telefone, tipo_usuario, senha) VALUES (?, ?, ?, ?, 'FUNCIONARIO', ?)";
        try (                Connection connection = DBUtil.conectar();

                             PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setDate(3, Date.valueOf(funcionario.getDataNascimento()));
            stmt.setString(4, funcionario.getTelefone());
            stmt.setString(5, funcionario.getSenha());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                // Recupera o ID do usuário gerado
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int idUsuario = rs.getInt(1);

                    // Agora insere os dados específicos do funcionário na tabela funcionario
                    String queryFuncionario = "INSERT INTO funcionario (id_usuario, codigo_funcionario, cargo) VALUES (?, ?, ?)";
                    try (PreparedStatement stmtFuncionario = connection.prepareStatement(queryFuncionario)) {
                        stmtFuncionario.setInt(1, idUsuario);
                        stmtFuncionario.setString(2, funcionario.getCodigoFuncionario());
                        stmtFuncionario.setString(3, funcionario.getCargo());
                        stmtFuncionario.executeUpdate();
                    }
                }
            }
        }
    }


    public Usuario buscarPorNomeESenha(String nome, String senha) throws SQLException {
        // Query genérica para cliente e funcionário
        String queryCliente = "SELECT * FROM cliente WHERE nome = ? AND senha = ?";
        String queryFuncionario = "SELECT * FROM funcionario f JOIN usuario u ON f.id_usuario = u.id_usuario WHERE u.nome = ? AND u.senha = ?";

        // Busca no banco de dados para clientes
        try (Connection connection = DBUtil.conectar();
             PreparedStatement stmt = connection.prepareStatement(queryCliente)) {
            stmt.setString(1, nome);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Criação do objeto Cliente
                return new Cliente(
                        rs.getInt("id_usuario"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getString("telefone"),
                        null, // Endereço pode ser obtido separadamente
                        rs.getString("senha"),
                        rs.getDouble("limite") // Supondo que o limite esteja no banco
                );
            }
        }

        // Busca no banco de dados para funcionários
        try (Connection connection = DBUtil.conectar();
             PreparedStatement stmt = connection.prepareStatement(queryFuncionario)) {
            stmt.setString(1, nome);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Criação do objeto Funcionario
                return new Funcionario(
                        rs.getInt("id_usuario"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getString("telefone"),
                        null, // Endereço pode ser obtido separadamente
                        rs.getString("codigo_funcionario"),
                        rs.getString("cargo"),
                        rs.getString("senha")
                );
            }
        }

        // Retorna null se nenhum usuário for encontrado
        return null;
    }

}

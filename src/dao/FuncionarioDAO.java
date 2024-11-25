package dao;

import util.DBUtil;

import java.sql.*;

public class FuncionarioDAO {

    // Método para autenticar o funcionário pelo código e senha
    public boolean autenticar(String codigoFuncionario, String senha) {
        String sql = "SELECT f.id_funcionario " +
                     "FROM funcionario f " +
                     "INNER JOIN usuario u ON f.id_usuario = u.id_usuario " +
                     "WHERE f.codigo_funcionario = ? AND u.senha = ?";

        try (Connection connection = DBUtil.conectar();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, codigoFuncionario); // Define o código do funcionário
            ps.setString(2, senha); // Define a senha do funcionário

            ResultSet rs = ps.executeQuery();
            return rs.next(); // Retorna true se o funcionário for autenticado
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retorna false em caso de erro
        }
    }

    // Método para cadastrar um novo funcionário
    public boolean cadastrarFuncionario(String nome, String cpf, String dataNascimento, String telefone,
                                        String senha, String codigoFuncionario, String cargo) {
        String usuarioSql = "INSERT INTO usuario (nome, cpf, data_nascimento, telefone, tipo_usuario, senha) " +
                            "VALUES (?, ?, ?, ?, 'FUNCIONARIO', ?)";

        String funcionarioSql = "INSERT INTO funcionario (codigo_funcionario, cargo, id_usuario) " +
                                "VALUES (?, ?, ?)";

        try (Connection connection = DBUtil.conectar()) {
            connection.setAutoCommit(false); // Inicia transação

            try (PreparedStatement usuarioPs = connection.prepareStatement(usuarioSql, Statement.RETURN_GENERATED_KEYS)) {
                usuarioPs.setString(1, nome);
                usuarioPs.setString(2, cpf);
                usuarioPs.setDate(3, Date.valueOf(dataNascimento));
                usuarioPs.setString(4, telefone);
                usuarioPs.setString(5, senha);

                usuarioPs.executeUpdate();

                ResultSet rs = usuarioPs.getGeneratedKeys();
                if (rs.next()) {
                    int usuarioId = rs.getInt(1);

                    try (PreparedStatement funcionarioPs = connection.prepareStatement(funcionarioSql)) {
                        funcionarioPs.setString(1, codigoFuncionario);
                        funcionarioPs.setString(2, cargo);
                        funcionarioPs.setInt(3, usuarioId);

                        funcionarioPs.executeUpdate();
                        connection.commit(); // Confirma a transação
                        return true; // Cadastro bem-sucedido
                    }
                }
            } catch (SQLException e) {
                connection.rollback(); // Reverte a transação em caso de erro
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    // Método para consultar informações do funcionário pelo ID
    public String consultarFuncionario(int idFuncionario) {
        String sql = "SELECT u.nome, u.cpf, u.data_nascimento, u.telefone, f.codigo_funcionario, f.cargo " +
                     "FROM funcionario f " +
                     "INNER JOIN usuario u ON f.id_usuario = u.id_usuario " +
                     "WHERE f.id_funcionario = ?";

        try (Connection connection = DBUtil.conectar();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idFuncionario);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                Date dataNascimento = rs.getDate("data_nascimento");
                String telefone = rs.getString("telefone");
                String codigoFuncionario = rs.getString("codigo_funcionario");
                String cargo = rs.getString("cargo");

                return String.format("Nome: %s\nCPF: %s\nData de Nascimento: %s\nTelefone: %s\nCódigo Funcionário: %s\nCargo: %s",
        nome, cpf, dataNascimento, telefone, codigoFuncionario, cargo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Funcionário não encontrado.";
    }

    // Método para atualizar o cargo de um funcionário
    public boolean atualizarCargo(int idFuncionario, String novoCargo) {
        String sql = "UPDATE funcionario SET cargo = ? WHERE id_funcionario = ?";

        try (Connection connection = DBUtil.conectar();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, novoCargo);
            ps.setInt(2, idFuncionario);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Retorna true se o cargo foi atualizado
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para excluir um funcionário pelo ID
    public boolean excluirFuncionario(int idFuncionario) {
        String sql = "DELETE FROM funcionario WHERE id_funcionario = ?";

        try (Connection connection = DBUtil.conectar();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idFuncionario);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Retorna true se o funcionário foi excluído
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

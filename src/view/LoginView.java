package view;

import dao.FuncionarioDAO;
import dao.ClienteDAO;
import dao.UsuarioDAO;
import model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginView extends JFrame {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();

    // Construtor da tela do Login
    public LoginView() {
        setTitle("Login - Selecione o tipo de acesso");
        setSize(400, 250); // Define o tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o programa ao fechar a janela
        setLocationRelativeTo(null); // Centraliza a janela

        setLayout(new GridLayout(3, 1, 10, 10)); // Layout para os botões

        JButton clienteButton = new JButton("Acessar como Cliente");
        JButton funcionarioButton = new JButton("Acessar como Funcionário");
        JButton sairButton = new JButton("Sair");

        clienteButton.addActionListener(e -> {
            mostrarCamposLogin("Cliente");
            dispose(); // Fecha a janela principal
        });

        funcionarioButton.addActionListener(e -> {
            mostrarCamposLogin("Funcionário");
            dispose(); // Fecha a janela principal
        });

        sairButton.addActionListener(e -> System.exit(0)); // Finaliza a aplicação

        add(clienteButton);
        add(funcionarioButton);
        add(sairButton);

        setVisible(true);
    }

    // Método para mostrar os campos de login após escolher o tipo de acesso
    private void mostrarCamposLogin(String tipoAcesso) {
        JFrame loginFrame = new JFrame("Login - " + tipoAcesso);
        loginFrame.setSize(400, 200);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha apenas esta janela
        loginFrame.setLocationRelativeTo(null); // Centraliza a janela

        loginFrame.setLayout(new GridLayout(4, 2, 10, 10)); // Layout de grid

        JLabel usuarioLabel = new JLabel("Usuário:");
        JTextField usuarioField = new JTextField();
        JLabel senhaLabel = new JLabel("Senha:");
        JPasswordField senhaField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            String usuario = usuarioField.getText();
            String senha = new String(senhaField.getPassword());

            if (usuario.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(loginFrame, "Por favor, preencha todos os campos.");
            } else {
                autenticarUsuario(loginFrame, tipoAcesso, usuario, senha);
            }
        });

        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(e -> {
            loginFrame.dispose(); // Fecha a janela de login
            new LoginView(); // Volta para a tela principal
        });

        loginFrame.add(usuarioLabel);
        loginFrame.add(usuarioField);
        loginFrame.add(senhaLabel);
        loginFrame.add(senhaField);
        loginFrame.add(voltarButton);
        loginFrame.add(loginButton);

        loginFrame.setVisible(true);
    }

    // Método para autenticar usuário
    private void autenticarUsuario(JFrame loginFrame, String tipoAcesso, String usuario, String senha) {
        try {
            Usuario usuarioAutenticado = usuarioDAO.buscarPorNomeESenha(usuario, senha);
            if (usuarioAutenticado == null) {
                JOptionPane.showMessageDialog(loginFrame, "Usuário ou senha incorretos.");
                return;
            }

            boolean acessoValido = false;
            if (tipoAcesso.equals("Funcionário")) {
                acessoValido = funcionarioDAO.verificarFuncionarioPorUsuario(usuarioAutenticado.getId());
            } else if (tipoAcesso.equals("Cliente")) {
                acessoValido = clienteDAO.verificarClientePorUsuario(usuarioAutenticado.getId());
            }

            if (acessoValido) {
                JOptionPane.showMessageDialog(loginFrame, "Login realizado com sucesso!");
                loginFrame.dispose();
                if (tipoAcesso.equals("Funcionário")) {
                    new MenuFuncionarioView();
                } else {
                    new MenuClienteView();
                }
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Acesso inválido para o tipo selecionado.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(loginFrame, "Erro ao autenticar: " + ex.getMessage());
        }
    }

    // Método principal para iniciar a aplicação
    public static void main(String[] args) {
        new LoginView();
    }
}

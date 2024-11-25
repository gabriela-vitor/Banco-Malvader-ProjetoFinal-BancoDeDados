package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Classe que representa a interface de login para o sistema Banco Malvader
public class LoginView extends JFrame {
    private JTextField campoUsuario; // Campo para entrada do nome de usuário
    private JPasswordField campoSenha; // Campo para entrada da senha
    private JButton botaoFuncionario, botaoCliente, botaoSair; // Botões para login e sair

    // Credenciais fixas para validação (exemplo)
    private final String usuarioFuncionario = ""; // Usuário de exemplo para funcionário
    private final String senhaFuncionario = ""; // Senha de exemplo para funcionário
    private final String usuarioCliente = ""; // Usuário de exemplo para cliente
    private final String senhaCliente = ""; // Senha de exemplo para cliente

    // Construtor da classe LoginView
    public LoginView() {
        setTitle("Banco Malvader - Login"); // Define o título da janela
        setSize(300, 180); // Define o tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define a ação ao fechar a janela
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setLayout(null); // Desativa o layout automático para uso de posicionamento manual

        // Rótulo (label) para o campo de usuário
        JLabel labelUsuario = new JLabel("Usuário:");
        labelUsuario.setBounds(20, 20, 80, 25); // Define a posição e o tamanho do rótulo
        add(labelUsuario);

        // Campo de entrada para o usuário
        campoUsuario = new JTextField();
        campoUsuario.setBounds(100, 20, 150, 25);
        add(campoUsuario);

        // Rótulo (label) para o campo de senha
        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setBounds(20, 50, 80, 25);
        add(labelSenha);

        // Campo de entrada para a senha
        campoSenha = new JPasswordField();
        campoSenha.setBounds(100, 50, 150, 25);
        add(campoSenha);

        // Botão para login como funcionário
        botaoFuncionario = new JButton("Funcionário");
        botaoFuncionario.setBounds(20, 90, 100, 25);
        botaoFuncionario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                autenticarFuncionario(); // Chama a função de autenticação de funcionário
            }
        });
        add(botaoFuncionario);

        // Botão para login como cliente
        botaoCliente = new JButton("Cliente");
        botaoCliente.setBounds(150, 90, 100, 25);
        botaoCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                autenticarCliente(); // Chama a função de autenticação de cliente
            }
        });
        add(botaoCliente);

        // Botão para sair do sistema
        botaoSair = new JButton("Sair");
        botaoSair.setBounds(100, 130, 100, 25);
        botaoSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Encerra o programa
            }
        });
        add(botaoSair);
    }

    // Função para autenticar um funcionário
    private void autenticarFuncionario() {
        String usuario = campoUsuario.getText(); // Obtém o texto do campo de usuário
        String senha = new String(campoSenha.getPassword()); // Obtém o texto do campo de senha

        if (usuario.equals(usuarioFuncionario) && senha.equals(senhaFuncionario)) {
            JOptionPane.showMessageDialog(this, "Login de Funcionário bem-sucedido!");
            abrirTelaFuncionario(); // Abre a tela de funcionário
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha de funcionário incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Função para autenticar um cliente
    private void autenticarCliente() {
        String usuario = campoUsuario.getText(); // Obtém o texto do campo de usuário
        String senha = new String(campoSenha.getPassword()); // Obtém o texto do campo de senha

        if (usuario.equals(usuarioCliente) && senha.equals(senhaCliente)) {
            JOptionPane.showMessageDialog(this, "Login de Cliente bem-sucedido!");
            abrirTelaCliente(); // Abre a tela de cliente
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha de cliente incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Função para abrir a tela do funcionário
    private void abrirTelaFuncionario() {
        JOptionPane.showMessageDialog(this, "Abrindo a tela do Funcionário...");
        this.dispose(); // Fecha a tela de login
    }

    // Função para abrir a tela do cliente 
    private void abrirTelaCliente() {
        new MenuClienteView(1); // Simula a abertura da tela do cliente com ID 1
        this.dispose(); // Fecha a tela de login
    }

    // Função principal para iniciar a aplicação
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginView().setVisible(true)); // Exibe a interface gráfica
    }
}

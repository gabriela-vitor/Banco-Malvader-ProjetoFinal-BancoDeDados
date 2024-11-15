package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField campoUsuario;
    private JPasswordField campoSenha;
    private JButton botaoFuncionario, botaoCliente, botaoSair;

    // Exemplos de credenciais para Funcionário e Cliente
    private final String usuarioFuncionario = "funcionario";
    private final String senhaFuncionario = "func123";
    private final String usuarioCliente = "cliente";
    private final String senhaCliente = "cli123";

    public LoginView() {
        setTitle("Banco Malvader - Login");
        setSize(300, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel labelUsuario = new JLabel("Usuário:");
        labelUsuario.setBounds(20, 20, 80, 25);
        add(labelUsuario);

        campoUsuario = new JTextField();
        campoUsuario.setBounds(100, 20, 150, 25);
        add(campoUsuario);

        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setBounds(20, 50, 80, 25);
        add(labelSenha);

        campoSenha = new JPasswordField();
        campoSenha.setBounds(100, 50, 150, 25);
        add(campoSenha);

        botaoFuncionario = new JButton("Funcionário");
        botaoFuncionario.setBounds(20, 90, 100, 25);
        botaoFuncionario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                autenticarFuncionario();  // função de autenticação para o Funcionário
            }
        });
        add(botaoFuncionario);

        botaoCliente = new JButton("Cliente");
        botaoCliente.setBounds(150, 90, 100, 25);
        botaoCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                autenticarCliente();  // função de autenticação para o Cliente
            }
        });
        add(botaoCliente);

        // Botão Sair
        botaoSair = new JButton("Sair");
        botaoSair.setBounds(100, 130, 100, 25);
        botaoSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // encerra o programa
            }
        });
        add(botaoSair);
    }

    // função de autenticação para o Funcionário
    private void autenticarFuncionario() {
        String usuario = campoUsuario.getText();
        String senha = new String(campoSenha.getPassword());

        if (usuario.equals(usuarioFuncionario) && senha.equals(senhaFuncionario)) {
            JOptionPane.showMessageDialog(this, "Login de Funcionário bem-sucedido!");
            abrirTelaFuncionario();  // abre a tela do funcionário
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha de funcionário incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // função de autenticação para o Cliente
    private void autenticarCliente() {
        String usuario = campoUsuario.getText();
        String senha = new String(campoSenha.getPassword());

        if (usuario.equals(usuarioCliente) && senha.equals(senhaCliente)) {
            JOptionPane.showMessageDialog(this, "Login de Cliente bem-sucedido!");
            abrirTelaCliente();  // abre a tela do cliente
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha de cliente incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Função para abrir a tela do Funcionário
    private void abrirTelaFuncionario() {
        JOptionPane.showMessageDialog(this, "Abrindo a tela do Funcionário...");
        this.dispose();  // fecha a tela de login
    }

    // função para abrir a tela do Cliente
    private void abrirTelaCliente() {
        new MenuClienteView(1);  // Exemplo com o ID 1 para o cliente
        this.dispose();  // fecha a tela de login
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
    }
}

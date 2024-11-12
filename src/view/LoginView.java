//tela de login onde o usuario escolher funcionario ou cliente

package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField campoUsuario;
    private JPasswordField campoSenha;
    private JButton botaoFuncionario, botaoCliente, botaoSair;

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
                // implementar autenticação do funcionário
            }
        });
        add(botaoFuncionario);

        botaoCliente = new JButton("Cliente");
        botaoCliente.setBounds(150, 90, 100, 25);
        botaoCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // implementar autenticação do cliente
            }
        });
        add(botaoCliente);

        // botão Sair
        botaoSair = new JButton("Sair");
        botaoSair.setBounds(100, 130, 100, 25);  // botao sair abaixo dos outros botoes
        botaoSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // encerra o programa
            }
        });
        add(botaoSair);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
    }
}

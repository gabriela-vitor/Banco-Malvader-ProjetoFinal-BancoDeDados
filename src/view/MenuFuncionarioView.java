package view;

import dao.FuncionarioDAO;

import javax.swing.*;
import java.awt.*;

public class MenuFuncionarioView extends JFrame {
    private final FuncionarioDAO funcionarioDAO;

    private JTextField txtCodigoFuncionario;
    private JPasswordField txtSenha;

    public MenuFuncionarioView() {
        funcionarioDAO = new FuncionarioDAO();
        initComponents();
    }

    private void initComponents() {
        setTitle("Menu de Funcionários");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel Principal
        JPanel mainPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        // Campos de autenticação
        mainPanel.add(new JLabel("Código do Funcionário:"));
        txtCodigoFuncionario = new JTextField();
        mainPanel.add(txtCodigoFuncionario);

        mainPanel.add(new JLabel("Senha:"));
        txtSenha = new JPasswordField();
        mainPanel.add(txtSenha);

        // Botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnAutenticar = new JButton("Autenticar");
        btnAutenticar.addActionListener(e -> autenticarFuncionario());
        buttonPanel.add(btnAutenticar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> voltarParaLogin());
        buttonPanel.add(btnVoltar);

        // Adiciona componentes à tela
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void autenticarFuncionario() {
        String codigo = txtCodigoFuncionario.getText();
        String senha = new String(txtSenha.getPassword());

        if (funcionarioDAO.autenticar(codigo, senha)) {
            JOptionPane.showMessageDialog(this, "Autenticação bem-sucedida!");
            SwingUtilities.invokeLater(FuncionarioView::new); // Abre a próxima tela
            dispose(); // Fecha a tela atual
        } else {
            JOptionPane.showMessageDialog(this, "Falha na autenticação. Verifique o código e a senha.");
        }
    }

    private void voltarParaLogin() {
        dispose(); // Fecha a tela atual
        SwingUtilities.invokeLater(LoginView::new); // Retorna ao menu principal
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuFuncionarioView::new);
    }
}

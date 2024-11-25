package view;

import java.util.Scanner;
import view.LoginView;

import dao.FuncionarioDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFuncionarioView extends JFrame {
    private final FuncionarioDAO funcionarioDAO;

    // Componentes
    private JTextField txtCodigoFuncionario, txtSenha, txtIdFuncionario, txtNome, txtCpf, txtDataNascimento, txtTelefone, txtCargo;
    private JTextArea txtAreaOutput;

    public MenuFuncionarioView() {
        funcionarioDAO = new FuncionarioDAO();
        initComponents();
    }

    private void initComponents() {
        setTitle("Menu de Funcionários");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel Principal
        JPanel mainPanel = new JPanel(new GridLayout(10, 2, 5, 5));

        // Campos para autenticação
        mainPanel.add(new JLabel("Código do Funcionário:"));
        txtCodigoFuncionario = new JTextField();
        mainPanel.add(txtCodigoFuncionario);

        mainPanel.add(new JLabel("Senha:"));
        txtSenha = new JPasswordField();
        mainPanel.add(txtSenha);

        JButton btnAutenticar = new JButton("Autenticar");
        btnAutenticar.addActionListener(e -> autenticarFuncionario());
        mainPanel.add(btnAutenticar);

        mainPanel.add(new JLabel("ID do Funcionário:"));
        txtIdFuncionario = new JTextField();
        mainPanel.add(txtIdFuncionario);

        // Campos para cadastro
        mainPanel.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        mainPanel.add(txtNome);

        mainPanel.add(new JLabel("CPF:"));
        txtCpf = new JTextField();
        mainPanel.add(txtCpf);

        mainPanel.add(new JLabel("Data de Nascimento (YYYY-MM-DD):"));
        txtDataNascimento = new JTextField();
        mainPanel.add(txtDataNascimento);

        mainPanel.add(new JLabel("Telefone:"));
        txtTelefone = new JTextField();
        mainPanel.add(txtTelefone);

        mainPanel.add(new JLabel("Cargo:"));
        txtCargo = new JTextField();
        mainPanel.add(txtCargo);

        // Botões
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(e -> cadastrarFuncionario());
        mainPanel.add(btnCadastrar);

        JButton btnConsultar = new JButton("Consultar");
        btnConsultar.addActionListener(e -> consultarFuncionario());
        mainPanel.add(btnConsultar);

        JButton btnAtualizar = new JButton("Atualizar Cargo");
        btnAtualizar.addActionListener(e -> atualizarCargoFuncionario());
        mainPanel.add(btnAtualizar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(e -> excluirFuncionario());
        mainPanel.add(btnExcluir);

        // Área de saída
        txtAreaOutput = new JTextArea(10, 50);
        txtAreaOutput.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtAreaOutput);

        add(mainPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void autenticarFuncionario() {
        String codigo = txtCodigoFuncionario.getText();
        String senha = txtSenha.getText();

        if (funcionarioDAO.autenticar(codigo, senha)) {
            txtAreaOutput.setText("Autenticação bem-sucedida!");
        } else {
            txtAreaOutput.setText("Falha na autenticação. Verifique o código e a senha.");
        }
    }

    private void cadastrarFuncionario() {
        String nome = txtNome.getText();
        String cpf = txtCpf.getText();
        String dataNascimento = txtDataNascimento.getText();
        String telefone = txtTelefone.getText();
        String senha = txtSenha.getText();
        String codigo = txtCodigoFuncionario.getText();
        String cargo = txtCargo.getText();

        boolean sucesso = funcionarioDAO.cadastrarFuncionario(nome, cpf, dataNascimento, telefone, senha, codigo, cargo);
        txtAreaOutput.setText(sucesso ? "Funcionário cadastrado com sucesso!" : "Erro ao cadastrar funcionário.");
    }

    private void consultarFuncionario() {
        try {
            int idFuncionario = Integer.parseInt(txtIdFuncionario.getText());
            String resultado = funcionarioDAO.consultarFuncionario(idFuncionario);
            txtAreaOutput.setText(resultado);
        } catch (NumberFormatException e) {
            txtAreaOutput.setText("Por favor, insira um ID válido.");
        }
    }

    private void atualizarCargoFuncionario() {
        try {
            int idFuncionario = Integer.parseInt(txtIdFuncionario.getText());
            String novoCargo = txtCargo.getText();

            boolean sucesso = funcionarioDAO.atualizarCargo(idFuncionario, novoCargo);
            txtAreaOutput.setText(sucesso ? "Cargo atualizado com sucesso!" : "Erro ao atualizar o cargo.");
        } catch (NumberFormatException e) {
            txtAreaOutput.setText("Por favor, insira um ID válido.");
        }
    }

    private void excluirFuncionario() {
        try {
            int idFuncionario = Integer.parseInt(txtIdFuncionario.getText());

            boolean sucesso = funcionarioDAO.excluirFuncionario(idFuncionario);
            txtAreaOutput.setText(sucesso ? "Funcionário excluído com sucesso!" : "Erro ao excluir o funcionário.");
        } catch (NumberFormatException e) {
            txtAreaOutput.setText("Por favor, insira um ID válido.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuFuncionarioView::new);
    }
}

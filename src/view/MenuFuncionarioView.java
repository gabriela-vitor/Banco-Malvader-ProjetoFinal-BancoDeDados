package view;

import controller.FuncionarioController;
import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

public class MenuFuncionarioView extends JFrame {

    private FuncionarioController funcionarioController;

    public MenuFuncionarioView() {
        funcionarioController = new FuncionarioController();
        initComponents();
    }

    private void initComponents() {
        setTitle("Funcionário - Opções");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelBotoes = new JPanel(new GridLayout(6, 1, 10, 10));
        panelBotoes.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnCadastrarFuncionario = new JButton("Cadastrar Funcionário");
        JButton btnCadastrarConta = new JButton("Cadastrar Conta");
        JButton btnConsultar = new JButton("Consultar");
        JButton btnAtualizar = new JButton("Atualizar Cargo");
        JButton btnExcluir = new JButton("Excluir");

        btnCadastrarFuncionario.addActionListener(e -> abrirFormularioCadastroFuncionario());

        btnCadastrarConta.addActionListener(e -> JOptionPane.showMessageDialog(this, "Cadastrar conta!"));
        btnConsultar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Consultar funcionário!"));
        btnAtualizar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Atualizar cargo!"));
        btnExcluir.addActionListener(e -> JOptionPane.showMessageDialog(this, "Excluir funcionário!"));

        panelBotoes.add(btnCadastrarFuncionario);
        panelBotoes.add(btnCadastrarConta);
        panelBotoes.add(btnConsultar);
        panelBotoes.add(btnAtualizar);
        panelBotoes.add(btnExcluir);

        JButton btnVoltar = new JButton("Voltar ao Menu");
        btnVoltar.addActionListener(e -> {
            dispose();
            new LoginView();
        });

        JPanel panelVoltar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelVoltar.add(btnVoltar);

        add(panelBotoes, BorderLayout.CENTER);
        add(panelVoltar, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void abrirFormularioCadastroFuncionario() {
        JFrame cadastroFrame = new JFrame("Cadastro de Funcionário");
        cadastroFrame.setSize(500, 400);
        cadastroFrame.setLocationRelativeTo(this);
        cadastroFrame.setLayout(new GridLayout(9, 2, 10, 10));
    
        JTextField txtNome = new JTextField();
        JTextField txtCpf = new JTextField();
        JTextField txtDataNascimento = new JTextField();
        JTextField txtTelefone = new JTextField();
        JTextField txtSenha = new JTextField();
        JTextField txtCargo = new JTextField();
    
        cadastroFrame.add(new JLabel("Nome:"));
        cadastroFrame.add(txtNome);
        cadastroFrame.add(new JLabel("CPF:"));
        cadastroFrame.add(txtCpf);
        cadastroFrame.add(new JLabel("Data de Nascimento:"));
        cadastroFrame.add(txtDataNascimento);
        cadastroFrame.add(new JLabel("Telefone:"));
        cadastroFrame.add(txtTelefone);
        cadastroFrame.add(new JLabel("Senha:"));
        cadastroFrame.add(txtSenha);
        cadastroFrame.add(new JLabel("Cargo:"));
        cadastroFrame.add(txtCargo);
    
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnCancelar = new JButton("Cancelar");
    
        btnCadastrar.addActionListener(e -> {
            String nome = txtNome.getText();
            String cpf = txtCpf.getText();
            String dataNascimento = txtDataNascimento.getText();
            String telefone = txtTelefone.getText();
            String senha = txtSenha.getText();
            String cargo = txtCargo.getText();
    
            String mensagem = funcionarioController.cadastrarFuncionario(nome, cpf, dataNascimento, telefone, senha, cargo);
            JOptionPane.showMessageDialog(cadastroFrame, mensagem);
    
            // Exibe o código gerado após o cadastro
            if (mensagem.contains("Código gerado:")) {
                String codigoFuncionario = mensagem.split(":")[1].trim(); // Pega o código gerado
                JOptionPane.showMessageDialog(cadastroFrame, "Código do Funcionário: " + codigoFuncionario);
            }
        });
    
        btnCancelar.addActionListener(e -> {
            int resposta = JOptionPane.showConfirmDialog(cadastroFrame, "Deseja realmente cancelar o cadastro?", "Cancelar", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                cadastroFrame.dispose();
            }
        });
    
        panelBotoes.add(btnCadastrar);
        panelBotoes.add(btnCancelar);
        cadastroFrame.add(new JLabel());
        cadastroFrame.add(panelBotoes);
    
        cadastroFrame.setVisible(true);
    }
    

    private JFormattedTextField criarCampoCpf() {
        try {
            return new JFormattedTextField(new javax.swing.text.MaskFormatter("###.###.###-##"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new JFormattedTextField();
    }

    private JFormattedTextField criarCampoDataNascimento() {
        try {
            return new JFormattedTextField(new javax.swing.text.MaskFormatter("##/##/####"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new JFormattedTextField();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuFuncionarioView::new);
    }
}

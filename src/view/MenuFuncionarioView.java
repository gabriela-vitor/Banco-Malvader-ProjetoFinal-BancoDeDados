package view;

import controller.FuncionarioController;
import controller.ContaController;
import javax.swing.*;
import java.awt.*;
import java.text.ParseException;

public class MenuFuncionarioView extends JFrame {

    private FuncionarioController funcionarioController;
    private ContaController contaController; // Instância do controller de conta

    public MenuFuncionarioView() {
        funcionarioController = new FuncionarioController();
        contaController = new ContaController(); // Inicialização do controlador de conta
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
        btnCadastrarConta.addActionListener(e -> abrirFormularioCadastroConta()); // Novo método
        btnConsultar.addActionListener(e -> mostrarMensagem("Consultar funcionário!"));
        btnAtualizar.addActionListener(e -> abrirFormularioAtualizarCargo());
        btnExcluir.addActionListener(e -> mostrarMensagem("Excluir funcionário!"));

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
            mostrarMensagem(mensagem);

            // Exibe o código gerado após o cadastro
            if (mensagem.contains("Código gerado:")) {
                String codigoFuncionario = mensagem.split(":")[1].trim(); // Pega o código gerado
                mostrarMensagem("Código do Funcionário: " + codigoFuncionario);
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

    // Novo método para abrir o formulário de cadastro de conta
    private void abrirFormularioCadastroConta() {
        JFrame cadastroFrame = new JFrame("Cadastro de Conta");
        cadastroFrame.setSize(500, 400);
        cadastroFrame.setLocationRelativeTo(this);
        cadastroFrame.setLayout(new GridLayout(6, 2, 10, 10));

        JTextField txtNumeroConta = new JTextField();
        JTextField txtAgencia = new JTextField();
        JComboBox<String> comboTipoConta = new JComboBox<>(new String[] { "POUPANCA", "CORRENTE" });
        JTextField txtSaldo = new JTextField();
        JTextField txtLimite = new JTextField(); // Para conta corrente
        JTextField txtTaxaRendimento = new JTextField(); // Para conta poupança
        JTextField txtIdCliente = new JTextField();

        cadastroFrame.add(new JLabel("Número da Conta:"));
        cadastroFrame.add(txtNumeroConta);
        cadastroFrame.add(new JLabel("Agência:"));
        cadastroFrame.add(txtAgencia);
        cadastroFrame.add(new JLabel("Tipo de Conta:"));
        cadastroFrame.add(comboTipoConta);
        cadastroFrame.add(new JLabel("Saldo:"));
        cadastroFrame.add(txtSaldo);
        cadastroFrame.add(new JLabel("Limite:"));
        cadastroFrame.add(txtLimite);  // Visível apenas para conta corrente
        cadastroFrame.add(new JLabel("Taxa de Rendimento:"));
        cadastroFrame.add(txtTaxaRendimento);  // Visível apenas para conta poupança
        cadastroFrame.add(new JLabel("ID do Cliente:"));
        cadastroFrame.add(txtIdCliente);

        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnCancelar = new JButton("Cancelar");

        btnCadastrar.addActionListener(e -> {
            String numeroConta = txtNumeroConta.getText();
            String agencia = txtAgencia.getText();
            String tipoConta = (String) comboTipoConta.getSelectedItem();
            Double saldo = Double.valueOf(txtSaldo.getText());
            Double limite = txtLimite.getText().isEmpty() ? null : Double.valueOf(txtLimite.getText());
            Double taxaRendimento = txtTaxaRendimento.getText().isEmpty() ? null : Double.valueOf(txtTaxaRendimento.getText());
            int idCliente = Integer.parseInt(txtIdCliente.getText());

            // Lógica para cadastrar a conta
            String mensagem = contaController.cadastrarConta(numeroConta, agencia, tipoConta, idCliente, saldo, limite, taxaRendimento, "");  // Você pode adicionar a data de vencimento conforme necessário
            mostrarMensagem(mensagem);
        });

        btnCancelar.addActionListener(e -> {
            int resposta = JOptionPane.showConfirmDialog(cadastroFrame, "Deseja realmente cancelar?", "Cancelar", JOptionPane.YES_NO_OPTION);
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

    private void mostrarMensagem(String mensagem) {
        if (GraphicsEnvironment.isHeadless()) {
            // Em um ambiente headless, exibe a mensagem no console
            System.out.println(mensagem);
        } else {
            // Em um ambiente gráfico, exibe a mensagem usando a interface gráfica
            JOptionPane.showMessageDialog(this, mensagem);
        }
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

    private void abrirFormularioAtualizarCargo() {
        JFrame atualizarCargoFrame = new JFrame("Atualizar Cargo");
        atualizarCargoFrame.setSize(400, 200);
        atualizarCargoFrame.setLocationRelativeTo(this);
        atualizarCargoFrame.setLayout(new GridLayout(3, 2, 10, 10));

        JTextField txtCpf = new JTextField();
        JTextField txtCargo = new JTextField();

        atualizarCargoFrame.add(new JLabel("CPF do Funcionário:"));
        atualizarCargoFrame.add(txtCpf);
        atualizarCargoFrame.add(new JLabel("Novo Cargo:"));
        atualizarCargoFrame.add(txtCargo);

        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnCancelar = new JButton("Cancelar");

        btnAtualizar.addActionListener(e -> {
            String cpf = txtCpf.getText();
            String novoCargo = txtCargo.getText();

            // Chama o método correto no controlador
            String mensagem = funcionarioController.atualizarCargoFuncionario(cpf, novoCargo);
            mostrarMensagem(mensagem);
        });

        btnCancelar.addActionListener(e -> atualizarCargoFrame.dispose());

        panelBotoes.add(btnAtualizar);
        panelBotoes.add(btnCancelar);

        atualizarCargoFrame.add(panelBotoes);

        atualizarCargoFrame.setVisible(true);
    }
}

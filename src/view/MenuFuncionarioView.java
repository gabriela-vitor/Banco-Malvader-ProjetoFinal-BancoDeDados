package view;

import controller.FuncionarioController;
import controller.ContaController;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;

public class MenuFuncionarioView extends JFrame {

    private FuncionarioController funcionarioController;
    private ContaController contaController;

    public MenuFuncionarioView() {
        funcionarioController = new FuncionarioController();
        contaController = new ContaController();
        initComponents();
    }

    private void initComponents() {
        setTitle("Funcionário - Opções");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelBotoes = new JPanel(new GridLayout(5, 1, 10, 10));
        panelBotoes.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnCadastrarFuncionario = new JButton("Cadastrar Funcionário");
        JButton btnCadastrarConta = new JButton("Cadastrar Conta");
        JButton btnConsultar = new JButton("Consultar");
        JButton btnAtualizar = new JButton("Atualizar Cargo");
        JButton btnExcluir = new JButton("Excluir");

        btnCadastrarFuncionario.addActionListener(e -> abrirFormularioCadastroFuncionario());
        btnCadastrarConta.addActionListener(e -> abrirFormularioCadastroConta());
        btnConsultar.addActionListener(e -> abrirFormularioConsulta());
        btnAtualizar.addActionListener(e -> abrirFormularioAtualizarCargo());
        // btnExcluir.addActionListener(e -> excluirConta());

        JPanel panelCadastro = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelCadastro.add(btnCadastrarFuncionario);
        panelCadastro.add(btnCadastrarConta);

        panelBotoes.add(panelCadastro);
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

            if (mensagem.contains("Código gerado:")) {
                String codigoFuncionario = mensagem.split(":")[1].trim();
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

    private void abrirFormularioCadastroConta() {
        JFrame cadastroFrame = new JFrame("Cadastro de Conta");
        cadastroFrame.setSize(500, 400);
        cadastroFrame.setLocationRelativeTo(this);
        cadastroFrame.setLayout(new GridLayout(6, 2, 10, 10));

        JTextField txtNumeroConta = new JTextField();
        JTextField txtAgencia = new JTextField();
        JComboBox<String> comboTipoConta = new JComboBox<>(new String[]{"POUPANCA", "CORRENTE"});
        JTextField txtSaldo = new JTextField();
        JTextField txtLimite = new JTextField();
        JTextField txtTaxaRendimento = new JTextField();
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
        cadastroFrame.add(txtLimite);
        cadastroFrame.add(new JLabel("Taxa de Rendimento:"));
        cadastroFrame.add(txtTaxaRendimento);
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

            String mensagem = contaController.cadastrarConta(numeroConta, agencia, tipoConta, idCliente, saldo, limite, taxaRendimento, "");
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

    private void abrirFormularioConsulta() {
        JFrame consultaFrame = new JFrame("Consultar");
        consultaFrame.setSize(400, 300);
        consultaFrame.setLocationRelativeTo(this);
        consultaFrame.setLayout(new BorderLayout());

        JTextField txtCodigo = new JTextField();
        JComboBox<String> comboTipoConsulta = new JComboBox<>(new String[]{"Funcionário", "Cliente"});
        JButton btnConsultar = new JButton("Consultar");

        JPanel panelInput = new JPanel(new GridLayout(3, 2, 10, 10));
        panelInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelInput.add(new JLabel("Selecione o Tipo:"));
        panelInput.add(comboTipoConsulta);
        panelInput.add(new JLabel("Código:"));
        panelInput.add(txtCodigo);

        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotoes.add(btnConsultar);

        btnConsultar.addActionListener(e -> {
            String tipoConsulta = (String) comboTipoConsulta.getSelectedItem();
            String codigo = txtCodigo.getText();
            String mensagem;

            if ("Funcionário".equals(tipoConsulta)) {
                mensagem = funcionarioController.consultarFuncionario(codigo);
            } else {
                mensagem = contaController.consultarCliente(codigo); 
            }

            mostrarMensagem(mensagem);
        });

        consultaFrame.add(panelInput, BorderLayout.CENTER);
        consultaFrame.add(panelBotoes, BorderLayout.SOUTH);

        consultaFrame.setVisible(true);
    }

    private void abrirFormularioAtualizarCargo() {
        JFrame atualizarCargoFrame = new JFrame("Atualizar Cargo");
        atualizarCargoFrame.setSize(400, 200);
        atualizarCargoFrame.setLocationRelativeTo(this);
        atualizarCargoFrame.setLayout(new GridLayout(3, 2, 10, 10));
    
        JTextField txtCodigo = new JTextField();
        JTextField txtCargo = new JTextField();
    
        atualizarCargoFrame.add(new JLabel("Código do Funcionário:"));
        atualizarCargoFrame.add(txtCodigo);
        atualizarCargoFrame.add(new JLabel("Novo Cargo:"));
        atualizarCargoFrame.add(txtCargo);
    
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnCancelar = new JButton("Cancelar");
    
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotoes.add(btnAtualizar);
        panelBotoes.add(btnCancelar);
    
        btnAtualizar.addActionListener(e -> {
            String codigoFuncionario = txtCodigo.getText();
            String novoCargo = txtCargo.getText();
    
            // Chamando o método correto
            String mensagem = funcionarioController.atualizarCargoFuncionario(codigoFuncionario, novoCargo);
            mostrarMensagem(mensagem);
        });
    
        btnCancelar.addActionListener(e -> {
            int resposta = JOptionPane.showConfirmDialog(atualizarCargoFrame, "Deseja realmente cancelar?", "Cancelar", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                atualizarCargoFrame.dispose();
            }
        });
    
        atualizarCargoFrame.add(panelBotoes);
        atualizarCargoFrame.setVisible(true);
    }
    

    // private void excluirConta() {
    //     // Obtendo os dados dos campos de texto
    //     Integer idCliente = null;
    //     String numeroConta = NumeroConta.getText();  // Obtém o número da conta
    
    //     // Verifica se o idCliente está preenchido
    //     if (Idcliente.getText() != null && !Idcliente.getText().isEmpty()) {
    //         try {
    //             idCliente = Integer.parseInt(Idcliente.getText());  // Converte o texto para inteiro
    //         } catch (NumberFormatException e) {
    //             mostrarMensagem("ID do cliente inválido.");
    //             return;
    //         }
    //     }
    
    //     // Chama o método da controller passando os parâmetros idCliente e numeroConta
    //     String mensagem = contaController.excluirContaPorClienteOuNumero(idCliente, numeroConta);
        
    //     // Exibe a mensagem retornada pela controller
    //     mostrarMensagem(mensagem);
    // }
    

    private void mostrarMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }
}

package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VerificarSenhaView extends JDialog {
    private int clienteId;
    private String acao;

    public VerificarSenhaView(JFrame parent, int clienteId, String acao) {
        super(parent, "Verificar Senha", true);  // true para tornar a janela modal (bloqueia a janela principal)
        this.clienteId = clienteId;
        this.acao = acao;

        setSize(300, 150);
        setLocationRelativeTo(parent);  // Fica no centro da tela pai
        setLayout(new BorderLayout());

        // Label de instrução
        JLabel labelInstrucoes = new JLabel("Digite a senha para " + acao, JLabel.CENTER);
        labelInstrucoes.setFont(new Font("Arial", Font.BOLD, 14));
        add(labelInstrucoes, BorderLayout.NORTH);

        // Campo para senha
        JPasswordField campoSenha = new JPasswordField();
        campoSenha.setFont(new Font("Arial", Font.PLAIN, 14));
        add(campoSenha, BorderLayout.CENTER);

        // Painel de botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton botaoConfirmar = new JButton("Confirmar");
        botaoConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String senha = new String(campoSenha.getPassword());
                if (autenticarSenha(senha)) {
                    realizarAcao();
                    dispose();  // Fecha a tela de senha
                } else {
                    JOptionPane.showMessageDialog(VerificarSenhaView.this, "Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton botaoCancelar = new JButton("Cancelar");
        botaoCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // Fecha a tela sem realizar nenhuma ação
            }
        });

        buttonPanel.add(botaoConfirmar);
        buttonPanel.add(botaoCancelar);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Simulação de autenticação de senha
    private boolean autenticarSenha(String senha) {
        // Aqui você pode verificar a senha do cliente no banco de dados ou algum outro método
        // Vamos usar uma senha fixa para exemplo
        return "cli123".equals(senha);  // Senha correta
    }

    private void realizarAcao() {
        // executar a ação específica para a qual a senha foi solicitada
        switch (acao) {
            case "Consultar Saldo":
                JOptionPane.showMessageDialog(this, "Exibindo o saldo...");
                break;
            case "Saque":
                JOptionPane.showMessageDialog(this, "Realizando saque...");
                break;
            case "Consultar Limite":
                JOptionPane.showMessageDialog(this, "Exibindo o limite...");
                break;
        }
    }
}

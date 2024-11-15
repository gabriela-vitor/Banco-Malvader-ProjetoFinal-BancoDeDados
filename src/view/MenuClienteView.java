package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuClienteView extends JFrame {
    private int clienteId;

    public MenuClienteView(int clienteId) {
        this.clienteId = clienteId;

        setTitle("Menu do Cliente");
        setSize(400, 350); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JLabel label = new JLabel("Bem-vindo ao Banco Malvader!", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1, 10, 10)); 

        // botão para consultar saldo
        JButton saldoButton = new JButton("Consultar Saldo");
        saldoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VerificarSenhaView(MenuClienteView.this, clienteId, "Consultar Saldo");
            }
        });

        // botão para realizar depósito
        JButton depositoButton = new JButton("Depósito");
        depositoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DepositoView(clienteId);
            }
        });

        // botão para realizar saque
        JButton saqueButton = new JButton("Saque");
        saqueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VerificarSenhaView(MenuClienteView.this, clienteId, "Saque");
            }
        });

        // botão para consultar extrato
        JButton extratoButton = new JButton("Consultar Extrato");
        extratoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ExtratoView(clienteId);
            }
        });

        // botão para consultar limite
        JButton limiteButton = new JButton("Consultar Limite");
        limiteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VerificarSenhaView(MenuClienteView.this, clienteId, "Consultar Limite");
            }
        });

        // botão para encerrar o programa
        JButton encerrarButton = new JButton("Encerrar Programa");
        encerrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // encerra o programa
            }
        });

        // adicionando botões no painel
        buttonPanel.add(saldoButton);
        buttonPanel.add(depositoButton);
        buttonPanel.add(saqueButton);
        buttonPanel.add(extratoButton);
        buttonPanel.add(limiteButton);
        buttonPanel.add(encerrarButton); 

        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        // para testar o MenuClienteView com um cliente de ID 1
        new MenuClienteView(1);
    }
}

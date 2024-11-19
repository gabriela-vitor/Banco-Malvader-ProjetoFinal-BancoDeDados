package view;

import controller.ClienteController;
import javax.swing.*;
import java.awt.*;

public class SaldoView extends JFrame {
    private ClienteController clienteController;

    public SaldoView(int clienteId) {
        clienteController = new ClienteController();
        
        setTitle("Consultar Saldo");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Consultar o saldo do cliente
        double saldo = clienteController.consultarSaldo(clienteId);
        
        // Formatar o saldo para exibir com duas casas decimais
        String saldoFormatado = String.format("R$ %.2f", saldo);

        // Criar o label com o saldo
        JLabel saldoLabel = new JLabel("Seu saldo: " + saldoFormatado);
        saldoLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Ajustando o estilo da fonte
        
        // Adicionar o label à tela
        add(saldoLabel, BorderLayout.CENTER);

        // Configurar a posição da janela
        setLocationRelativeTo(null); // Para exibir a janela no centro da tela

        // Tornar a janela visível
        setVisible(true);
    }
}

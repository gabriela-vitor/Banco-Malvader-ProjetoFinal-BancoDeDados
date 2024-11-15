package view;

import controller.ClienteController;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaldoView extends JFrame {
    private ClienteController clienteController;

    public SaldoView(int clienteId) {
        clienteController = new ClienteController();
        
        setTitle("Consultar Saldo");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        double saldo = clienteController.consultarSaldo(clienteId);
        
        JLabel saldoLabel = new JLabel("Seu saldo: " + saldo);
        add(saldoLabel);
        
        setVisible(true);
    }
}

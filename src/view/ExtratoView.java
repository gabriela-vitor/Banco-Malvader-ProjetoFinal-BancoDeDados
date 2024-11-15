package view;

import controller.ClienteController;
import javax.swing.*;
import java.awt.BorderLayout;

public class ExtratoView extends JFrame {
    private ClienteController clienteController;

    public ExtratoView(int clienteId) {
        clienteController = new ClienteController();
        
        setTitle("Extrato");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        String extrato = clienteController.consultarExtrato(clienteId);
        
        JTextArea extratoArea = new JTextArea(extrato);
        extratoArea.setEditable(false);
        add(new JScrollPane(extratoArea), BorderLayout.CENTER);
        
        setVisible(true);
    }
}

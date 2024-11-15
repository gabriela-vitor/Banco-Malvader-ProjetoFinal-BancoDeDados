package view;

import controller.ClienteController;
import javax.swing.*;

public class LimiteView extends JFrame {
    private ClienteController clienteController;

    public LimiteView(int clienteId) {
        clienteController = new ClienteController();
        
        setTitle("Consultar Limite");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        double limite = clienteController.consultarLimite(clienteId);
        
        JLabel limiteLabel = new JLabel("Seu limite: " + limite);
        add(limiteLabel);
        
        setVisible(true);
    }
}

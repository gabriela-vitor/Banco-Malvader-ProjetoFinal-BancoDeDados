package view;

import controller.ClienteController;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepositoView extends JFrame {
    private ClienteController clienteController;

    public DepositoView(int clienteId) {
        clienteController = new ClienteController();
        
        setTitle("Depósito");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JLabel valorLabel = new JLabel("Valor para depositar:");
        JTextField valorField = new JTextField(10);
        JButton depositarButton = new JButton("Depositar");
        
        depositarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double valor = Double.parseDouble(valorField.getText());
                clienteController.depositar(clienteId, valor);
                JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso!");
            }
        });
        
        add(valorLabel);
        add(valorField);
        add(depositarButton);
        
        setVisible(true);
    }
}

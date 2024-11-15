package view;

import controller.ClienteController;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaqueView extends JFrame {
    private ClienteController clienteController;

    public SaqueView(int clienteId) {
        clienteController = new ClienteController();
        
        setTitle("Saque");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JLabel valorLabel = new JLabel("Valor para sacar:");
        JTextField valorField = new JTextField(10);
        JButton sacarButton = new JButton("Sacar");
        
        sacarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double valor = Double.parseDouble(valorField.getText());
                if (clienteController.sacar(clienteId, valor)) {
                    JOptionPane.showMessageDialog(null, "Saque realizado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Saldo insuficiente.");
                }
            }
        });
        
        add(valorLabel);
        add(valorField);
        add(sacarButton);
        
        setVisible(true);
    }
}

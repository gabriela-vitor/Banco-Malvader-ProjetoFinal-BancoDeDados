package view;

import controller.ClienteController; // Importa a classe responsável por controlar as operações dos clientes
import javax.swing.*; // Importa componentes para criação de interface gráfica
import java.awt.BorderLayout; // Importa o layout para organização da interface

// Classe que cria a interface para exibir o extrato do cliente
public class ExtratoView extends JFrame {
    private ClienteController clienteController; // Controlador para gerenciar as operações dos clientes

    // Construtor da janela de extrato
    public ExtratoView(int clienteId) {
        clienteController = new ClienteController(); // Inicializa o controlador
        
        setTitle("Extrato"); // Define o título da janela
        setSize(400, 300); // Define o tamanho da janela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha apenas esta janela ao clicar em "X"
        
        // Obtém o extrato do cliente chamando o método do controlador
        String extrato = clienteController.consultarExtrato(clienteId);
        
        // Área de texto para exibir o extrato
        JTextArea extratoArea = new JTextArea(extrato);
        extratoArea.setEditable(false); // Impede que o texto seja editado pelo usuário
        
        // Adiciona a área de texto dentro de um painel com barra de rolagem
        add(new JScrollPane(extratoArea), BorderLayout.CENTER); 
        
        // Torna a janela visível
        setVisible(true);
    }
}


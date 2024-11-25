package view;

import controller.ClienteController; // Importa a classe responsável por controlar as ações dos clientes
import javax.swing.*; // Importa classes para criar a interface gráfica
import java.awt.*; // Importa classes para layouts e gráficos
import java.awt.event.ActionEvent; // Importa a classe para eventos de ação
import java.awt.event.ActionListener; // Importa a interface para ouvir eventos de ação

// Classe que cria a janela para realizar depósitos
public class DepositoView extends JFrame {
    private ClienteController clienteController; // Controlador responsável por gerenciar as operações do cliente

    // Construtor da janela de depósito
    public DepositoView(int clienteId) {
        clienteController = new ClienteController(); // Inicializa o controlador
        
        setTitle("Depósito"); // Define o título da janela
        setSize(300, 200); // Define o tamanho da janela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha apenas esta janela ao clicar em "X"
        
        // Configuração do layout da janela
        setLayout(new GridLayout(3, 2, 10, 10)); // Usando GridLayout para organizar os componentes em 3 linhas e 2 colunas

        // Criação dos componentes da interface
        JLabel valorLabel = new JLabel("Valor para depositar:"); // Rótulo para informar o que fazer
        JTextField valorField = new JTextField(10); // Campo de texto para o usuário inserir o valor
        JButton depositarButton = new JButton("Depositar"); // Botão para realizar o depósito
        JButton voltarButton = new JButton("Voltar"); // Botão para voltar à outra página
        
        // Define a ação a ser executada ao clicar no botão de depósito
        depositarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double valor = Double.parseDouble(valorField.getText()); // Tenta converter o texto em número
                    if (valor <= 0) { // Verifica se o valor é inválido
                        JOptionPane.showMessageDialog(null, "Por favor, insira um valor válido!");
                    } else {
                        clienteController.depositar(clienteId, valor); // Chama o método de depósito no controlador
                        JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso!"); // Mensagem de sucesso
                    }
                } catch (NumberFormatException ex) { // Captura erro de conversão de texto para número
                    JOptionPane.showMessageDialog(null, "Por favor, insira um valor numérico válido.");
                }
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela atual
                new MenuClienteView(); // Abre a janela do Menu Cliente
            }
        });
        
        // Adiciona os componentes à janela
        add(valorLabel); // Adiciona o rótulo
        add(valorField); // Adiciona o campo de texto
        add(depositarButton); // Adiciona o botão de depósito
        add(voltarButton); // Adiciona o botão de voltar
        
        // Centraliza a janela na tela
        setLocationRelativeTo(null);
        
        // Torna a janela visível
        setVisible(true);
    }
}

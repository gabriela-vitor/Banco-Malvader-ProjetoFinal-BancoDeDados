package view;

import java.util.Scanner;
import view.LoginView;

public class MenuFuncionarioView {

    public void exibirMenu() {
        Scanner entrada = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("===== MENU FUNCIONÁRIO =====");
            System.out.println("1. Entrar como Administrador");
            System.out.println("2. Entrar como Funcionário Padrão");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = entrada.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Entrando como Administrador...");
                    // Chamar método ou classe específica para Administrador
                    break;
                case 2:
                    System.out.println("Entrando como Funcionário Padrão...");
                    // Chamar método ou classe específica para Funcionário Padrão
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);

        entrada.close();
    }

    public static void main(String[] args) {
        MenuFuncionarioView menu = new MenuFuncionarioView();
        menu.exibirMenu();
    }
}

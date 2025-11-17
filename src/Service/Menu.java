package Service;

import java.util.Scanner;

public class Menu {
    private static Scanner input = new Scanner(System.in);

    public static void menuPrincipal(){
        int escolha;
        do{
            System.out.println();
            System.out.println("1 - Login");
            System.out.println("2 - Cadastrar");
            System.out.println("3 - Sair");
            escolha = input.nextInt();
            input.nextLine();
            switch (escolha){
                case 1 -> MenuFuncoes.login();
                case 2 -> menuCadastrar();
            }
        }while (escolha!=3);

    }

    public static void menuCadastrar(){
        System.out.println("1 - Cadastrar funcionário");
        System.out.println("2 - Cadastrar cliente");
        System.out.println("3 - Voltar");
        int escolha = input.nextInt();
        input.nextLine();
        switch (escolha){
            case 1 -> MenuFuncoes.cadastrarFuncionario();
            case 2 -> MenuFuncoes.cadastrarCliente();
            case 3 -> menuPrincipal();
        }
    }
}

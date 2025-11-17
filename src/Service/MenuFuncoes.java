package Service;

import Entidades.*;
import Gerenciador.*;
import Tipos.*;

import java.util.Scanner;

public class MenuFuncoes {
    private static Scanner input = new Scanner(System.in);

    private static GerenciadorUsuarios gerenciadorUsuarios = GerenciadorUsuarios.getGerenciadorUsuarios();
    private static GerenciadorID gerenciadorID = GerenciadorID.getGerenciadorID(TipoID.USUARIO);

    public static void login() {
        String login, senha;
        System.out.print("Informe seu login: ");
        login = input.nextLine();
        System.out.print("Informe sua senha: ");
        senha = input.nextLine();
        if (gerenciadorUsuarios.autenticar(login, senha)) {
            verificaEntidade(login);
        } else {
            System.out.println("Login ou senha incorretos!");
        }
    }

    public static void cadastrarFuncionario() {
        String nome, senha, login = gerenciadorID.cadastrarID();
        int cargo;
        System.out.print("Insira o nome do funcionário: ");
        nome = input.nextLine();
        System.out.printf("Para testes o seu login é: %s .\n", login);
        System.out.print("Insira a senha do funcionário: ");
        senha = input.nextLine();
        System.out.print("Insira o cargo do funcionário (1 - Coordenador | 2 - Atendente): ");
        cargo = input.nextInt();
        input.nextLine();
        Funcionario novoFuncionario = new Funcionario(nome, senha, cargo == 1 ? Cargo.COORDENADOR : Cargo.ATENDENTE, login);
    }

    public static void cadastrarCliente() {
        String nome, senha, login = gerenciadorID.cadastrarID();
        int cargo;
        System.out.print("Insira o nome do cliente: ");
        nome = input.nextLine();
        System.out.printf("Para testes o seu login é: %s .\n", login);
        System.out.print("Insira a senha do cliente: ");
        senha = input.nextLine();
        Cliente novoCliente = new Cliente(nome, login, senha);
    }

    private static void verificaEntidade(String login) {
        Usuario novoUsuario = gerenciadorUsuarios.getUsuarioPorID(login);
        if (novoUsuario instanceof Funcionario) {
            switch (((Funcionario) novoUsuario).getCargo()) {
                case Cargo.ATENDENTE -> System.out.printf("Bem vindo atendente %s", novoUsuario.getNomeUsuario());
                case Cargo.COORDENADOR -> System.out.printf("Bem vindo coordenador %s", novoUsuario.getNomeUsuario());
            }
        } else {
            System.out.printf("Bem vindo cliente %s", novoUsuario.getNomeUsuario());
        }
    }

}

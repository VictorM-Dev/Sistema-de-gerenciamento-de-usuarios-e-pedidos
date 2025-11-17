package Cargos;

import Entidades.*;
import Gerenciador.GerenciadorUniversalDePedidos;
import Gerenciador.GerenciadorUsuarios;
import Pedidos.Pedido;

import java.util.ArrayList;

public class Coordenador{
    private static Coordenador instanciaUnica;

    private final GerenciadorUniversalDePedidos gerenciadorUniversalDePedidos = GerenciadorUniversalDePedidos.getGerenciadorUniversalDePedidos();
    private final GerenciadorUsuarios gerenciadorUsuarios = GerenciadorUsuarios.getGerenciadorUsuarios();

    private Coordenador(){}
    public static Coordenador getCoordenador(){
        if(instanciaUnica != null){
            instanciaUnica = new Coordenador();
        }
        return instanciaUnica;
    }
    public void verTodosOsPedidos(){
        ArrayList<Pedido> todosOsPedidos = gerenciadorUniversalDePedidos.getTodosOsPedidos();
        for(Pedido pedido: todosOsPedidos){
            System.out.println(pedido);
        }
    }
    public void verBalancoGeral(){
        Double balancoGeral = gerenciadorUniversalDePedidos.getBalancoGeral();
    }
    public void verTodosOsClientes(){
        ArrayList<Usuario> todosOsUsuarios = gerenciadorUsuarios.getUsuariosCadastrados();
        for(Usuario usuario: todosOsUsuarios){
            if(usuario instanceof Cliente){
                System.out.println(usuario);
            }
        }
    }
    public void verTodosOsFuncionarios(){
        ArrayList<Usuario> todosOsUsuarios = gerenciadorUsuarios.getUsuariosCadastrados();
        for(Usuario usuario: todosOsUsuarios){
            if(usuario instanceof Funcionario){
                System.out.println(usuario);
            }
        }
    }
    public void removerFuncionario(Funcionario funcionario){
        Usuario novoUsuario = funcionario;
        gerenciadorUsuarios.removerFuncionario(funcionario.getIdUsuario());
    }
}

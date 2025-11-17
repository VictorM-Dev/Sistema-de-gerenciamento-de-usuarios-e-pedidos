package Cargos;

import Entidades.*;
import Gerenciador.GerenciadorUniversalDeItens;
import Gerenciador.GerenciadorUsuarios;
import Pedidos.Item;
import Pedidos.Pedido;

import java.util.ArrayList;

public class Atendente{
    private static Atendente instanciaUnica;
    private final GerenciadorUsuarios gerenciadorUsuarios = GerenciadorUsuarios.getGerenciadorUsuarios();
    private final GerenciadorUniversalDeItens gerenciadorUniversalDeItens = GerenciadorUniversalDeItens.getGerenciadorUniversalDeItens();

    private Atendente() {};

    public static Atendente getAtendente() {
        if (instanciaUnica == null) {
            instanciaUnica = new Atendente();
        }
        return instanciaUnica;
    }

    public void verPedidosDeUmCliente(Cliente cliente) {
        if (verificaSeUmClienteExiste(cliente)) {
            ArrayList<Pedido> meusPedidos = cliente.getMeusPedidos();
            if(meusPedidos.isEmpty()){
                System.out.println("Não existe pedidos cadastrados!");
                return;
            }
            for (Pedido pedido : meusPedidos) {
                System.out.println(pedido);
            }
        }
    }

    private boolean verificaSeUmClienteExiste(Cliente cliente) {
        Usuario novoUsuario = gerenciadorUsuarios.verificaSeOUsuarioExiste(cliente.getNomeUsuario());
        return novoUsuario instanceof Cliente;
    }

    public boolean removerPedidoDeCliente(Cliente cliente, Pedido pedido){
        if(verificaSePedidoExiste(cliente, pedido.getCodigoPedido())){
            cliente.removerPedido(pedido);
            return true;
        }
        return false;
    }

    private boolean verificaSePedidoExiste(Cliente cliente, String pedidoID){
        ArrayList<Pedido> meusPedidos = cliente.getMeusPedidos();
        for(Pedido pedido: meusPedidos){
            if(pedido.getCodigoPedido().equals(pedidoID)) return true;
        }
        return false;
    }

    public void criarItem(String nomeDoProduto, double precoDoProduto, String codigoDoProduto){
        Item novoItem = new Item(nomeDoProduto, precoDoProduto, codigoDoProduto);
    }

    public Item getItemPorCodigo(String ItemID){
        return gerenciadorUniversalDeItens.getItemPorCodigo(ItemID);
    }

    public void removerItem(String itemID){
        gerenciadorUniversalDeItens.removerItemDoSistema(itemID);
    }


}

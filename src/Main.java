import Gerenciador.GerenciadorID;
import Gerenciador.GerenciadorUniversalDeItens;
import Gerenciador.GerenciadorUniversalDePedidos;
import Pedidos.*;
import Tipos.StatusPedido;
import Tipos.TipoID;

public class Main {
    public static void main(String[] args) {
        //Ambiente de teste da main!
        GerenciadorID novosITens = GerenciadorID.getGerenciadorID(TipoID.ITEM);

        Item escova = new Item("Escova", 15, novosITens.cadastrarID());
        Item carrinho = new Item("Carro", 10, novosITens.cadastrarID());

        GerenciadorUniversalDeItens gerenciadorUniversalDeItens = GerenciadorUniversalDeItens.getGerenciadorUniversalDeItens();
        System.out.println(gerenciadorUniversalDeItens.getItemPorCodigo("ITEM-0001"));
        System.out.println(gerenciadorUniversalDeItens.getItemPorCodigo("ITEM-0002"));
    }
}

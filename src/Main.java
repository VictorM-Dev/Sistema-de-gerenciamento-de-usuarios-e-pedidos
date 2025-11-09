import Gerenciador.GerenciadorID;
import Pedidos.*;
import Tipos.TipoID;

public class Main {
    public static void main(String[] args) {
        //Ambiente de teste da main!
        GerenciadorID novosPedidos = GerenciadorID.getGerenciadorID(TipoID.PEDIDO);
        GerenciadorID novosITens = GerenciadorID.getGerenciadorID(TipoID.ITEM);

        Pedido novoPedido = new Pedido(novosPedidos.cadastrarID());

        Item escova = new Item("Escova",15,novosITens.cadastrarID());
        Item carrinho = new Item("Carro", 10, novosITens.cadastrarID());

        novoPedido.adicionarItem(escova, 2);
        novoPedido.adicionarItem(carrinho, 1);

        System.out.println(novoPedido);
        novoPedido.exibirItensDoPedido();
    }
}

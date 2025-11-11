package Gerenciador;

import Pedidos.Item;

import java.util.ArrayList;

public class GerenciadorUniversalDeItens {
    private static GerenciadorUniversalDeItens instanciaUnica;
    private ArrayList<Item> todosOsItens = new ArrayList<>();

    private GerenciadorUniversalDeItens() {};

    public static GerenciadorUniversalDeItens getGerenciadorUniversalDeItens() {
        if (instanciaUnica == null) {
            instanciaUnica = new GerenciadorUniversalDeItens();
        }
        return instanciaUnica;
    }

    public boolean adicionarItemAoSistema(Item novoItem) {
        for(Item item: todosOsItens){
            if(item.getCodigoDoProduto().equals(novoItem.getCodigoDoProduto())){
                return false;
            }
        }
        todosOsItens.add(novoItem);
        return true;
    }

    public void removerItemDoSistema(String codigoDoProduto) {
        Item itemParaRemover = verificaSeItemExiste(codigoDoProduto);
        if (itemParaRemover != null) {
            todosOsItens.remove(itemParaRemover);
        }
    }

    public Item getItemPorCodigo(String codigoDoProduto) {
        return verificaSeItemExiste(codigoDoProduto);
    }

    private Item verificaSeItemExiste(String codigoDoProduto) {
        for (Item item : todosOsItens) {
            if (item.getCodigoDoProduto().equals(codigoDoProduto)) {
                return item;
            }
        }
        return null;
    }
}

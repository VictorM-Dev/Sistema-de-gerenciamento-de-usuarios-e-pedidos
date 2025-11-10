package Pedidos;

import Gerenciador.GerenciadorUniversalDePedidos;
import Tipos.StatusPedido;

import java.util.ArrayList;
import java.util.HashMap;

public class Pedido {
    private ArrayList<Item> meusItens = new ArrayList<>();

    // Utilização de HashMap para manter a quantidade do item no pedido
    private HashMap<String, Integer> produtosNoPedido = new HashMap<>();

    private StatusPedido statusPedido;
    private double valorTotal;
    private final String codigoPedido;

    public Pedido(String codigoPedido) {
        this.codigoPedido = codigoPedido;
        setStatusPedido(StatusPedido.CARRINHO);

        // Assim que um pedido é criado, ele cria a instância única do Gerenciador
        GerenciadorUniversalDePedidos gerenciadorUniversalDePedidos = GerenciadorUniversalDePedidos.getGerenciadorUniversalDePedidos();
        gerenciadorUniversalDePedidos.adicionarPedidoAoSistema(this);
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public String getCodigoPedido() {
        return codigoPedido;
    }

    public void adicionarItem(Item item, int quantidade) {
        if (quantidade <= 0) return;
        if (!meusItens.contains(item)) {
            meusItens.add(item);
        }
        // O getOrDefault transforma o null em 0 se o item não existir por inconsistência
        produtosNoPedido.put(item.getCodigoDoProduto(), produtosNoPedido.getOrDefault(item.getCodigoDoProduto(), 0) + quantidade);
        atualizaPedido();
    }


    public boolean removerItem(String codigoDoProduto) {
        Item itemParaRemover = verificaSeItemExiste(codigoDoProduto);
        if (itemParaRemover != null) {
            meusItens.remove(itemParaRemover);
            produtosNoPedido.remove(itemParaRemover.getCodigoDoProduto());
            return true;
        }
        return false;
    }

    private Item verificaSeItemExiste(String codigoDoProduto) {
        for (Item item : meusItens) {
            if (item.getCodigoDoProduto().equals(codigoDoProduto)) {
                return item;
            }
        }
        return null;
    }

    private void calculaValor() {
        valorTotal = 0;
        for (Item item : meusItens) {
            valorTotal += item.getPrecoDoProduto() * produtosNoPedido.getOrDefault(item.getCodigoDoProduto(), 0);
        }
    }

    public void atualizaPedido() {
        calculaQuantidadeDeItens();
        //Remove o item se a quantidade for 0
        meusItens.removeIf(item -> produtosNoPedido.getOrDefault(item.getCodigoDoProduto(), 0) == 0);
        calculaValor();
    }

    private int calculaQuantidadeDeItens() {
        int quantidadeDeItens = 0;
        for (Item item : meusItens) {
            quantidadeDeItens += this.produtosNoPedido.get(item.getCodigoDoProduto());
        }
        return quantidadeDeItens;
    }

    public void exibirItensDoPedido() {
        for (Item item : meusItens) {
            System.out.println(item);
            System.out.printf("Quantidade: %d\n", produtosNoPedido.getOrDefault(item.getCodigoDoProduto(), 0));
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Código do pedido: %s\nItens diferentes no pedido: %d\nQuantidade de itens no pedido: %d\nValor total do pedido: R$ %.2f",
                codigoPedido, meusItens.size(), calculaQuantidadeDeItens(), valorTotal
        );
    }
}

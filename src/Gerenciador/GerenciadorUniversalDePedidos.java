package Gerenciador;

import Pedidos.Pedido;
import Tipos.StatusPedido;

import java.util.ArrayList;

public class GerenciadorUniversalDePedidos {
    public static GerenciadorUniversalDePedidos instanciaUnica;
    public ArrayList<Pedido> todosOsPedidos = new ArrayList<>();
    public double balancoGeral;

    private GerenciadorUniversalDePedidos() {};

    public static GerenciadorUniversalDePedidos getGerenciadorUniversalDePedidos() {
        if (instanciaUnica == null) {
            instanciaUnica = new GerenciadorUniversalDePedidos();
        }
        return instanciaUnica;
    }

    public void adicionarPedidoAoSistema(Pedido pedido) {
        if (!todosOsPedidos.contains(pedido)) {
            todosOsPedidos.add(pedido);
        }
    }

    public void calcularBalancoGeral() {
        balancoGeral = 0;
        for (Pedido pedido : todosOsPedidos) {
            balancoGeral += pedido.getValorTotal();
        }
    }

    public double getBalancoGeral() {
        return balancoGeral;
    }

    // Se for null, exiba que não é possível encontrar o pedido
    public Pedido getPedidoPorCodigo(String codigoPedido) {
        return verificaSeOPedidoExiste(codigoPedido);
    }

    private Pedido verificaSeOPedidoExiste(String codigoPedido) {
        for (Pedido pedido : todosOsPedidos) {
            if (pedido.getCodigoPedido().equals(codigoPedido)) {
                return pedido;
            }
        }
        return null;
    }

    public void exibirPedidosPorTipo(StatusPedido statusPedido) {
        for (Pedido pedido : todosOsPedidos) {
            if (pedido.getStatusPedido() == statusPedido) {
                System.out.println(pedido);
            }
        }
    }
}

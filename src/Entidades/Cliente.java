package Entidades;

import Pedidos.Pedido;
import Tipos.StatusCliente;
import Tipos.StatusPedido;

import java.util.ArrayList;

public class Cliente extends Usuario {
    private ArrayList<Pedido> meusPedidos = new ArrayList<>();
    private Pedido meuPedido;
    private String endereco;
    private StatusCliente statusCliente;

    public Cliente(String nomeDoCliente) {
        super(nomeDoCliente);
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEndereco() {
        return endereco;
    }

    public Pedido getMeuPedido() {
        return meuPedido;
    }

    public void alteraStatusCliente(StatusCliente statusCliente) {
        this.statusCliente = statusCliente;
    }

    public StatusCliente getStatusCliente() {
        return statusCliente;
    }

    public void novoPedido(Pedido novoPedido) {
        Pedido existente = verificarSePedidoExiste(novoPedido.getCodigoPedido());
        if (existente == null) {
            meusPedidos.add(novoPedido);
        }
    }

    public boolean removerPedido(Pedido pedido) {
        Pedido existente = verificarSePedidoExiste(pedido.getCodigoPedido());
        if (existente != null) {
            meusPedidos.remove(pedido);
            return true;
        }
        return false;
    }

    public StatusPedido verificarStatusPedido(String codigoPedido) {
        Pedido pedido = verificarSePedidoExiste(codigoPedido);
        if (pedido != null) {
            return pedido.getStatusPedido();
        }
        return null;
    }

    private Pedido verificarSePedidoExiste(String codigoPedido) {
        for (Pedido pedido : meusPedidos) {
            if (pedido.getCodigoPedido().equals(codigoPedido)) {
                return pedido;
            }
        }
        return null;
    }
}

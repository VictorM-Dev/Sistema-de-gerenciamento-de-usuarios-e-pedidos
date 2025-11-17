import Cargos.Atendente;
import Entidades.Cliente;
import Entidades.Funcionario;
import Gerenciador.GerenciadorID;
import Pedidos.*;
import Tipos.Cargo;
import Tipos.TipoID;

public class Main {
    public static void main(String[] args) {
        //Ambiente de teste da main!
        GerenciadorID novosItens = GerenciadorID.getGerenciadorID(TipoID.ITEM);
        GerenciadorID novosPedidos = GerenciadorID.getGerenciadorID(TipoID.PEDIDO);
        GerenciadorID novosUsuarios = GerenciadorID.getGerenciadorID(TipoID.USUARIO);

        Cliente novoCliente = new Cliente("João Victor", novosUsuarios.cadastrarID());
        Pedido novoPedido = new Pedido(novosPedidos.cadastrarID());

        novoCliente.novoPedido(novoPedido);

        Atendente atendente = Atendente.getAtendente();

        Funcionario novoFuncionario = new Funcionario("Rafael", "rafael","rafael123",Cargo.ATENDENTE, novosUsuarios.cadastrarID());
        if(novoFuncionario.getCargo() == Cargo.ATENDENTE){
            atendente.verPedidosDeUmCliente(novoCliente);

            atendente.removerPedidoDeCliente(novoCliente, novoPedido);
            atendente.verPedidosDeUmCliente(novoCliente);

            atendente.criarItem("Escova", 15, novosItens.cadastrarID());
            atendente.criarItem("Carro", 10, novosItens.cadastrarID());
        }

        novoPedido.adicionarItem(atendente.getItemPorCodigo("ITEM-0001"), 5);
        novoPedido.adicionarItem(atendente.getItemPorCodigo("ITEM-0002"), 5);

        novoCliente.novoPedido(novoPedido);
        if(novoFuncionario.getCargo() == Cargo.ATENDENTE){
            atendente.verPedidosDeUmCliente(novoCliente);
        }
    }
}

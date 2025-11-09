## Sistema de gerenciamento de usuários e pedidos - SGUP

Projeto que visa a criação de um sistema real de gestão com unicidade, histórico global e separação clara de entidade e
funções.

### Sistemas de gerenciamento (package Gerenciador)

O package Gerenciador é responsável por gerenciar todos os 3 tipos de ID, o sistema universal de pedidos,
sistema universal de itens e sistema universal de funcionários.

**GerenciadorID**

Classe que garante a unicidade e universalidade dos IDs usados ao decorrer do projeto. Ela incorpora um padrão singleton
adaptado para múltiplas instâncias com distinção de enumeração.
As intâncias são definidas em uma coleção *static final* do tipo EnumMap.

```Java
private static final EnumMap<TipoID, GerenciadorID> INSTANCIA = new EnumMap<>(TipoID.class);
```

Método singleton adaptado para instâncias distintas. Retorna a instância única do GerenciadorID para o TipoID. Cria a
instância se ainda não existir.

```Java
public static GerenciadorID getGerenciadorID(TipoID tipoID) {
    if (!INSTANCIA.containsKey(tipoID)) {
        INSTANCIA.put(tipoID, new GerenciadorID(tipoID));
    }
    return INSTANCIA.get(tipoID);
}
```

Método de criação do universo de IDs disponíveis. Usa a tipificação da enum para prefixar os IDs possíveis.

```Java
private void criarIDsDisponiveis(TipoID tipoID) {
    String ID = tipoID.toString();
    for (int i = 0; i < QUATIDADE_DE_IDS; i++) {
        IDsDisponiveis.add(String.format("%s%04d", ID, i + 1));
    }
    Collections.shuffle(IDsDisponiveis);
}
```

Para criação de uma instância da classe GerenciadorID:

```Java
GerenciadorID UsuariosID = getGerenciadorID(TipoID.USUARIO);
```

O construtor privado da GerenciadorID se encarrega de chamar o método criarIDsDiponiveis.
A classe GerenciadorID tem um relacionamento de uso da enum TipoID.

**TipoID**

```Java
public enum TipoID {
    USUARIO {
        @Override
        public String toString() {
            return "USER-";
        }
    },
    ITEM {
        @Override
        public String toString() {
            return "ITEM-";
        }
    },
    PEDIDO {
        @Override
        public String toString() {
            return "PEDI-";
        }
    }
}
```

O toString é sobrescrito para servir como prefixador dos IDs da classe GerenciadorID.

### Sistema de pedidos (package Pedidos)

O package Pedidos é responsável por gerenciar os pedidos realizados pelos clientes, incluindo os itens adicionados, o
valor total e o status de andamento do pedido.

A classe item é um objeto concreto simples, serve apenas para modelar o objeto item.
A classe Pedido, é um gerenciador de todos os itens adicionados a ele, utiliza a enum StatusPedido para controle do
cliente.

```Java
public enum StatusPedido {
    CARRINHO,
    ENVIADO,
    ENTREGUE,
    CANCELADO
}
```

**Pedido**

A classe Pedido, contém o array de itens, e controla o fluxo de dados da classe, adiciona e remove item, não consegue
editar o item em si.

Cada pedido pertence a um cliente, que mantém uma lista de seus pedidos. O cliente é o responsável por criar e gerenciar
instâncias da classe Pedido.

A edição da quantidade de um item específico está no sistema de servico.
Note o funcionamento do método de adição de itens:

```Java
public void adicionarItem(Item item, int quantidade) {
    if (quantidade <= 0) return;
    if (!meusItens.contains(item)) {
        meusItens.add(item);
    }
    produtosNoPedido.put(item.getCodigoDoProduto(), produtosNoPedido.getOrDefault(item.getCodigoDoProduto(), 0) + quantidade);
    atualizaPedido();
}
```

Se por alguma razão a quantidade for 0, não é adicionado o pedido.  
Se a quantidade for maior que 0, primeiro é perguntado se o pedido já existe no carrinho, se o pedido já existir
não é adicionado, é apenas somado no HashMap produtosNoPedido.  
Por fim, o método chama o método atualiza pedido que calcula a quantidade de itens, e remove o item se sua quantidade
for 0.

```Java
public void atualizaPedido() {
    calculaQuantidadeDeItens();
    meusItens.removeIf(item -> produtosNoPedido.getOrDefault(item.getCodigoDoProduto(), 0) == 0);
    calculaValor();
}
```

Por segurança é utilizado o getOrDefault, para caso o item não exista no pedido em casos de inconsistências.  

É sobrescrito o método toString padrão:

```Java

@Override
public String toString() {
    return String.format(
            "Código do pedido: %s\nItens diferentes no pedido: %d\nQuantidade de itens no pedido: %d\nValor total do pedido: R$ %.2f",
            codigoPedido, meusItens.size(), calculaQuantidadeDeItens(), valorTotal
    );
}
```

Formato de exibição:

Código do pedido: PEDI-0001  
Itens diferentes no pedido: 4  
Quantidade de itens no pedido: 5  
Valor total do pedido: R$ 45,50

Note que o StatusPedido não é exibido no toString padrão do pedido, ele é feito na exibição de Usuario.

Para criar uma instância da classe pedido, que é controla pelas instância da classe cliente, pdoemos fazer a chamada
normal
do construtor com new. O construtor recebe uma string (gerada pelo gerenciador de ids), e por padrão seta o status do
pedido com o valor StatusPedido.CARRINHO.

```Java
public Pedido(String codigoPedido) {
    setCodigoPedido(codigoPedido);
    setStatusPedido(StatusPedido.CARRINHO);
}
 ```
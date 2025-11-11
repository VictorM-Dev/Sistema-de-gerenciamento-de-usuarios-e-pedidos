# Sistema de gerenciamento de usuários e pedidos - SGUP

Projeto que visa a criação de um sistema real de gestão com unicidade, histórico global e separação clara de entidade e
funções.

## Sistemas de gerenciamento (package Gerenciador)

O package Gerenciador é responsável por gerenciar todos os 3 tipos de ID, o sistema universal de pedidos,
sistema universal de itens e sistema universal de funcionários.

### GerenciadorID

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

#### TipoID (package enumerações)

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

### GerenciadorUniversalDePedidos  
  
A classe que faz o gerenciamento de todos pedidos, é uma classe singleton padrão, o objeto dela é servir como
pacote de funções de gerencimanto, para os objetos criados a partir da classe de marcação Cargo.  
  
O método mais usual dela é o getPedidoPorCodigo:

```Java
public Pedido getPedidoPorCodigo(String codigoPedido) {
    Pedido novoPedido = verificaSeOPedidoExiste(codigoPedido);
    if (novoPedido != null) {
        return novoPedido;
    }
    return null;
}
```
Em versões futuras, esse retorno null deverá ser substituído por uma exceção customizada, por exemplo PedidoNaoEncontradoException, para melhorar o tratamento de erros e a clareza da API.  
  
A instância desta classe é criada no momento que qualquer pedido é criado, ela está atrelada ao construtor do pedido.
```Java
public static GerenciadorUniversalDePedidos getGerenciadorUniversalDePedidos() {
    if (instanciaUnica == null) {
        instanciaUnica = new GerenciadorUniversalDePedidos();
    }
    return instanciaUnica;
}
```
O método getGerenciadorUniversalDePedidos utiliza o padrão singleton tradicional.
  
Caso a instância ainda não exista, é criada com new GerenciadorUniversalDePedidos, garantindo que apenas uma instância do gerenciador exista durante toda a execução.  

Note que o GerenciadorUniversalDePedidos é configurado para armazenar todos os pedidos independente do StatusPedido. Para um balanço geral mais realístico, deve ser feito
a adição do pedido apenas quando seu StatusPedido for de ENTREGUE. Dessa forma, armazenamos apenas vendas realizadas.  
  
Para fazer essa implementação, é necessário alterar o construtor da classe Pedido.

**GerenciadorUniversalDeItens**  
  
A classe funciona similar a classe anterior que controla todo o histórico de pedidos. Com uma diferença sútil na adição automática do item ao ArrayList dos itens. Que sempre será implementada
diretamente dentro do construtor da classe Item.

## Sistema de pedidos (package Pedidos)

O package Pedidos é responsável por gerenciar os pedidos realizados pelos clientes, incluindo os itens adicionados, o
valor total e o status de andamento do pedido.

A classe item é um objeto concreto simples, serve apenas para modelar o objeto item.
A classe Pedido, é um gerenciador de todos os itens adicionados a ele, utiliza a enum StatusPedido para controle do
cliente.

#### StatusPedido (package enumerações)

```Java
public enum StatusPedido {
    CARRINHO,
    ENVIADO,
    ENTREGUE,
    CANCELADO
}
```

### Pedido

A classe Pedido, contém o array de itens, e controla o fluxo de dados da classe, adiciona e remove item, não consegue
editar o item em si.  
  
Em versões futuras do código, para melhor modelagem, as funções da classe pedido devem ser postas em uma nova classe.
Como por exemplo, uma classe manipuladora de pedidos, para que pedido possa ser uma classe limpa e sólida, sem violar o princípio de repsonsabilidade.

```Java
public Pedido(String codigoPedido) {
    this.codigoPedido = codigoPedido;
    setStatusPedido(StatusPedido.CARRINHO);

    // Assim que um pedido é criado, ele cria a instância única do Gerenciador
    GerenciadorUniversalDePedidos gerenciadorUniversalDePedidos = GerenciadorUniversalDePedidos.getGerenciadorUniversalDePedidos();
    gerenciadorUniversalDePedidos.adicionarPedidoAoSistema(this);
}
```

Note que assim que um pedido é criado, ele é adicionado ao gerenciador universal de pedidos, que se comporta como uma classe singleton.

Cada instância de Pedido adiciona automaticamente uma referência de si mesma ao GerenciadorUniversalDePedidos, garantindo que todos os 
pedidos criados sejam rastreados globalmente.

A edição da quantidade de um item específico está no sistema de servico.
Note o funcionamento do método de adição de itens:

```Java
public void adicionarItem(Item item, int quantidade) {
    if(quantidade == 0) return;

    Item novoItem = verificaSeItemExiste(item.getCodigoDoProduto());
    if(novoItem==null) return;

    meusItens.add(item);
    controleDeProdutosNoPedido(item, quantidade);
    
    atualizaPedido();
}
```

Se por alguma razão a quantidade for 0, não é adicionado o pedido.    

Se a quantidade for maior que 0, primeiro é perguntado se o pedido já existe no carrinho, se o pedido já existir
não é adicionado, é apenas somado no HashMap produtosNoPedido.  

o método controleDeProdutosNoPedido recebe o item e quantidade, se a quantidade for menor que 0 ele chama o método
de remover pedido da classe, e não deixa que exista pedidos com quantidades incoerentes.

```Java
public void atualizaPedido() {
    calculaQuantidadeDeItens();
    calculaValor();
}
```

O método atualizaPedido, controla a quantidade de itens e calcula o valor do pedido.  

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
## Sistema de Entidades (package entidades)  

A classe Usuario é uma classe abstrata, para implementar a interface SistemaDeMenus, que é usada nas classes que herdam de Usuario.  
  
Assim como itens ou pedidos, usuário tem um id próprio que é fornecido utilizando o GerenciadorID. É uma classe de criaçaõ de um objeto simples.

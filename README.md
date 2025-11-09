## Sistem de gerenciamento de usuários e pedidos - SGUP
Projeto que visa a criação de um sistema real de gestão com unicidade, histórico global e separação clara de entidade e funções.
### Sistemas de gerenciamento
**GerenciadorID**

Classe que garante a unicidade e universalidade dos IDs usados ao decorrer do projeto. Ela incorpora um padrão singleton adaptado para múltiplas instâncias com distinção de enumeração.
As intâncias são definidas em uma coleção *static final* do tipo EnumMap.

```Java
private static final EnumMap<TipoID, GerenciadorID> INSTANCIA = new EnumMap<>(TipoID.class);
```
Método singleton adaptado para instâncias distintas. Retorna a instância única do GerenciadorID para o TipoID. Cria a instância se ainda não existir.
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
private void criarIDsDisponiveis(TipoID tipoID){  
    String ID = tipoID.toString();  
    for(int i=0; i<QUATIDADE_DE_IDS; i++){  
        IDsDisponiveis.add(String.format("%s%04d",ID,i+1));  
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
  public String toString(){  
            return "ITEM-";  
        }  
    },  
    PEDIDO{  
        @Override  
  public String toString(){  
            return "PEDI-";  
        }  
    }  
}
```
O toString é sobrescrito para servir como prefixador dos IDs da classe GerenciadorID.

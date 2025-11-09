package Gerenciador;

import Tipos.TipoID;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;

public class GerenciadorID {
    // EnumMap usado para criar uma instância única de GerenciadorID por TipoID (Singleton por tipo).
    private static final EnumMap<TipoID, GerenciadorID> INSTANCIA = new EnumMap<>(TipoID.class);

    private ArrayList<String> IDsDisponiveis = new ArrayList<>();
    private ArrayList<String> IDsCadastrados = new ArrayList<>();
    private final int QUATIDADE_DE_IDS = 10;

    private final TipoID tipoID;

    private GerenciadorID(TipoID tipoID){
        // Construtor privado. Inicializa a lista de IDs disponíveis para o TipoID específico.
        criarIDsDisponiveis(tipoID);
        this.tipoID = tipoID;
    }

    public static GerenciadorID getGerenciadorID(TipoID tipoID){
        // Retorna a instância única do GerenciadorID para o TipoID. Cria a instância se ainda não existir.
        if(!INSTANCIA.containsKey(tipoID)){
            INSTANCIA.put(tipoID, new GerenciadorID(tipoID));
        }
        return INSTANCIA.get(tipoID);
    }

    private void criarIDsDisponiveis(TipoID tipoID){
        String ID = tipoID.toString();
        for(int i=0; i<QUATIDADE_DE_IDS; i++){
            IDsDisponiveis.add(String.format("%s%04d",ID,i+1));
        }
        // Linha comentada para testes
        // Collections.shuffle(IDsDisponiveis);
    }

    public String cadastrarID(){
        String ID = IDsDisponiveis.getFirst();
        IDsDisponiveis.removeFirst();
        IDsCadastrados.add(ID);
        return ID;
    }

    public void removeID(String IDRecebido){
        String ID = tipoID.toString() + IDRecebido;
        if(verificaIDsCadastrados(ID)){
            IDsCadastrados.remove(ID);
            IDsDisponiveis.add(ID);
        }
    }

    public boolean verificaIDsCadastrados(String ID){
        return IDsCadastrados.contains(ID);
    }
}

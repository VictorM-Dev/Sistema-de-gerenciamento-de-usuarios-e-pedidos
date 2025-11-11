package Gerenciador;

import Cargos.Cargo;
import Entidades.*;
import Entidades.Usuario;
import Tipos.StatusCliente;

import java.util.ArrayList;

public class GerenciadorUsuarios {
    private static GerenciadorUsuarios instanciaUnica;
    private ArrayList<Usuario> usuariosCadastrados = new ArrayList<>();

    private GerenciadorUsuarios(){};

    public static GerenciadorUsuarios getGerenciadorUsuarios(){
        if(instanciaUnica != null){
            instanciaUnica = new GerenciadorUsuarios();
        }
        return instanciaUnica;
    }

    public void cadastrarUsuario(Usuario usuario){
        Usuario novoUsuario = verificaSeOUsuarioExiste(usuario.getNomeUsuario());
        if(novoUsuario==null){
            usuariosCadastrados.add(usuario);
        }
    }

    private Usuario verificaSeOUsuarioExiste(String nomeUsuario){
        for(Usuario usuario : usuariosCadastrados){
            if(usuario.getNomeUsuario().equals(nomeUsuario)){
                return usuario;
            }
        }
        return null;
    }

    public boolean removerFuncionario(String idUsuario){
        Usuario novoUsuario = verificaSeOUsuarioExiste(idUsuario);
        if(novoUsuario != null){
            usuariosCadastrados.remove(novoUsuario);
            return true;
        }
        return false;
    }

    public boolean editarUsuario(String idUsuario, Cargo novoCargo){
        Usuario funcionario = verificaSeOUsuarioExiste(idUsuario);
        if(funcionario instanceof Funcionario){
            Funcionario novoFuncionario = (Funcionario) funcionario;
            novoFuncionario.alterarCargo(novoCargo);
            return true;
        }
        return false;
    }

    public boolean editarUsuario(String idUsuario, StatusCliente statusCliente){
        Usuario cliente = verificaSeOUsuarioExiste(idUsuario);
        if(cliente instanceof Cliente){
            Cliente novoCliente = (Cliente) cliente;
            novoCliente.alteraStatusCliente(statusCliente);
            return true;
        }
        return false;
    }
}

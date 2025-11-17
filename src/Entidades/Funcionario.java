package Entidades;

import Cargos.*;
import Gerenciador.GerenciadorUsuarios;
import Tipos.Cargo;

public class Funcionario extends Usuario{
    private String login;
    private String senha;
    private Cargo cargo;

    public Funcionario(String nomeFuncionario, String login, String senha, Cargo cargo, String ID){
        super(ID, nomeFuncionario);
        this.login=login;
        this.senha=senha;
        this.cargo=cargo;
        GerenciadorUsuarios gerenciadorUsuarios = GerenciadorUsuarios.getGerenciadorUsuarios();
        gerenciadorUsuarios.cadastrarUsuario(this);
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }

    public void alterarCargo(Cargo cargo){
        this.cargo = cargo;
    }

    public Cargo getCargo() {
        return cargo;
    }
}

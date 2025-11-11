package Entidades;

import Cargos.Cargo;

public class Funcionario extends Usuario{
    private String login;
    private String senha;
    private Cargo cargo;

    public Funcionario(String nomeFuncionario, String login, String senha, Cargo cargo){
        super(nomeFuncionario);
        this.login=login;
        this.senha=senha;
        this.cargo=cargo;
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
    @Override
    public void exibirMenu(){};
    @Override
    public void funcionalidade(int opcao){};
}

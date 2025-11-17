package Entidades;

import Cargos.*;
import Gerenciador.GerenciadorUsuarios;
import Tipos.Cargo;

public class Funcionario extends Usuario{
    private Cargo cargo;

    public Funcionario(String nomeFuncionario, String senha, Cargo cargo, String ID){
        super(ID, nomeFuncionario, senha);
        this.cargo=cargo;
        GerenciadorUsuarios gerenciadorUsuarios = GerenciadorUsuarios.getGerenciadorUsuarios();
        gerenciadorUsuarios.cadastrarUsuario(this);
    }

    public void alterarCargo(Cargo cargo){
        this.cargo = cargo;
    }

    public Cargo getCargo() {
        return cargo;
    }
}

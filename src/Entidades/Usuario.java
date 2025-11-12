package Entidades;

public abstract class Usuario {
    private final String idUsuario;
    private String nomeUsuario;

    public Usuario(String idUsuario){
        this.idUsuario = idUsuario;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setNomeUsuario(String nomeUsuario){
        this.nomeUsuario=nomeUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }
}

//classe base para funcionario e cliente

package model;

public abstract class Usuario {
    protected int id;
    protected String nome;
    protected String cpf;
    protected String telefone;
    protected Endereco endereco;

    public abstract boolean login(String senha);
    public abstract void logout();
    public abstract String consultarDados();
}

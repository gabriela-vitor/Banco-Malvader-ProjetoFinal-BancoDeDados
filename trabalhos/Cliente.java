public class Cliente {
    private String nome;
    private String cpf;
    private String telefone;
    private String endereco;
    private String senha;


    public Cliente(String nome, String cpf, String telefone, String endereco, String senha){
        this.nome = nome;
        this.cpf =  cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.senha = senha;

    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

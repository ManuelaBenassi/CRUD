package dao;
public class Cliente{
    private String email,nome,estado,cidade,bairro,rua,complemento;
    private int numero;

    public Cliente(String email, String nome, String estado, String cidade, String bairro, String rua, String complemento, int numero) throws Exception {
        this.setEmail(email);
        this.setNome(nome);
        this.setEstado(estado);
        this.setCidade(cidade);
        this.setBairro(bairro);
        this.setRua(rua);
        this.setComplemento(complemento);
        this.setNumero(numero);
    }

    public void setEmail(String email) throws Exception {
        if(email.length() > 50)
            throw new Exception("o comprimento deve ser menor que 50 caracteres");
        this.email = email;
    }

    public void setNome(String nome) throws Exception {
        if(nome.length() > 50)
            throw new Exception("o comprimento deve ser menor que 50 caracteres");
        this.nome = nome;
    }

    public void setEstado(String estado) throws Exception {
        if(estado.length() > 50)
            throw new Exception("o comprimento deve ser menor que 50 caracteres");
        this.estado = estado;
    }

    public void setCidade(String cidade) throws Exception {
        if(cidade.length() > 50)
            throw new Exception("o comprimento deve ser menor que 50 caracteres");
        this.cidade = cidade;
    }

    public void setBairro(String bairro) throws Exception {
        if(bairro.length() > 50)
            throw new Exception("o comprimento deve ser menor que 50 caracteres");
        this.bairro = bairro;
    }

    public void setRua(String rua) throws Exception {
        if(rua.length() > 50)
            throw new Exception("o comprimento deve ser menor que 50 caracteres");
        this.rua = rua;
    }

    public void setComplemento(String complemento) throws Exception {
        if(complemento.length() > 50)
            throw new Exception("o comprimento deve ser menor que 50 caracteres");
        this.complemento = complemento;
    }

    public void setNumero(int numero) throws Exception {
        if(numero < 0)
            throw new Exception("o numero deve ser positivo");
        this.numero = numero;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEstado() {
        return estado;
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public String getRua() {
        return rua;
    }

    public String getComplemento() {
        return complemento;
    }

    public int getNumero() {
        return numero;
    }
}
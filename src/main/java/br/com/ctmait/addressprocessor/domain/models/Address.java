package br.com.ctmait.addressprocessor.domain.models;

import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;

public class Address {

    private String id;
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String numero;
    private Provider provider;
    private State state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return Objects.equals(getId(), address.getId()) && Objects.equals(getCep(), address.getCep()) && Objects.equals(getLogradouro(), address.getLogradouro()) && Objects.equals(getComplemento(), address.getComplemento()) && Objects.equals(getBairro(), address.getBairro()) && Objects.equals(getCidade(), address.getCidade()) && Objects.equals(getUf(), address.getUf()) && Objects.equals(getNumero(), address.getNumero()) && getProvider() == address.getProvider() && getState() == address.getState();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCep(), getLogradouro(), getComplemento(), getBairro(), getCidade(), getUf(), getNumero(), getProvider(), getState());
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", uf='" + uf + '\'' +
                ", numero='" + numero + '\'' +
                ", provider=" + provider +
                ", state=" + state +
                '}';
    }

    public void visit(Consumer<Address> visitor) {
        visitor.accept(this);
    }
}
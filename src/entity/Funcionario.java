package entity;

import java.util.ArrayList;
import java.util.List;

public class Funcionario {
    private String nome;
    private Double salario;
    private String email;

    private List<Funcionario> lista = new ArrayList<>();


    public Funcionario() {
    }

    public Funcionario(String nome, String email, Double salario) {
        this.nome = nome;
        this.salario = salario;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Funcionario> getLista() {
        return lista;
    }

    public void setLista(Funcionario f) {
        lista.add(f);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(Funcionario f:lista){

           s.append(f.getNome());
           s.append(",");
           s.append(f.getSalario());
           s.append(",");
           s.append(f.getEmail());
           //s.append("\n");

       }
        return s.toString();
    }
}

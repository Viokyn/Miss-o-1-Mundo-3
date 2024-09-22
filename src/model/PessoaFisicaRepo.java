package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaRepo   {
    private List<PessoaFisica> pessoasFisicas = new ArrayList<>();
    
    public void inserir(PessoaFisica pessoaFisica) {
        pessoasFisicas.add(pessoaFisica);
    }
    
    public void alterar(PessoaFisica pessoaFisica) {
        for (PessoaFisica pf : pessoasFisicas) {
            if (pf.getId() == pessoaFisica.getId()) {
                pf.setNome(pessoaFisica.getNome());
                pf.setCpf(pessoaFisica.getCpf());
                pf.setIdade(pessoaFisica.getIdade());
                break;
            }
        }
    }
    
    public void excluir(int id) {
        pessoasFisicas.removeIf(pf -> pf.getId() == id);
    }
    
    public PessoaFisica obter(int id) {
        for (PessoaFisica pf : pessoasFisicas) {
            if(pf.getId() == id) {
                return pf;
            }
        }
        return null;
    }
    public List<PessoaFisica> obterTodos() {
        return new ArrayList<>(pessoasFisicas);
    }
   
    
    public void persistir(String nomeArquivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(pessoasFisicas);
        }
    }
    
    @SuppressWarnings("unchecked")
    public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            pessoasFisicas = (ArrayList<PessoaFisica>) ois.readObject();
        }
    }
}
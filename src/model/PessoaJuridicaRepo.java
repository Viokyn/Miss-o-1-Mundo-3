package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaRepo {
    private List<PessoaJuridica> pessoasJuridicas = new ArrayList<>();

    public void inserir(PessoaJuridica pessoaJuridica) {
        pessoasJuridicas.add(pessoaJuridica);
    }

    public void alterar(PessoaJuridica pessoaJuridica) {
        for (PessoaJuridica pj : pessoasJuridicas) {
            if (pj.getId() == pessoaJuridica.getId()) {
                pj.setNome(pessoaJuridica.getNome());
                pj.setCnpj(pessoaJuridica.getCnpj());
                break;
            }
        }
    }

    public void excluir(int id) {
        pessoasJuridicas.removeIf(pj -> pj.getId() == id);
    }

    public PessoaJuridica obter(int id) {
        for (PessoaJuridica pj : pessoasJuridicas) {
            if (pj.getId() == id) {
                return pj;
            }
        }
        return null;
    }

    public List<PessoaJuridica> obterTodos() {
        return new ArrayList<>(pessoasJuridicas);
    }

    public void persistir(String nomeArquivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(pessoasJuridicas);
        }
    }

    @SuppressWarnings("unchecked")
    public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            pessoasJuridicas = (ArrayList<PessoaJuridica>) ois.readObject();
        }
    }
}

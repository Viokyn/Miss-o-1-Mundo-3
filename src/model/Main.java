package model;
        
import java.io.IOException;
import java.util.Scanner;

    public class Main {
        public static void main(String [] args) {
            Scanner scanner = new Scanner(System.in);

            PessoaFisicaRepo repo1 = new PessoaFisicaRepo();
            repo1.inserir(new PessoaFisica(1, "João Silva", "123.456.789-00", 30));
            repo1.inserir(new PessoaFisica(2, "Maria Oliveira", "987.654.321-00", 25));

            try {
                repo1.persistir("pessoas_fisicas.bin");
                System.out.println("Dados de pessoas físicas salvos com sucesso.");
            } catch (IOException e) {
                System.out.println("Erro ao salvar dados de pessoas físicas: " + e.getMessage());
            }

            PessoaFisicaRepo repo2 = new PessoaFisicaRepo();
            try {
                repo2.recuperar("pessoas_fisicas.bin");
                System.out.println("Dados de pessoas físicas recuperados com sucesso.");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Erro ao recuperar dados de pessoas físicas: " + e.getMessage());
            }

            System.out.println("Pessoas Físicas Recuperadas:");
            for (PessoaFisica pf : repo2.obterTodos()) {
                pf.exibir();
            }

            PessoaJuridicaRepo repo3 = new PessoaJuridicaRepo();
            repo3.inserir(new PessoaJuridica(1, "Empresa X", "12.345.678/0001-99"));
            repo3.inserir(new PessoaJuridica(2, "Empresa Y", "98.765.432/0001-55"));

            try {
                repo3.persistir("pessoas_juridicas.bin");
                System.out.println("Dados de pessoas jurídicas salvos com sucesso.");
            } catch (IOException e) {
                System.out.println("Erro ao salvar dados de pessoas jurídicas: " + e.getMessage());
            }

            PessoaJuridicaRepo repo4 = new PessoaJuridicaRepo();
            try {
                repo4.recuperar("pessoas_juridicas.bin");
                System.out.println("Dados de pessoas jurídicas recuperados com sucesso.");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Erro ao recuperar dados de pessoas jurídicas: " + e.getMessage());
            }

            System.out.println("Pessoas Jurídicas Recuperadas:");
            for (PessoaJuridica pj : repo4.obterTodos()) {
                pj.exibir();
            }
            
            boolean running = true;
            
            while(running) {
                System.out.println("\n --- Menu ---");
                System.out.println("1. Incluir");
                System.out.println("2. Alterar");
                System.out.println("3. Excluir");
                System.out.println("4. Exibir");
                System.out.println("5. Exibir Todos");
                System.out.println("6. Salvar Dados");
                System.out.println("7. Recuperar Dados");
                System.out.println("0. Sair");
                
                int opcao = scanner.nextInt();
                scanner.nextLine();
                
                switch (opcao) {
                    case 1: 
                        System.out.println("1. Pessoa Jurídica");
                        System.out.println("2. Pessoa Física");
                        System.out.println("Escolha o tipo: ");
                        
                        int tipo = scanner.nextInt();
                        scanner.nextLine();
                        
                        if (tipo == 1) {
                            incluirPessoaJuridica(scanner, repo3);
                        }
                        
                        else if (tipo == 2) {
                            incluirPessoaFisica(scanner, repo1);
                        }
                        break;
                    case 2:
                        System.out.println("1. Pessoa Jurídica");
                        System.out.println("2. Pessoa Física");
                        System.out.println("Escolha o tipo: ");
                        
                        tipo = scanner.nextInt();
                        scanner.nextLine();
                        
                        if (tipo == 1) {
                            alterarPessoaJuridica(scanner, repo3);
                        }
                        
                        else if (tipo == 2) {
                            alterarPessoaFisica(scanner, repo1);
                        }
                        break;
                    case 3:
                        System.out.println("1. Pessoa Jurídica");
                        System.out.println("2. Pessoa Física");
                        System.out.println("Escolha o tipo: ");
                        
                        tipo = scanner.nextInt();
                        scanner.nextLine();
                        
                        if (tipo == 1) {
                            excluirPessoaJuridica(scanner, repo3);
                        }
                        
                        else if (tipo == 2) {
                            excluirPessoaFisica(scanner, repo1);
                        }
                        break;
                    case 4:
                        System.out.println("1. Pessoa Jurídica");
                        System.out.println("2. Pessoa Física");
                        System.out.println("Escolha o tipo: ");
                        
                        tipo = scanner.nextInt();
                        scanner.nextLine();
                        
                        if (tipo == 1) {
                            exibirPessoaJuridica(scanner, repo3);
                        }
                        
                        else if (tipo == 2) {
                            exibirPessoaFisica(scanner, repo1);
                        }
                        break;
                    case 5:
                        System.out.println("1. Pessoa Jurídica");
                        System.out.println("2. Pessoa Física");
                        System.out.println("Escolha o tipo: ");
                        
                        tipo = scanner.nextInt();
                        scanner.nextLine();
                        
                        if (tipo == 1) {
                            exibirTodosJuridico(repo3);
                        }
                        
                        else if(tipo == 2) {
                            exibirTodosFisico(repo1);
                        }
                        break;
                    case 6:
                        System.out.println("Digite o prefixo do arquivo: ");
                        String prefixo = scanner.nextLine();
                        salvarDados(prefixo, repo3, repo1);
                        break;
                    case 7:
                        System.out.println("Digite o prefixo do arquivo: ");
                        prefixo = scanner.nextLine();
                        recuperarDados(prefixo, repo4, repo2);
                        break;
                    case 0: 
                        running = false;
                        break;
                    default:
                        System.out.println("Opção Inválida");
                }
            }
            scanner.close();
        }
        private static void incluirPessoaFisica(Scanner scanner, PessoaFisicaRepo repoFisica) {
            System.out.println("Digite o ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Digite o nome: ");
            String nome = scanner.nextLine();
            System.out.println("Digite o CPF: ");
            String cpf = scanner.nextLine();
            System.out.println("Digite a idade: ");
            int idade = scanner.nextInt();
            
            PessoaFisica pessoaFisica = new PessoaFisica(id, nome, cpf, idade);
            repoFisica.inserir(pessoaFisica);
            System.out.println("Pessoa física inserida com sucesso!");
    }    
       private static void incluirPessoaJuridica(Scanner scanner, PessoaJuridicaRepo repoJuridica) {
           System.out.println("Digite o ID: ");
           int id = scanner.nextInt();
           scanner.nextLine();
           System.out.println("Digite o nome: ");
           String nome = scanner.nextLine();
           System.out.println("Digite o CNPJ: ");
           String cnpj = scanner.nextLine();
           
           PessoaJuridica pessoaJuridica = new PessoaJuridica(id, nome, cnpj);
           repoJuridica.inserir(pessoaJuridica);
           System.out.println();
       }
       
       private static void alterarPessoaFisica(Scanner scanner, PessoaFisicaRepo repoFisica) {
           System.out.println("Digite o ID da pessoa Fisica: ");
           int id = scanner.nextInt();
           scanner.nextLine();
           PessoaFisica pessoaFisica = repoFisica.obter(id);
           if (pessoaFisica != null) {
               System.out.println("Dados atuais: ");
               pessoaFisica.exibir();
               
               System.out.println("Digite o novo nome: ");
               String nome = scanner.nextLine();
               System.out.println("Digite o novo CPF: ");
               String cpf = scanner.nextLine();
               System.out.println("Digite a nova idade: ");
               int idade = scanner.nextInt();
               scanner.nextLine();
               
               pessoaFisica.setNome(nome);
               pessoaFisica.setCpf(cpf);
               pessoaFisica.setIdade(idade);
               repoFisica.alterar(pessoaFisica);
               System.out.println("Pessoa Física alterada com sucesso!");
           } else {
               System.out.println("Pessoa Física não encontrada.");
           }
       }
       
       private static void alterarPessoaJuridica(Scanner scanner, PessoaJuridicaRepo repoJuridica) {
           System.out.println("Digite o ID da pessoa Jurídica: ");
           int id = scanner.nextInt();
           scanner.nextLine();
           PessoaJuridica pessoaJuridica = repoJuridica.obter(id);
           if (pessoaJuridica != null) {
               System.out.println("Dados atuais: ");
               pessoaJuridica.exibir();
               
               System.out.println("Digite o novo nome: ");
               String nome = scanner.nextLine();
               System.out.println("Digite o novo CNPJ: ");
               String cnpj = scanner.nextLine();
               
               pessoaJuridica.setNome(nome);
               pessoaJuridica.setCnpj(cnpj);
               repoJuridica.alterar(pessoaJuridica);
               System.out.println("PessoaJurídica alterada com sucesso!");
           } else {
               System.out.println("Pessoa Jurídica não encontrada.");
           }
       }
       
       private static void excluirPessoaFisica(Scanner scanner, PessoaFisicaRepo repoFisica) {
           System.out.println("Digite o ID da pessoa física a ser excluida: ");
           int id = scanner.nextInt();
           scanner.nextLine();
           repoFisica.excluir(id);
           System.out.println("Pessoa Física excluída com sucesso.");
       }
       
       private static void excluirPessoaJuridica(Scanner scanner, PessoaJuridicaRepo repoJuridica) {
           System.out.println("Digite o ID da pessoa jurídica a ser excluida: ");
           int id = scanner.nextInt();
           scanner.nextLine();
           repoJuridica.excluir(id);
           System.out.println("Pessoa Jurídica excluída com sucesso.");
       }
       
       private static void exibirPessoaFisica(Scanner scanner, PessoaFisicaRepo repoFisica) {
           System.out.println("Digite o ID da pessoa física: ");
           int id = scanner.nextInt();
           scanner.nextLine();
           PessoaFisica pessoaFisica = repoFisica.obter(id);
           if (pessoaFisica != null){
               pessoaFisica.exibir();
           } else {
               System.out.println("Pessoa Física não encontrada.");
           }
       }
       
       private static void exibirPessoaJuridica(Scanner scanner, PessoaJuridicaRepo repoJuridica) {
           System.out.println("Digite o ID da pessoa jurídica: ");
           int id = scanner.nextInt();
           scanner.nextLine();
           PessoaJuridica pessoaJuridica = repoJuridica.obter(id);
           if (pessoaJuridica != null) {
               pessoaJuridica.exibir();
           } else {
               System.out.println("Pessoa Jurídica não encontrada.");
           }
       }
       
       private static void exibirTodosFisico(PessoaFisicaRepo repoFisica) {
           for (PessoaFisica pessoaFisica : repoFisica.obterTodos()) {
               pessoaFisica.exibir();
           }
       }
       
       private static void exibirTodosJuridico(PessoaJuridicaRepo repoJuridica) {
           for (PessoaJuridica pessoaJuridica : repoJuridica.obterTodos()) {
               pessoaJuridica.exibir();
           }
       }
       
       private static void salvarDados(String prefixo, PessoaJuridicaRepo repoJuridica, PessoaFisicaRepo repoFisica) {
        try {
            repoFisica.persistir(prefixo + ".fisica.bin");
            repoJuridica.persistir(prefixo + ".juridica.bin");
            System.out.println("Dados salvos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

        private static void recuperarDados(String prefixo, PessoaJuridicaRepo repoJuridica, PessoaFisicaRepo repoFisica) {
            try {
                repoFisica.recuperar(prefixo + ".fisica.bin");
                repoJuridica.recuperar(prefixo + ".juridica.bin");
                System.out.println("Dados recuperados com sucesso.");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Erro ao recuperar os dados: " + e.getMessage());
            }
        }
}

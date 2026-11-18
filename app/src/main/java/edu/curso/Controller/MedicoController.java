package edu.curso.Controller;

import edu.curso.Entity.Medico;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Scanner;

public class MedicoController {
    private int count = 0;
    private Scanner ler = new Scanner(System.in);
    private ArrayList<Medico> list = new ArrayList<>();

    public void criar() {
        Medico m = new Medico();
        System.out.println("Insira Abaixo as infos do Medico");

        m.setId(count);

        preencherDados(m);


        list.add(m);

        count = count + 1;
    }

    public Medico procurar(long id) {
        for (Medico m : list) {
            if (id == m.getId()) {
                return m;
            }
        }

        return null;
    }

    public void listar() {
        if (list.isEmpty()) {
            System.out.println("Lista Vazia");
        } else {
            for (Medico m : list) {
                System.out.println(m);
            }
        }
    }

    public void mostrar() {
        System.out.println("Insira o id do Medico");
        Medico m = procurar(ler.nextLong());

        if (m == null) {
            System.out.println("Medico não encontrado");
        }

        System.out.println(m);
    }

    public void alterar() {
        System.out.println("Insira o id do Medico");
        Medico m = procurar(ler.nextLong());

        if (m == null) {
            System.out.println("Medico não encontrado");
            return;
        }

        if (confirmar()) {
            preencherDados(m);
        } else {
            System.out.println("Operação Cancelada");
        }
    }

    public void deletar() {
        System.out.println("Insira o id que deseja Deletar");
        Medico m = procurar(ler.nextLong());

        if (m == null) {
            System.out.println("Medico não Encontrado");
            return;
        }

        if (confirmar()) {
            list.remove(m);
            System.out.println("Medico Removido Com Sucesso");
        } else {
            System.out.println("Operação cancelada");
        }
    }

    public void preencherDados(Medico m) {
        System.out.println("Insira o nome: ");
        m.setNome(ler.nextLine());

        System.out.println("Insira o cpf: ");
        m.setCpf(ler.nextLong());

        ler.nextLine();

        System.out.println("Insira o email: ");
        m.setEmail(ler.nextLine());

        System.out.println("Insira a idade: ");
        m.setIdade(ler.nextInt());

        ler.nextLine();

        System.out.println("Insira o CRM: ");
        m.setCrm(ler.nextLine());
    }

    public Boolean confirmar() {
        System.out.println("Deseja Prosseguir com a Operação ?");

        return ler.nextLine().equalsIgnoreCase("SIM");
    }
}
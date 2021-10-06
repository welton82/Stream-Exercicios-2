package Application;

import entity.Funcionario;
import org.w3c.dom.stylesheets.LinkStyle;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        operacao(sc);

    }

    public static int menu(Scanner sc){

        try{
            System.out.println("0 - Sair");
            System.out.println("1 - Inserir na Lista");
            System.out.println("2 - Listar");
            System.out.println("3 - Arquivar Formato .TXT");
            System.out.println("4 - Ler Arquivo .TXT");
            System.out.println("5 - Ler Arquivo Formato Stream");
            System.out.print("6 - Operação: ");
            int op = sc.nextInt();
            sc.nextLine();
            return op;

        }catch(IndexOutOfBoundsException e){
            e.getMessage();
        }
        return 0;
    }
    public static void operacao(Scanner sc){

        Funcionario funcionario = new Funcionario();
        int op = -1;
        while(op != 0 ){

            op = menu(sc);
            switch (op){
                case 0:
                    System.out.println("SAIR DA LISTA GERADA");
                    break;
                case 1:
                    System.out.println("------------GERANDO LISTA PARA ARQUIVAR----------------------------");
                    System.out.print("Informe o Nome do Funcionario; ");
                    String nome = sc.nextLine();
                    System.out.print("Informe o email do Funcionario: ");
                    String email = sc.nextLine();
                    System.out.print("Informe o Salario do Funcionário: ");
                    Double salario = sc.nextDouble();
                    sc.nextLine();
                    funcionario.setLista(new Funcionario(nome, email,salario));
                    break;
                case 2:
                    System.out.println(funcionario.toString());
                    break;
                case 3:
                    System.out.print("Informe o caminho da pasta a ser salvo: ");
                    String path = sc.nextLine();
                    path += ".txt";

                    try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
                        String []lines = {funcionario.toString()};
                        for(String l:lines){
                            bw.write(l);
                            bw.newLine();
                        }
                        System.out.println("Sucess!!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    System.out.print("Caminho e nome do Arquivo para Leitura: ");
                    path = sc.next();
                    path += ".txt";

                    try(BufferedReader br = new BufferedReader(new FileReader(path))){

                        String line = br.readLine();
                        System.out.println("-----------------Mostrando o Arquivo----------------");
                        while (line != null){
                            System.out.println(line);
                            line = br.readLine();
                        }
                        System.out.println("---------------------------------------------");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    System.out.print("Caminho e nome do Arquivo para Leitura: ");
                    path = sc.nextLine();
                    path += ".txt";

                    try(BufferedReader br = new BufferedReader(new FileReader(path))){

                        List<Funcionario> lista = new ArrayList<>();

                        String line = br.readLine();

                        while(line != null){
                            String [] fields = line.split(",");
                            lista.add(new Funcionario(fields[0], fields[2], Double.parseDouble(fields[1])));
                            line = br.readLine();
                        }

                        System.out.print("Mostrar Pessoas que recebem mais que: ");
                        salario = sc.nextDouble();

                        Double condicao = lista.stream()
                                .map(p -> p.getSalario())
                                .reduce(0.0, (x,y) -> salario);


                        System.out.println("-------PESSOAS QUE RECEBEM MAIS QUE " + salario + "R$  -------------");

                        Comparator<String>comp = (s1,s2)->s1.toUpperCase().compareTo(s2.toUpperCase());
                        List<String>nomes = lista.stream()
                                .filter(p -> p.getSalario() > condicao)
                                .map(p -> p.getNome())
                                .sorted(comp.reversed())//.sorted()
                                .collect(Collectors.toList());

                        nomes.forEach(System.out::println);


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;


            }

        }

    }
}
// C:\\temp\funcionarios
// C:\temp\funcionarios
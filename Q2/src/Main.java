import java.util.Random;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ArvoreBusca arvore = new ArvoreBusca();

        Thread[] threads = new Thread[50];
        for (int i = 0; i < 50; i++) {
            threads[i] = new Thread(() -> {
                Random random = new Random();
                for (int j = 0; j < 2000; j++) {
                    int elemento = random.nextInt(10000);
                    arvore.inserir(elemento);
                }
            });
        }

        for (int i = 0; i < 50; i++) {
            threads[i].start();
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }


        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Tempo de execução paralelo: " + executionTime + " milissegundos");

//        Agora sequencial
        long startTimeSequencial = System.currentTimeMillis();

        ArvoreBusca arvoreNova = new ArvoreBusca();

        Random random = new Random();
        for (int i = 0; i < 50 * 2000; i++) {
            int elemento = random.nextInt(10000);
            arvoreNova.inserir(elemento);
        }


        long endTimeSequencial = System.currentTimeMillis();
        long executionTimeSequencial = endTimeSequencial - startTimeSequencial;
        System.out.println("Tempo de execução sequencial: " + executionTimeSequencial + " milissegundos");
    }
}
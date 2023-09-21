import java.util.Random;

public class Main {
    public static void main(String[] args) {
    long startTime = System.currentTimeMillis();
        ArvoreBusca tree = new ArvoreBusca();

        Thread[] threads = new Thread[50];
        for (int i = 0; i < 50; i++) {
            threads[i] = new Thread(() -> {
                Random random = new Random();
                for (int j = 0; j < 2000; j++) {
                    int value = random.nextInt(10000);
                    tree.inserir(value);
                    tree.remover(value);
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
        System.out.println("Tempo de execução: " + executionTime + " milissegundos");


    }
}

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ArvoreBusca {
        private No root;
        private Lock lock = new ReentrantLock();


        public void inserir(int value) {
            lock.lock();
            try {
                if (root == null) {
                    root = new No(value);
                } else {
                    inserindo(value, root);
                }
            } finally {
                lock.unlock();
            }
        }

        private void inserindo(int elemento, No no) {
            if (elemento < no.elemento) {
                if (no.esquerda == null) {
                    no.esquerda = new No(elemento);
                } else {
                    inserindo(elemento, no.esquerda);
                }
            } else {
                if (no.direita == null) {
                    no.direita = new No(elemento);
                } else {
                    inserindo(elemento, no.direita);
                }
            }
        }

        private static class No {
            private int elemento;
            private No esquerda;
            private No direita;

            public No(int elemento) {
                this.elemento = elemento;
            }
        }

        //    Remoção de nós
        public void remover(int elemento) {
            lock.lock();
            try {
                root = removerRecursivo(elemento, root);
            } finally {
                lock.unlock();
            }
        }

        private No removerRecursivo(int elemento, No no) {
            if (no == null) {
                return null;
            }

            if (elemento < no.elemento) {
                no.esquerda = removerRecursivo(elemento, no.esquerda);
            } else if (elemento > no.elemento) {
                no.direita = removerRecursivo(elemento, no.direita);
            } else {
                if (no.esquerda == null) {
                    return no.direita;
                } else if (no.direita == null) {
                    return no.esquerda;
                }

                no.elemento = encontrarMenorValor(no.direita);
                no.direita = removerRecursivo(no.elemento, no.direita);
            }

            return no;
        }

        private int encontrarMenorValor(No no) {
            return no.esquerda == null ? no.elemento : encontrarMenorValor(no.esquerda);
        }
    }


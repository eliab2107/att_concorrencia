import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ArvoreBusca {
    private No raiz;
    private Lock lock = new ReentrantLock();

    public void inserir(int elemento) {
//        Aqui faço o travamento para tentativa de obter o recurso por nó
        lock.lock();
        try {
            if (raiz == null) {
                raiz = new No(elemento);
            } else {
                inserirRecursivo(elemento, raiz);
            }
        } finally {
            lock.unlock();
        }
    }

    private void inserirRecursivo(int elemento, No no) {
        if (elemento < no.value) {
            if (no.left == null) {
                no.left = new No(elemento);
            } else {
                inserirRecursivo(elemento, no.left);
            }
        } else {
            if (no.right == null) {
                no.right = new No(elemento);
            } else {
                inserirRecursivo(elemento, no.right);
            }
        }
    }

    private static class No {
        private int value;
        private No left;
        private No right;

        public No(int elemento) {
            this.value = elemento;
        }
    }

    //    Remoção de nós
    public void remover(int elemento) {
        lock.lock();
        try {
            raiz = removendo(elemento, raiz);
        } finally {
            lock.unlock();
        }
    }

    private No removendo(int elemento, No no) {
        if (no == null) {
            return null;
        }

        if (elemento < no.value) {
            no.left = removendo(elemento, no.left);
        } else if (elemento > no.value) {
            no.right = removendo(elemento, no.right);
        } else {
            if (no.left == null) {
                return no.right;
            } else if (no.right == null) {
                return no.left;
            }

            no.value = encontrarMenorValor(no.right);
            no.right = removendo(no.value, no.right);
        }

        return no;
    }

    private int encontrarMenorValor(No no) {
        return no.left == null ? no.value : encontrarMenorValor(no.left);
    }

}


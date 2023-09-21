import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
class ArvoreBusca {
    private No raiz;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void inserir(int elemento) {
//
        lock.writeLock().lock();
        try {
            if (raiz == null) {
                raiz = new No(elemento);
            } else {
                inserirRecursivo(elemento, raiz);
            }
        } finally {
//            lock.unlock();
            lock.writeLock().unlock();
        }
    }

    private void inserirRecursivo(int elemento, No no) {
        if (elemento < no.elemento) {
            if (no.esquerda == null) {
                no.esquerda = new No(elemento);
            } else {
                inserirRecursivo(elemento, no.esquerda);
            }
        } else {
            if (no.direita == null) {
                no.direita = new No(elemento);
            } else {
                inserirRecursivo(elemento, no.direita);
            }
        }
    }

    public int contarNos() {
        return contarNosRecursivo(raiz);
    }

    private int contarNosRecursivo(No no) {
        if (no == null) {
            return 0;
        }
        return 1 + contarNosRecursivo(no.esquerda) + contarNosRecursivo(no.direita);
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
        //    lock.lock();
        lock.writeLock().lock();
        try {
            raiz = removerRecursivo(elemento, raiz);
        } finally {
            //        lock.unlock();
            lock.writeLock().unlock();
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


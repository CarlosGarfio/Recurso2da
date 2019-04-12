package doubleLinkedList;

import exception.isEmptyException;

public class DoubleLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int     lenght;
    
    public DoubleLinkedList() {
        head   = new Node<>();
        tail   = new Node<>();
        lenght = 0;
    }
    
    public void isEmpty() throws isEmptyException {
        if (head.getNext() == null)
            throw new isEmptyException("Empty list");
    }
    
    public int lenght() {
        return lenght;
    }
    
    // Este método es mío
    public void printList() {
        try {
            isEmpty();
            System.out.println(printList(head) + " lenght: " + lenght);
        } catch (isEmptyException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private String printList(Node<T> node) {
        if (node.getNext() == null)
            return "";
        else
            return "[" + node.getNext().getValue() + "] " + printList(node.getNext());
    }
    
    // Para este reflejé la lógica de mi printList()
    public void rPrint() {
        try {
            isEmpty();
            System.out.println(rPrint(tail) + " lenght: " + lenght);
        } catch (isEmptyException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private String rPrint(Node<T> node) {
        if (node.getBack() == null)
            return "";
        else
            return "[" + node.getBack().getValue() + "] " + rPrint(node.getBack());
    }
    
    private boolean isThere(Node<T> node, T value) {
        if (node.getNext() == null)
            return false;
        else if (node.getNext().getValue().equals(value))
            return true;
        else
            return isThere(node.getNext(), value);
    }
    
    private Node<T> isThereNode(Node<T> node, T value) {
        if (node.getNext() == null)
            return null;
        else if (node.getNext().getValue().equals(value))
            return node.getNext();
        else
            return isThereNode(node.getNext(), value);
    }
    
    private Node<T> getElementAt(int position) {
        if (position < 0 || position >= lenght)
            return null;
        else
            return getElementAt(head.getNext(), 0, position);
    }
    
    private Node<T> getElementAt(Node<T> node, int i, int position) {
        if (i == position)
            return node;
        else
            return getElementAt(node.getNext(), i + 1, position);
    }
    
    public boolean add(T value) {
        if (value == null) // Solo si el valor es nulo regresaría falso
            return false;
        else {
            Node<T> _new = new Node<>(value);
            try { // Caso 1: La lista no está vacía
                isEmpty();
                tail.getBack().setNext(_new);
                _new.setBack(tail.getBack());
                tail.setBack(_new);
            } catch (isEmptyException e) { // Caso 2: La lista está vacía
                head.setNext(_new);
                tail.setBack(_new);
            }
            lenght++;
            return true;
        }
    }
    
    public boolean add(Node<T> node) {
        return add(node.getValue());
    }
    
    public boolean remove(T value) {
        try {
            isEmpty();
            if (value == null) // Valor nulo
                return false;
            else if (isThere(head, value)) { // Sí se encuentra en la lista
                Node<T> rem = isThereNode(head, value);
                if (rem.getBack() == null)
                    if (rem.getNext() == null) { // Caso 1: Es el ÚNICO
                        head.setNext(null);
                        tail.setBack(null);
                    } else { // Caso 2: Es el PRIMERO
                        rem.getNext().setBack(null);
                        head.setNext(rem.getNext());
                        rem.setNext(null);
                    }
                else
                    if (rem.getNext() == null) { // Caso 3: Es el ÚLTIMO
                        rem.getBack().setNext(null);
                        tail.setBack(rem.getBack());
                        rem.setBack(null);
                    } else { // Caso 4: Esta ENMEDIO
                        rem.getNext().setBack(rem.getBack());
                        rem.getBack().setNext(rem.getNext());
                        rem.setNext(null);
                        rem.setBack(null);
                    }
                rem = null;
                System.gc();
                lenght--;
                return true;
            } else // No se encuentra en la lista
                return false;
        } catch (isEmptyException e) { // Lista vacía
            return false;
        }
    }
    
    public boolean remove(Node<T> node) {
        return remove(node.getValue());
    }
    
    public boolean removeAll(T value) {
        try {
            isEmpty();
            if (value == null)
                return false;
            else
                if (isThere(head, value)) {
                    do {
                        remove(value);
                    } while (isThere(head, value));
                    return true;
                } else
                    return false;
        } catch (isEmptyException e) {
            return false;
        }
    }
    
    public boolean addAt(T value, int position) {
        try {
            isEmpty();
            if (value == null)
                return false;
            else {
                Node<T> tmp = getElementAt(position);
                if (tmp == null)
                    return false;
                else {
                    Node<T> _new = new Node<>(value);
                    if (tmp.getBack() == null) { // Se agregaría al principio
                        head.setNext(_new);
                        tmp.setBack(_new);
                        _new.setNext(tmp);
                    } else {
                        tmp.getBack().setNext(_new);
                        _new.setBack(tmp.getBack());
                        tmp.setBack(_new);
                        _new.setNext(tmp);
                    }
                    lenght++;
                    return true;
                }
            }
        } catch (isEmptyException e) {
            return false;
        }
    }
    
    public boolean addAt(Node<T> node, int position) {
        return addAt(node.getValue(), position);
    }
    
    public boolean addAfter(T after, T value) {
        try {
            isEmpty();
            if (after == null || value == null)
                return false;
            else {
                Node<T> tmp = isThereNode(head, after);
                if (tmp == null)
                    return false;
                else {
                    Node<T> _new = new Node<>(value);
                    if (tmp.getNext() == null) {
                        tmp.setNext(_new);
                        _new.setBack(tmp);
                        tail.setBack(_new);
                    } else {
                        tmp.getNext().setBack(_new);
                        _new.setNext(tmp.getNext());
                        tmp.setNext(_new);
                        _new.setBack(tmp);
                    }
                    lenght++;
                    return true;
                }
            }
        } catch (isEmptyException e) {
            return false;
        }
    }
    
    public boolean addBefore(T before, T value) {
        try {
            isEmpty();
            if (before == null || value == null)
                return false;
            else {
                Node<T> tmp = isThereNode(head, before);
                if (tmp == null)
                    return false;
                else {
                    Node<T> _new = new Node<>(value);
                    if (tmp.getBack() == null) {
                        head.setNext(_new);
                        _new.setNext(tmp);
                        tmp.setBack(_new);
                    } else {
                        tmp.getBack().setNext(_new);
                        _new.setBack(tmp.getBack());
                        _new.setNext(tmp);
                        tmp.setBack(_new);
                    }
                    lenght++;
                    return true;
                }
            }
        } catch (isEmptyException e) {
            return false;
        }
    }
    
    public boolean removeAfter(T after) {
        try {
            isEmpty();
            if (after == null)
                return false;
            else {
                Node<T> tmp = isThereNode(head, after);
                if (tmp == null)
                    return false;
                else if (tmp.getNext() == null)
                    return false;
                else {
                    Node<T> rem = tmp.getNext();
                    if (rem.getNext() == null) {
                        tmp.setNext(null);
                        tail.setBack(tmp);
                        rem.setBack(null);
                    } else {
                        rem.getNext().setBack(tmp);
                        tmp.setNext(rem.getNext());
                        rem.setNext(null);
                        rem.setBack(null);
                    }
                    rem = null;
                    System.gc();
                    lenght--;
                    return true;
                }
            }
        } catch (isEmptyException e) {
            return false;
        }
    }
    
    public boolean removeBefore(T before) {
        try {
            isEmpty();
            if (before == null)
                return false;
            else {
                Node<T> tmp = isThereNode(head, before);
                if (tmp == null)
                    return false;
                else if (tmp.getBack() == null)
                    return false;
                else {
                    Node<T> rem = tmp.getBack();
                    if (rem.getBack() == null) {
                        head.setNext(tmp);
                        tmp.setBack(null);
                        rem.setNext(null);
                    } else {
                        rem.getBack().setNext(tmp);
                        tmp.setBack(rem.getBack());
                        rem.setBack(null);
                        rem.setNext(null);
                    }
                    rem = null;
                    System.gc();
                    lenght--;
                    return true;
                }
            }
        } catch (isEmptyException e) {
            return false;
        }
    }
    
    public boolean addStart(T value) {
        if (value == null)
            return false;
        else {
            Node<T> _new = new Node<>(value);
            try {
              isEmpty();
              head.getNext().setBack(_new);
              _new.setNext(head.getNext());
              head.setNext(_new);
            } catch (isEmptyException e) {
                head.setNext(_new);
                tail.setBack(_new);
            }
            lenght++;
            return true;
        }
    }
    
    public boolean addStart(Node<T> node) {
        return addStart(node.getValue());
    }

    public DoubleLinkedList<T> clone(DoubleLinkedList<T> myList){
        DoubleLinkedList<T> copia = new DoubleLinkedList<>();
        for (int i = 0; i < myList.lenght(); i++) {
            copia.add(myList.getElementAt(i));
        }
        return copia;
    }
    
    public DoubleLinkedList<T> mayoresPromedio(DoubleLinkedList<T> myList){
        try {
            myList.isEmpty();
            DoubleLinkedList<T> _new= new DoubleLinkedList<>();
            double prom= promedio(myList.head.getNext(), 0,myList.lenght());
            System.out.println("El promedio es: "+prom);
            return mayoresPromedio(_new,myList.head.getNext(),prom);
        } catch (isEmptyException e) {
        }
        return null;
    }
    
    private DoubleLinkedList<T> mayoresPromedio(DoubleLinkedList<T> myList,Node<T> node,double prom){
        if(node== null){
            return myList;
        }else if((Integer)node.getValue() > prom){
            myList.add(node.getValue());
        }
        return mayoresPromedio(myList, node.getNext(), prom);
    }
    
    private double promedio(Node<T> node, int suma,int length){
        if(node == null){
            return suma/length;
        }else {
            suma+=(Integer)node.getValue();
        }
        return promedio(node.getNext(), suma,length);
    }   
}

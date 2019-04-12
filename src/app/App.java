package app;

import doubleLinkedList.DoubleLinkedList;
import java.util.Scanner;

public class App {
    
    public App() {
        DoubleLinkedList<Integer> lista = new DoubleLinkedList<>();
        
        // Prueba del add()
        for (int i = 0; i < 5; i++)
            lista.add((int)(Math.random() * 100) + 1);
        
        lista.printList();
        
        DoubleLinkedList<Integer> copia= lista.mayoresPromedio(lista);
        
        copia.printList();
/*
        Prueba del remove()
        System.out.print("¿Valor a eliminar? ");
        valor = s.nextInt();
        lista.remove(valor);
        lista.printList();
        lista.rPrint();
*/

/*
        Prueba del removeAll()
        System.out.print("¿Valor que desea eliminar completamente? ");
        valor = s.nextInt();
        lista.removeAll(valor);
        lista.printList();
        lista.rPrint();
*/
/*
        prueba de addAt()
        System.out.print("¿Posición en la que desea agregar? ");
        posicion = s.nextInt();
        System.out.print("¿Valor que desea agregar? ");
        valor = s.nextInt();
        lista.addAt(valor, posicion);
        lista.printList();
        lista.rPrint();
*/
/*
        Prueba de addAfter()
        System.out.print("Valor a agregar? ");
        valor = s.nextInt();
        System.out.print("Despues de cual? ");
        despues = s.nextInt();
        lista.addAfter(despues, valor);
        lista.printList();
        lista.rPrint();
*/
/*
        Prueba de addBefore()
        System.out.print("Valor a agregar? ");
        valor = s.nextInt();
        System.out.print("Antes de cual? ");
        antes = s.nextInt();
        lista.addBefore(antes, valor);
        lista.printList();
        lista.rPrint();
*/
/*
        Prueba addStart()
        System.out.print("Valor a agregar al principio? ");
        valor = s.nextInt();
        lista.addStart(valor);
        lista.printList();
        lista.rPrint();
*/
/*
        Prueba removeAfter()
        System.out.print("Despues de cual valor va a eliminar? ");
        despues = s.nextInt();
        lista.removeAfter(despues);
        lista.printList();
        lista.rPrint();
*/
/*
        Prueba removeBefore()
        System.out.print("Antes de cual valor va a eliminar? ");
        antes = s.nextInt();
        lista.removeBefore(antes);
        lista.printList();
        lista.rPrint();
*/
    }
}

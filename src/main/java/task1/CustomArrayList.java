package task1;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Реализация интерфейса CustomList на основе динамического массива.
 */

public class CustomArrayList<E> implements CustomList<E> {


    private Object[] elements;
    private int curSize;
    /**
     * Создаёт пустой список с начальной ёмкостью по умолчанию (10).
     */
    public CustomArrayList() {
        this.elements = new Object[10];
    }
    /**
     * Создаёт пустой список с заданной начальной ёмкостью.
     */
    public CustomArrayList(int capacity) {
        this.elements = new Object[capacity];
    }
    @Override
    public void add(E element){
        if (element == null){
            throw new NullPointerException("Новый элемент не может быть NULL !");
        }
        if (curSize == elements.length){
            int extendedCapacity = (int) (elements.length*1.5);
            Object[] extendedElements = new Object[extendedCapacity];
            for (int i = 0;i<elements.length;i+=1){
                extendedElements[i]=elements[i];
            }
            this.elements = extendedElements;
        }
        elements[curSize]=element;
        curSize+=1;
    }
    @Override
    public E get(int index){
        if (index <0 || index>=curSize){
            throw new IndexOutOfBoundsException("Невалидный индекс!! (<0 или >текущешо размера");
        }
        return (E) elements[index];
    }
    @Override
    public E remove(int index){
        if (index <0 || index>=curSize){
            throw new IndexOutOfBoundsException("Невалидный индекс!! (<0 или >текущешо размера");
        }
        E removable = (E) elements[index];
        for (int i = index; i< (curSize-1);i+=1){
            elements[i] = elements[i+1];
        }
        elements[curSize-1]=null;
        curSize-=1;
        return removable;
    }

    @Override
    public int size(){
        return curSize;
    }

    @Override
    public boolean isEmpty(){
        return curSize==0;
    }
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int curI = 0; //не буду

            @Override
            public boolean hasNext() {
                return curI < curSize;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                curI+=1;
                return get(curI-1);
            }
        };
    }
}

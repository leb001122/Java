package practice.list;

import java.util.NoSuchElementException;

public class ArrList <E> {
    private E a[];
    private int size;

    public ArrList() { // 생성자
        a = (E[]) new Object[1];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 비어있거나 k가 음수거나 배열 size의 범위를 넘을 때 예외처리
    public E peek(int k) {
        if (isEmpty() || k < 0 || k >= size)
            throw new NoSuchElementException();
        return a[k];
    }

    public void insertLast(E newItem) { // 가장 뒤에 새 항목 삽입
        if (size == a.length) // 배열에 빈공간이 없으면
            resize(2 * a.length); // 2배로 확장
        a[size++] = newItem; // 새 항목 삽입
    }

    public void insert(E newItem, int k) {
        if (size == a.length) {
            resize(2 * a.length);
        }
        for (int i = size - 1; i >= k; i--) {
            a[i + 1] = a[i];
        }
        a[k] = newItem;
        size++;
    }

    public void insert(E newItem) {
        insert(newItem, 0);
    }

    public void resize(int newSize) {
        Object[] t = new Object[newSize];
        for (int i = 0; i < size; i++) {
            t[i] = a[i];
        }
        a = (E[]) t;
    }

    public E delete(int k) {
        if (isEmpty())
            throw new NoSuchElementException();
        E item = a[k];

        for (int i = k; i < size; i++) {
            a[i] = a[i + 1];
        }
        size--;

        if (size > 0 && size == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    public void print() {
        for (E e : a) {
            System.out.print(e + " ");
        }
        System.out.println();
    }
}

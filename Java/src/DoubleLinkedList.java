import java.util.Scanner;

public class DoubleLinkedList {

    static class Node {
        int value;
        Node next;
        Node prev;

        Node(int value) {
            this.value = value;
        }
    }

    static Node start;
    static Node end;
    static int size;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int test = 1;
        for (int t = 1; t <= test; t++) {
            init();
            first(2);
            first(3);
            print(2);
            first(4);
            print(3);
            first(5);
            first(6);
            first(7);
            first(8);
            first(9);
            print(8);
            insert(3, 10);
            insert(9, 10);
            System.out.println();
            print(10);
            delete(2);
            System.out.println();
            print(9);
            remove();
            System.out.println();
            print(8);
            delete(6);
            System.out.println();
            print(7);
        }
    }

    private static void remove() {
        if (start != null) {
            start = start.next;
        }
        size--;
    }

    private static void removeLast() {
        if (end != null) {
            end = end.prev;
        }
        size--;
    }

    private static void delete(int pos) {
        if (pos >= size) {
            pos = size - 1;
        }

        if (pos == 0) {
            remove();
            return;
        }


        if (pos == size - 1) {
            removeLast();
            return;
        }

        Node tmp;
        if (pos <= size / 2) {
            tmp = start;
            for (int i = 0; i < pos; i++) tmp = tmp.next;
        } else {
            tmp = end;
            for (int i = size - 1; i > pos; i--) tmp = tmp.prev;
        }
        Node next = tmp.next;
        Node prev = tmp.prev;

        prev.next = next;
        next.prev = prev;
        size--;
    }

    private static void insert(int pos, int value) {
        Node node = new Node(value);
        if (pos > size) {
            pos = size;
        }

        if (pos == 0) {
            first(value);
            return;
        }

        if (pos == size) {
            last(value);
            return;
        }

        if (pos <= size / 2) {
            Node tmp = start;
            for (int i = 0; i < pos - 1; i++) tmp = tmp.next;
            Node next = tmp.next;

            tmp.next = node;
            node.prev = tmp;

            node.next = next;
            next.prev = node;
        } else {
            Node tmp = end;
            for (int i = size - 1; i > pos + 1; i--) tmp = tmp.prev;
            Node prev = tmp.prev;

            tmp.prev = node;
            node.next = tmp;

            node.prev = prev;
            prev.next = node;
        }
        size++;
    }

    private static void last(int value) {
        Node node = new Node(value);
        if (size == 0) {
            node.next = null;
            node.prev = null;
            start = node;
            end = node;
        } else {
            node.next = null;
            node.prev = end;

            end.next = node;
            end = node;
        }
        size++;
    }

    private static void print(int amount) {
        if (size == 0)
            System.out.print("empty ");
        Node tmp = start;
        for (int i = 1; i <= amount && tmp != null; i++) {
            System.out.print(tmp.value + " ");
            tmp = tmp.next;
        }
    }

    private static void first(int v) {
        Node node = new Node(v);
        if (size == 0) {
            node.next = null;
            node.prev = null;
            start = node;
            end = node;
        } else {
            node.prev = null;
            node.next = start;

            start.prev = node;
            start = node;
        }
        size++;
    }
    private static void init() {
        start = null;
        end = null;
        size = 0;
    }
}

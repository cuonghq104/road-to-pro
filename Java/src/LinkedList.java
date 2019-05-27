import javafx.geometry.Pos;

import java.util.Scanner;

public class LinkedList {

    static class Node {
        int value;
        Node next;
    }

    static Node start;
    static int size;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int test = 1;
        for (int t = 1; t <= test; t++) {
            init();
            int total = sc.nextInt();
            for (int i = 1; i <= total; i++) {

                char cmd = sc.next().charAt(0);
                switch (cmd) {
                    case 'f': {
                        int value = sc.nextInt();
                        first(value);
                    }
                    break;
                    case 'p': {
                        int amount = sc.nextInt();
                        print(amount);
                    }
                    break;
                    case 'i': {
                        int pos = sc.nextInt();
                        int value = sc.nextInt();
                        insert(pos, value);
                    }
                    break;
                    case 'r': {
                        remove();
                    }
                    break;
                    case 'd': {
                        int pos = sc.nextInt();
                        delete(pos);
                    }
                    break;
                }
            }
        }
    }

    private static void delete(int pos) {
        if (pos >= size)
            return;

        Node tmp = start;
        for (int i = 0; i < pos - 1; i++) {
            tmp = tmp.next;
        }
        Node nextNode = tmp.next;
        tmp.next = nextNode.next;
        size--;
    }

    private static void remove() {
        if (start != null) {
            start = start.next;
            size--;
        }
    }

    private static void first(int value) {
        Node node = new Node();
        node.value = value;
        node.next = start;
        start = node;
        size++;
    }

    private static void print(int amount) {
        Node tmp = start;
        if (tmp == null)
            System.out.print("empty ");
        for (int i = 1; i <= amount && tmp != null; i++) {
            System.out.print(tmp.value + " ");
            tmp = tmp.next;
        }
    }

    private static void insert(int pos, int value) {
        if (pos > size) {
            pos = size;
        }

        if (pos == 0) {
            first(value);
            return;
        }
        Node tmp = start;
        for (int i = 0; i < pos - 1; i++) {
            tmp = tmp.next;
        }

        Node next = tmp.next;
        Node newNode = new Node();
        newNode.value = value;

        tmp.next = newNode;
        newNode.next = next;
        size++;
    }

    private static void init() {
        start = null;
        size = 0;
    }
}

package hash;

import java.util.Scanner;

public class BinaryTreeByLinkedList {

    static class Node {
        int value;
        Node prev;
        Node next;
    }

    static Node start;
    static int size;

    static void init() {
        start = new Node();
        start.value = 0;

        size = 0;
    }

    static void insert(int value) {
        Node tmp = start.next;
        Node tmpPrev = start;

        while (tmp != null && tmp.value < value) {
            tmpPrev = tmp;
            tmp = tmp.next;
        }

        if (tmp != null) {
            if (tmp.value > value) {
                Node node = new Node();
                node.value = value;

                tmpPrev.next = node;
                node.prev = tmpPrev;

                node.next = tmp;
                tmp.prev = node;
                size++;
            }
        } else {
            Node node = new Node();
            node.value = value;

            tmpPrev.next = node;
            node.prev = tmpPrev;
            size++;
        }
    }

    static int higher(int value) {
        Node tmp = start.next;
        while (tmp != null && tmp.value <= value) {
            tmp = tmp.next;
        }

        if (tmp == null) {
            return 0;
        }
        return tmp.value;
    }

    static int lower(int value) {
        Node tmp = start.next;
        Node tmpPrev = start;

        while (tmp != null && tmp.value < value) {
            tmpPrev = tmp;
            tmp = tmp.next;
        }

        return tmpPrev.value;
    }

    static void showList() {
        Node tmp = start.next;
        System.out.print("#" + size + " ");
        while (tmp != null) {
            System.out.print(tmp.value + " ");
            tmp = tmp.next;
        }
        System.out.println();
    }

    static void remove(int value) {
        Node tmp = start.next;
        Node tmpPrev = start;

        while (tmp != null && tmp.value < value) {
            tmpPrev = tmp;
            tmp = tmp.next;
        }

        if (tmp != null) {

            if (tmp.value == value) {
                tmpPrev.next = tmp.next;
                if (tmp.next != null)
                    tmp.next.prev = tmpPrev;
                size--;
            }
        }
    }

    static boolean contains(int value) {
        Node tmp = start.next;
        while (tmp != null && tmp.value < value) {
            tmp = tmp.next;
        }

        if (tmp != null)
            return (tmp.value == value);
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        init();
        insert(4);
        showList();
        insert(5);
        showList();
        insert(5);
        showList();
        insert(10);
        showList();
        insert(8);
        showList();
        System.out.println("Contains " + 8 + " " + (contains(8) ? "true" : "false"));
        System.out.println("Contains " + 9 + " " + (contains(9) ? "true" : "false"));
        System.out.println("Contains " + 3 + " " + (contains(3) ? "true" : "false"));
//        System.out.println("Higher than " + 6 + " " + higher(6));
//        System.out.println("Higher than " + 3 + " " + higher(3));
//        System.out.println("Higher than " + 10 + " " + higher(10));
//
//        System.out.println("Lower than " + 4 + " " + lower(4));
//        System.out.println("Higher than " + 10 + " " + lower(10));
        System.out.println("Contains " + 4 + " " + (contains(4) ? "true" : "false"));
        remove(4);
        showList();
        System.out.println("Contains " + 4 + " " + (contains(4) ? "true" : "false"));
        remove(3);
        showList();
        remove(10);
        showList();
    }
}

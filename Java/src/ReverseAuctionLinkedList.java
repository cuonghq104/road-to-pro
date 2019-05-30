import java.io.FileInputStream;
import java.util.Scanner;

public class ReverseAuctionLinkedList {

    static class Node {
        int value;
        int quantity;
        Node next;
        Node prev;

        Node(int value) {
            this.value = value;
            this.quantity = 1;
        }
    }

    static Node start;

    static void readData(Scanner sc, int size) {
        Node tmp = start;
        Node tmpPrev = null;

        for (int i = 1; i <= size; i++) {
            int value = sc.nextInt();

            if (start == null) {
                start = new Node(value);
                tmp = start;
                continue;
            }


            while (tmp != null && tmp.value > value) {
                tmpPrev = tmp;
                tmp = tmp.next;
            }

            if (tmp == null) {
                Node node = new Node(value);
                node.prev = tmpPrev;
                if (tmpPrev != null)
                    tmpPrev.next = node;

                tmp = node;
            } else {

                if (tmp.value == value) {
                    tmp.quantity = tmp.quantity + 1;
                } else {
                    Node node = new Node(value);
                    Node prev = tmp.prev;

                    node.next = tmp;
                    tmp.prev = node;

                    node.prev = prev;
                    if (prev != null) prev.next = node;

                    tmp = tmp.prev;
                    start = tmp;
                }
            }
        }
    }

    static void printLinkedList() {
        if (start == null) {
            System.out.println("Clear");
        }
        else {
            Node tmp = start;
            while (tmp != null) {
                System.out.println(tmp.value + " " + tmp.quantity);
                tmp = tmp.next;
            }
            System.out.println();
        }
    }

    static int findMinValue() {
        Node tmp = start;
        int minValue = 0;
        int min = Integer.MAX_VALUE;

        while (tmp != null) {
            if (tmp.quantity <= min) {
                min = tmp.quantity;
                minValue = tmp.value;
            }
            tmp = tmp.next;
        }

        return minValue;
    }

    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(new FileInputStream("F:\\Samsung-stc\\Java\\input.txt"));
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            readData(sc, m);
            readData(sc, n);
            int result = findMinValue();
            System.out.println("#" + test_case + " " + result);
        }
    }
}

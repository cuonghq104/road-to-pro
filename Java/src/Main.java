import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream("F:\\Samsung-stc\\Java\\input.txt"));
        int n = sc.nextInt();
        int[][] mtx = new int[n + 1][n + 1];
        boolean[] checked = new boolean[n + 1];
        int[] distance = new int[n + 1];
        int[] prev = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            distance[i] = n;
            for (int j = 1; j <= n; j++) {
                mtx[i][j] = sc.nextInt();
            }
        }
        int result = 0;

        Queue queue = new Queue(n);
        int start = 9;
        queue.push(start);
        distance[start] = 0;
        prev[start] = 0;
        while (!queue.isEmpty()) {
            int t = queue.pop();
            int dist = distance[t];
            checked[t] = true;
            for (int i = 1; i <= n; i++) {
                if (mtx[t][i] == 1 && !checked[i]) {
                    queue.push(i);
                    if (dist + 1 < distance[i]) {
                        distance[i] = dist + 1;
                        prev[i] = t;
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(distance[i] + " ");
        }
        System.out.println();
        for (int i = 1; i <= n; i++) {
            System.out.print(prev[i] + " ");
        }
        System.out.println();
    }
}

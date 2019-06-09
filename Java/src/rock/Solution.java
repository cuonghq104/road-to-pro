package rock;

public class Solution {

    static int module[][][];
    static int seed = 3;
    static int pseudo_rand() {
        seed = seed * 214013 + 2531011;
        return (seed >> 16) & 0x7FFF;
    }

    public static void main(String[] args) {
        UserSolution solution = new UserSolution();
        for (int tc = 1; tc <= 10; tc++) {
            module = new int[30000][4][4];
            for (int c = 0; c < 30000; c++) {
                int base = 1 + (pseudo_rand() % 6);
                for (int y = 0; y < 4; y++) {

                    for (int x = 0; x < 4; x++) {

                        module[c][y][x] = base + (pseudo_rand() % 3);
                    }
                }
            }
            System.out.println("#" + tc + " " + solution.test(module));
        }
    }
}

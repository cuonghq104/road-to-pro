package rock;

public class UserSolution {

    static final int SIZE = 30000;
    static final int SURFACE_SIZE = 4;

    static class Rock {
        int[][] surface;
        int height;
        int baseHeight;
    }

    static class Node {
        int index;
        Node next;
        Node prev;
        int baseHeight;
    }

    static Rock[] rocks;
    boolean[] used;

    boolean isMatch(int[][] a, int[][] b) {
        for (int i = 0; i < SURFACE_SIZE; i++) {
            for (int j = 0; j < SURFACE_SIZE; j++) {

                if (a[i][j] != b[i][j])
                    return false;
            }
        }
        return true;
    }

    int test(int[][][] module) {
        preprocessing(module);
        createHashTable();
        int result = 0;

        for (int i = 0; i < SIZE; i++) {
            Rock rock = rocks[i];
            used[i] = true;

            int[][][] matches = findMatch(rock.surface, rock.height - rock.baseHeight);
            boolean found = false;
            int foundIndex = 0;

            for (int r = 0; r < 4 && !found; r++) {
                int[][] sf = matches[r];
                int hash = hash(sf);
                Node tmp = nodes[hash];

                while (tmp != null) {
                    if (!used[tmp.index] && isMatch(sf, rocks[tmp.index].surface)) {
                        found = true;
                        foundIndex = tmp.index;
                        break;
                    }
                    tmp = tmp.next;
                }
            }

            if (found) {
                used[foundIndex] = true;
                result += (rock.height + rocks[foundIndex].baseHeight);
            }
        }
        return result;
    }

    int[][][] findMatch(int[][] surface, int surfaceMaxHeight) {
        int[][] base = new int[SURFACE_SIZE][SURFACE_SIZE];
        for (int i = 0; i < SURFACE_SIZE; i++) {
            for (int j = 0; j < SURFACE_SIZE; j++) {
                base[i][j] = surfaceMaxHeight - surface[i][j];
            }
        }

        int[][][] matches = new int[4][SURFACE_SIZE][SURFACE_SIZE];
        for (int i = 0; i < SURFACE_SIZE; i++) {
            for (int j = 0; j < SURFACE_SIZE; j++) {
                matches[0][i][j] = base[i][3 - j];
                matches[1][j][3 - i] = matches[0][i][j];
                matches[2][3 - i][3 - j] = matches[0][i][j];
                matches[3][3 - j][i] = matches[0][i][j];
            }
        }

//        for (int i = 0; i < SURFACE_SIZE; i++) {
//            for (int j = 0; j < SURFACE_SIZE; j++) {
//                matches[1][i][j] = matches[0][3 - j][i];
//                matches[2][i][j] = matches[0][3 - i][3 - j];
//                matches[3][i][j] = matches[0][j][3 - i];
//            }
//        }
        return matches;
    }

    static final int HASH_BASE = 3;
    static final int MOD = 100007;

    int hash(int[][] mtx) {
        int h = 1;
        for (int i = 0; i < SURFACE_SIZE; i++) {
            for (int j = 0; j < SURFACE_SIZE; j++) {
                h = (h * HASH_BASE) + mtx[i][j];
            }
        }
        return (h % MOD);
    }

    Node[] nodes;

    private void createHashTable() {
        nodes = new Node[MOD];

        for (int i = 0; i < SIZE; i++) {

            Rock r = rocks[i];
            int hash = hash(r.surface);

            Node newNode = new Node();
            newNode.index = i;
            newNode.baseHeight = r.baseHeight;

            Node baseNode = nodes[hash];
            nodes[hash] = insert(baseNode, newNode);
        }
    }

    private Node insert(Node baseNode, Node newNode) {
        Node tmp = baseNode;
        Node tmpPrev = null;
        while (tmp != null) {
            if (tmp.baseHeight < newNode.baseHeight) {
                break;
            }
            tmpPrev = tmp;
            tmp = tmp.next;
        }

        if (tmp != null) {
            newNode.next = tmp;
            tmp.prev = newNode;
            if (tmpPrev == null) {
                // Insert first
                return newNode;
            } else {
                // Insert
                tmpPrev.next = newNode;
                newNode.prev = tmpPrev;
                return baseNode;
            }
        } else {
            if (tmpPrev == null) {
                // list empty
                return newNode;
            } else {
                // insert last
                tmpPrev.next = newNode;
                newNode.prev = tmpPrev;
                return baseNode;
            }
        }
    }

    private void preprocessing(int[][][] module) {
        rocks = new Rock[SIZE];
        used = new boolean[SIZE];

        for (int rock = 0; rock < SIZE; rock++) {
            int[][] curRock = module[rock];
            int maxHeight = 0;
            int minHeight = Integer.MAX_VALUE;

            for (int i = 0; i < SURFACE_SIZE; i++) {
                for (int j = 0; j < SURFACE_SIZE; j++) {
                    if (curRock[i][j] < minHeight)
                        minHeight = curRock[i][j];
                    if (curRock[i][j] > maxHeight)
                        maxHeight = curRock[i][j];
                }
            }

            Rock r = new Rock();
            r.baseHeight = minHeight;
            r.height = maxHeight;
            r.surface = new int[SURFACE_SIZE][SURFACE_SIZE];
            for (int i = 0; i < SURFACE_SIZE; i++) {
                for (int j = 0; j < SURFACE_SIZE; j++) {
                    r.surface[i][j] = curRock[i][j] - minHeight;
                }
            }
            rocks[rock] = r;
        }
        sort(0, SIZE - 1);
    }

    private void sort(int start, int end) {
        if (start < end) {
            int pivot = partition(start, end);
            sort(start, pivot - 1);
            sort(pivot + 1, end);
        }
    }

    void swap(int i, int j) {
        if (i != j) {
            Rock tmp = rocks[i];
            rocks[i] = rocks[j];
            rocks[j] = tmp;
        }
    }

    private int partition(int start, int end) {
        int pivot = end;
        int wall = start - 1;
        for (int i = start; i < end; i++) {
            if (rocks[i].height > rocks[pivot].height) {
                wall++;
                swap(wall, i);
            }
        }
        Rock tmp = rocks[pivot];
        for (int i = pivot; i > wall + 1; i--) {
            rocks[i] = rocks[i - 1];
        }
        rocks[wall + 1] = tmp;
        return wall + 1;
    }


}

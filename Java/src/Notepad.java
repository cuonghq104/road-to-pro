public class Notepad {

    static class Node {
        char value;
        Node next;
        Node prev;

        Node(char v) {
            this.value = v;
        }
    }

    static class Row {
        int length;
        Node start;
        Node end;

        Row next;
        Row prev;

        Row() {
            start = new Node('*');
            end = new Node('#');
            length = 0;
            start.next = end;
            end.prev = start;
        }
    }

    int size;

    int nRow;

    Row currentRow;
    Node cursor;

    int idxR;
    int idxC;

    void init(int N) {
        nRow = 1;
        size = N;
        idxR = 1;
        idxC = 0;
        currentRow = new Row();
        cursor = currentRow.start;
    }

    void addText(char char_inserted) {
        if (currentRow.length == size)
            return;

        Node node = new Node(char_inserted);
        Node next = cursor.next;

        next.prev = node;

        node.next = cursor.next;
        node.prev = cursor;

        cursor.next = node;
        cursor = node;

        currentRow.length++;
        idxC++;
    }

    void newLine() {
        if (nRow == size)
            return;

        Node cut = cursor.next;
        int lengthLeft = idxC;
        int lengthCut = currentRow.length - lengthLeft;
        Node endCut = currentRow.end;

        Node end = new Node('#');
        end.prev = cut.prev;
        cut.prev.next = end;
        currentRow.end = end;
        currentRow.length = lengthLeft;

        Row row = new Row();
        row.length = lengthCut;
        row.start.next = cut;
        row.end = endCut;

        cursor = row.start;

        Row nextRow = currentRow.next;

        if (nextRow != null)
            nextRow.prev = row;

        row.next = nextRow;
        row.prev = currentRow;

        currentRow.next = row;
        currentRow = row;

        idxC = 0;
        idxR++;
        nRow++;
    }

    char get_char(int row, int column) {
        while (idxR > row) {
            currentRow = currentRow.prev;
            idxR--;
        }

        while (idxR < row) {
            currentRow = currentRow.next;
            idxR++;
        }

        Node tmp = currentRow.start;
        for (int i = 1; i <= column; i++) {
            tmp = tmp.next;
        }

        idxC = column;
        cursor = tmp;
        return cursor.value;
    }

    void move_cursor(int cmd) {
        if (cmd == 2)  {

            if (idxC == 0)
                return;

            idxC--;
            cursor = cursor.prev;
        }

        else if (cmd == 3) {

            if (idxC == size - 1)
                return;

            idxC++;
            cursor = cursor.next;
        }

        else if (cmd == 0) {

            if (idxR == 1)
                return;

            Row prev = currentRow.prev;
            if (idxC > prev.length) {
                currentRow = prev;
                idxR--;
                idxC = currentRow.length;

                cursor = currentRow.end.prev;
            } else {
                currentRow = prev;
                Node node = prev.start;

                for (int i = 1; i <= idxC; i++) {
                    node = node.next;
                }

                cursor = node;
                idxR--;
            }
        }

        else {

            if (idxR == size)
                return;

            Row next = currentRow.next;
            if (idxC > next.length) {
                currentRow = next;
                idxR++;
                idxC = currentRow.length;

                cursor = currentRow.end.prev;
            } else {
                currentRow = next;
                Node node = next.start;

                for (int i = 1; i <= idxC; i++) {
                    node = node.next;
                }

                cursor = node;
                idxR++;
            }
        }
    }

    public static void main(String[] args) {
        Notepad notepad = new Notepad();
        notepad.init(5);
        notepad.addText('H');
        notepad.addText('i');
        notepad.newLine();
        notepad.addText('H');
        notepad.addText('e');
        notepad.addText('l');
        notepad.addText('l');
        notepad.addText('o');
        notepad.newLine();

        System.out.println('e' == notepad.get_char(2, 5));
        System.out.println('i' == notepad.get_char(1, 2));
        notepad.addText('k');
        notepad.move_cursor(2);
        notepad.move_cursor(3);
        notepad.move_cursor(1);
        notepad.move_cursor(1);
        notepad.move_cursor(0);
        notepad.move_cursor(3);
        notepad.newLine();

        notepad.addText('d');
        notepad.addText('g');
        notepad.move_cursor(2);
        notepad.move_cursor(1);
        notepad.move_cursor(0);

        notepad.addText('u');
        notepad.newLine();
        notepad.addText('r');
        notepad.newLine();

        System.out.println(notepad.get_char(4, 1));
    }
}

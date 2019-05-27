public class Stack {
    int top;
    int size;
    int[] values;

    public Stack(int size) {
        this.size = size;
        this.top = -1;
        values = new int[size];
    }

    boolean isEmpty() {
        return (top == -1);
    }

    boolean isFull() {
        return (top == size - 1);
    }

    boolean push(int v) {
        if (isFull()) {
            return false;
        }
        top++;
        values[top] = v;
        return true;
    }

    int pop() {
        if (isEmpty()) {
            return -1;
        }
        int v = values[top];
        top--;
        return v;
    }

    int peek() {
        if (isEmpty()) {
            return -1;
        }
        return values[top];
    }

    void showStack() {
        if (isEmpty()) {
            System.out.println("Empty");
            return;
        }
        for (int i = 0; i <= top; i++) {
            System.out.print(values[i] + " ");
        }
        System.out.println();
    }
}

public class Queue {
    int front;
    int rear;
    int[] values;
    int size;

    public Queue(int size) {
        values = new int[size + 1];
        front = rear = 0;
        this.size = size;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull() {
        return (rear + 1) % (size) == front;
    }

    public boolean push(int v) {
        if (isFull()) {
            return false;
        }
        rear = (rear + 1) % (size);
        values[rear] = v;
        return true;
    }

    public int pop() {
        if (isEmpty())
            return -1;
        front = (front + 1) % (size);
        return values[front];
    }

    public int peek() {
        if (isEmpty()) {
            return -1;
        }
        return values[(front + 1) % size];
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("Empty");
            return;
        }
        for (int i = 0; i < size; i++) {
            System.out.print(values[i] + " ");
        }
        System.out.println();
    }
}

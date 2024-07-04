public class LinkedList {
    static class Node {
        private int value;
        private Node next;
        Node(int value) {
            this.value = value;
        }
    }
    private Node head;
    private Node tail;
    private int length;

    public LinkedList(int value) {
        Node newNode = new Node((value));
        this.head = newNode;
        this.tail = newNode;
        length = 1;
    }
    public void append(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        length++;
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    public void printAll() {
        if (length == 0) {
            System.out.println("Head: null");
            System.out.println("Tail: null");
        } else {
            System.out.println("Head: " + head.value);
            System.out.println("Tail: " + tail.value);
        }
        System.out.println("Length:" + length);
        System.out.println("\nLinked List:");
        if (length == 0) {
            System.out.println("empty");
        } else {
            printList();
        }
    }

    public Node removeLast() {
        if (length == 0) return null;
        Node temp = head;
        Node pre = head;
        while (temp.next != null) {
            pre = temp;
            temp = temp.next;
        }
        tail = pre;
        tail.next = null;
        length--;
        if (length == 0) {
            head = null;
            tail = null;
        }
        return temp;
    }

    public void prepend(int value) {
        Node temp = new Node(value);
        if (length == 0) {
            head = temp;
            tail = temp;
        }
        else {
            temp.next = head;
            head = temp;
        }
        length++;
    }

    public Node removeFirst() {
        if (length == 0) return null;
        Node temp = head;
        head = head.next;
        temp.next = null;
        length--;
        if (length == 0) tail = null;
        return temp;
    }

    public Node get(int index) {
        if (length == 0 || index >= length || index < 0) return null;
        Node temp = head;
        for (int i=0; i < index; i++) {
            temp = temp.next;
        }
        System.out.println("getByIndex");
        System.out.println(temp.value);
        return temp;
    }

    public boolean set(int index, int value) {
        Node temp = get(index);
        if (temp != null) {
            temp.value = value;
            return true;
        }
        return false;
    }

    public boolean insert(int  index, int value) {
        if (index > length || index < 0) return false;
        if (index == 0) {
            prepend(value);
            return true;
        }
        if (index == length) {
            append(value);
            return true;
        }
        Node newNode = new Node(value);
        Node pre = get(index-1);
        newNode.next = pre.next;
        pre.next = newNode;
        length++;
        return true;
    }

    public Node remove(int index) {
        if (length == 0 || index >= length || index < 0) return null;
        if (index == 0) return removeFirst();
        if (index == length-1) return removeLast();
        Node pre = get(index-1);
        Node toRemove = pre.next;
        pre.next = toRemove.next;
        toRemove.next = null;
        length--;
        return toRemove;
    }

    public void reverse() {
        Node temp = head;
        head = tail;
        tail = temp;
        Node after = temp.next;
        Node before = null;
        for (int i = 0; i < length; i++) {
            after = temp.next;
            temp.next = before;
            before = temp;
            temp = after;
        }
    }

    public Node findMiddleNode() {
        Node slow = head;
        Node fast = head;
        while (fast != null || fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public boolean hasLoop() {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    Node findKthFromEnd(int k) {
        Node slow = head;
        Node fast = head;

        for (int i = 0; i < k; i++) {
            fast = fast.next;
            if (fast == null) return null;
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public void partitionList(int x) {
        if (head != null) return;
        
        Node dummyFirst = new Node(x-1);
        Node dummyLast = new Node(x);
        Node firstPointer = dummyFirst;
        Node lastPointer = dummyLast;
        Node temp = head;

        while (temp != null) {
            if (temp.value < x) {
                firstPointer.next = temp;
                firstPointer = temp;
            }
            else {
                lastPointer.next = temp;
                lastPointer = temp;
            }
            temp = temp.next;
        }

        lastPointer.next = null;
        firstPointer.next = dummyLast.next;
        head = dummyFirst.next;
    }

    public void reverseBetween(int m, int n) {
        if (head == null) return;
        Node dummy = new Node(0);
        dummy.next = head;
        Node prev = dummy;

        for (int i = 0; i < m; i++) {
            prev = prev.next;
        }
        Node current = prev.next;
        Node toMove = current.next;

        for (int i = m; i < n; i++) {
            toMove = current.next;
            current.next = toMove.next;
            toMove.next = prev.next;
            prev.next = toMove;
        }

        head = dummy.next;
    }
}

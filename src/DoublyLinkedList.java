public class DoublyLinkedList {
    public static class Node {
        private int value;
        private Node prev;
        private Node next;

        Node(int value) {
            this.value = value;
        }
    }

    private Node head;
    private Node tail;
    private int length;

    public DoublyLinkedList(int value) {
        Node newNode = new Node(value);
        head = newNode;
        tail = newNode;
        length = 1;
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
        System.out.println("\nDoubly Linked List:");
        if (length == 0) {
            System.out.println("empty");
        } else {
            printList();
        }
    }

    public int getHead() {
        return head.value;
    }

    public int getTail() {
        return tail.value;
    }

    public int getLength() {
        return length;
    }

    public void append(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        }
        else {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;
        length++;
    }

    public Node removeLast() {
        if (head == null) return null;
        Node toRemove = tail;
        if (length == 1) {
            head = null;
            tail = null;
        }
        else {
            tail = tail.prev;
            tail.next = null;
            toRemove.prev = null;
        }
        length--;
        return toRemove;
    }

    public void prepend(int value) {
        Node newNode = new Node(value);
        if (head == null) tail = newNode;
        else {
            newNode.next = head;
            head.prev = newNode;
        }
        head = newNode;
        length++;
    }

    public Node removeFirst() {
        if (head == null) return null;
        Node toRemove = head;
        if (length == 1) {
            head = null;
            tail = null;
        }
        else  {
            head = head.next;
            head.prev = null;
            toRemove.next = null;
        }
        length--;
        return toRemove;
    }
    public Node get(int index) {
        if (index < 0 || index >= length) return null;
        Node temp = head;
        if (index < length/2) {
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
        }
        else {
            temp = tail;
            for(int i = index; i < length-1; i++) {
                temp = temp.prev;
            }
        }
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

    public boolean insert(int index, int value) {
        if (index < 0 || index > length) return false;
        else if (index == 0) prepend(value);
        else if (index == length) append(value);
        else {
            Node newNode = new Node(value);
            Node before = get(index-1);
            Node after = before.next;
            before.next = newNode;
            newNode.prev = before;
            newNode.next = after;
            after.prev = newNode;
            length++;
        }
        return true;
    }

    public Node remove(int index) {
        if (index < 0 || index >= length) return null;
        if (index == 0) return removeFirst();
        if (index == length-1) return removeLast();
        Node toRemove = get(index);
        toRemove.prev.next = toRemove.next;
        toRemove.next.prev = toRemove.prev;
        toRemove.next = null;
        toRemove.prev = null;
        length--;
        return toRemove;
    }

    public void swapFirstLast() {
        if (length < 2) return;
        int swap = head.value;
        head.value = tail.value;
        tail.value = swap;
    }

    public void reverse() {
        if (length <2 )return;
        Node current = head;
        Node temp = current.next;
        for(int i = 0; i < length; i++) {
            temp = current.next;
            current.next = current.prev;
            current.prev = temp;
            current = temp;
        }
        temp = head;
        head = tail;
        tail = temp;
    }

    public boolean isPalindrome() {
        if (length < 2) return true;
        Node left = head;
        Node right = tail;
        while (left != right) {
            if (left.value != right.value) return false;
            left = left.next;
            right = right.prev;
        }
        return true;
    }

    public void swapPairs() {
        if (length < 2) return;
        Node dummy = new Node(0);
        dummy.next = head;
        Node previousNode = dummy;
        while (head != null && head.next != null) {
            Node firstNode = head;
            Node secondNode = head.next;
            previousNode.next = secondNode;
            firstNode.next = secondNode.next;
            secondNode.next = firstNode;
            secondNode.prev = previousNode;
            firstNode.prev = secondNode;
            if (firstNode.next != null) firstNode.next.prev = firstNode;
            head = firstNode.next;
            previousNode = firstNode;
        }
        head = dummy.next;
        head.prev = null;
        dummy.next = null;
    }
}

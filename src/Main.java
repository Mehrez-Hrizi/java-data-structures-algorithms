public class Main {
    public static void main(String[] args) {
        LinkedList list = new LinkedList(1);
        list.append(2);
        list.append(3);
        list.append(4);

        list.removeLast();
        list.prepend(-1);
        list.removeFirst();
        list.get(2);
        list.set(2, 10);
        list.insert(3, 20);
        list.remove(1);
        list.reverse();
        list.reverseBetween(0,2);
        list.printAll();;
    }
}
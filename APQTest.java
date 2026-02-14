public class APQTest {
    public static void main(String[] args) {
        System.out.println("=== Advanced Priority Queue Test Suite ===\n");

        AdvancedPQ<Integer, String> apq = new AdvancedPQ<>();

        System.out.println("Example 1: Insert operations");
        apq.insert(5, "Five"); apq.insert(3, "Three");
        apq.insert(7, "Seven"); apq.insert(1, "One");
        apq.display();

        System.out.println("\nExample 2: Top element");
        System.out.println("Top: " + apq.top());

        System.out.println("\nExample 3: Remove top");
        System.out.println("Removed: " + apq.removeTop());
        apq.display();

        System.out.println("\nExample 4: Current state");
        System.out.println("State: " + apq.state());

        System.out.println("\nExample 5: Toggle to max-heap");
        apq.toggle();
        apq.display();

        System.out.println("\nExample 6: Top after toggle");
        System.out.println("Top: " + apq.top());

        System.out.println("\nExample 7: More insertions");
        apq.insert(10, "Ten"); apq.insert(2, "Two"); apq.insert(8, "Eight");
        apq.display();

        System.out.println("\nExample 8: Size");
        System.out.println("Size: " + apq.size());

        System.out.println("\nExample 9: Is empty?");
        System.out.println("Empty: " + apq.isEmpty());

        System.out.println("\nExample 10: Replace key");
        APQEntry<Integer, String> entry = apq.top();
        System.out.println("Replacing key of " + entry);
        Integer oldKey = apq.replaceKey(entry, 15);
        System.out.println("Old key: " + oldKey);
        apq.display();

        System.out.println("\nExample 11: Replace value");
        entry = apq.top();
        String oldValue = apq.replaceValue(entry, "Fifteen");
        System.out.println("Old value: " + oldValue);
        System.out.println("New top: " + apq.top());

        System.out.println("\nExample 12: Remove specific entry");
        entry = apq.top();
        apq.remove(entry);
        apq.display();

        System.out.println("\nExample 13: Peek at 2nd element");
        System.out.println("2nd element: " + apq.peekAt(2));

        System.out.println("\nExample 14: Peek at 3rd element");
        System.out.println("3rd element: " + apq.peekAt(3));

        System.out.println("\nExample 15: Toggle back to min-heap");
        apq.toggle();
        apq.display();

        System.out.println("\nExample 16: Merge with another APQ");
        AdvancedPQ<Integer, String> apq2 = new AdvancedPQ<>();
        apq2.insert(4, "Four"); apq2.insert(6, "Six"); apq2.insert(9, "Nine");
        System.out.println("Second APQ:");
        apq2.display();
        apq.merge(apq2);
        System.out.println("After merge:");
        apq.display();

        System.out.println("\nExample 17: Test array resizing");
        for (int i = 20; i < 35; i++) apq.insert(i, "Number" + i);
        System.out.println("Size after many insertions: " + apq.size());

        System.out.println("\nExample 18: Multiple removeTop");
        for (int i = 0; i < 5; i++) System.out.println("Removed: " + apq.removeTop());

        System.out.println("\nExample 19: ReplaceKey requiring downheap");
        entry = apq.top();
        System.out.println("Top before: " + entry);
        apq.replaceKey(entry, 100);
        System.out.println("Top after: " + apq.top());

        System.out.println("\nExample 20: Remove all elements");
        while (!apq.isEmpty()) apq.removeTop();
        System.out.println("Is empty now? " + apq.isEmpty());

        System.out.println("\nExample 21: Insert after clearing");
        apq.insert(50, "Fifty"); apq.insert(25, "TwentyFive");
        apq.display();

        System.out.println("\nExample 22: PeekAt first element");
        System.out.println("1st element: " + apq.peekAt(1));
        System.out.println("Should match top: " + apq.top());

        System.out.println("\n=== All tests completed successfully! ===");
    }
}

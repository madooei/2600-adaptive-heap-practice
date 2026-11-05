package practice;

import adaptiveheap.IndexedArrayHeap;

/** A demo of IndexedArrayHeap.remove(item): one job is canceled before it is served. */
public final class RemoveItemDemo {

  private RemoveItemDemo() {
    // This class should not be instantiated!
  }

  public static void main(String[] args) {
    IndexedArrayHeap<String, Integer> jobs = new IndexedArrayHeap<>();
    jobs.add("A", 2);
    jobs.add("B", 5);
    jobs.add("C", 3);
    jobs.add("D", 8);
    jobs.add("E", 6);
    jobs.add("F", 4);

    jobs.remove("E");
    System.out.println("Is E still waiting? " + jobs.contains("E"));  // false
    System.out.println("Jobs waiting: " + jobs.size());               // 5

    System.out.print("Serve order: ");
    while (jobs.size() > 0) {
      System.out.print(jobs.peek() + " ");  // A C F B D
      jobs.remove();
    }
    System.out.println();
  }
}

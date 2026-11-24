package demo;

import adaptiveheap.IndexedArrayHeap;

/** A demo of IndexedArrayHeap.remove(item): one job is canceled before it is served. */
public final class IndexedArrayHeapDemo {

  private IndexedArrayHeapDemo() {
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
    System.out.println("jobs waiting: " + jobs.size());

    // Cancel job E. Expect E to be gone and 5 jobs left.
    jobs.remove("E");
    System.out.println("is E still waiting? " + jobs.contains("E"));
    System.out.println("jobs waiting: " + jobs.size());

    // Serve the rest by priority. Expect A C F B D.
    System.out.print("serve order: ");
    while (jobs.size() > 0) {
      System.out.print(jobs.peek() + " ");
      jobs.remove();
    }
    System.out.println();
  }
}

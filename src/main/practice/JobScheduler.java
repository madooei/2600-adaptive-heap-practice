package practice;

import adaptiveheap.AdaptivePriorityQueue;
import adaptiveheap.IndexedHeap;
import java.util.ArrayList;
import java.util.List;

/**
 * A scheduler whose waiting tasks can change priority while they wait. Each
 * event is one of {"add", id, priority}, {"change", id, priority}, or
 * {"serve"}, where a smaller priority number means a more urgent task.
 */
public final class JobScheduler {

  private JobScheduler() {
    // This class should not be instantiated!
  }

  /**
   * Processes the events in order and returns the ids of the served tasks, in
   * the order they were served.
   *
   * @param events the events to process, each an add, change, or serve
   * @return the served task ids, in serve order
   */
  public static List<String> serveOrder(String[][] events) {
    AdaptivePriorityQueue<String, Integer> pq = new IndexedHeap<>();
    List<String> served = new ArrayList<>();
    for (String[] e : events) {
      if (e[0].equals("add")) {
        pq.add(e[1], Integer.parseInt(e[2]));
      } else if (e[0].equals("change")) {
        pq.changePriority(e[1], Integer.parseInt(e[2]));
      } else {  // "serve"
        served.add(pq.peek());  // the highest-priority task
        pq.remove();
      }
    }
    return served;
  }
}

package practice;

import adaptiveheap.AdaptivePriorityQueue;
import adaptiveheap.IndexedArrayHeap;
import java.util.ArrayList;
import java.util.List;

/** A solution to the problem of serving tasks whose priorities change. */
public final class JobScheduler {

  private JobScheduler() {
    // This class should not be instantiated!
  }

  // Processes the events in order and returns the ids of the served tasks, in
  // the order they were served. Each event is {"add", id, priority},
  // {"change", id, priority}, or {"serve"}, and the smallest priority is served
  // first. Assumes the events are well formed.
  public static List<String> serveOrder(String[][] events) {
    AdaptivePriorityQueue<String, Integer> pq = new IndexedArrayHeap<>();
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

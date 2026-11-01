package practice;

import adaptiveheap.AdaptivePriorityQueue;
import adaptiveheap.IndexedHeap;
import java.util.ArrayList;
import java.util.List;

/**
 * A scheduler whose waiting tasks can change priority while they wait. Each
 * event is one of {"add", id, priority}, {"change", id, priority}, or
 * {"serve"}. The task with the smallest priority is served first.
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
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }
}

package practice;

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
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }
}

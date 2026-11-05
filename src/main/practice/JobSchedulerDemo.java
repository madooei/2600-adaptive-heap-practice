package practice;

/** A demo of JobScheduler.serveOrder: the example event list from the problem. */
public final class JobSchedulerDemo {

  private JobSchedulerDemo() {
    // This class should not be instantiated!
  }

  public static void main(String[] args) {
    String[][] events = {
      {"add", "A", "5"},
      {"add", "B", "3"},
      {"add", "C", "8"},
      {"change", "C", "1"},
      {"serve"},
      {"change", "A", "2"},
      {"serve"},
      {"serve"}
    };
    System.out.println("Served: " + JobScheduler.serveOrder(events));  // [C, A, B]
  }
}

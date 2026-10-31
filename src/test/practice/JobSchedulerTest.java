package practice;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/** Tests for JobScheduler.serveOrder, the practice problem "Changing Priorities". */
public class JobSchedulerTest {

  @Test
  public void theExampleFromTheProblem() {
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
    assertEquals(listOf("C", "A", "B"), JobScheduler.serveOrder(events));
  }

  @Test
  public void noEventsServeNothing() {
    String[][] events = {};
    assertEquals(listOf(), JobScheduler.serveOrder(events));
  }

  @Test
  public void addsAloneServeInPriorityOrder() {
    String[][] events = {
      {"add", "A", "5"},
      {"add", "B", "3"},
      {"add", "C", "8"},
      {"add", "D", "1"},
      {"serve"},
      {"serve"},
      {"serve"},
      {"serve"}
    };
    assertEquals(listOf("D", "B", "A", "C"), JobScheduler.serveOrder(events));
  }

  @Test
  public void aLowerNumberMovesATaskAhead() {
    String[][] events = {
      {"add", "A", "5"},
      {"add", "B", "3"},
      {"change", "A", "1"},
      {"serve"},
      {"serve"}
    };
    assertEquals(listOf("A", "B"), JobScheduler.serveOrder(events));
  }

  @Test
  public void aHigherNumberMovesATaskBack() {
    String[][] events = {
      {"add", "A", "1"},
      {"add", "B", "3"},
      {"add", "C", "4"},
      {"change", "A", "9"},
      {"serve"},
      {"serve"},
      {"serve"}
    };
    assertEquals(listOf("B", "C", "A"), JobScheduler.serveOrder(events));
  }

  @Test
  public void aServedTaskCanBeAddedAgain() {
    String[][] events = {
      {"add", "A", "2"},
      {"add", "B", "3"},
      {"serve"},
      {"add", "A", "4"},
      {"serve"},
      {"serve"}
    };
    assertEquals(listOf("A", "B", "A"), JobScheduler.serveOrder(events));
  }

  @Test
  public void tasksLeftWaitingAreNotReported() {
    String[][] events = {
      {"add", "A", "2"},
      {"add", "B", "3"},
      {"serve"}
    };
    assertEquals(listOf("A"), JobScheduler.serveOrder(events));
  }

  private static List<String> listOf(String... items) {
    List<String> list = new ArrayList<>();
    for (String item : items) {
      list.add(item);
    }
    return list;
  }
}

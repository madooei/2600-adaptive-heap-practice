package adaptiveheap;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import map.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests for IndexedArrayHeap.remove(T item), the practice problem "Cancel a Job".
 * The tests read the private heap array and index map by reflection.
 */
public class RemoveItemTest {

  private IndexedArrayHeap<String, Integer> heap;

  @BeforeEach
  public void setup() {
    heap = new IndexedArrayHeap<>();
  }

  @Test
  public void removeFromEmptyHeapThrows() {
    try {
      heap.remove("A");
      fail("expected NoSuchElementException when removing from an empty queue");
    } catch (NoSuchElementException e) {
      return;
    }
  }

  @Test
  public void removeTheOnlyItemLeavesEmpty() {
    heap.add("A", 1);
    heap.remove("A");
    assertEquals(0, heap.size());
  }

  // The six jobs the problem draws: A 2, B 5, C 3, D 8, E 6, and F 4, in slots
  // 0 to 5.
  private void addSixJobs() {
    heap.add("A", 2);
    heap.add("B", 5);
    heap.add("C", 3);
    heap.add("D", 8);
    heap.add("E", 6);
    heap.add("F", 4);
  }

  @Test
  public void removedItemIsNoLongerContained() {
    addSixJobs();
    heap.remove("B");
    assertFalse(heap.contains("B"));
  }

  @Test
  public void removeClearsTheVacatedSlot() {
    addSixJobs();
    heap.remove("E");
    assertNull(entries()[5]);
  }

  @Test
  public void removeTheLastSlotLeavesTheRestInPlace() {
    addSixJobs();
    heap.remove("F");
    assertSlots(listOf("A", "B", "C", "D", "E"), 2, 5, 3, 8, 6);
    assertIndexMapMatchesHeap();
  }

  @Test
  public void removeWhereTheFillerStaysPut() {
    addSixJobs();
    heap.remove("B");
    assertSlots(listOf("A", "F", "C", "D", "E"), 2, 4, 3, 8, 6);
    assertIndexMapMatchesHeap();
  }

  @Test
  public void removeWhereTheFillerSwimsUp() {
    addSixJobs();
    heap.remove("E");
    assertSlots(listOf("A", "F", "C", "D", "B"), 2, 4, 3, 8, 5);
    assertIndexMapMatchesHeap();
  }

  @Test
  public void removeTheRootSinksTheFiller() {
    addSixJobs();
    heap.remove("A");
    assertSlots(listOf("C", "B", "F", "D", "E"), 3, 5, 4, 8, 6);
    assertIndexMapMatchesHeap();
  }

  @Test
  public void removeWhereTheFillerSinks() {
    heap.add("A", 1);
    heap.add("B", 2);
    heap.add("C", 9);
    heap.add("D", 3);
    heap.add("E", 4);
    heap.add("F", 10);
    heap.add("G", 11);
    heap.remove("B");
    assertSlots(listOf("A", "D", "C", "G", "E", "F"), 1, 3, 9, 11, 4, 10);
    assertIndexMapMatchesHeap();
  }

  @Test
  public void itemsLeftAfterRemoveServeInPriorityOrder() {
    addSixJobs();
    heap.remove("C");
    assertEquals(listOf("A", "F", "B", "E", "D"), drain());
  }

  @Test
  public void removeEveryItemEmptiesTheHeap() {
    addSixJobs();
    String[] order = {"C", "A", "F", "D", "B", "E"};
    for (String item : order) {
      heap.remove(item);
    }
    assertEquals(0, heap.size());
  }

  @Test
  public void removeNullThrows() {
    addSixJobs();
    try {
      heap.remove(null);
      fail("expected IllegalArgumentException when removing null");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void removeAbsentItemThrows() {
    addSixJobs();
    try {
      heap.remove("Z");
      fail("expected NoSuchElementException when the item is not in the queue");
    } catch (NoSuchElementException e) {
      return;
    }
  }

  // Removes every item, highest priority first, and returns them in that order.
  private List<String> drain() {
    List<String> served = new ArrayList<>();
    while (heap.size() > 0) {
      served.add(heap.peek());
      heap.remove();
    }
    return served;
  }

  private static List<String> listOf(String... items) {
    List<String> list = new ArrayList<>();
    for (String item : items) {
      list.add(item);
    }
    return list;
  }

  // Checks the items and priorities in the filled slots, in index order.
  private void assertSlots(List<String> items, int... priorities) {
    Object[] entries = entries();
    assertEquals(items.size(), heap.size());
    for (int i = 0; i < heap.size(); i++) {
      assertEquals(items.get(i), readField(entries[i], "item"));
      assertEquals(priorities[i], readField(entries[i], "priority"));
    }
  }

  // Checks that every item's map entry is the slot it occupies.
  @SuppressWarnings("unchecked")
  private void assertIndexMapMatchesHeap() {
    Object[] entries = entries();
    Map<String, Integer> index = (Map<String, Integer>) readField(heap, "index");
    assertEquals(heap.size(), index.size());
    for (int i = 0; i < heap.size(); i++) {
      assertEquals(Integer.valueOf(i), index.get((String) readField(entries[i], "item")));
    }
  }

  private Object[] entries() {
    return (Object[]) readField(heap, "heap");
  }

  // Reads a private field of an object by reflection.
  private static Object readField(Object object, String name) {
    try {
      Field field = object.getClass().getDeclaredField(name);
      field.setAccessible(true);
      return field.get(object);
    } catch (ReflectiveOperationException e) {
      throw new RuntimeException(e);
    }
  }
}

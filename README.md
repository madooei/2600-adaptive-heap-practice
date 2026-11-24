# The Adaptive Priority Queue — Practice

One practice problem: add a `remove(item)` operation to `IndexedArrayHeap` that takes out a given item wherever it sits. It has a JUnit suite and a demo.

## Prerequisites

- JDK 17+ (JUnit 6 requires it).
- The JUnit jar is already vendored in `lib/`; there is nothing to download.

## Repository layout

```plaintext
code/
  README.md
  .gitignore
  lib/
    junit-platform-console-standalone-6.1.0.jar
  src/
    main/
      adaptiveheap/
        AdaptivePriorityQueue.java      # the AdaptivePriorityQueue ADT
        IndexedArrayHeap.java           # the chapter's IndexedArrayHeap, plus remove(item)
      demo/                             # one demo per class you implement
        IndexedArrayHeapDemo.java
      map/
        Map.java                        # the Map ADT
        TreeMap.java                    # the AVL tree map from the TreeMap chapter
      stack/
        Stack.java                      # the Stack ADT, used by the map's iterator
        LinkedStack.java                # linked implementation of Stack
    test/
      practice/
        RemoveItemTest.java
  scripts/
    run.sh                              # compile and run one demo
    test.sh                             # compile and run the JUnit tests
```

## How to compile and run

- `scripts/test.sh` — compiles everything and runs the full JUnit suite.
- `scripts/test.sh practice.RemoveItemTest` — compiles everything and runs only that test class.
- `scripts/run.sh demo.IndexedArrayHeapDemo` — compiles everything and runs that one demo. Every class you implement has its own demo, `<Class>Demo`.

## Contents

- `adaptiveheap.IndexedArrayHeap<T, P>` — the chapter's `IndexedArrayHeap` with one more public method, `remove(T item)`. It looks the item up in the index map, moves the last entry into the gap, and compares the priority now at that slot with the removed item's priority, the way `changePriority` does: it swims if the new priority is smaller and sinks if it is larger.
- `adaptiveheap.AdaptivePriorityQueue<T, P>`, `map.Map<K, V>`, `map.TreeMap<K, V>`, `stack.Stack<T>`, and `stack.LinkedStack<T>` — copied unchanged from the chapter so this code compiles and runs on its own.
- `demo.IndexedArrayHeapDemo` — a demo of `remove(item)` on the example from the problem.
- `practice.RemoveItemTest` — tests for the problem. They read the heap array and the index map through reflection.

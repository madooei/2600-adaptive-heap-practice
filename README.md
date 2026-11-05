# The Adaptive Priority Queue — Practice

Two practice problems: add a `remove(item)` operation to `IndexedArrayHeap` that takes out a given item wherever it sits, and process a stream of scheduler events with an adaptive priority queue. Each problem has a JUnit suite and a demo.

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
      practice/
        RemoveItemDemo.java             # demo of remove(item)
        JobScheduler.java               # serve tasks whose priorities change
        JobSchedulerDemo.java           # demo of serveOrder
      map/
        Map.java                        # the Map ADT
        TreeMap.java                    # the AVL tree map from the TreeMap chapter
      stack/
        Stack.java                      # the Stack ADT, used by the map's iterator
        LinkedStack.java                # linked implementation of Stack
    test/
      practice/
        RemoveItemTest.java
        JobSchedulerTest.java
  scripts/
    run.sh                              # compile and run one demo
    test.sh                             # compile and run the JUnit tests
```

## How to compile and run

- `scripts/test.sh` — compiles everything and runs the full JUnit suite.
- `scripts/test.sh practice.JobSchedulerTest` — compiles everything and runs only that test class.
- `scripts/run.sh practice.JobSchedulerDemo` — compiles everything and runs that one demo. Each problem has its own demo, so you can run one before you start the other.

## What's here

- `adaptiveheap.IndexedArrayHeap<T, P>` — the chapter's `IndexedArrayHeap` with one more public method, `remove(T item)`. It looks the item up in the index map, moves the last entry into the gap, and restores the heap with `sink` and `swim`.
- `practice.JobScheduler` — `serveOrder`, which processes add, change, and serve events with one or two queue calls per event and returns the served task ids in order.
- `adaptiveheap.AdaptivePriorityQueue<T, P>`, `map.Map<K, V>`, `map.TreeMap<K, V>`, `stack.Stack<T>`, and `stack.LinkedStack<T>` — copied unchanged from the chapter so this code compiles and runs on its own.
- `practice.RemoveItemDemo`, `practice.JobSchedulerDemo` — one demo per problem, each running the example from the problem.
- `practice.RemoveItemTest` and `practice.JobSchedulerTest` — tests for the two problems. `RemoveItemTest` reads the heap array and the index map through reflection.

package com.github.jbellis.jvector.graph;

import java.util.NoSuchElementException;
import java.util.PrimitiveIterator;

/**
 * Iterator over the graph nodes on a certain level, Iterator also provides the size – the total
 * number of nodes to be iterated over. The nodes are NOT guaranteed to be presented in any
 * particular order.
 */
public abstract class NodesIterator implements PrimitiveIterator.OfInt {
    protected final int size;

    /**
     * Constructor for iterator based on the size
     */
    public NodesIterator(int size) {
        this.size = size;
    }

    /**
     * The number of elements in this iterator *
     */
    public int size() {
        return size;
    }

    public static class ArrayNodesIterator extends NodesIterator {
        private final int[] nodes;
        private int cur = 0;

        /** Constructor for iterator based on integer array representing nodes */
        public ArrayNodesIterator(int[] nodes, int size) {
            super(size);
            assert nodes != null;
            assert size <= nodes.length;
            this.nodes = nodes;
        }

        public ArrayNodesIterator(int[] nodes) {
            this(nodes, nodes.length);
        }

        @Override
        public int nextInt() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (nodes == null) {
                return cur++;
            } else {
                return nodes[cur++];
            }
        }

        @Override
        public boolean hasNext() {
            return cur < size;
        }
    }
}

package br.ufu.ai.app;

import br.ufu.ai.app.utils.BoardHelper;
import br.ufu.ai.app.utils.Helpers;

import java.util.Arrays;

public class Node implements Comparable<Node>{

    private int[] state;
    private Node parent;
    // cost
    int g;
    // heuristic
    int h;

    public Node(int[] state, Node parent, int h, int g) {
        this.state = state;
        this.parent = parent;
        this.h = h;
        this.g = g;
    }

    public Node(int[] state) {
        this.state = state;
    }

    public int[] getState() {
        return state;
    }

    public Node getParent() {
        return parent;
    }

    public int getG() {
        return g;
    }

    public int getH() {
        return h;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (h != node.h) return false;
        return Arrays.equals(state, node.state);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(state);
        result = 31 * result + h;
        return result;
    }

    @Override
    public int compareTo(Node o) {
        return h - o.h;
    }

    @Override
    public String toString() {
        Helpers.plot(this.state, "Node");
        return "\nNode{" +
                "state=" + Arrays.toString(state) +
                ", parent=" + parent +
                ", g=" + g +
                ", h=" + h +
                '}';
    }
}

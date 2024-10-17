import java.util.ArrayList;
import java.util.List;

class Node {
    double val;
    List<Node> children;

    public Node(double val) {
        this.val = val;
        this.children = new ArrayList<>();
    }

    public Node(double val, List<Node> children) {
        this.val = val;
        this.children = children;
    }
}

public class AlphaBetaPruning {

    private static final double MAX = Double.POSITIVE_INFINITY;
    private static final double MIN = Double.NEGATIVE_INFINITY;

    public static void main(String[] args) {
        Node root = buildTree();
        // Starting the Alpha-Beta pruning with the root node, as a maximizing node
        System.out.println("Optimal value: " + alphaBetaPruning(root, true, MIN, MAX));
    }

    private static double alphaBetaPruning(Node node, boolean isMaxNode, double alpha, double beta) {
        if (node.children.isEmpty()) {
            return node.val;  // Return the value of the leaf node
        }

        if (isMaxNode) {
            double best = MIN;
            for (Node child : node.children) {
                double value = alphaBetaPruning(child, false, alpha, beta);
                best = Math.max(best, value);
                alpha = Math.max(alpha, best);
                // Prune the branch
                if (alpha >= beta) {
                    System.out.println("Pruned node with value: " + child.val);
                    break;  // Exit the loop as further exploration is unnecessary
                }
            }
            return best;
        } else {
            double best = MAX;
            for (Node child : node.children) {
                double value = alphaBetaPruning(child, true, alpha, beta);
                best = Math.min(best, value);
                beta = Math.min(beta, best);
                // Prune the branch
                if (alpha >= beta) {
                    System.out.println("Pruned node with value: " + child.val);
                    break;  // Exit the loop as further exploration is unnecessary
                }
            }
            return best;
        }
    }

    private static Node buildTree() {
        Node x = new Node(0.88);
        Node y = new Node(-0.98);
        Node p1 = new Node(MAX, List.of(x, y));

        Node x1 = new Node(-8);
        Node x2 = new Node(-9);
        Node p2 = new Node(MAX, List.of(x1, p1, x2));

        Node x3 = new Node(0);
        Node x4 = new Node(9);
        Node p3 = new Node(MAX, List.of(x3, p2, x4));
        Node p4 = new Node(MAX, List.of(p3));

        Node y1 = new Node(-9);
        Node y2 = new Node(-8.9);
        Node t1 = new Node(MAX, List.of(y1, y2));
        Node y3 = new Node(7);
        Node t2 = new Node(MAX, List.of(y3));
        Node y4 = new Node(-0.009);
        Node t3 = new Node(MAX, List.of(t1, t2, y4));

        Node y5 = new Node(78);
        Node y6 = new Node(-7.8);
        Node t4 = new Node(MAX, List.of(y5, y6));
        Node t5 = new Node(MAX, List.of(t4));

        Node y7 = new Node(4);
        Node t6 = new Node(MAX, List.of(y7, t3, t5));

        Node y8 = new Node(6);
        Node y9 = new Node(-7);
        Node y10 = new Node(0.8);
        Node t7 = new Node(MAX, List.of(y8, y9, y10));

        Node y11 = new Node(-0.66);
        Node t8 = new Node(MAX, List.of(y11));

        Node t9 = new Node(MAX, List.of(t7, t8));
        Node t10 = new Node(MAX, List.of(t6, t9));

        Node z1 = new Node(67);
        Node z2 = new Node(-6.7);
        Node z3 = new Node(0.067);
        Node s1 = new Node(MAX, List.of(z1, z2, z3));

        Node z4 = new Node(0.66);
        Node s2 = new Node(MAX, List.of(z4, s1));

        Node z5 = new Node(99);
        Node z6 = new Node(9.9);
        Node s3 = new Node(MAX, List.of(z5, z6));

        Node z7 = new Node(-0.09);
        Node s4 = new Node(MAX, List.of(s3, z7));

        Node z8 = new Node(87);
        Node z9 = new Node(8.7);
        Node s5 = new Node(MAX, List.of(z8, z9));

        Node z10 = new Node(-99);
        Node s6 = new Node(MAX, List.of(s2, s4, s5, z10));

        return new Node(MAX, List.of(p4, t10, s6));  // Root node
    }
}

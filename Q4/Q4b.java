package Q4;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class Q4b {
    // Helper method to find the node, its parent, and depth
    private TreeNode[] findNodeAndParent(TreeNode node, int target, TreeNode parent, int depth) {
        if (node == null) {
            return null;
        }

        if (node.val == target) {
            return new TreeNode[] { node, parent, new TreeNode(depth) };
        }

        // Recursively search in the left subtree
        TreeNode[] leftResult = findNodeAndParent(node.left, target, node, depth + 1);
        if (leftResult != null) {
            return leftResult;
        }

        // Recursively search in the right subtree
        TreeNode[] rightResult = findNodeAndParent(node.right, target, node, depth + 1);
        if (rightResult != null) {
            return rightResult;
        }

        return null;
    }

    public boolean areBrothers(TreeNode root, int x, int y) {
        TreeNode[] xResult = findNodeAndParent(root, x, null, 0);
        TreeNode[] yResult = findNodeAndParent(root, y, null, 0);

        // Check if both nodes are found and have the same depth but different parents
        if (xResult != null && yResult != null) {
            return xResult[2].val == yResult[2].val && xResult[1] != yResult[1];
        }

        return false;
    }

    // Test case
    public static void main(String[] args) {
        Q4b tree = new Q4b();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);

        int x = 4;
        int y = 3;

        System.out.println(tree.areBrothers(root, x, y)); // Output: false
    }
}

// # Problem 1
// Binary Tree Level Order Traversal (https://leetcode.com/problems/binary-tree-level-order-traversal/)

// Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).

// Input: root = [3,9,20,null,null,15,7]
// Output: [[3],[9,20],[15,7]]

//Approach:
// As it is a BFS traversal we will use a queue data structure to store the nodes/
// We will first store the root and go to it's left and right and if they are not null then we will get the nodes in the queue of next level
// We will keep the size variable and then iterate a loop until that size.

// Time Complexity : O(n) -> as we are processing n nodes. Though we are using 2 loops while and for we will not consider it as 
// n square because the loop is just iterating for n nodes

//Space Complexity : O(n) -> the no of nodes in the queue will be atmost the leaf nodes
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root==null){
            return new ArrayList<>();
        }
        //Initialized Queue
        Queue<TreeNode> q=new LinkedList<>();
        //Initialized a list
        List<List<Integer>> result=new ArrayList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size=q.size();
            List<Integer> list = new ArrayList<>();
            for(int i=0;i<size;i++){
                TreeNode curr=q.poll();
                list.add(curr.val);
                if(curr.left!=null){
                    q.add(curr.left);
                }
                if(curr.right!=null){
                    q.add(curr.right);
                }
            }
            result.add(list);
            
        }
        return result;
    }
}

// Traversal Using DFS
// Time Complexity : O(n)
//Space Complexity : O(h)
class Solution {
    List<List<Integer>> result;
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root==null){
            return new ArrayList<>();
        }
        result=new ArrayList<>();
        dfs(root,0);
        return result;
    }
    private void dfs(TreeNode root, int level){
        if(root == null){
            return;
        }
        if(result.size()==level){
            result.add(new ArrayList<>());
        }
        result.get(level).add(root.val);

        dfs(root.left,level+1);
        dfs(root.right,level+1);

    }
}
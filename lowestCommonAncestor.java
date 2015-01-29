public TreeNode lowestCommonAncestor(TreeNode q, TreeNode p, TreeNode root){
	if(root == null || q == null || p == null) return null;
	if(q == p && (root.left == q || root.right == q)) return root;
	if(q.child == p) return q;
	if(p.child == q) return p;
	
	int left_num = numberInSubtree(root.left, p, q);
	if(left_num == 2){
		if(root.left == q || root.left == p) return root.left;
		return lowestCommonAncestor(root.left, q, p);
	}
	
	int right_num = numberInSubtree(root.right, p, q);
	if(right_num == 2 && left_num == 0){
		if(root.right == p || root.right == q) return root.right;
		return lowestCommonAncestor(root.right, q, p);
	}
	
	if(right_num == 1 && left_num == 1) return root;
	return null;
}

public int numberInSubtree(TreeNode root, TreeNode q, TreeNode p){
	if(root == null) return 0;
	int num = 0;
	if(root == q || root == p) num = 1;
	num += numberInSubtree(root.left, q, p);
	if(num == 2) return 2;
	num += numberInSubstree(root.right, q, p);
	return num;
}

// two nodes must exist in the tree
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
	if(root == null) return null;
	
	if(root == q || root == p) return root;
	
	TreeNode left_node = lowestCommonAncestor(root.left, p, q);
	TreeNode right_node = lowestCommonAncestor(root.right, p, q);
	
	if(left_node != null && right_node !=null) return root;
	if(left_node != null) return left_node;
	if(right_node != null) return right_node;
	return null;
}
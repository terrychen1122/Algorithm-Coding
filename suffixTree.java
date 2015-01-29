public class TreeNode(){
	int currentIndex;
	List<TreeNode> children;
	String s;
	
	
	public TreeNode(){
		s = "";
		currentIndex = 0;
		children = new ArrayList<TreeNode>;
	}
	
	public TreeNode(String suffix){
		s = suffix;
		currentIndex = 0;
		children = new ArrayList<TreeNode>;
	}
}

public TreeNode buildSUffixTree(String s)
{
	if(s == null || s.length()==0) return null;
	TreeNode root = new TreeNode();
	for(int i = 0; i < s.length(); i++){
		String c = s.substring(i, i+1);
		TreeNode node = new TreeNode(c);
		for( TreeNode child in root.children){
			push(child, c);
		}
	}
}

private void push(TreeNode node, String s){
	if(node.children.size() == 0){
		node.s = node.s + s;
		if(s.equals(node.s.substring(node.currentIndex, node.currentIndex+1))){
			node.currentIndex++;
		}else if(node.currentIndex > 0){
			TreeNode node1 = new TreeNode(node.s.substring(node.currentIndex, node.s.length()));
			TreeNode node2 = new TreeNode(c);
			node1.children = node.children;
			node.children = new ArrayList<TreeNode>;
			node.children.add(node1);
			node.children.add(node2);
		}
		return;
	}
	
	for(TreeNode t in node.children){
		push(t, s);
	}
}
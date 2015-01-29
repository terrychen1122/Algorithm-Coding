import java.util.Arrays;

public class Solution{
	public static void main(String[] args){
	
	}
	
	/*
	*	public data structure	
	*/
		
	public class ListNode{
		int val;
		ListNode next;
		ListNode (int x){ val = x; next = null;}
	}

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode (int x){ val = x; left = null; right = null;}
	}
	
	public class RandomListNode {
		int label;
		RandomListNode next, random;
		RandomListNode(int x){ this.label = x;}
	}
	
	public class UndirectedGraphNode {
		int label;
		List<UndirectedGraphNode> neighbors;
		UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
	}
	/*
	 *	Programming Problems Set
	*/
		
	// Find Minimum in Rotated Sorted Array
	public int findMin(int[] num) {
        int len = num.length;
        int startIndex = 0;
        int endIndex = len-1;
    
        while(startIndex < endIndex){
            if(endIndex - startIndex == 1) return Math.min(num[startIndex], num[endIndex]);
            int midIndex = (endIndex + startIndex)/2;
            int midValue = num[midIndex];
            if(midValue < num[midIndex-1]) return midValue;
        
            if(midValue < num[endIndex]){
                endIndex = midIndex-1;
            }else if(midValue > num[startIndex]){
                startIndex = midIndex+1;
            }
        }
    
        return num[startIndex];
    }
	
	// Maximum Product Subarray
	public int maxProduct(int[] A) {
        int len = A.length;
        int res = A[len-1];
        int[] positive = new int[len];
        int[] negative = new int[len];
        positive[len-1] = A[len-1]>0? A[len-1]:0;
        negative[len-1] = A[len-1]<0? A[len-1]:0;
    
        for(int i = len-2; i>=0; i--){
            if(A[i]>0){
                positive[i] = positive[i+1]>0? positive[i+1]*A[i] : A[i];
                negative[i] = negative[i+1]<0? negative[i+1]*A[i] : 0;
            }else{
                positive[i] = negative[i+1]<0? negative[i+1]*A[i] : 0;
                negative[i] = positive[i+1]>0? positive[i+1]*A[i] : A[i];
            }
            if(positive[i]>res){
                res = positive[i];
            }
        }
        return res;
    }
	
	// Reverse Words in a String
	public String reverseWords(String s) {
        String res = "";
        int end = s.length();
        boolean isStored = false;
        for(int i = s.length()-1; i>=0; i--){
            if(s.substring(i, i+1).equals(" ")){
                if(i+1<end){
                    res = res + s.substring(i+1, end) + " ";
                }
                end = i;
            }
        }
        res += s.substring(0, end);
        int j = res.length();
        for(int i = res.length()-1; i>=0; i--){
            if(res.substring(i, i+1).equals(" ")){
                j--;
            }else{
                break;
            }
        }
        return j < 0 ? "" : res.substring(0, j);
    }
	
	// Evaluate Reverse Polish Notation
	public int evalRPN(String[] tokens) {
        List<Integer> stack = new ArrayList<Integer>();
        for(int i = 0; i < tokens.length; i++){
            String s = tokens[i];
            if(s.equals("+") || s.equals("*") || s.equals("/") || s.equals("-")){
                if(stack.size()>=2){
                    int num1 = stack.remove(stack.size()-1);
                    int num2 = stack.remove(stack.size()-1);
                    int res = 0;
                    if(s.equals("+")) res = num1 + num2;
                    if(s.equals("-")) res = num2 - num1;
                    if(s.equals("*")) res = num1 * num2;
                    if(s.equals("/")) res = num2 / num1;
                    stack.add(res);
                }
            }else{
                int push_num = Integer.parseInt(tokens[i]);
                stack.add(push_num);
            }
        }
        if(stack.isEmpty()) return 0;
        return stack.remove(stack.size()-1);
    }
	
	// Sort List
	public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode fast = head;
        ListNode slow = head;
        ListNode pre = null;
        while(fast!=null && fast.next!=null){
            fast = fast.next.next;
            pre = slow;
            slow = slow.next;
        }
        pre.next = null;
        ListNode leftSort = sortList(head);
        ListNode rightSort = sortList(slow);
    
        ListNode root = leftSort;
        ListNode leftPreNode = null;
    
        while(leftSort!=null && rightSort!=null){
            if(leftSort.val <= rightSort.val){
                leftPreNode = leftSort;
                leftSort = leftSort.next;
            }else{
                if(leftPreNode==null){
                    ListNode tmp = rightSort;
                    rightSort = rightSort.next;
                    root = tmp;
                    tmp.next = leftSort;
                    leftPreNode = root;
                }else{
                    leftPreNode.next = rightSort;
                    rightSort = rightSort.next;
                    leftPreNode.next.next = leftSort;
                    leftPreNode = leftPreNode.next;
                }
            }
        }
    
        if(leftSort==null) leftPreNode.next = rightSort;
    
        return root;
    }
	
	// Insertion Sort List	
	public ListNode insertionSortList(ListNode head){
		ListNode traverse = head;
        ListNode pre_node = null;

        while(traverse!=null){
            ListNode traverse_sorted = head;
            ListNode pre_node_sorted = null;
            while(traverse_sorted!=traverse){
                if(traverse.val < traverse_sorted.val){
                    ListNode temp = traverse;
                    pre_node.next = traverse.next;
                    traverse = pre_node;
                    if(pre_node_sorted==null){
                        head = temp;
                    }else{
                        pre_node_sorted.next = temp;
                    }
                    temp.next = traverse_sorted;
                    break;
                }else{
                    pre_node_sorted = traverse_sorted;
                    traverse_sorted = traverse_sorted.next;
                }
            }
            pre_node = traverse;
            traverse = traverse.next;
        }

        return head;
	}
	
	// LRU Cache
	public class LRUCache {
    
	    static class Node {
	        Node next;
	        Node prev;
	        int val;
	        int key;
        
	        Node(){};
	        Node(int key, int value){
	            this.val = value;
	            this.key = key;
	            this.next = null;
	            this.prev = null;
	        }
	    }
    
	    private int capacity;
	    private Map<Integer, Node> cache;
    
	    Node head = new Node();
	    Node tail = head;
    
	    public LRUCache(int capacity) {
	        this.capacity = capacity;
	        this.cache = new HashMap<Integer, Node>();
	    }
    
	    public int get(int key) {
	        if(this.cache.containsKey(key)){
	            Node n = this.cache.get(key);
	            if(n==null) return -1;
	            int value = n.val;
	            moveToEnd(n);
	            return value;
	        }else{
	            return -1;
	        }
	    }
    
	    public void set(int key, int value) {
	        if(!this.cache.containsKey(key)){
	            if(this.cache.size()>=this.capacity){
	                if(this.head!=null&&this.head.next!=null){
	                    this.cache.remove(this.head.next.key);
	                    Node tmp = this.head.next;
	                    this.head.next = tmp.next;
	                    if(tmp.next!=null) tmp.next.prev = tmp.prev;
	                    tmp = null;
	                }
	            }
	            Node node = new Node(key, value);
	            addToEnd(node);
	            this.cache.put(key, this.tail);
	        }else{
	            Node node = this.cache.get(key);
	            node.val = value;
	            moveToEnd(node);
	        }
	    }
    
	    private void moveToEnd(Node n){
	        if(n==tail) return;
	        Node node = delete(n);
	        addToEnd(node);
	    }
    
	    private void addToEnd(Node n){
	        n.prev = this.tail;
	        this.tail.next = n;
	        this.tail = n;
	    }
    
	    private Node delete(Node n){
	        n.prev.next = n.next;
	        if(n.next!=null) n.next.prev = n.prev;
	        n.next = null;
	        return n;
	    }
	}
	
	// Binary Tree Postorder Traversal
	public List<Integer> postorderTraversal(TreeNode root) {
	    List<Integer> postorder_list = new ArrayList<Integer>();
	    if(root==null){ return postorder_list; }

	    List<TreeNode> queue = new ArrayList<TreeNode>();
	    if(root.left!=null){ queue.add(root.left); }
	    if(root.right!=null){ queue.add(root.right); }
	    queue.add(new TreeNode(root.val));
	    while(!queue.isEmpty()){
	        TreeNode node = queue.remove(0);
	        if(node.left==null&&node.right==null){
	            postorder_list.add(node.val);
	        }else{
	            queue.add(0, new TreeNode(node.val));
	            if(node.right!=null){ queue.add(0, node.right);}
	            if(node.left!=null){ queue.add(0, node.left);}
	        }
	    }
	    return postorder_list;
	}
	
	// Binary Tree Preorder Traversal
	public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        List<TreeNode> stack = new ArrayList<TreeNode>();
        TreeNode traverse = root;
        while(traverse!=null){
            list.add(traverse.val);
            if(traverse.right!=null){
                stack.add(traverse.right);
            }
            if(traverse.left!=null){
                stack.add(traverse.left);
            }
            int length = stack.size();
            if(length == 0) { break; }
            traverse = stack.remove(length-1);
        }
    
        return list;
    }
	
	// Reorder List
	public void reorderList(ListNode head) {
        if(head == null) return ;
        ListNode fast = head;
        ListNode slow = head;
    
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
    
        ListNode halfHead = slow.next;
        ListNode traverse = halfHead;
        slow.next = null;
    
        while(traverse!=null&&traverse.next!=null){
            if(traverse == halfHead){
                halfHead = traverse.next;
                traverse.next = halfHead.next;
                halfHead.next = traverse;
            }else{
                ListNode tmp = halfHead;
                halfHead = traverse.next;
                traverse.next = halfHead.next;
                halfHead.next = tmp;
            }
        }
    
        traverse = head;
        while(halfHead!=null){
            ListNode tmp = halfHead;
            halfHead = tmp.next;
            tmp.next = traverse.next;
            traverse.next = tmp;
            traverse = traverse.next.next;
        }
    }
	
	// Linked List Cycle II
	public ListNode detectCycle(ListNode head) {
        if(head==null){
    		return null;
    	}
    	boolean isCycle = false;
	
    	ListNode point1 = head;
    	ListNode point2 = head;
    	while(point1!=null&&point2!=null)
    	{
    		point1 = point1.next;
    		point2 = point2.next;
    		if(point2 == null){ return null; }
    		point2 = point2.next;
    		if(point1 == point2) { isCycle = true;  break;}
    	}
	
    	if(!isCycle) { return null;}
	
    	point1 = head;
    	while(point1!=point2){
    	    point1 = point1.next;
    	    point2 = point2.next;
    	}
	
    	return point1;
    }
	
	// Linked List Cycle
	public boolean hasCycle(ListNode head) {
        if(head==null){
    		return false;
    	}
	
    	ListNode point1 = head;
    	ListNode point2 = point1.next;
    	if(point2==null){ return false; }
    	point2 = point2.next;
    	while(point1!=null)
    	{
    		if(point2==null){ return false; }
    		if(point1 == point2) { return true; }
		
    		point1 = point1.next;
    		point2 = point2.next;
    		if(point2 == null){ return false; }
    		point2 = point2.next;
    	}
	
    	return false;
    }
	
	// Word Break
	public boolean wordBreak(String s, Set<String> dict) {
        int len = s.length();
        boolean[] checker = new boolean[len+1];
        checker[0] = true;
        for(int i = 0; i<len; i++){
            if(!checker[i]) continue;
        
            for(String d : dict){
                int d_len = d.length();
                int end = d_len + i;
                if(end > len) continue;
                if(checker[end]) continue;
                if(s.substring(i, end).equals(d)){
                    checker[end] = true;
                }
            }
        }
        return checker[len];
    }
	
	// Copy List with Random Pointer
	public RandomListNode copyRandomList(RandomListNode head) {
        if(head==null){ return null; }
    
        RandomListNode traverse = head;
        while(traverse!= null){
            RandomListNode node = new RandomListNode(traverse.label);
            node.next = traverse.next;
            traverse.next = node;
            traverse = traverse.next.next;
        }
    
        traverse = head;
        RandomListNode copy_node = traverse.next;
        while(traverse != null){
            copy_node.random = (traverse.random == null)? null: traverse.random.next;
            traverse = traverse.next.next;
            if(copy_node.next!=null){
                copy_node = copy_node.next.next;
            }
        }
    
        traverse = head;
        copy_node = head.next;
        RandomListNode result = copy_node;
        while(copy_node!=null&&traverse!=null){
            traverse.next = traverse.next.next;
            if(copy_node.next!= null){
                copy_node.next = copy_node.next.next;
            }
            copy_node = copy_node.next;
            traverse = traverse.next;
        }
        return result;
    }
	
	// Single Number II
	public int singleNumber(int[] A) {
        int ones = 0;
        int twos = 0;
        for(int i = 0; i < A.length; i++){
            ones = (ones ^ A[i]) & ~twos;
            twos = (twos ^ A[i]) & ~ones;
        }
        return ones;
    }
	
	// Single Number 
	public int singleNumber(int[] A) {
       int result = A[0];
       int n = A.length;
       for(int i = 1; i < n ; i++){
           result = result ^ A[i];
       }
       return result;
    }
	
	// Gas Station
	public int canCompleteCircuit(int[] gas, int[] cost) {
        int start_index = -1;
        for(int i = 0; i < gas.length; i++){
            int j = i+1;
            int current_gas = 0;
            int loop = gas.length;
            start_index = i;
            while(loop>0){
                int current_index = (j-1<0)? gas.length-1: j-1;
                if(j >= gas.length){ j = 0; }
                current_gas = current_gas + gas[current_index];
                if(current_gas < cost[current_index]){
                    if(j==i){
                        return -1;
                    }
                    i = current_index;
                    start_index = -1;
                    break;
                }else{
                    current_gas = current_gas - cost[current_index];
                }
                j++;
                loop--;
            }
            if(start_index!=-1){
                return start_index;
            }
        }
    
        return start_index;
    }
	
	// Clone Graph
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null) return null;
    
        List<UndirectedGraphNode> queue = new ArrayList<UndirectedGraphNode>();
        HashMap<Integer, UndirectedGraphNode> hash = new HashMap<Integer, UndirectedGraphNode>();
        hash.put(node.label, new UndirectedGraphNode(node.label));
    
        queue.add(node);
        while(!queue.isEmpty()){
            UndirectedGraphNode vertex = queue.remove(0);
            for(UndirectedGraphNode neighbor : vertex.neighbors){
                if(!hash.containsKey(neighbor.label)){
                    queue.add(neighbor);
                    hash.put(neighbor.label, new UndirectedGraphNode(neighbor.label));
                }
                hash.get(vertex.label).neighbors.add(hash.get(neighbor.label));
            }
        }
    
        return hash.get(node.label);
    }
	
	// Palindrome Partitioning
	public List<List<String>> partition(String s) {
        List<List<String>> sol = new ArrayList<List<String>>();
        List<String> arr = new ArrayList<String>();
        partition_helper(s, sol, arr);
        return sol;
	}
   	public void partition_helper(String s, List<List<String>> sol, List<String> arr){
        if(s.length() == 0){
            if(!arr.isEmpty()){
                List<String> new_arr = new ArrayList<String>(arr);
                sol.add(new_arr);
            }
            return;
        }
        
        if(s.length() == 1){
            List<String> new_arr = new ArrayList<String>(arr);
            new_arr.add(s);
            sol.add(new_arr);
            return;
        }
    
        for(int i = 1; i<=s.length(); i++){
            String sub = s.substring(0, i);
            if(isPalindrome(sub)){
                arr.add(sub);
                partition_helper(s.substring(i, s.length()), sol, arr);
                arr.remove(arr.size()-1);
            }
        }
    }
	public boolean isPalindrome(String s){
        int start = 0;
        int end = s.length()-1;
        while(start <= end){
            char start_char = s.charAt(start);
            char tail_char = s.charAt(end);
            if(start_char!=tail_char){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
	
	// Surrounded Regions
	public void solve(char[][] board) {
        int row = board.length;
        if(row == 0) return;
        int col = board[0].length;
        if(col == 0) return;
    
        int[][] visited = new int[row][col];
    
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(board[i][j] == 'X'){
                    visited[i][j] = 1;
                }else{
                    if(visited[i][j] == 1) continue;
                    bfs(i, j, board, visited);
                }
            }
        }
    }
    public void bfs(int i, int j, char[][] board, int[][] visited){
        List<Integer> coordinates = new ArrayList<Integer>();
        coordinates.add(i);
        coordinates.add(j);
    
        List<List<Integer>> queue = new ArrayList<List<Integer>>();
        List<List<Integer>> zeros = new ArrayList<List<Integer>>();
        boolean isSurrounded = true;
        queue.add(coordinates);
        visited[i][j] = 1;
        while(!queue.isEmpty()){
            List<Integer> xy = queue.remove(0);
            int row = xy.get(0);
            int col = xy.get(1);
            if(isSurrounded == true){
                if(row <= 0 || row >= board.length-1 || col <= 0 || col >= board[0].length-1){
                    isSurrounded = false;
                }
            }
            zeros.add(xy);
            // up
            if(row - 1 >= 0 && visited[row-1][col] == 0 && board[row-1][col] == 'O'){
                List<Integer> coordinate = new ArrayList<Integer>();
                coordinate.add(row-1);
                coordinate.add(col);
                queue.add(coordinate);
                visited[row-1][col] = 1;
            }
            // right
            if(col + 1 <= board[0].length-1 && visited[row][col+1] == 0 && board[row][col+1] == 'O'){
                List<Integer> coordinate = new ArrayList<Integer>();
                coordinate.add(row);
                coordinate.add(col+1);
                queue.add(coordinate);
                visited[row][col+1] = 1;
            }
            // down
            if(row + 1 <= board.length-1 && visited[row+1][col] == 0 && board[row+1][col] == 'O'){
                List<Integer> coordinate = new ArrayList<Integer>();
                coordinate.add(row+1);
                coordinate.add(col);
                queue.add(coordinate);
                visited[row+1][col] = 1;
            }
            // left
            if(col - 1 >= 0 && visited[row][col-1] == 0 && board[row][col-1] == 'O'){
                List<Integer> coordinate = new ArrayList<Integer>();
                coordinate.add(row);
                coordinate.add(col-1);
                queue.add(coordinate);
                visited[row][col-1] = 1;
            }
        }
        if(isSurrounded){
            for(List<Integer> xy : zeros){
                int x = xy.get(0);
                int y = xy.get(1);
                board[x][y] = 'X';
            }
        }
    }
	``
	// Sum Root to Leaf Numbers
	public int sumNumbers(TreeNode root) {
        return sumNumbers_helper(root, 0);
    }
    public int sumNumbers_helper(TreeNode root, int num){
        if(root==null){
            return 0;
        }
        num = num * 10 + root.val;
    
        if(root.left==null&&root.right==null){
            return num;
        }
        return sumNumbers_helper(root.left, num) + sumNumbers_helper(root.right, num);
    }
	
	// Word Ladder
	public int ladderLength(String start, String end, Set<String> dict) {
        int length = 1;
        List<String> queue = new ArrayList<String>();
        queue.add(start);
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size>0){
                String word = queue.remove(0);
                for(int i = 0; i<word.length(); i++){
                    char[] charWord = word.toCharArray();
                    for(char c = 'a'; c<='z'; c++){
                        charWord[i] = c;
                        String temp = new String(charWord);
                        if(temp.equals(end)) return length+1;
                        if(dict.contains(temp)){
                            queue.add(temp);
                            dict.remove(temp);
                        }
                    }
                }
                size--;
            }
            length++;
        }
        return 0;
    }
	
	// Valid Palindrome
	public boolean isPalindrome(String s) {
        int start = 0;
        int end = s.length()-1;
        boolean skip = false;
        while(start < end){
            if(!isAplhanumeric(s.charAt(start))){
                start++;
                skip = true;
            }
            if(!isAplhanumeric(s.charAt(end))){
                end--;
                skip = true;
            }
            if(skip){ skip = false; continue; }
            if(toLowerCase(s.charAt(start))!=toLowerCase(s.charAt(end))) return false;
            start++;
            end--;
        }
        return true;
    }
    public int toLowerCase(char c){
        if(c-'A'>=0&&c-'Z'<=0){
            return c + 'a' - 'A';
        }
        return c;
    }
    public boolean isAplhanumeric(char c){
        if(c-'0'<0) return false;
        if(c-'9'>0 && c-'A'<0) return false;
        if(c-'Z'>0 && c-'a'<0) return false;
        if(c-'z'>0) return false;
    
        return true;
    }
	
	// Best Time to Buy and Sell Stock II
	public int maxProfit(int[] prices) {
        if(prices.length == 0){ return 0;}
        int profit = 0;
        int buy = prices[0];
        boolean isSold = false;
        for(int i = 1; i<prices.length; i++){
            if(isSold){
                buy = prices[i];
                isSold = false;
            }else{
                if(prices[i]<=buy){
                    buy = prices[i];
                }else{
                    int current_sell = prices[i];
                    int j = i+1;
                    while(j<prices.length){
                        if(prices[j]>=current_sell){
                            current_sell = prices[j];
                            j++;
                        }else{
                            break;
                        }
                    }
                    i = j -1;
                    profit = profit + current_sell - buy;
                    isSold = true;
                }
            }
        }
        return profit;
    }
	
	// Best Time to Buy and Sell Stock
	public int maxProfit(int[] prices) {
        int max = 0;
        int n = prices.length;
    
        if(n<2){ return 0;}
    
        int buy = prices[0];
    
        for(int i = 1; i<n; i++){
            if(prices[i]>buy){
                int profit = prices[i] - buy;
                if(profit > max){
                max = profit;
                }
            } else {
                buy = prices[i];
            }
        }
        return max;
    }
	
	// Triangle
	public int minimumTotal(List<List<Integer>> triangle) {
        int[] minPath = new int[triangle.size()];
        for(int i = 0; i < triangle.size(); i++){
            minPath[i] = triangle.get(triangle.size()-1).get(i);
        }
    
        for(int i = triangle.size()-2; i>=0; i--){
            for(int j = 0; j<=i; j++){
                minPath[j] = Math.min(minPath[j], minPath[j+1]) + triangle.get(i).get(j);
            }
        }
    
        return minPath[0];
    }
	
	// Pascal's Triangle II
	public List<Integer> getRow(int rowIndex) {
        if(rowIndex<2){
            List<Integer> row = new ArrayList<Integer>();
            row.add(1);
            if(rowIndex==1){ row.add(1); }
            return row;
        }
    
        List<Integer> current_row = new ArrayList<Integer>();
        List<Integer> pre_row = getRow(rowIndex-1);
    
        for(int i = 0; i <= rowIndex; i++){
            if(i==0||i==rowIndex){
                current_row.add(1);
            }else{
                current_row.add(pre_row.get(i-1)+pre_row.get(i));
            }
        }
    
        return current_row;
    }
	
	// Pascal's Triangle
	public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> p_row = new ArrayList<List<Integer>>();
        for(int i = 0; i < numRows; i++){
            List<Integer> row = new ArrayList<Integer>();
            for(int j = 0; j < i+1; j++){
                if(j==0||j==i){
                    row.add(1);
                }else{
                   List<Integer> l_row = p_row.get(i-1);
                   int num = l_row.get(j-1) + l_row.get(j);
                   row.add(num);
                }
            }
            p_row.add(row);
        }
        return p_row;  
    }
	
	// Populating Next Right Pointers in Each Node II
	public void connect(TreeLinkNode root) {
        List<TreeLinkNode> queue = new ArrayList<TreeLinkNode>();
        if(root==null) { return; }
    
        queue.add(root);
        while(!queue.isEmpty()){
            int n = queue.size();
            int i = 0;
            while(i<n){
                TreeLinkNode node = queue.remove(0);
                if(node.left!=null) { queue.add(node.left); }
                if(node.right!=null) { queue.add(node.right); }
                i++;
            }
            n = queue.size();
            i = 0;
            while(i<n){
                if(i<n-1){
                    TreeLinkNode node_h = queue.get(i);
                    TreeLinkNode node_t = queue.get(i+1);
                    node_h.next = node_t;
                }else{
                    TreeLinkNode node = queue.get(i);
                    node.next = null;
                }
                i++;
            }
        }
    }
	
	// Populating Next Right Pointers in Each Node
	public void connect(TreeLinkNode root) {
        if(root == null) return;
    
        if(root.left != null){
            root.left.next = root.right;
            if(root.next!=null){
                root.right.next = root.next.left;
            }
        }
    
        connect(root.left);
        connect(root.right);
    }
	
	// Flatten Binary Tree to Linked List
	public void flatten(TreeNode root) {
        if(root == null){ return; }
        TreeNode leaf = null;
    
        List<TreeNode> stack = new ArrayList<TreeNode>();
        stack.add(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.remove(stack.size()-1);
            if(node.right!=null){ stack.add(node.right); }
            if(node.left!=null){ stack.add(node.left); }
        
            if(leaf == null){
                leaf = node;
            }else{
                leaf.right = node;
                leaf = leaf.right;
            }
            leaf.left = null;
        }
    }
	
	// Path Sum II 
	List<List<Integer>> sol_pathSum = new ArrayList<List<Integer>>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        pathSum_helper(root, sum, new ArrayList<Integer>());
        return sol_pathSum;
    }
    public void pathSum_helper(TreeNode root, int sum, List<Integer> path){
        if(root==null){ return;}
        path.add(root.val);
    
        if(root.val==sum && root.left==null && root.right==null){
            List<Integer> cp_path = new ArrayList<Integer>(path);
            sol_pathSum.add(cp_path);
            return;
        }
    	
        if(root.left!=null){
        	pathSum_helper(root.left, sum-root.val, path);
        	path.remove(path.size()-1);
        }
    
        if(root.right!=null){
        	pathSum_helper(root.right, sum-root.val, path);
        	path.remove(path.size()-1);
        }
    }
	
	// Path Sum
	public boolean hasPathSum(TreeNode root, int sum) {
        if(root==null){
            return false;
        }
    
        if(root.left==null&&root.right==null){
            return (sum==root.val)? true:false;
        }else if(root.left==null){
            return hasPathSum(root.right, sum-root.val);
        }else if(root.right==null){
            return hasPathSum(root.left, sum-root.val);
        }else{
            return hasPathSum(root.right, sum-root.val) || hasPathSum(root.left, sum-root.val);
        }
    }
	
	// Minimum Depth of Binary Tree
	public int minDepth(TreeNode root) {
        if(root==null){ return 0;}
    
        if(root.left==null&&root.right==null){
            return 1;
        }else if(root.left==null){
            return minDepth(root.right)+1;
        }else if(root.right==null){
            return minDepth(root.left)+1;
        }else{
            return Math.min(minDepth(root.left), minDepth(root.right))+1;
        }
    }
	
	// Balanced Binary Tree
	public boolean isBalanced(TreeNode root) {
	    Wrapper balance_o = new Wrapper(true);
	    isBalanced_helper(root, balance_o);
	    return balance_o.balance;
	}
	public int isBalanced_helper(TreeNode root, Wrapper o){
	    if(root==null){ return 0;}
	    if(!o.balance){ return 0;}

	    int lh = isBalanced_helper(root.left, o);
	    int rh = isBalanced_helper(root.right, o);

	    if(!o.balance){ return 0;}

	    o.balance = Math.abs(lh-rh) > 1? false: true;

	    return Math.max(lh, rh)+1;
	}
	public class Wrapper {
	    boolean balance;
	    Wrapper(boolean a) { balance = a;}
	}
	
	// Convert Sorted List to Binary Search Tree
	public TreeNode sortedListToBST(ListNode head) {
    
        // to List
        // ListNode traverse = head;
        // List<Integer> list = new ArrayList<Integer>();
        // while(traverse!=null){
        //     list.add(traverse.val);
        //     traverse = traverse.next;
        // }
    
        // return sortedListToBST_array(list, 0, list.size()-1);
    
        // 
        if(head == null){ return null; }
        if(head.next == null){ return new TreeNode(head.val); }
        ListNode step1 = head;
        ListNode step2 = head.next;
        while(step2.next!=null && step2.next.next!=null){
            step1 = step1.next;
            step2 = step2.next.next;
        }
        TreeNode root = new TreeNode(step1.next.val);
        ListNode head2 = step1.next.next;
        step1.next = null;
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(head2);
    
        return root;
    
    }
    public TreeNode sortedListToBST_array( List<Integer> list, int start, int end){
        if(start > end){ return null; }
    
        int mid = (end+start)/2;
        TreeNode root = new TreeNode(list.get(mid));
    
        root.left = sortedListToBST_array(list, start, mid-1);
        root.right = sortedListToBST_array(list, mid+1, end);
    
        return root;
    }
	
	// Convert Sorted Array to Binary Search Tree
	public TreeNode sortedArrayToBST(int[] num) {
        if(num.length==0){ return null;}
        if(num.length==1){
            return new TreeNode(num[0]);
        }
        if(num.length==2){
            TreeNode root = new TreeNode(num[0]);
            TreeNode leaf = new TreeNode(num[1]);
            root.right = leaf;
            return root;
        }
        if(num.length==3){
            TreeNode root = new TreeNode(num[1]);
            TreeNode l_leaf = new TreeNode(num[0]);
            TreeNode r_leaf = new TreeNode(num[2]);
            root.right = r_leaf;
            root.left = l_leaf;
            return root;
        }
    
        int root_val = num.length/2;
    
        int[] f_half = new int[root_val];
        int[] s_half = new int[num.length-root_val-1];
        for(int i=0; i<f_half.length; i++){
            f_half[i] = num[i];
        }
        for(int i=0; i<s_half.length; i++){
            s_half[i] = num[root_val+i+1];
        }
    
        TreeNode root = new TreeNode(num[root_val]);
        TreeNode l_node = sortedArrayToBST(f_half);
        TreeNode r_node = sortedArrayToBST(s_half);
        root.left = l_node;
        root.right = r_node;
    
        return root;
    }
	
	// Binary Tree Level Order Traversal II
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> sol = new ArrayList<List<Integer>>();
    
        List<TreeNode> queue = new ArrayList<TreeNode>();
        if(root==null){ return sol;}
    
        queue.add(root);
        while(!queue.isEmpty()){
            List<Integer> level_list = new ArrayList<Integer>();
            int n = queue.size();
            while(n>0){
                TreeNode visit = queue.remove(0);
                level_list.add(visit.val);
                if(visit.left!=null){ queue.add(visit.left);}
                if(visit.right!=null){ queue.add(visit.right);}
                n--;
            }
            sol.add(0, level_list);
        }
    
        return sol;
    }
	
	// Construct Binary Tree from Inorder and Postorder Traversal
	public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder.length==0||postorder.length==0){ return null; }
        // inorder = left, root, right
        // postorder = left, right, root
        int n = inorder.length;
        int root_val = postorder[n-1];
        int root_index_inorder = 0;
        for(int i=0; i<n; i++){
            if(inorder[i] == root_val){
                root_index_inorder = i;
                break;
            }
        }
    
        int[] inorder_left = new int[root_index_inorder];
        int[] inorder_right = new int[n-root_index_inorder-1];
        int[] postorder_left = new int[root_index_inorder];
        int[] postorder_right = new int[n-root_index_inorder-1];
    
        for(int i = 0; i<inorder_left.length; i++){
            inorder_left[i] = inorder[i];
            postorder_left[i] = postorder[i];
        }
        for(int i = root_index_inorder+1; i<n; i++){
            inorder_right[i-root_index_inorder-1] = inorder[i];
            postorder_right[i-root_index_inorder-1] = postorder[i-1];
        }
    
        TreeNode root = new TreeNode(root_val);
        root.left = buildTree(inorder_left, postorder_left);
        root.right = buildTree(inorder_right, postorder_right);
        return root;
    }
	
	// Construct Binary Tree from Preorder and Inorder Traversal
	public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length==0||inorder.length==0){ return null; }
        // preorder: root, left ,right
        // inorder: left, root, right
        int n = preorder.length;
        int root_val = preorder[0];
        int root_index = 0;
        for(int i=0; i<n; i++){
            if(inorder[i]==root_val){
                root_index = i;
                break;
            }
        }
    
        int[] inorder_left = new int[root_index];
        int[] preorder_left = new int[root_index];
        int[] inorder_right = new int[n-root_index-1];
        int[] preorder_right = new int[n-root_index-1];
    
        for(int i = 0; i<inorder_left.length; i++){
            inorder_left[i] = inorder[i];
            preorder_left[i] = preorder[i+1];
        }
        for(int i = 0; i<inorder_right.length; i++){
            inorder_right[i] = inorder[i+root_index+1];
            preorder_right[i] = preorder[i+root_index+1];
        }
    
        TreeNode root = new TreeNode(root_val);
        root.left = buildTree(preorder_left, inorder_left);
        root.right = buildTree(preorder_right, inorder_right);
    
        return root;
    }
	
	// Maximum Depth of Binary Tree
	public int maxDepth(TreeNode root) {
        if(root==null){ return 0;}
    
        return Math.max(maxDepth(root.left)+1, maxDepth(root.right)+1);
    }
	
	// Binary Tree Zigzag Level Order Traversal
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> sol = new ArrayList<List<Integer>>();
        if(root==null){ return sol; }
    
        List<TreeNode> stack1 = new ArrayList<TreeNode>();
        List<TreeNode> stack2 = new ArrayList<TreeNode>();
    
        stack1.add(root);
        while(!stack1.isEmpty() || !stack2.isEmpty()){
            if(!stack1.isEmpty()){
                List<Integer> row = new ArrayList<Integer>();
                for(int i = 0; i<stack1.size(); i++){
                    TreeNode node = stack1.get(stack1.size()-i-1);
                    row.add(node.val);
                    if(node.left!=null){
                        stack2.add(node.left);
                    }
                    if(node.right!=null){
                        stack2.add(node.right);
                    }
                }
                stack1.clear();
                sol.add(row);
                continue;
            }
        
            if(!stack2.isEmpty()){
                List<Integer> row = new ArrayList<Integer>();
                for(int i = 0; i<stack2.size(); i++){
                    TreeNode node = stack2.get(stack2.size()-i-1);
                    row.add(node.val);
                    if(node.right!=null){
                        stack1.add(node.right);
                    }
                    if(node.left!=null){
                        stack1.add(node.left);
                    }
                }
                stack2.clear();
                sol.add(row);
                continue;
            }
        }
        return sol;
    }
	
	// Binary Tree Level Order Traversal
	public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> order_list = new ArrayList<List<Integer>>();
    
        List<TreeNode> queue = new ArrayList<TreeNode>();
        if(root==null){ return order_list; }
    
        queue.add(root);
        while(!queue.isEmpty()){
            List<Integer> level_l = new ArrayList<Integer>();
            int len = queue.size();
            while(len>0){
                TreeNode node = queue.remove(0);
                level_l.add(node.val);
                if(node.left!=null){ queue.add(node.left); }
                if(node.right!=null){ queue.add(node.right);}
                len--;
            }
            order_list.add(level_l);
        }
    
        return order_list;
    }
	
	// Symmetric Tree
	public boolean isSymmetric(TreeNode root){
        // recursive
		// public boolean isSymmetric(TreeNode root) {
// 	        if(root==null)return true;
// 	        return isMirror(root.left, root.right);
// 	    }
//
// 	    public boolean isMirror(TreeNode node1, TreeNode node2){
// 	        if(node1 == null && node2 ==null) return true;
// 	        if(node1 == null || node2 == null) return false;
//
// 	        if(node1.val != node2.val) return false;
//
// 	        return isMirror(node1.left, node2.right)&&isMirror(node1.right, node2.left);
// 	    }
    
        // iterative
        if(root==null){ return true;}
        if(root.left==null && root.right==null){ return true;}
        if(root.left==null || root.right==null){ return false;}
    
        List<TreeNode> left_tree = new ArrayList<TreeNode>();
        List<TreeNode> right_tree = new ArrayList<TreeNode>();
    
        left_tree.add(root.left);
        right_tree.add(root.right);
        while(!left_tree.isEmpty()&&!right_tree.isEmpty()){
            TreeNode left_node = left_tree.remove(0);
            TreeNode right_node = right_tree.remove(0);
            if(left_node.val!=right_node.val){ return false;}
            if(left_node.left!=null&&right_node.right==null || left_node.left==null&&right_node.right!=null){ 
                return false;
            }else if(left_node.left!=null&&right_node.right!=null){
                left_tree.add(0, left_node.left);
                right_tree.add(0, right_node.right);
            }
            if(left_node.right!=null&&right_node.left==null || left_node.right==null&&right_node.left!=null){ 
                return false;
            }else if(left_node.right!=null&&right_node.left!=null){
                left_tree.add(0, left_node.right);
                right_tree.add(0, right_node.left);
            }
        }
        if(!left_tree.isEmpty() || !right_tree.isEmpty()){
            return false;
        }
        return true;
    }
	
	// Same Tree
	public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null&&q==null){ return true;}
        if(p==null||q==null){ return false;}
    
        return (p.val==q.val)&&(isSameTree(p.left, q.left))&&(isSameTree(p.right, q.right));
    }
	
	// Recover Binary Search Tree
	TreeNode pre = null;
    TreeNode disorder1 = null;  
    TreeNode disorder2 = null;
    public void recoverTree(TreeNode root) {
        recoverTree_helper(root);
        if(disorder1!=null && disorder2!=null){
            disorder1.val = disorder2.val ^ disorder1.val;
            disorder2.val = disorder2.val ^ disorder1.val;
            disorder1.val = disorder2.val ^ disorder1.val;
        }
    }
    public void recoverTree_helper(TreeNode root){
        if(root == null){ return; }
    
        recoverTree_helper(root.left);
    
        if(pre!=null){
            if(pre.val > root.val){
                if(disorder1==null){
                    disorder1 = pre;
                }
                disorder2 = root;
            }
        }
    
        pre = root;
    
        recoverTree_helper(root.right);
    }
	
	// Validate Binary Search Tree
	TreeNode pre = null;
    public boolean isValidBST(TreeNode root) {
        if(root==null){ return true; }
    
        boolean isLeftValid = isValidBST(root.left);
        if(pre!=null){
            if(pre.val >= root.val){
                return false;
            }
        }
        pre = root;
        return isLeftValid && isValidBST(root.right);
    }
	
	// Unique Binary Search Trees II
	public List<TreeNode> generateTrees(int n) {
        return generateTrees_helper(1, n);
    }
    public List<TreeNode> generateTrees_helper(int start, int end){
        List<TreeNode> list = new ArrayList<TreeNode>();
        if(start > end){
            list.add(null);
            return list;
        }
    
        for(int i = start; i <= end; i++){
            List<TreeNode> left_subtrees = generateTrees_helper(start, i-1);
            List<TreeNode> right_subtrees = generateTrees_helper(i+1, end);
        
            for(TreeNode left_root : left_subtrees){
                for(TreeNode right_root : right_subtrees){
                    TreeNode root = new TreeNode(i);
                    root.left = left_root;
                    root.right = right_root;
                    list.add(root);
                }
            }
        }
        return list;
    }
	
	// Unique Binary Search Tree
	public int numTrees(int n) {
        if(n==1){ return 1;}
        if(n==2){ return 2;}
        int sum = 0;
        for(int i = 1; i<=n; i++){
            if(i==1 || i==n){ 
                sum = sum + numTrees(n-1); 
            }else{
                sum = sum + numTrees(i-1)*numTrees(n-i);
            }
        }
        return sum;
    }
	
	// Binary Tree Inorder Traversal
	public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inorder_list = new ArrayList<Integer>();
        if(root==null){ return inorder_list; }
    
        List<TreeNode> queue = new ArrayList<TreeNode>();
        if(root.right!=null){ queue.add(root.right);}
        queue.add(new TreeNode(root.val));
        if(root.left!=null){ queue.add(root.left);}
        while(!queue.isEmpty()){
            int n = queue.size();
            TreeNode node = queue.remove(n-1);
            if(node.left==null&&node.right==null){
                inorder_list.add(node.val);
            }else{
                if(node.right!=null){
                    queue.add(node.right);
                }
                queue.add(new TreeNode(node.val));
                if(node.left!=null){
                    queue.add(node.left);
                }
            }
        }
        return inorder_list;
    }
	
	// Restore IP Addresses
	public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<String>();
        restoreIpAddrHelper(s, result, 4, "");
        return result;
    }
    public void restoreIpAddrHelper(String s, List<String> l, int octels, String addr){
        if(octels == 0){
            if(s.length() == 0){
                l.add(addr);
            }
            return;
        }
        if(octels < 4){
            addr += ".";
        }
        octels--;
        if(s.length() - 1 <= 3*octels && s.length() - 1 >= octels){
            restoreIpAddrHelper(s.substring(1, s.length()), l, octels, addr + s.substring(0, 1));
        }
    
        if(s.length() - 2 <= 3*octels && s.length() - 2 >= octels){
            String s1 = s.substring(0, 2);
            int num = Integer.parseInt(s1);
            if(num >= 10){
                restoreIpAddrHelper(s.substring(2, s.length()), l, octels, addr + s1);
            }
        }
    
        if(s.length() - 3 <= 3*octels && s.length() - 3 >= octels){
            String s1 = s.substring(0, 3);
            int num = Integer.parseInt(s1);
            if(num <= 255 && num >= 100){
                restoreIpAddrHelper(s.substring(3, s.length()), l, octels, addr + s1);
            }
        } 
    }
	
	// Reverse Linked List II
	public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null) return null;
        if(m==n) return head;
        ListNode preNode = null;
        ListNode mNode = head;
        int diff = n - m;
        while(m>1){
            if(preNode == null) {
                preNode = head;
            }else{
                preNode = preNode.next;
            }
            mNode = mNode.next;
            m--;
        }
    
        while(diff > 0 && mNode.next!=null){
            if(preNode == null){
                ListNode tmp = head;
                head = mNode.next;
                mNode.next = mNode.next.next;
                head.next = tmp;
            }else{
                ListNode tmp = preNode.next;
                preNode.next = mNode.next;
                mNode.next = mNode.next.next;
                preNode.next.next = tmp;
            }
            diff--;
        }
    
        return head;
    }
	
	// Subsets II
	public List<List<Integer>> subsetsWithDup(int[] num) {
        int[] sorted_s = mergesort(num, 0, num.length-1);
        return subsetsWithDup_helper(sorted_s, 0);
    }
    public int[] mergesort(int[] s, int start, int end){
        if(start>end){ return new int[]{}; }
        if(start==end){ return new int[]{s[start]}; }
    
        int mid = (start+end)/2;
    
        int[] left_list = mergesort(s, start, mid);
        int[] right_list = mergesort(s, mid+1, end);
    
        int n = end - start + 1;
        int[] comb_arr = new int[n];
    
        int i = 0;
        int j = 0;
        int k = 0;
        while(i<left_list.length&&j<right_list.length){
            comb_arr[k++] = (left_list[i] < right_list[j]) ? left_list[i++] : right_list[j++];
        }
        while(i<left_list.length){
            comb_arr[k++] = left_list[i++];
        }
        while(j<right_list.length){
            comb_arr[k++] = right_list[j++];
        }
    
        return comb_arr;
    }
    public List<List<Integer>> subsetsWithDup_helper(int[] s, int start){
        List<List<Integer>> all_sets = new ArrayList<List<Integer>>();
        if(start > s.length-1){ 
            List<Integer> empty_set = new ArrayList<Integer>();
            all_sets.add(empty_set);
            return all_sets;
        }
    
    
        int num = s[start];
        List<List<Integer>> sets= subsetsWithDup_helper(s, start+1);
        all_sets.addAll(sets);
        for(List<Integer> set : sets){
            List<Integer> entry = new ArrayList<Integer>(set);
            entry.add(0, num);
            if(!all_sets.contains(entry)){ all_sets.add(entry); }
        }
    
        return all_sets;
    }
	
	// Decode Ways
	public int numDecodings(String s) {
        if(s.length()==0) return 0;
        int[] decode = new int[s.length()+1];
        decode[0] = 1;
        if(s.charAt(0)=='0') return 0;
        decode[1] = 1;
        for(int i = 1; i<s.length(); i++){
            char c = s.charAt(i);
            int sum = (s.charAt(i-1)-'0')*10+c-'0';
            if(c=='0'){
                if(sum > c-'0' && sum <= 26){
                    decode[i+1] = decode[i-1];
                }else{
                    return 0;
                }
            }else{
                if(sum > c-'0' && sum <= 26){
                    decode[i+1] = decode[i] + decode[i-1];
                }else{
                    decode[i+1] = decode[i];
                }
            }
        }
        return decode[s.length()];
    }
	
	// Gray Code
	public List<Integer> grayCode(int n) {
        int total_num = (int)Math.pow(2, n);
        List<Integer> code_list = new ArrayList<Integer>();
        code_list.add(0);
        for(int i=1; i<total_num; i++){
            int pre_num = code_list.get(i-1);
            for(int j=0; j<n; j++){
                int bit = (int)Math.pow(2,j);
                if((pre_num/bit)%2==0){
                    int new_num = pre_num + bit;
                    if(!code_list.contains(new_num)&&new_num<total_num){
                       code_list.add(new_num);
                       break;
                    }
                }else{
                    int new_num = pre_num - bit;
                    if(!code_list.contains(new_num)&&new_num<total_num){
                       code_list.add(new_num); 
                       break;
                    }
                }
            }
        }
        return code_list;
    }
	
	// Merge Sorted Array
	public void merge(int A[], int m, int B[], int n) {
        int i = m-1;
        int j = n-1;
        int k = m+n-1;
        while(j>=0 && i>=0){
            if(A[i]>B[j]){
                A[k] = A[i];
                i--;
            }else{
                A[k]=B[j];
                j--;
            }
            k--;
        }
    
        while(j>=0){
            A[k]=B[j];
            k--;
            j--;
        }
    }
	
	// Partition List
	public ListNode partition(ListNode head, int x) {
        if(head == null){ return null; }
    
        ListNode tail = head;
        while(tail.next!=null){
            tail = tail.next;
        }
        ListNode first_swap = null;
        ListNode traverse = head;
        ListNode pre = null;
    
        while(traverse != first_swap && traverse != null){
            if(traverse.val >= x){
                if(first_swap == null){ first_swap = traverse; }
                if(pre==null){
                    tail.next = traverse;
                    tail = traverse;
                    head = traverse.next;
                    traverse.next = null;
                    traverse = head;
                }else{
                    tail.next = traverse;
                    tail = traverse;
                    pre.next = pre.next.next;
                    traverse.next = null;
                    traverse = pre.next;
                }
            }else{
                pre = traverse;
                traverse = traverse.next;
            }
        }
    
        return head;
    }
	
	// Remove Duplicates from Sorted List II
	public ListNode deleteDuplicates(ListNode head) {
        ListNode traverse = head;
        ListNode prev = null;
        boolean isFoundDuplicate = false;
        while(traverse!=null){
            if(isFoundDuplicate){
                if(traverse.next==null){
                    if(prev==null){
                        head = null;
                    }else{
                        prev.next = null;
                    }
                    traverse = traverse.next;
                }else{
                    if(traverse.val != traverse.next.val){
                        traverse = traverse.next;
                        if(prev==null){
                            head = traverse;
                        }else{
                            prev.next = traverse;
                        }
                        isFoundDuplicate = false;
                    }else{
                        traverse = traverse.next;
                    }
                }
            }else{
                if(traverse.next==null){
                    traverse = traverse.next;
                }else{
                    if(traverse.val != traverse.next.val){
                        prev = traverse;
                    }else{
                        isFoundDuplicate = true;
                    }
                    traverse = traverse.next;
                }
            }
        }
        return head;
    }
	
	// Remove Duplicates from Sorted List
	public ListNode deleteDuplicates(ListNode head) {
        ListNode traverse = head;
        while(traverse!=null){
            ListNode nextNode = traverse.next;
            if(nextNode!=null){
                if(nextNode.val == traverse.val){
                    traverse.next = nextNode.next;
                }else{
                    traverse = traverse.next;
                }
            }else{
                break;
            }
        }
        return head;
    }
	
	// Search in Rotated Sorted Array II
	public boolean search(int[] A, int target) {
        int left = 0;
        int right = A.length-1;
        while(right >= left){
            int mid = left + (right-left)/2;
            if(A[mid]==target){
                return true;
            }
        
            if(A[left]<A[mid]){
                // left sorted
                if(target>=A[left]&&target<A[mid]){
                    right = mid-1;
                }else{
                    left = mid + 1;
                }
            }else if(A[left]>A[mid]){
                // right sorted
                if(target>A[mid]&&target<=A[right]){
                    left = mid + 1;
                }else{
                    right = mid -1;
                }
            }else{
                left++;
            }
        }
        return false;
    }
	
	// Remove Duplicates from Sorted Array II
	public int removeDuplicates(int[] A) {
        if(A.length<3){ return A.length; }
    
        int valid_len = 1;
        int duplicates = 1;
        for(int i=1; i<A.length; i++){
            if(A[i]!=A[i-1]){
                A[valid_len++]=A[i];
                duplicates = 1;
            }else{
                duplicates++;
                if(duplicates<=2){
                    A[valid_len++]=A[i];
                }
            }
        }
        return valid_len;
    }
	
	// Word Search
	public boolean exist(char[][] board, String word) {
        int row = board.length;
        if(row == 0) return false;
        int col = board[0].length;
        if(col == 0) return false;
    
        boolean[][] p = new boolean[row][col];
    
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(board[i][j] == word.charAt(0)){
                    p[i][j] = true;
                    boolean res = exist_helper(board, i, j, word.substring(1, word.length()), p);
                    if(res) return res;
                    p[i][j] = false;
                }
            }
        }
        return false;
    }
    public boolean exist_helper(char[][] board, int rowIndex, int colIndex, String word, boolean[][] p){
        if(word.length() == 0) return true;
    
        // for left letter
        if(colIndex > 0){
            int newColIndex = colIndex - 1;
            if(!p[rowIndex][newColIndex] && board[rowIndex][newColIndex] == word.charAt(0)){
                p[rowIndex][newColIndex] = true;
                boolean res = exist_helper(board, rowIndex, newColIndex, word.substring(1, word.length()), p);
                if(res) return res;
                p[rowIndex][newColIndex] = false;
            }
        }
        // for up letter
        if(rowIndex > 0){
            int newRowIndex = rowIndex - 1;
            if(!p[newRowIndex][colIndex] && board[newRowIndex][colIndex] == word.charAt(0)){
                p[newRowIndex][colIndex] = true;
                boolean res = exist_helper(board, newRowIndex, colIndex, word.substring(1, word.length()), p);
                if(res) return res;
                p[newRowIndex][colIndex] = false;
            }
        }
        // for right letter
        if(colIndex < board[0].length-1){
            int newColIndex = colIndex + 1;
            if(!p[rowIndex][newColIndex] && board[rowIndex][newColIndex] == word.charAt(0)){
                p[rowIndex][newColIndex] = true;
                boolean res = exist_helper(board, rowIndex, newColIndex, word.substring(1, word.length()), p);
                if(res) return res;
                p[rowIndex][newColIndex] = false;
            }
        }
        // for down letter
        if(rowIndex < board.length - 1){
            int newRowIndex = rowIndex + 1;
            if(!p[newRowIndex][colIndex] && board[newRowIndex][colIndex] == word.charAt(0)){
                p[newRowIndex][colIndex] = true;
                boolean res = exist_helper(board, newRowIndex, colIndex, word.substring(1, word.length()), p);
                if(res) return res;
                p[newRowIndex][colIndex] = false;
            }
        }
        return false;
    }
	
	// Subset
	public List<List<Integer>> subsets(int[] S) {
        int[] sorted_s = mergesort(S, 0, S.length-1);
        return subsets_helper(sorted_s, 0);
    }
    public int[] mergesort(int[] s, int start, int end){
        if(start>end){ return new int[]{}; }
        if(start==end){ return new int[]{s[start]}; }
    
        int mid = (start+end)/2;
    
        int[] left_list = mergesort(s, start, mid);
        int[] right_list = mergesort(s, mid+1, end);
    
        int n = end - start + 1;
        int[] comb_arr = new int[n];
    
        int i = 0;
        int j = 0;
        int k = 0;
        while(i<left_list.length&&j<right_list.length){
            comb_arr[k++] = (left_list[i] < right_list[j]) ? left_list[i++] : right_list[j++];
        }
        while(i<left_list.length){
            comb_arr[k++] = left_list[i++];
        }
        while(j<right_list.length){
            comb_arr[k++] = right_list[j++];
        }
    
        return comb_arr;
    }
    public List<List<Integer>> subsets_helper(int[] s, int start){
        List<List<Integer>> all_sets = new ArrayList<List<Integer>>();
        if(start > s.length-1){ 
            List<Integer> empty_set = new ArrayList<Integer>();
            all_sets.add(empty_set);
            return all_sets;
        }
    
    
        int num = s[start];
        List<List<Integer>> sets= subsets_helper(s, start+1);
        for(List<Integer> set : sets){
            List<Integer> entry = new ArrayList<Integer>(set);
            entry.add(0, num);
            all_sets.add(entry);
        }
        all_sets.addAll(sets);
    
        return all_sets;
    }
	
	// Combinations
	public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> comb_list = new ArrayList<List<Integer>>();
        if(n==0){
            return comb_list;
        }
    
        if(k==1){
            while(n>0){
                List<Integer> l = new ArrayList<Integer>();
                l.add(n);
                comb_list.add(l);
                n--;
            }
            return comb_list;
        }
    
        for(int i = 0; i < n; i++){
            int num = n-i;
            List<List<Integer>> list = combine(n-i-1, k-1);
            for(Iterator<List<Integer>> iter = list.iterator(); iter.hasNext(); ){
                List<Integer> sub_list = iter.next();
                sub_list.add(num);
                comb_list.add(sub_list);
            }
        }
    
        return comb_list;
    }
	
	// Sort Colors
	public void sortColors(int[] A) {
        int red_tail = -1;
        int blue_head = A.length;
    
        for(int i = 0; i<A.length; i++){
            if(A[i]==0){
                if(i>red_tail){
                    int num = A[red_tail+1];
                    A[red_tail+1] = 0;
                    A[i] = num;
                    red_tail++;
                }
            }else if(A[i]==2){
                if(i<blue_head){
                    int decrement = 1;
                    while(blue_head-decrement>0&&A[blue_head-decrement]==2&&i!=blue_head-decrement){
                        decrement++;
                    }
                
                    int num = A[blue_head-decrement];
                    A[blue_head-decrement] = 2;
                    A[i] = num;
                    blue_head = blue_head - decrement;
                    if(num==0&&i>red_tail){
                        int replace_num = A[red_tail+1];
                        A[red_tail+1] = 0;
                        A[i] = replace_num;
                        red_tail++;
                    }
                }
            }
        }
    }
	
	// Search a 2D Matrix
	public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length<1){ return false;}
    
        int m_rows = matrix.length;
        int n_columns = matrix[0].length;
    
        int min = 0;
        int max = m_rows-1;
        while(max>=min){
            int mid = min + (max-min)/2;
            if(matrix[mid][0]<=target&&target<=matrix[mid][n_columns-1]){
                int min_row = 0;
                int max_row = n_columns-1;
                while(max_row>=min_row){
                    int mid_row = min_row + (max_row-min_row)/2;
                    if(target == matrix[mid][mid_row]){ 
                        return true; 
                    }else if(target > matrix[mid][mid_row]){
                        min_row = mid_row + 1;
                    }else{
                        max_row = mid_row - 1;
                    }
                }
                return false;
            }else if(target > matrix[mid][n_columns-1]){
                min = mid + 1;
            }else{
                max = mid - 1;
            }
        }
    
        return false;
    }
	
	// Set Matrix Zeroes
	public void setZeroes(int[][] matrix) {
        boolean isFirstRowContainingZeros = false;
        boolean isFirstColumnContainingZeros = false;
    
        int row = matrix.length;
        if(row==0){ return; }
        int column = matrix[0].length;
        if(column==0){ return; }
    
        for(int i = 0; i<column; i++){
            if(matrix[0][i]==0){
                isFirstRowContainingZeros = true;
                break;
            }
        }
        for(int i=0; i<row; i++){
            if(matrix[i][0]==0){
                isFirstColumnContainingZeros = true;
                break;
            }
        }
    
        // Check for zeros and mark position on column 0 and row 0
        for(int row_i = 1; row_i<row; row_i++){
            for(int col_i = 1; col_i<column; col_i++){
                if(matrix[row_i][col_i]==0){
                    matrix[0][col_i] = 0;
                    matrix[row_i][0] = 0;
                }
            }
        }
    
        // Set zeros for positions excepts first row and first column
        for(int row_i = 1; row_i<row; row_i++){
            if(matrix[row_i][0]==0){
                for(int i = 1; i<column; i++){
                    matrix[row_i][i] = 0;
                }
            }
        }
        for(int col_i = 1; col_i<column; col_i++){
            if(matrix[0][col_i]==0){
                for(int i = 1; i<row; i++){
                    matrix[i][col_i] = 0;
                }
            }
        }
    
        // Set zeros for first row and first column if necessary
        if(isFirstRowContainingZeros){
            for(int col_i = 0; col_i<column; col_i++){
                matrix[0][col_i] = 0;
            }
        }
        if(isFirstColumnContainingZeros){
            for(int row_i = 0; row_i<row; row_i++){
                matrix[row_i][0] = 0;
            }
        }
    
        return;
    }
	
	// Edit Distance
	public int minDistance(String word1, String word2) {
        // initialization
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] map = new int[len1+1][len2+1];
        for(int i = 0; i<len1+1; i++){ map[i][0] = i; }
        for(int i = 0; i<len2+1; i++){ map[0][i] = i; }
    
        for(int row = 1; row < len1+1; row++){
            for(int col = 1; col < len2+1; col++){
                int op1 = map[row-1][col] + 1;
                int op2 = map[row][col-1] + 1;
                int op3 = word1.charAt(row-1) == word2.charAt(col-1)? map[row-1][col-1] : map[row-1][col-1]+1;
                map[row][col] = Math.min(Math.min(op1, op2), op3);
            }
        }
    
        return map[len1][len2];
    }
	
	// Simplify Path
	public String simplifyPath(String path) {
        List<String> queue = new ArrayList<String>();
        String[] tokens = path.split("/");
        for(String token : tokens){
            if(token.equals(".")){
                continue;
            }
            if(token.equals("..")){
                if(!queue.isEmpty()){
                    queue.remove(queue.size()-1);
                }
                continue;
            }
            if(token.length()>0) queue.add(token);
        }
        if(queue.isEmpty()){
            return "/";
        }else{
            String addr = "";
            while(!queue.isEmpty()){
                String token = queue.remove(0);
                addr = addr + '/' + token;
            }
            return addr;
        }
    }
	
	// Climbing Stairs
	public int climbStairs(int n) {
        int pre_one_step = 0;
        int n_paths = 0;
    
        for(int i=1; i<=n; i++){
            if(i==1){
                n_paths=1;
                pre_one_step = 0;
                continue;
            }
            if(i==2){
                pre_one_step = 1;
                n_paths = n_paths + pre_one_step;
                continue;
            }
            int temp = n_paths;
            n_paths = n_paths + pre_one_step;
            pre_one_step = temp;
        }
    
        return n_paths;
    }
	
	// Sqrt(x)
	public int sqrt(int x) {
        for(int i = 1; i <= x; i++){
            if(i*i==x) return i;
            if(i*i > x || i*i < 0) return i-1;
        }
        return x;
    }
	
	// Valid Number
	public boolean isNumber(String s) {
        return s.matches("(\\s*)([+-]?)((([0-9]+)(\\.?)([0-9]*))|(([0-9]*)(\\.)([0-9]+)))((([Ee])([+-]?)([0-9]+))?)(\\s*)");
    }
	
	// Add Binary
	public String addBinary(String a, String b) {
        List<Character> a_l = new ArrayList<Character>();
        List<Character> b_l = new ArrayList<Character>();
        int len = Math.min(a.length(), b.length());
        int i = 0;
        while(i<len){
            a_l.add(a.charAt(i));
            b_l.add(b.charAt(i));
            i++;
        }
        while(i<a.length()){
            a_l.add(a.charAt(i));
            i++;
        }
        while(i<b.length()){
            b_l.add(b.charAt(i));
            i++;
        }
        String res = "";
        boolean hasCarry = false;
        while(!a_l.isEmpty() && !b_l.isEmpty()){
            char a_binary = a_l.remove(a_l.size()-1);
            char b_binary = b_l.remove(b_l.size()-1);
            int num1 = a_binary-'0';
            int num2 = b_binary-'0';
            int sum = num1 + num2;
            sum = hasCarry? sum+1: sum;
            hasCarry = sum/2 > 0;
            char res_c = sum%2 == 0? '0': '1';
            res = res_c + res;
        }
        while(!a_l.isEmpty()){
            char a_binary = a_l.remove(a_l.size()-1);
            int num1 = a_binary-'0';
            int sum = hasCarry? num1+1: num1;
            hasCarry = sum/2 > 0;
            char res_c = sum%2 == 0? '0': '1';
            res = res_c + res;
        }
        while(!b_l.isEmpty()){
            char b_binary = b_l.remove(b_l.size()-1);
            int num1 = b_binary-'0';
            int sum = hasCarry? num1+1: num1;
            hasCarry = sum/2 > 0;
            char res_c = sum%2 == 0? '0': '1';
            res = res_c + res;
        }
        if(hasCarry) res = '1' + res;
        return res;
    }
	
	// Plus One
	public int[] plusOne(int[] digits) {
        boolean isOutOfBound = false;
        int n = digits.length;
    
        if(n<1){ return digits; }
    
        int i = n-1;
        while(digits[i]==9){
            digits[i]=0;
            i--;
            if(i<0){
                isOutOfBound = true;
                break;
            }
        }
        if(isOutOfBound){
            int[] new_digits = new int[n+1];
            new_digits[0] = 1;
            for(int index = 0; index < n; index++){
                new_digits[index+1] = digits[index];
            }
            return new_digits;
        }else{
            digits[i] = digits[i] + 1;
            return digits;
        }
    }
	
	// Merge Two Sorted Lists
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1==null){ return l2; }
        if(l2==null){ return l1; }
    
        ListNode head = l2;
        ListNode pre_node_l2 = null;
    
        while(l1!=null&&l2!=null){
            if(l1.val<=l2.val){
                ListNode temp = l1;
                l1= l1.next;
                temp.next = l2;
                if(l2 == head){ head = temp; }
                if(pre_node_l2!=null){ pre_node_l2.next = temp; }
                pre_node_l2 = temp;
            }else{
                pre_node_l2 = l2;
                l2 = l2.next;
            }
        }
        if(l2==null&&l1!=null){
            pre_node_l2.next = l1;
        }
    
        return head;
    }
	
	// Minimum Path Sum
	public int minPathSum(int[][] grid) {
        if(grid.length==0||grid[0].length==0){ return 0; }
        int m = grid.length;
        int n = grid[0].length;
    
        int[] p = new int[n+1];
        for(int i = 0; i<p.length; i++){
            if(i==1){
                p[i] = 0;
            }else{
                p[i] = 2147483647;
            }
        }
    
        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                p[j+1] = Math.min(p[j+1], p[j]) + grid[i][j];
            }
        }
    
        return p[p.length-1];
    }
	
	// Unique Paths II
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid.length==0||obstacleGrid[0].length==0){ return 0; }

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] map = new int[m][n];
        map[0][0] = (obstacleGrid[0][0] == 1)? 0:1;
        for(int i=1; i<m; i++){
            if(obstacleGrid[i][0]==1){
                map[i][0]=0;
            }else{
                map[i][0] = map[i-1][0];
            }
        }
        for(int i=1; i<n; i++){
            if(obstacleGrid[0][i]==1){
                map[0][i] = 0;
            }else{
                map[0][i] = map[0][i-1];
            }
        }

        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                if(obstacleGrid[i][j]==1){
                    map[i][j] = 0;
                }else{
                    map[i][j] = map[i-1][j] + map[i][j-1];
                }
            }
        }

        return map[m-1][n-1];
    }
	
	// Unique Paths
	public int uniquePaths(int m, int n) {
        if(m==0||n==0){ return 0; }
    
        if(m==1||n==1){ return 1; }
    
        int[][] map = new int[m][n];
        map[0][0]=0;
        for(int i = 0; i<m; i++){
            map[i][0] = 1;
        }
        for(int i = 0; i<n; i++){
            map[0][i] = 1;
        }
    
        for(int i = 1; i<m; i++){
            for(int j = 1; j<n; j++){
                map[i][j] = map[i-1][j] + map[i][j-1];
            }
        }
    
        return map[m-1][n-1];
    }
	
	// Spiral Matrix II
	public int[][] generateMatrix(int n) {
        if(n==0){ 
            int[][] empty = {};
            return empty;
        }
        int m = (int)Math.pow(n,2);
        int[][] matrix = new int[n][n];
    
        int direction = 0; // 0: right, 1: down, 2:left, 3:up
        int row = 0;
        int column = -1;
        int i = 1;
        while(i<=m){
            if(direction==0){
                if(column+1>=n||matrix[row][column+1]!=0){
                    direction = 1;
                }else{
                    matrix[row][++column] = i;
                    i++;
                }
            }else if(direction==1){
                if(row+1>=n||matrix[row+1][column]!=0){
                    direction = 2;
                }else{
                    matrix[++row][column] = i;
                    i++;
                }
            }else if(direction==2){
                if(column-1<0||matrix[row][column-1]!=0){
                    direction = 3;
                }else{
                    matrix[row][--column] = i;
                    i++;
                }
            }else{
                if(row-1<0||matrix[row-1][column]!=0){
                    direction = 0;
                }else{
                    matrix[--row][column] = i;
                    i++;
                }
            }
        }
        return matrix;
    }
	
	// Length of Last Word
	public int lengthOfLastWord(String s) {
        int n = 0;
        boolean isWordFound = false;
        for(int i = s.length()-1; i>=0; i--){
            char c = s.charAt(i);
            if(c==' '){
                if(isWordFound) return n;
            }else if((c >='A'&&c<='Z')||(c>='a'&&c<='z')){
                n++;
                isWordFound = true;
            }
        }
        return n;
    }
	
	// Permuatation Sequence
	public String getPermutation(int n, int k) {
        List<Integer> allNums = new ArrayList<Integer>();
        for(int i = 1; i<=n; i++) allNums.add(i);
        int index = k - 1;
        int f = factorial(n);
        String str = "";
    
        while(allNums.size()>1){
            int numInGroup = f/n;
            int numIndex = index / numInGroup;
            str += Integer.toString(allNums.remove(numIndex));
            index = index % numInGroup;
            f = f/n;
            n--;
        }
    
        return allNums.size()==0 ? str : str + Integer.toString(allNums.remove(0));
    }
    public int factorial(int n){
        int result = 1;
        while(n > 1){
            result *= n;
            n--;
        }
        return result;
    }
	
	// Rotate List
	public ListNode rotateRight(ListNode head, int n) {
        if(head == null) return head;
        int length = 1;
        ListNode tail = head;
        while(tail.next!=null){
            tail = tail.next;
            length++;
        }
        int lengthToRotate = length - n%length;
        if(lengthToRotate==length) return head;
        ListNode traverse = head;
        while(lengthToRotate>1){
            traverse = traverse.next;
            lengthToRotate--;
        }
        tail.next = head;
        head = traverse.next;
        traverse.next = null;
        return head;
    }
	
	// Jump Game
	public boolean canJump(int[] A) {
        if(A.length == 0) return false;
        boolean[] board = new boolean[A.length];
        board[0] = true;
        int initialStep = A[0];
        int furthestStep = initialStep;
        for(int i = 1; i<=initialStep && i<A.length; i++){
            board[i] = true;
            if(i == A.length-1) return true;
        }
    
        for(int i = 1; i<board.length; i++){
            if(board[i]){
                int step = A[i];
                if(i+step>furthestStep){
                    for(int j = furthestStep + 1; j-i <= step && j < A.length; j++){
                        board[j] =true;
                        if(j == A.length-1) return true;
                    }
                    furthestStep = i + step;
                }
            }
        }
    
        return board[A.length-1];
    }
	
	// Sprial Matrix
	public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> spiralMatrix = new ArrayList<Integer>();
        int row = matrix.length;
        if(row == 0) return spiralMatrix;
        int col = matrix[0].length;
        if(col == 0) return spiralMatrix;
        int totalCells = row * col;
        boolean[][] visited = new boolean[row][col];
    
        int direction = 1; // 1: right, 2: down, 3: left, 4: up
        int spiralIndex = 0;
        int rowIndex = 0;
        int colIndex = 0;
        while(spiralMatrix.size() < totalCells){
            spiralMatrix.add(matrix[rowIndex][colIndex]);
            visited[rowIndex][colIndex] = true;
            switch(direction){
                case 1:
                    if(colIndex + 1 >= col || visited[rowIndex][colIndex+1]){
                        rowIndex++;
                        direction = 2;
                    }else{
                        colIndex++;
                    }
                    break;
                case 2:
                    if(rowIndex + 1 >= row || visited[rowIndex+1][colIndex]){
                        colIndex--;
                        direction = 3;
                    }else{
                        rowIndex++;
                    }
                    break;
                case 3:
                    if(colIndex - 1 < 0 || visited[rowIndex][colIndex-1]){
                        rowIndex--;
                        direction = 4;
                    }else{
                        colIndex--;
                    }
                    break;
                case 4:
                    if(rowIndex - 1 < 0 || visited[rowIndex-1][colIndex]){
                        colIndex++;
                        direction = 1;
                    }else{
                        rowIndex--;
                    }
                    break;
                default:
                    break;
            }
        }
        return spiralMatrix;
    }
	
	// Maximum Subarray
	public int maxSubArray(int[] A) {
        /*
        int max = A[0];
        int sum = A[0];
        for(int i=1; i<A.length; i++){
            if(A[i]>0){
                if(sum < 0){
                    sum = A[i];
                }else{
                    sum = sum + A[i];
                }
            }else{
                if(sum > 0){
                    sum = sum + A[i];
                }else{
                    if(A[i]>sum){ sum = A[i]; }
                }
            }
            if(sum > max){ max = sum;}
        }
        return max;*/
    
        int max = A[0]; int sum = 0;
        for(int i = 0; i<A.length; i++){
            sum = sum + A[i];
            max = Math.max(sum, max);
            if(sum < 0){ sum = 0; }
        }
        return max;
    }
	
	// Pow(x, n)
	public double pow(double x, int n) {
        if(n==0){ return 1.0;}
    
        double temp = pow(x, n/2);
        if(n%2==0){
            return temp*temp;
        }else{
            if(n<0){
                return temp*temp/x;
            }else{
                return temp*temp*x;
            }
        }
    }
	
	// Anagrams
	public List<String> anagrams(String[] strs) {
        List<String> anagramsSet = new ArrayList<String>();
        if(strs == null || strs.length == 0) return anagramsSet;
    
        HashMap<String, List<String>> hashMap = new HashMap<String, List<String>>();
        for(String str : strs){
            char[] strArray = str.toCharArray();
            Arrays.sort(strArray);
            String key = String.valueOf(strArray);
            if(hashMap.containsKey(key)){
                hashMap.get(key).add(str);
            }else{
                List<String> temp = new ArrayList<String>();
                temp.add(str);
                hashMap.put(key, temp);
            }
        }
    
        for(Map.Entry<String, List<String>> entry : hashMap.entrySet()){
            List<String> list = entry.getValue();
            if(list.size()>1) anagramsSet.addAll(list);
        }
    
        return anagramsSet;
    }
	
	// Rotate Image
	public void rotate(int[][] matrix) {
        int n = matrix.length;
        int len = matrix.length;
        int i = 0;
        while(n>1){
            int rotate_i = 0;
            while(rotate_i<n-1){
                int temp_r = matrix[i][i+rotate_i];
                int temp_b = matrix[i+rotate_i][len-i-1];
                int temp_l = matrix[len-i-1][len-i-rotate_i-1];
                int temp_t = matrix[len-i-rotate_i-1][i];
            
                //top
                matrix[i][i+rotate_i] = temp_t;
                matrix[i+rotate_i][len-i-1] = temp_r;
                matrix[len-i-1][len-i-rotate_i-1] = temp_b;
                matrix[len-i-rotate_i-1][i] = temp_l;
            
                rotate_i++;
            }
            n = n - 2;
            i++;
        }
    }
	
	// Permutations
	List<List<Integer>> permute_list = new ArrayList<List<Integer>>();
    public List<List<Integer>> permute(int[] num) {
        if(num.length<1){ return permute_list;}
        int[] arr = new int[num.length];
        dfs(num, arr);
        return permute_list;
    }
    public void dfs(int[] num, int[] arr){
        if(num.length==1){
            arr[arr.length-1] = num[0];
            List<Integer> new_arr = new ArrayList<Integer>();
            for(int i = 0; i<arr.length; i++){
                new_arr.add(arr[i]);
            }
            permute_list.add(new_arr);
        }
    
        for(int i = 0; i<num.length; i++){
            arr[arr.length-num.length] = num[i];
            int[] arrCopy = new int[num.length-1];
            for(int j = 0, k = 0; j<num.length&&k<arrCopy.length; j++){
                if(j!=i){
                    arrCopy[k] = num[j];
                    k++;
                }
            }
            dfs(arrCopy, arr);
        }
    }
	
	// Jump Game II
	public int jump(int[] A) {
        if(A.length==0) return 0;
        // int[] arr = new int[A.length];
        // // initialize
        // arr[0] = 0;
        // for(int i = 1; i < arr.length; i++){
        //     arr[i] = 2147483647;
        // }
        // // update the arr
        // for(int i = 0; i< arr.length-1; i++){
        //     int maxJump = A[i];
        //     for(int j = i+1; j<=maxJump+i&&j<arr.length; j++){
        //         arr[j] = Math.min(arr[j], arr[i]+1);
        //         if(j==arr.length-1){
        //             return arr[j];
        //         }
        //     }
        // }
        // return arr[arr.length-1];
        int pre_layer = -1;
        int layer_tail = 0;
        int level = 0;
        while(layer_tail < A.length-1){
            int i = pre_layer+1;
            pre_layer = layer_tail;
            for(; i<=pre_layer; i++){
                int steps = A[i];
                layer_tail = Math.max(steps+i, layer_tail);
                if(layer_tail>=A.length-1) return level+1;
            }
            level++;
        }
        return level;
    }
	
	// Multiply Strings
	public String multiply(String num1, String num2) {
        if(num1.length() == 0 || num2.length()==0) return "";
        boolean isPositive = true;
        if(num1.charAt(0) == '-' && num2.charAt(0) == '-'){
            num1 = num1.substring(1, num1.length());
            num2 = num2.substring(1, num2.length());
        }else if(num1.charAt(0) == '-'){
            isPositive = false;
            num1 = num1.substring(1, num1.length());
        }else if(num2.charAt(0) == '-'){
            isPositive = false;
            num2 = num2.substring(1, num2.length());
        }
        int commaLocation = 0;
        int indexComma1 = num1.indexOf(".");
        int indexComma2 = num2.indexOf(".");
        if(indexComma1 != -1){
            commaLocation += num1.length() - indexComma1 - 1;
            num1 = num1.substring(0, indexComma1) + num1.substring(indexComma1 + 1, num1.length());
        }
        if(indexComma2 != -1){
            commaLocation += num2.length() - indexComma2 - 1;
            num2 = num2.substring(0, indexComma2) + num2.substring(indexComma2 + 1, num2.length());
        }
        if(num1.indexOf(".")!=-1 || num2.indexOf(".")!=-1) return "";
    
        // make num2 longer one
        if(num2.length() < num1.length()){
            String tmp = num2;
            num2 = num1;
            num1 = tmp;
        }

        int[] sums = new int[num2.length() + num1.length()];
        for(int i = num1.length()-1; i >= 0; i--){
            int carry = 0;
            int digit1 = num1.charAt(i) - '0';
            if(digit1 != 0){
                for(int j = num2.length()-1; j>=0; j--){
                    int digit2 = num2.charAt(j) - '0';
                    if(digit2!=0){
                        int product = digit1 * digit2 + carry;
                        int remainder = product % 10;
                        int index = num1.length() - (i + 1) + num2.length() - (j + 1);
                        int sum = sums[sums.length - 1 - index] + remainder;
                        sums[sums.length - 1 - index] = sum % 10;
                        carry = sum / 10 + product / 10;
                    }else{
                        int index = num1.length() - (i + 1) + num2.length() - (j + 1);
                        int sum = sums[sums.length - 1 - index] + carry;
                        sums[sums.length - 1 - index] = sum % 10;
                        carry = sum / 10;
                    }
                }
            }
            if(carry > 0){
                int index = num1.length() - (i + 1) + num2.length();
                sums[sums.length - 1 - index] += carry;
            }
        }
    
        // convert to String
        String s = "";
        boolean isZero = true;
        for(int d : sums){
            if(d == 0){
                if(!isZero) s = s + String.valueOf(d);
            }else{
                isZero = false;
                s = s + String.valueOf(d);
            }
        }
        if(s.length()==0) s += "0";
    
        if(commaLocation > 0 && !s.equals("0")){
            int indexOfComma = s.length() - commaLocation;
            s = s.substring(0, indexOfComma) + "." + s.substring(indexOfComma, s.length());
            // delete zeros
            for(int i = s.length()-1; i > indexOfComma; i++){
                if(s.charAt(i) != '0'){
                    s = s.substring(0, i + 1);
                    break;
                }
            }
        }
        if(!isPositive && !s.equals("0")) s = "-" + s;
        return s;
    }
	
	// Combination Sum
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        return combinationSumHelper(candidates, target, 0);
    }
    public List<List<Integer>> combinationSumHelper(int[] candidates, int target, int start){
        if(target == 0){
            List<Integer> sum = new ArrayList<Integer>();
            List<List<Integer>> sums = new ArrayList<List<Integer>>(); 
            sums.add(sum);
            return sums;
        }
        if(start == candidates.length){
            return new ArrayList<List<Integer>>();
        }
        List<List<Integer>> combs = new ArrayList<List<Integer>>();
        for(int i = start; i < candidates.length; i++){
            if(candidates[i] > target){
                return combs;
            }
            int n = target / candidates[i];
            List<List<Integer>> all_sums = new ArrayList<List<Integer>>();
            while(n > 0){
                List<List<Integer>> sums = combinationSumHelper(candidates, target - n * candidates[i], i + 1);
                all_sums.addAll(sums);
                for(List<Integer> sum : all_sums){
                    sum.add(0, candidates[i]);
                }
                n--;
            }
            combs.addAll(all_sums);
        }
        return combs;
    }
	
	// Combination Sum II
	public List<List<Integer>> combinationSum2(int[] num, int target) {
        Arrays.sort(num);
        return combinationSum2Helper(num, target, 0);
    }
    public List<List<Integer>> combinationSum2Helper(int[] num, int target, int start){
        List<List<Integer>> combs = new ArrayList<List<Integer>>();
        if(target == 0){
            List<Integer> sum = new ArrayList<Integer>();
            combs.add(sum);
            return combs;
        }
    
        if(start == num.length){
            return combs;
        }
    
        for(int i = start; i < num.length; i++){
            if(num[i] > target) return combs;
            List<List<Integer>> sums = combinationSum2Helper(num, target - num[i], i + 1);
            for(List<Integer> sum : sums){
                sum.add(0, num[i]);
                if(!combs.contains(sum)){
                    combs.add(sum);
                }
            }
        }
        return combs;
    }
	
	// Count and Say
	public String countAndSay(int n) {
        String res = "1";
        for(; n>1; n--){
            int count = 1;
            String seq = "";
            for(int i = 1; i<res.length(); i++){
                if(res.charAt(i)==res.charAt(i-1)){
                    count++;
                }else{
                    seq = seq + Integer.toString(count) + res.charAt(i-1);
                    count = 1;
                }
            }
            seq = seq + Integer.toString(count) + res.substring(res.length()-1, res.length());
            res = seq;
        }
        return res;
    }
	
	// Valid Sudoku
	public boolean isValidSudoku(char[][] board) {
        int row = board.length;
        int col = board[0].length;
    
        HashMap<Integer, List<List<Integer>>> map = new HashMap<Integer, List<List<Integer>>>();
    
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(board[i][j] != '.'){
                    int key = board[i][j] - '0';
                    List<Integer> pos = new ArrayList<Integer>();
                    pos.add(i);
                    pos.add(j);
                    List<List<Integer>> positions = map.get(key);
                    if(positions == null){
                        List<List<Integer>> l = new ArrayList<List<Integer>>();
                        l.add(pos);
                        map.put(key, l);
                    }else{
                        boolean isValid = isValidPosition(i, j, positions);
                        if(!isValid) return false;
                        positions.add(pos);
                        map.put(key, positions);
                    }
                }
            }
        }
    
        return true;
    }
    public boolean isValidPosition(int i, int j, List<List<Integer>> l){
        for(List<Integer> pos : l){
            int row = pos.get(0);
            int col = pos.get(1);
            if(row == i || col == j) return false;
            if(i/3 == row/3 && j/3 == col/3) return false;
        }
    
        return true;
    }
	
	// Search Insert Position
	public int searchInsert(int[] A, int target) {
        int n = A.length;
        for(int i=0; i<n; i++){
            if(A[i]>=target) { 
                return i;
            }
        }
        return n;
    }
	
	// Search in Rotated Sorted Array
	public int search(int[] A, int target) {
        int left = 0;
        int right = A.length-1;
        while(right >= left){
            int mid = left + (right-left)/2;
            if(A[mid]==target){
                return mid;
            }
        
            if(A[left]<=A[mid]&&A[mid]>=A[right]){
                if(target>=A[left]&&target<A[mid]){
                    right = mid-1;
                }else{
                    left = mid + 1;
                }
            }else{
                if(target>A[mid]&&target<=A[right]){
                    left = mid + 1;
                }else{
                    right = mid -1;
                }
            }
        }
        return - 1;
    }
	
	// Search for a Range
	public int[] searchRange(int[] A, int target) {
        int[] res = {-1, -1};
        if(A.length == 0) return res;
        double lowerBound = target - 0.5;
        double upperBound = target + 0.5;
        int start = 0;
        int end = A.length - 1;
        // find lower bound
        int lower = -1;
        while(start < end){
            int mid = (start + end) / 2;
            if(A[mid] < lowerBound){
                start = mid + 1;
            }else{
                end = mid;
            }
        }
        lower = start;
        if(A[lower]!= target) return res;
        // find upper bound
        start++;
        int upper = -1;
        end = A.length -1 ;
        while(start < end){
            int mid = (start + end) / 2;
            if(A[mid] < upperBound){
                start = mid + 1;
            }else{
                end = mid;
            }
        }
        upper = end;
        res[0] = lower;
        res[1] = upper > 0 && A[upper]!= target ? upper - 1: upper;
        return res;
    }
	
	// Longest Valid Parentheses
	public int longestValidParentheses(String s) {
        if(s.length() == 0) return 0;
        int[] arr = new int[s.length()];
        arr[0] = s.charAt(0)=='('? 1 : 0;
        for(int i = 1; i < s.length(); i++){
            char c = s.charAt(i);
            if(c=='('){
                arr[i] = 1;
            }else{
                arr[i] = 0;
				for(int j = i-1; j>=0; j--){
                    if(arr[j]==0){
                        break;
                    }else if(arr[j]==1){
                        arr[j] = 2;
                        arr[i] = 2;
                        break;
                    }
				}
            }
        }
    
        int len = 0;
        int maxLen = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i]==2){
                len++;
                maxLen = Math.max(maxLen, len);
            }else{
                len = 0;
            }
        }
        return maxLen;
    }
	
	// Next Permutation
	public void nextPermutation(int[] num) {
        if (num.length <= 1) return;
        int descendIndex = -1;
    
        for(int i = num.length - 1; i > 0 ; i--){
            if(num[i] > num[i-1]){
                descendIndex = i;
                break;
            }
        }
        if(descendIndex == -1){
            Arrays.sort(num);
            return;
        }
        int nextHead = num[descendIndex - 1];
    
        for(int i = num.length - 1; i >= descendIndex; i--){
            if(num[i] > nextHead){
                num[descendIndex - 1] = num[i];
                num[i] = nextHead;
                break;
            }
        }
        Arrays.sort(num, descendIndex, num.length);
    }
	
	// Divide Two Integers
	public int divide(int dividend, int divisor) {
        long p = Math.abs((long)dividend);
        long q = Math.abs((long)divisor);
    
        int ret = 0;
        while(p >= q){
            int count = 0;
            while(p >= (q << count)){
                count++;
            }
            ret += 1 << (count - 1);
            p -= q << (count - 1);
        }
    
        if((dividend > 0 && divisor < 0 )||(dividend < 0 && divisor > 0)){
            return -1 * ret;
        }else{
            return ret;
        }
    }
	
	// Implement strStr()
	public String strStr(String haystack, String needle) {
        if(needle.length()==0) return haystack; 
        if(haystack.length()==0) return null;
        int first_occur = -1;
        int k = 0;
        for(int i = 0; i<haystack.length(); i++){
            if(haystack.charAt(i) == needle.charAt(k)){
                if(first_occur == -1) first_occur = i;
                if(k==needle.length()-1) return haystack.substring(i-k, haystack.length());
                k++;
            }else{
                k = 0;
                if(first_occur!=-1){
                    i = first_occur;
                    first_occur = -1;
                }
            }
        }
        return null;
    }
	
	// Remove Element
	public int removeElement(int[] A, int elem) {
        int count = 0;
        for(int i = 0; i<A.length; i++){
            if(A[i]!=elem){
                A[count] = A[i];
                count++;
            }else{
                if(i+1<A.length){
                    A[count] = A[i+1];
                }
            }
        }
        return count;
    }
	
	// Remove Duplicates from Sorted Array
	public int removeDuplicates(int[] A) {
        if(A.length<1){ return 0;}
        if(A.length<2){ return 1;}
        int current_int = A[0];
        int valid_length = 1;
    
        for(int i = 1; i<A.length; i++){
            if(A[i]!=A[i-1]){
                A[valid_length] = A[i];
                valid_length++;
            }
        }
    
        return valid_length;
    }
	
	// Swap Nodes in Pairs
	public ListNode swapPairs(ListNode head) {
        ListNode traverse = head;
        ListNode pre = null;
        while(traverse!=null&&traverse.next!=null){
            ListNode temp = traverse.next;
            traverse.next = traverse.next.next;
            temp.next = traverse;
            if(pre==null){
                head = temp;
            }else{
                pre.next = temp;
            }
            pre = traverse;
            traverse = traverse.next;
        }
        return head;
    }
	
	// Generate Parentheses
	List<String> gen_paren_sol = new ArrayList<String>();
    public List<String> generateParenthesis(int n) {
        generateParenthesis_helper(n, 0, 0, "");
        return gen_paren_sol;
    }
    public void generateParenthesis_helper(int n, int n_open_paren, int n_close_paren, String combination){
    
        if(combination.length() == 2*n-1){
            combination = combination + ")";
            n_close_paren++;
            gen_paren_sol.add(combination);
            return;
        }
    
        if(combination.length() == 0){
            generateParenthesis_helper(n, n_open_paren+1, n_close_paren, combination + "(");
            return;
        }
        
        if(n_open_paren<n){
            generateParenthesis_helper(n, n_open_paren+1, n_close_paren, combination + "(");
        }
    
        if(n_close_paren<n_open_paren){
            generateParenthesis_helper(n, n_open_paren, n_close_paren+1, combination + ")");
        }
    }
	
	// Valid Parentheses
	public boolean isValid(String s) {
        List<Character> stack = new ArrayList<Character>();
    
        for(int i = 0; i<s.length(); i++){
            char element = s.charAt(i);
            if(element == '(' || element == '{' || element == '['){
                stack.add(element);
            }else if(stack.isEmpty()){
                return false;
            }else{
                char top_element = stack.remove(stack.size()-1);
                if(element == ')' && top_element != '(' || 
                element == '}' && top_element != '{' ||
                element == '[' && top_element != ']'){
                    return false;
                }
            }
        }
    
        return stack.isEmpty();
    }
	
	// Remove Nth Node From End of List
	public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode traverse1 = head;
        ListNode traverse2 = head;
        while(n>0){
            traverse1 = traverse1.next;
            n--;
        }
        if(traverse1 == null) return head.next;
        traverse1 = traverse1.next;
    
        while(traverse1!=null){
            traverse1 = traverse1.next;
            traverse2 = traverse2.next;
        }
        traverse2.next = traverse2.next.next;
        return head;
    }
	
	// 4Sum
	public List<List<Integer>> fourSum(int[] num, int target) {
        Arrays.sort(num);
        HashSet<List<Integer>> hashSet = new HashSet<List<Integer>>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for(int i = 0; i<num.length; i++){
            for(int j = i+1; j<num.length; j++){
                int k = j+1;
                int l = num.length-1;
                while(k < l){
                    int sum = num[i] + num[j] + num[k] + num[l];
                    if(sum == target){
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(num[i]);
                        list.add(num[j]);
                        list.add(num[k]);
                        list.add(num[l]);
                        if(!hashSet.contains(list)){
                            hashSet.add(list);
                            result.add(list);
                        }
                        k++;
                        l--;
                    }else if(sum > target){
                        l--;
                    }else{
                        k++;
                    }
                }
            }
        }
        return result;
    }
	
	// Letter Combinations of a Phone Number
	public List<String> letterCombinations(String digits) {
        if(digits.length()==0){
            List<String> emptyList = new ArrayList<String>();
            emptyList.add("");
            return emptyList;
        }
    
        char c = digits.charAt(0);
        List<String> letters = numberToLetter(c);
        List<String> output = new ArrayList<String>();
        for(String letter : letters){
            List<String> set = letterCombinations(digits.substring(1, digits.length()));
            for(String s : set){
                output.add(letter + s);
            }
        }
        return output;
    }
    public List<String> numberToLetter(char digit){
        List<String> letters = new ArrayList<String>();
        if(digit == '2'){
            letters.add("a");
            letters.add("b");
            letters.add("c");
        }else if(digit == '3'){
            letters.add("d");
            letters.add("e");
            letters.add("f");
        }else if(digit == '4'){
            letters.add("g");
            letters.add("h");
            letters.add("i");
        }else if(digit == '5'){
            letters.add("j");
            letters.add("k");
            letters.add("l");
        }else if(digit == '6'){
            letters.add("m");
            letters.add("n");
            letters.add("o");
        }else if(digit == '7'){
            letters.add("p");
            letters.add("q");
            letters.add("r");
            letters.add("s");
        }else if(digit == '8'){
            letters.add("t");
            letters.add("u");
            letters.add("v");
        }else if(digit == '9'){
            letters.add("w");
            letters.add("x");
            letters.add("y");
            letters.add("z");
        }
        return letters;
    }
	
	// 3Sum Closet
	public int threeSumClosest(int[] num, int target) {
        int diff = 2147483647;
        int res = 0;
        Arrays.sort(num);
    
        for(int i = 0; i<num.length-2; i++){
            int number = num[i];
            int startIndex = i+1;
            int endIndex = num.length-1;
            while(startIndex < endIndex){
                int sum = number + num[startIndex] + num[endIndex];
                if(Math.abs(sum-target) < diff){
                    diff = Math.abs(sum-target);
                    res = sum;
                }
                if(sum < target){
                    startIndex++;
                }else if(sum > target){
                    endIndex--;
                }else{
                    return sum;
                }
            }
        }
    
        return res;
    }
	
	// 3Sum
	public List<List<Integer>> threeSum(int[] num) {
        List<List<Integer>> threeSumSet = new ArrayList<List<Integer>>();
        Arrays.sort(num);
    
        for(int i = 0; i<num.length-2; i++){
            if(i == 0 || num[i] > num[i-1]){
                int number = num[i];
                int startIndex = i+1;
                int endIndex = num.length-1;
                while(startIndex < endIndex){
                    if(num[startIndex]+num[endIndex]+number==0){
                        List<Integer> solution = new ArrayList<Integer>();
                        solution.add(number);
                        solution.add(num[startIndex]);
                        solution.add(num[endIndex]);
                        threeSumSet.add(solution);
                        startIndex++;
                        endIndex--;
            
                        while(startIndex<endIndex&&num[startIndex]==num[startIndex-1]) startIndex++;
                        while(startIndex<endIndex&&num[endIndex]==num[endIndex+1]) endIndex--;
                    }else{
                        if(num[startIndex]+num[endIndex]+number<0){
                            startIndex++;
                        }else{
                            endIndex--;
                        }
                    }
                }
            }
        }
        return threeSumSet;
    }
	
	// Longest Common Prefix
	public String longestCommonPrefix(String[] strs) {
        String CommonPrefix = "";
        if(strs.length==0) return CommonPrefix;
        int len = minLength(strs);
        int i = 0;
        while(i < len){
            for(int j = 1; j<strs.length; j++){
                if(strs[j].charAt(i)!=strs[j-1].charAt(i)){
                    return CommonPrefix;
                }
            }
            CommonPrefix += strs[0].charAt(i);
            i++;
        }
        return CommonPrefix;
    }
    public int minLength(String[] strs){
        int min = strs[0].length();
        for(String str : strs){
            min = Math.min(str.length(), min);
        }
        return min;
    }
	
	// Roman to Integer
	public int romanToInt(String s) {
        /* 
        1: I
        5: V
        10: X
        50: L
        100: C
        500: D
        1000: M
        */
        if(s.equals("N")){ return 0; };
    
        int num = 0;
        while(s.length() > 0){
            char element = s.charAt(s.length()-1);
            int numOfElmentsRemoved = 1;
            switch(element){
                case 'I': num += 1;
                break;
            
                case 'V': {
                    num += 5;
                    int decrement = convert_helper(s, 'I', 1);
                    num -= decrement;
                    numOfElmentsRemoved = decrement > 0? 2:1;
                }
                break;
            
                case 'X': {
                    num += 10;
                    int decrement = convert_helper(s, 'I', 1);
                    num -= decrement;
                    numOfElmentsRemoved = decrement > 0? 2:1;
                }
                break;
            
                case 'L': {
                    num += 50;
                    int decrement = convert_helper(s, 'X', 10);
                    num -= decrement;
                    numOfElmentsRemoved = decrement > 0? 2:1;
                }
                break;
            
                case 'C':{
                    num += 100;
                    int decrement = convert_helper(s, 'X', 10);
                    num -= decrement;
                    numOfElmentsRemoved = decrement > 0? 2:1;
                }
                break;
            
                case 'D':{
                    num+=500;
                    int decrement = convert_helper(s, 'C', 100);
                    num -= decrement;
                    numOfElmentsRemoved = decrement > 0? 2:1;
                }
                break;
            
                case 'M':{
                    num+=1000;
                    int decrement = convert_helper(s, 'C', 100);
                    num -= decrement;
                    numOfElmentsRemoved = decrement > 0? 2:1;
                }
                break;
            }
            s = s.substring(0, s.length()-numOfElmentsRemoved);
        }
    
        return num;
    }
    public int convert_helper(String s, char element, int num){
        if(s.length()>1){
            if(s.charAt(s.length()-2) == element){ return num; }
        }
        return 0;
    }
	
	// Integer to Roman
	public String intToRoman(int num) {
        if(num==0){
            return "N";
        }
    
        String one = "I";
        String five = "V";
        String ten = "X";
        String fifty = "L";
        String one_hundred = "C";
        String five_hundred = "D";
        String thousand = "M";
    
        String res = "";
    
        if(num>=1000){
            int thousandth = num/1000;
            res += intToRoman_helper(thousandth, "", "", "M");
            num = num - (num/1000)*1000;
        }
        if(num>=100){
            int hundredth = num/100;
            res += intToRoman_helper(hundredth, "M", "D", "C");
            num = num - (num/100)*100;
        }
        if(num>=10){
            int tenth = num/10;
            res += intToRoman_helper(tenth, "C", "L", "X");
            num = num - (num/10)*10;
        }
        if(num>0){
            res += intToRoman_helper(num, "X", "V", "I");
        }
        return res;
    }
    public String intToRoman_helper(int num, String upper, String mid, String single){
        String input = "";
        if(num==9){
            input = input + single + upper;
        }else if(num >= 5){
            input+= mid;
            num = num - 5;
            while(num>0){
                input+=single;
                num--;
            }
        }else if(num == 4){
            input = input +single + mid;
        }else{
            while(num>0){
                input += single;
                num--;
            }
        }
        return input;
    }
	
	// Container With Most Water
	public int maxArea(int[] height) {
        if(height.length < 2){ return 0; }
    
        int max = 0;
        int start = 0;
        int end = height.length-1;
    
        while(end>start){
            max = Math.max(max, Math.min(height[start], height[end]) * (end - start));
            if(height[start] < height[end]){
                start++;
            }else{
                end--;
            }
        }
    
        return max;
    }
	
	// Regular Expression Matching
	public boolean isMatch(String s, String p) {
        if(s.length()==0){
            if(p.length()==0) return true;
            if(p.indexOf('*')==1) return isMatch(s, p.substring(2, p.length()));
            return false;
        }else if(p.length()==0) return false; 
    
        char c = p.charAt(0);
        int indexAsterisk = p.indexOf('*');
        if(indexAsterisk != 1){
            if(c=='.'){
                return isMatch(s.substring(1, s.length()), p.substring(1, p.length()));
            }else{
                return (c==s.charAt(0))&&isMatch(s.substring(1, s.length()), p.substring(1, p.length()));
            }
        }else{
            if(c == s.charAt(0) || c == '.'){
                return isMatch(s.substring(1, s.length()), p) || isMatch(s, p.substring(2, p.length()));
            }else{
                return isMatch(s, p.substring(2, p.length()));
            }
        }
    }
	
	// Palindrome Number
	public boolean isPalindrome(int x) {
        if(x<0){ return false; }
        if(x<=9){ return true; }
    
        int divider = (int)Math.pow(10, (int)(Math.log10(x)));
    
        while(divider >= 10 && x/divider == x%10){
            x = (x%divider)/10;
            divider = divider / 100;
        }
    
        return (divider >= 10)? false: true;
    }
	
	// Reverse Integer
	public int reverse(int x) {
        boolean isNegative = x > 0 ? false: true;
    	int res = 0;
	
    	if(isNegative){
    		x = -1 * x;
    	}
	
    	int numberOfDigits = 1;
    	int divider = x/10;
    	while(divider >= 1){
    		numberOfDigits++;
    		divider = divider / 10;
    	}
	
    	int i = 1;
    	boolean isFirstDigit = true;
    	int remainer = x % 10;
    	int numLeft = x / 10;
    	while(i <= numberOfDigits)
    	{
    		if(isFirstDigit && remainer==0){
    			isFirstDigit = false;
    		}else{
    		    res = res + remainer * (int)Math.pow(10, numberOfDigits-i);
    		}
    		remainer = numLeft % 10;
    		numLeft = numLeft / 10;
    		i++;
    	}
	
    	return isNegative? -1*res: res;
    }
	
	// String to Integer (atoi)
	public int atoi(String str) {
        long res = 0;
        int sign = 1;
        boolean isNumberFound = false;
        List<Character> stack = new ArrayList<Character>();
        for(int i = 0; i<str.length();i++){
            char c = str.charAt(i);
            if(c >= '0' && c <= '9'){
                stack.add(c);
                isNumberFound = true;
            }else if(c=='-' || c=='+'){
                if(isNumberFound) return 0;
                sign = (c=='-')? -1: 1;
                isNumberFound = true;
            }else if(c==' '){
                if(isNumberFound) break;
            }else{
                break;
            }
        }
        long digits = 1;
        while(!stack.isEmpty()){
            char c = stack.remove(stack.size()-1);
            int num = c - 48;
            res += digits * num;
            digits*=10;
        }
        res *= sign;
        if(res > 2147483647){
            res = 2147483647;
        }
        if(res < -2147483648){
            res = -2147483648;
        }
    
        return (int)res;
    }
	
	// ZigZag Conversion
	public String convert(String s, int nRows) {
        if(s.length()==0 || nRows == 1){ return s; }
        int n = nRows + nRows - 2;
        int m = (nRows > s.length()) ? (s.length()-nRows)/n : s.length()/n;
        int r = (nRows > s.length()) ? (s.length()-nRows)%n : s.length()%n;
        int nCols = m+1;
        if(r>0){ nCols += 1; }
        nCols = 2 * nCols;
    
        int[][] board = new int[nRows][nCols];
        // initialize
        for(int i = 0; i< nCols; i++){
            if(i==0){ 
                board[0][i] = 0;
            }else{
                board[0][i] = (i%2==0)? board[0][i-1]+n : board[0][i-1];
            }
        }
        // loop through the board
        for(int row = 1; row < nRows; row++){
            for(int col = 0; col < nCols; col++){
                board[row][col] = board[row-1][col] + (int)Math.pow(-1, col+1);
            }
        }
        // form a new string
        StringBuffer strBuffer = new StringBuffer();
        for(int row = 0; row < nRows; row++){
            for(int col = 0; col < nCols; col++){
                if(board[row][col] >= 0 && board[row][col] < s.length()){
                    if(row == 0 || row == nRows-1){
                        if(col%2==1){ strBuffer.append(s.charAt(board[row][col])); }
                    }else{
                        strBuffer.append(s.charAt(board[row][col]));
                    }
                }
            }
        }
    
        return strBuffer.toString();
    }
	
	// Longest Palindromic Substring
	public String longestPalindrome(String s) {
        int len = s.length();
        int max = 0;
        String str = "";
        boolean[][] board = new boolean[len][len];
        for(int col = 0; col<len; col++){
            for(int row = 0; row<=col; row++){
                if(col-row>2){
                    if(s.charAt(row)==s.charAt(col)&&board[row+1][col-1]){
                        board[row][col] = true;
                    }else{
                        board[row][col] = false;
                    }
                }else{
                    if(row==col){
                        board[col][row] = true;
                    }else{
                        if(s.charAt(col)==s.charAt(row)){
                            board[row][col] = true;
                        }else{
                            board[row][col] = false;
                        }
                    }
                }
                if(board[row][col]){
                    if(col-row+1>max){
                        max = col-row+1;
                        str = s.substring(row, col+1);
                    }
                }
            }
        }
    
        return str;
    }
	
	// Add Two Numbers
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        boolean carry = false;
    	ListNode sum_node = null;
    	ListNode traverse = null;
    	while(l1!=null||l2!=null){
    		int l1_val = 0;
    		int l2_val = 0;
    		if(l1!=null){
    			l1_val = l1.val;
    			l1 = l1.next;
    		}
    		if(l2!=null){
    			l2_val = l2.val;
    			l2 = l2.next;
    		}
    		int sum = l1_val + l2_val + (carry? 1:0);
    		if(sum>=10){
    			carry = true;
    			sum = sum%10;
    		}else{
    			carry = false;
    		}
    		if(traverse!=null){
    			traverse.next = new ListNode(sum);
    			traverse = traverse.next;
    		}else{
    			sum_node = new ListNode(sum);
    			traverse = sum_node;
    		}
    	}
    	if(carry){
    		traverse.next = new ListNode(1);
    		traverse = traverse.next;
    	}
    	return sum_node;
    }
	
	// Longest Substring Without Repeating Characters
	public int lengthOfLongestSubstring(String s) {
        int n = s.length();
    	int i = 0;
    	int j = 0;
    	int maxLen = 0;
    	boolean[] exist = new boolean[256];
    	for(int k = 0; k<exist.length; k++){
    		exist[k] = false;
    	}
	
    	while (j < n){
    		int c = s.charAt(j);
    		if (exist[c]){
    			maxLen = maxLen > j-i ? maxLen : j-i;
    			while(s.charAt(i) != s.charAt(j)){
    				exist[s.charAt(i)] = false;
    				i++;
    			}
    			i++;
    			j++;
    		}else{
    			exist[c] = true;
    			j++;
    		}
    	}
    	maxLen = maxLen > n-i ? maxLen : n-i;
    	return maxLen;
    }
	
	// Median of Two Sorted Arrays
	public double findMedianSortedArrays(int A[], int B[]) {
        if(B.length<A.length){
            return findMedianSortedArrays(B, A);
        }
    
        double m_a = getMedian(A);
        double m_b = getMedian(B);
    
        if (A.length == 0 && B.length==0){
            return 0;
        } else if(A.length==0){
            return m_b;
        } else if(B.length==0){
            return m_a;
        }
    
        if (A.length == 1){
            // Case 1
            if(B.length == 1){
                return (A[0]+B[0])/2.0;
            }
            else if(isOdd(B)){    // Case 2     
                if(A[0] <= B[B.length/2-1]){
                    return (B[B.length/2-1]+B[B.length/2])/2.0;
                }else if(A[0]>B[B.length/2-1] && A[0] <= B[B.length/2]){
                    return (A[0]+B[B.length/2])/2.0;
                }else if(A[0]>B[B.length/2] && A[0] < B[B.length/2+1]){
                    return (A[0]+B[B.length/2])/2.0;
                }else{
                    return (B[B.length/2]+B[B.length/2+1])/2.0;
                }
            }
            else{       // Case 3
                if(A[0] <= B[(B.length-1)/2]){
                    return B[(B.length-1)/2];
                }else if(A[0] > B[(B.length-1)/2] && A[0] <= B[B.length/2]){
                    return A[0];
                }else{
                    return B[B.length/2];
                }
            }
        }
    
        if (A.length == 2){
            // Case 4
            if(B.length == 2){
                return findMedianTwoArrays(A,B);
            }else if(isOdd(B)){
                int sub_min = (A[1]<B[B.length/2+1])? A[1]: B[B.length/2+1];
                int sub_max = (A[0]>B[B.length/2-1])? A[0]: B[B.length/2-1];
                int[] res = {sub_min, B[B.length/2], sub_max};
                if(res[0]>res[1]){
                    int temp = res[0];
                    res[0] = res[1];
                    res[1] = temp;
                }
                if(res[1]>res[2]){
                    int temp = res[1];
                    res[1] = res[2];
                    res[2] = temp;
                }
                if(res[0]>res[1]){
                   int temp = res[0];
                    res[0] = res[1];
                    res[1] = temp; 
                }
                return res[1];
            }else{
                int sub_min = (A[1]<B[B.length/2+1])? A[1]: B[B.length/2+1];
                int sub_max = (A[0]>B[B.length/2-2])? A[0]: B[B.length/2-2];
                int[] array1 = {B[B.length/2-1], B[B.length/2]};
                int[] array2 = {sub_min, sub_max};
                if(sub_min>sub_max){ array2[0] = sub_max; array2[1] = sub_min; }
                return findMedianTwoArrays(array1, array2);
            }
        }
    
        if(m_a==m_b)
        {
            return m_a;
        }
        else if(m_a>m_b)
        {
            int a_end_index = A.length/2+1;
            int a_cut_length = A.length - a_end_index;
            int b_start_index = a_cut_length;
    	
            int new_a[] = Arrays.copyOfRange(A, 0, a_end_index);
            int new_b[] = Arrays.copyOfRange(B, b_start_index, B.length);
            if(new_a.length<new_b.length){
                return findMedianSortedArrays(new_a, new_b);
            }else{
                return findMedianSortedArrays(new_b, new_a);
            }
        
        }
        else
        {
            int a_start_index = (A.length-1)/2;
            int a_cut_length = a_start_index;
            int b_end_index = B.length - a_cut_length;
    	
            int new_a[] = Arrays.copyOfRange(A, a_start_index, A.length);
            int new_b[] = Arrays.copyOfRange(B, 0, b_end_index);
            if(new_a.length<new_b.length){
                return findMedianSortedArrays(new_a, new_b);
            }else{
                return findMedianSortedArrays(new_b, new_a);
            }
        }
    }
    public double getMedian(int a[]){
        double result;
        if(a.length==0) { return 0; }
    
        if(a.length%2==0){
            result = (a[a.length/2-1]+a[a.length/2])/2.0;
        }else{
            result = a[(a.length-1)/2];
        }
        return result;
    }
    public boolean isOdd(int a[]){
		return a.length%2!=0
    }
    public double findMedianTwoArrays(int a[], int b[]){
        int first = (a[0]<b[0])? a[0]:b[0];
        int fourth = (a[1]>b[1])? a[1]:b[1];
        int second;
        int third;
    
        if(a[0]>b[1]){
            second = b[1];
            third = a[0];
        }else{
            if(a[1]<b[1]){
                third = a[1];
                if(a[0]<b[0]){
                    second = b[0];
                }else{
                    second = a[0];
                }
            }else{
                third = b[1];
                if(a[0]<b[0]){
                    second = b[0];
                }else{
                    second = a[0];
                }
            }
        }

        return (second+third)/2.0;
    }
	
	// Two Sum
	public int[] twoSum(int[] numbers, int target) {
        int[] res = {0,0};
        for(int i = 0; i<numbers.length-1; i++){
            int j = i + 1;
            while(j<numbers.length){
                if (numbers[i] + numbers[j] == target){
                    res[0] = i+1;
                    res[1] = j+1;
                    return res;
                }
                j++;
            }
        }
        return res;
    }
}
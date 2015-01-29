public class NestedInteger{
	boolean isInteger();
	
	Integer getInteger();
	
	List<NestedInteger> getList();
}

public class iterator{
	Stack<NestedInteger> stack = new Stack<NestedInteger>();
	
	public iterator(NestedInteger input){
		if(input != null)
			stack.push(input);
	}
	
	public boolean hasNext(){
		return !stack.empty();
	}
	
	public Integer next(){
		if(stack.empty())
			throw exception;
		NestedInteger item = stack.pop();
		while(!item.isInteger()){
			List<NestedInteger> list = item.getList();
			for(int i = list.size() - 1; i >= 0; i--)
				stack.push(list.get(i));
			item = stack.pop();
		}
		return item.getInteger();
	}
}
public int depthSum(List<NestedInteger> input){
	return depthSumHelper(input, 1);
}

private int depthSumHelper(List<NestedInteger> input, int depth){
	int sum = 0;
	for(NestedInteger x : input){
		if(x.isInteger()){
			sum += x.getInteger() * 1;
		}else{
			sum += depthSumHelper(x.getList(), depth + 1);
		}
	}
	return sum;
}
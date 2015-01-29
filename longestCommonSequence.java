public List<Integer> longestIncreasingSubsequence(int[] num){
	if(num == null || num.length == 0) return 0;
	List<List<Integer>> subsequence = new ArrayList<List<Integer>>();
	int[] l = new int[num.length];
	int globalMaxIndex = 0;
	for(int i = 0; i < num.length; i++){
		if(i == 0){
			List<Integer> sequence  = new ArrayList<Integer>();
			sequence.add(l[0]);
			subsequence.add(sequence);
			continue;
		}
		int currentMax = 0;
		int maxIndex = -1;
		List<Integer> s = new ArrayList<Integer>();
		for(int j = 0; j < i; j++){
			if(num[j] < num[i]){
				int curLength = subsequence.get(j).size() + 1;
				if(curLength > currentMax){
					currentMax = curLength;
					maxIndex = j;
				}
			}
		}
		if(maxIndex != -1){
			s.addAll(subsequence.get(maxIndex));
		}
		s.add(num[i]);
		subsequence.add(s);
		if(currentMax > subsequence.get(globalMaxIndex).size()){
			globalMaxIndex = i;
		}
	}
	return subsequence.get(globalMaxIndex);
}
public int knapsnack(int[] values, int[] weight, int maxWeight)
{
	if( values.length == 0 || weight.length == 0) return 0;
	int[][] s = new int[values.length() + 1][maxWeight + 1];
	
	for(int row = 1; row < s.lengh; row++){
		for(int col = 1; col < s[0].length; col++){
			int currentValue = values[row - 1];
			int currentWeight = weight[row - 1];
			if(currentWeight <= col){
				s[row][col] = Math.max(s[row - 1][col], currentValue + s[row - 1][col - currentWeight]);
			}else{
				s[row][col] = s[row - 1][col];
			}
		}
	}
	
	return s[values.length()][maxWeight];
}
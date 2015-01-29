public int coinPick(int[] coins, int from, int to){
	if(coins == null || coins.length == 0) return 0;
	if(to - from == 1)
		return Math.max(coins[from], coins[to]);
	int a = coinPick(coins, from + 2, to);
	int b = coinPick(coins, from + 1, to - 1);
	int c = coinPick(coins, from, to - 2);
	// to pick the 'from' coin
	int value1 = Math.min(a, b) + coins[from];
	// to pick the 'to' coin
	int value2 = Math.min(coinPick(c, b) + coins[to];
	return Math.max(value1, value2);
}
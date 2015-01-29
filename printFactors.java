# input 32
# print 32 * 1, 16 * 2, 8 * 4, 8 * 2 * 2, 4 * 4 * 2, 4 * 2 * 2 * 2, 2 * 2 * 2 * 2 * 2
# or print 1 * 32, 2 * 2 * 2 * 2 * 2, 2 * 2 * 2 * 4, 2 * 2 * 8, 2 * 16, 2 * 4 * 4, 4 * 8	

public void printMultipyFactors(int n){
	if( n <= 0) return;
	System.out.println("1 * " + n);
	List<Integer> factors = new ArrayList<Integer>();
	for(int i = 2; i <= n / 2; i++){
		if(n % i == 0)
			factors.add(i);
	}
	List<List<Integer>> factorsCombinations = combinationProduct(factors, 0, n);
	for(List<Integer> l : factorsCombinations){
		System.out.print(l.get(0));
		for(int i = 1; i < l.size(); i++){
			System.out.print("*" + l.get(i));
		}
		System.out.println();
	}
}

private List<List<Integer>> combinationProduct(List<Integer> factors, int start, int product){
	List<LIst<Integer>> comb = new ArrayList<List<Integer>>();
	
	if(product == 1){
		List<Integer> l = new ArrayList<Integer>();
		comb.add(l);
		return comb;
	}
	
	for(int i = start, i < factors.size(); i++){
		int factor = factors.get(i);
		int currentProduct = factor;
		int n = 1;
		while(1){
			if( prodcut % currentProdcut == 0 && currentProduct <= product){
				List<List<Integer>> res = combinationProduct(factors, i + 1, product / currentProduct);
				for(List<Integer> l : res){
					for(int j = n; j > 0; j--){
						l.add(j, 0);
					}
					comb.add(l);
				}
				currentProdcut = currentProduct * factor;
				n++;
			}
			break;
		}
	}
	return comb;
}


public void printMultiplyFactor(int n){
	if(n < = 0) return;
	printFactors(n + "*1", n, n);
}

public void printFactors(String printSeq, int dividend, int preDivisor){
	for(int divisor = dividend - 1; divisor >= 2; divisor--){
		if(dividend % divisor != 0)
			continue;
		if(divisor > preDivisor)
			continue;
		int quotient = dividend / divisor;
		if(quotient <= divisor){
			System.out.println(printSeq + divisor + "*" + quotient);
		}
		printFactors(printSeq + divisor + "*", quotient, divisor);
	}
}
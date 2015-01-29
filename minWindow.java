public String minWindow(String S, String T){
	if( S == null || T == null || S.length() == 0 || T.length() == 0 || S.length() < T.length())
		return "";
	for(int i = 0; i < T.length(); i++){
		int index = S.indexOf(T.charAt(i));
		if(index == -1)
			return "";
	}
	int count = T.length();
	Map<String, Integer> keywordCount = new HashMap<String, Integer>();
	for(int i = 0; i < T.length(); i++){
		String subt = T.substring(i, i + 1);
		if(keywordCount.containsKey(subt)){
			keywordCount.put(subt, keywordCount.get(subt) - 1);
		}else{
			keywordCount.put(subt, -1);
		}
	}
	int l = 0;
	int r = 0;
	int leftIndex = 0;
	int rightIndex = 0;
	int minWin = Integer.MAX_VALUE;
	while( l < S.length() && r < S.length()){
		String sub = S.substring(r, r + 1);
		if(keywordCount.containsKey(sub)){
			keywordCount.put(sub, keywordCount.get(sub) + 1);
			boolean allAppear = true;
			for( String key : keywordCount.keySet()){
				if(keywordCount.get(key) < 0){
					allAppear = false;
					break;
				}
			}
			if(allAppear){
				while(true){
					String subleft = S.substring(l, l + 1);
					if(keywordCount.containsKey(subleft)){
						keywordCount.put(subleft, keywordCount.get(subleft) - 1);
						allAppear = true;
						for( String key : keywordCount.keySet()){
							if(keywordCount.get(key) < 0){
								allAppear = false;
								break;
							}
						}
						if(!allAppear)
							break;
					}
					l++;
				}
				if(minWin > r - l + 1){
					minWin = r - l + 1;
					rightIndex = r;
					leftIndex = l;
				}
				l++;
			}
		}
		r++;
	}
	return leftIndex == -1 || rightIndex == -1 ? "" : S.substring(leftIndex, rightIndex + 1);
}
public boolean wordbreak(String s, Set<String> dict)
{
	int n = s.length();
	boolean[] p = new boolean[n + 1];
	p[0] = true;
	for(int i = 0; i < n; i++){
		if(p[i]){
			for( String word : dict){
				int len = word.length();
				if(len > n - i) continue;
				if(word.equals(s.substring(i, i + len))){
					p[i + len] = true;
				}
			}
		}
	}
	return p[n];
}
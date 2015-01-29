public boolean ismorphic(String s1, String s2){
	if(s1.length()!=s2.length()) return false;
	Map<Character, Character> map = new HashMap<Character, Character>();
	
	for(int i = 0; i < s1.length(); i++){
		char c1 = s1.charAt(i);
		char c2 = s2.charAt(i);
		if(map.containsKey(c1)){
			if(map.get(c1)!=c2) 
				return false;
		}else if(map.containsValue(c2)){
			return false;
		}else{
			map.put(c1, c2);
		}
	}
	return true;
}
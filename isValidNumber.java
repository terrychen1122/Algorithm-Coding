public boolean isValidNumber(String s){
	if(s==null||s.length()==0) return false;
	int i = 0;
	int n = s.length();
	while(i < n && Character.isWhitespace(s.charAt(i))) i++;
	if(i < n && (s.charAt(i) == '+' || s.charAt(i) == '-') ) i++;
	boolean isNumber = false;
	while( i < n && Character.isDigit(s.charAt(i))){
		i++;
		if(!isNumber) isNumber = true;
	}
	if( i < n && s.charAt(i) == '.'){
		i++;
		while( i < n && Character.isDigit(s.charAt(i))){
			i++;
			if(!isNumber) isNumber = true;
		}
	}
	while(i < n && Character.isWhitespace(s.charAt(i))) i++;
	return isNumber && i == n;
}
public int atoi(String s){
	if(s==null || s.length()==0) return 0;
	int maxDiv10 = Integer.MAX_VALUE / 10;
	int i = 0;
	int n = s.length();
	while(i < n && Character.isWhitespcae(s.charAt(i))) i++;
	int sign = 1;
	if(i < n && s.charAt(i) == '+'){
		i++;
	}else if(i < n && s.charAt(i) == '-'){
		i++;
		sign = -1;
	}
	int num = 0;
	while(i < n && Character.isDigit(s.charAt(i))){
		int digit = s.charAt(i) - '0';
		if(num > maxDiv10 || (num == maxDiv10 && digit >= 8)){
			return sign == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		}
		num = num * 10 + digit;
		i++;
	}
	return num * sign;
}
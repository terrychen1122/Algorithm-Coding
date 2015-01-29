public String longestPalindrome(String s){
	if(s == null || s.length() == 0) return "";
    	int n = s.length();
    	int maxLen = 0;
    	int startIndex = -1;
    	int endIndex = -1;
    	// For odd substring
    	for(int i = 0; i < n; i++){
    		int pointer1 = i - 1;
    		int pointer2 = i + 1;
    		while( pointer1 >= 0 && pointer2 <= n - 1){
    			if(s.charAt(pointer1) == s.charAt(pointer2)){
    				pointer1--;
    				pointer2++;
    			}else{
    			    break;
    			}
    		}
    		if(pointer2 - pointer1 - 1 > maxLen){
    		    maxLen = pointer2 - pointer1 - 1;
    		    startIndex = pointer1 + 1;
    		    endIndex = pointer2;
    		}
    	}
    	// For even substring
    	for(int i = 0; i < n - 1; i++){
    		if(s.charAt(i)!=s.charAt(i+1)) continue;
    		int pointer1 = i - 1;
    		int pointer2 = i + 2;
    		while( pointer1 >= 0 && pointer2 <= n - 1){
    			if(s.charAt(pointer1) == s.charAt(pointer2)){
    				pointer1--;
    				pointer2++;
    			}else{
    			    break;
    			}
    		}
    		if(pointer2 - pointer1 - 1 > maxLen){
    		    maxLen = pointer2 - pointer1 - 1;
    		    startIndex = pointer1 + 1;
    		    endIndex = pointer2;
    		}
    	}
    	return s.substring(startIndex, endIndex);
    }
}
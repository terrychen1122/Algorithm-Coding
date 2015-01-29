public List<String> fullJustify(String[] words, int L) {
    if(words == null || words.length == 0) return new ArrayList<String>();
    int count = 0;
    
    List<String> l = new ArrayList<String>();
    List<String> wordsList = new ArrayList<String>();
    for(String s : words){
        if(count + s.length() > L){
            addLines(wordsList, l, L);
            wordsList.clear();
            wordsList.add(s);
            count = s.length() + 1;
        }else{
            count += s.length() + 1;
            wordsList.add(s);
        }
    }
    if(!wordsList.isEmpty()){
    	String s = "";
		int spaceLength = L;
		for( String word : wordsList){
			spaceLength -= word.length();
		}
		for(int i = 0; i < wordsList.size() - 1; i++){
			s += wordsList.get(i) + " ";
			spaceLength--;
		}
		s += wordsList.get(wordsList.size() - 1);
		while(spaceLength > 0){
			s += " ";
			spaceLength--;
		}
		l.add(s);
    }
    return l;
}
    
private void addLines(List<String> wordsList, List<String> l, int n){
	if( wordsList.size() == 0)
		return;
    String s = "";
    int spaceLength = n;
	for( String word : wordsList){
		spaceLength -= word.length();
	}
	int numSpace = wordsList.size() - 1;
	if( numSpace == 0){
		s += wordsList.get(0);
		for(int i = spaceLength; i > 0; i--)
			s += " ";
		l.add(s);
		return;
	}
	int baseSpace = spaceLength / numSpace;
	int additionSpace = spaceLength % numSpace;
	for( String word : wordsList){
		s += word;
		if(spaceLength > 0){
			for(int i = baseSpace; i > 0; i--){
				s += " ";
			}
			spaceLength -= baseSpace;
		}
		if(additionSpace > 0){
			s += " ";
			additionSpace--;
			spaceLength -= 1;
		}
	}
    l.add(s);
}
public class WordDistanceFinder {
	
	private List<String> words;
	
	public WordDistanceFinder( List<String> words) {
		this.words = words;
	}
	
	public int distance( String wordOne, String wordTwo){
		if( this.words == null || !this.words.contains(wordOne) || !this.words.contains(wordTwo))
			return -1;
		if( wordOne.equals(wordTwo))
			return 0;
		int minDistance = Integer.MAX_VALUE;
		int wordOneIndex = -1;
		int wordTwoIndex = -1;
		for(int i = 0; i < this.words.size(); i++){
			if(this.words.get(i).equals(wordOne))
				wordOneIndex = i;
			if(this.words.get(i).equals(wordTwo))
				wordTwoIndex = i;
			if(wordOneIndex != -1 && wordTwoIndex != -1 && Math.abs(wordOneIndex - wordTwoIndex) < minDistance)
				minDistance = Math.abs(wordOneIndex - wordTwoIndex);
		}
		return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
	}
	
}

public class WordDistanceFinder {
	private Map<String, List<Integer>> map;
	
	public WordDistanceFinder( List<String> words) {
		this.map = new HashMap<String, List<Integer>>();
		for(int i = 0; i < words.size(); i++){
			String word = words.get(i);
			if(this.map.containsKey(word)){
				List<Integer> l = this.map.get(word);
				l.add(i);
				this.map.put(word, l);
			}else{
				List<Integer> l = new ArrayList<Integer>();
				l.add(i);
				this.map.put(word, l);
			}
		}
	}
	
	public int distance( String wordOne, String wordTwo ){
		if( this.map == null || !this.map.containsKey(wordOne) || !this.map.containsKey(wordTwo))
			return -1;
		if( wordOne.equals(wordTwo))
			return 0;
		
		List<Integer> wordOneIndice = this.map.get(wordOne);
		List<Integer> wordTwoIndice = this.map.get(wordTwo);
		
		int minDistance = Integer.MAX_VALUE;
		int i = 0;
		int j = 0;
		while( i < wordOneIndice.size() && j < wordTwoIndices.size()){
			int index1 = wordOneIndice.get(i);
			int index2 = wordOneIndice.get(j);
			minDistance = Math.min(minDistance, Math.abs(index1 - index2));
			if(index1 > index2){
				j++;
			}else{
				i++;
			}
		}
		return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
	}
}
public int[] findRange(int[] num, int target){
	if(num.length == 1){
		return (num[0] == target)? new int[]{0, 0} : new int[]{-1, -1};
	}
	
	int start = 0;
	int end = num.length - 1;
	while(start <= end){
		int mid = (start + end) / 2;
		if(num[mid] == tartet){
			int[] left_range = findRange(Arrays.copyOfRange(num, start, mid), target);
			int[] right_range = findRange(Arrays.copyOfRange(num, mid+1, end+1), target);
			int left_index = (left_range[0] == -1)? mid : left_range[0] + start;
			int right_index = (right_range[1] == -1)? mid : right_range[1] + mid;
			return new int[]{left_index, right_index};
		}
		
		if(num[mid] > target){
			end = mid - 1;
		}else{
			start = mid + 1;
		}
	}
	return new int[]{-1, -1};
}

public int[] findRange(int[] num, int target){
	if(num == null) return new int[]{-1, -1};
	int index_l = findLeft(num, 0, num.length-1, target);
	int index_r = findRight(num, 0, num.length-1, target);
	return new int[]{index_l, index_r};
}

private int findLeft(int[] num, int start, int end, int target){
	if(start >= end){
		return num[start] == target ? start : -1;
	}
	while(start <= end){
		int mid = (start + end) / 2;
		if(num[mid] == target){
			int index = findLeft(num, start, mid - 1, target);
			return index == -1 ? mid : index;
		}
		if(num[mid] > target){
			end = mid - 1;
		}else{
			start = mid + 1;
		}
	}
	return -1;
}

private int findRight(int[] num, int start, int end, int target){
	if(start >= end){
		return num[end] == target ? end: -1;
	}
	while(start <= end){
		int mid = (start + end) / 2;
		if(num[mid] == target){
			int index = findRight(num, mid + 1, end, target);
			return index == -1 ? mid : index;
		}
		if(num[mid] > target){
			end = mid - 1;
		}else{
			start = mid + 1;
		}
	}
	return -1;
}
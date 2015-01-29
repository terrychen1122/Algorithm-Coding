List<List<Integer>> p_list = new ArrayList<List<Integer>>();
public List<List<Integer>> permute(int[] num) {
    if(num.length<1){ return p_list;}
    dfs(num, 0);
    return p_list;
}

private void dfs(int[] num, int start){
    if(start == num.length){
        List<Integer> arr = new ArrayList<Integer>();
        for(int i = 0; i<num.length; i++){
            arr.add(num[i]);
        }
        p_list.add(arr);
        return;
    }
    
    for(int i = start; i<num.length; i++){
        swap(num, start, i);
        dfs(num, start + 1);
        swap(num, start, i);
    }
}

private void swap(int[] num, int i, int j){
    int temp = num[i];
    num[i] = num[j];
    num[j] = temp;
}
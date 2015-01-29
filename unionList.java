public ListNode unionList(ListNode a, ListNode b){
	ListNode head = null;
	ListNode union = head;
	while( a != null || b != null){
		int val = 0;
		if( a != null && b != null){
			if( a.val < b.val){
				val = a.val;
				a = a.next;
			}else if( a.val > b.val){
				val = b.val;
				b = b.next;
			}else{
				val = a.next;
				a = a.next;
				b = b.next;
			}
		}else if( a == null){
			val = b;
			b = b.next;
		}else if( b == null){
			val = a;
			a = a.next;
		}
		if( union == null){
			union = new ListNode(val);
			head = union;
			union = union.next;
		}else if( val != union.val){
			union.next = new ListNode(val);
			union = union.next;
		}
	}
	return head;
}
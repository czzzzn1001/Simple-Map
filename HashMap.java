/**
 * Explanation: Implementation of HashMap
 * Known Bugs:None
 * Zheng Chu
 * zhengchu@brandeis.edu
 * Dec 8, 2020
 * COSI 21A PA3
 */
public class HashMap {
   Entry[] buckets;
   int size;
   int len;
   /**
	 * The constructor of HashMap 
	 */
   public HashMap() {
	   size=0;
	   len=10;
	   buckets=new Entry[len];
   }
   /**
	 * convert GraphNode's id to an integer 
	 */
   private int HashCode(GraphNode node) {
	   String id=node.getId();
	   String[] arr=id.split("-");
	   int sum=0;
	   for(int i=0;i<arr.length;i++) {
		   String temp=arr[i];
		   for(int j=0;j<temp.length();j++) {
			   char ch=temp.charAt(j);
			   if(Character.isDigit(ch)) {
				   sum+=ch-'0';
			   }
			   else if(Character.isLetter(ch)) {
				   sum+=ch-'a';
			   }
		   }
	   }
	   return sum;
   }
   /**
	 * put key and value to the map, check if there is an entry for the key
	 * if yes, change its value to value, if no, add it to the hashmap with that
	 */
   public void set(GraphNode key,int value) {
	   Entry entry=new Entry(key,value);
	   boolean set=false;
	   for(int i=0;i<len-1&&!set;i++) {
	     int num=(HashCode(key)+i)%len;
	     if(buckets[num]==null) {
	    	 buckets[num]=entry;
	    	 set=true;
	    	 size++;
	     }
	   }
	   double factor=(size*1.0)/len;
	   if(factor>0.5) {
		   rehash();
	   }
   }
   /**
	 * get the value mapped to the key
	 * return -1 if key does not exist
	 */
   public int getValue(GraphNode g) {
	   String id=g.getId();
	   for(int i=0;i<len-1;i++) {
		     int num=(HashCode(g)+i)%len;
		     if(buckets[num]!=null&&buckets[num].key.getId().equals(id)) {
		    	 return buckets[num].value;
		     }
	   }
	   return -1;
   }
   /**
	 * true if the map has the key
	 */
   public boolean hasKey(GraphNode g) {
	   String id=g.getId();
	   for(int i=0;i<len-1;i++) {
		     int num=(HashCode(g)+i)%len;
		     if(buckets[num]!=null&&buckets[num].key.getId().equals(id)) {
		    	 if(buckets[num].value!=-1)
		    	    return true;
		     }
	   }
	   return false;
   }
   /**
	 * rehash to make best use of the space
	 */
   private void rehash() {
	   this.len*=2;
	   int temp_len=this.size;
	   this.size=0;
	   Entry[] temp=this.buckets;
	   this.buckets=new Entry[len];
	   for(int i=0;i<temp_len;i++) {
		   Entry curr=temp[i];
		   if(curr!=null&&curr.value!=-1) {
			   GraphNode key=curr.key;
			   int value=curr.value;
			   this.set(key, value);
		   }
	   }
   }
}

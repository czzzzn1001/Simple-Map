/**
 * Explanation: Implementation of MinQueue
 * Known Bugs:None
 * Zheng Chu
 * zhengchu@brandeis.edu
 * Dec 8, 2020
 * COSI 21A PA3
 */
public class MinQueue {
 public GraphNode[] heap;
 public int size;
 public int capacity;
 public HashMap map;
    /**
	 * The constructor of MinQueue 
	 */
    public MinQueue(int capacity) {
    	this.capacity=capacity+1;
    	this.size=0;
    	heap=new GraphNode[this.capacity];
    	map=new HashMap();
    }
    /**
	 * insert g to the min-heap 
	 */
    public void insert(GraphNode g) {
    	if(size>=capacity) {
    		return;
    	}
    	size++;
    	heap[size]=g;
    	map.set(g, size);
    	heapify(g);
    }
    /**
	 *  return and remove the GraphNode with highest priority
	 */
    public GraphNode pullHighestPriorityElement() {
    	GraphNode min=heap[1];
    	map.set(min, -1);
    	//-1 indicates deletion from heap
    	heap[1]=heap[size];
    	size--;
    	//replace the first node with the bottom one
    	map.set(heap[1], 1);
    	heapify(heap[1]);
    	return min;
    }
    /**
	 *  calls heapify from the node g
	 */
    public void rebalance(GraphNode g) {
    	heapify(g);
    }
    /**
	 *  returns if the heap is empty
	 */
    public boolean isEmpty() {
    	return this.size==0;
    }
    /**
	 * The left child index of current index 
	 */
    private int left(int i) {
    	return 2*i;
    }
    /**
	 * The right child index of current index 
	 */
    private int right(int i) {
    	return 2*i+1;
    }
    /**
	 * The parent index of current index 
	 */
    private int parent(int i) {
    	return i/2;
    }
    /**
	 * swap the elements indices at a and b 
	 */
    private void swap(int a,int b) {
    	GraphNode temp=heap[a];
    	heap[a]=heap[b];
    	heap[b]=temp;
    	map.set(heap[a], b);
    	map.set(heap[b], a);
    }
    /**
	 * heapify-up from the index
	 */
    private void heapify_up(int i) {
    	while(i>1&&heap[i].priority<heap[parent(i)].priority) {
    		swap(i,parent(i));
    		i=parent(i);
    	}
    }
    /**
	 * heapify from the node g
	 */
    private void heapify(GraphNode g) {
    	int i=map.getValue(g);
    	int l=left(i);
    	int r=right(i);
    	int smallest;
    	if(l<=size&&heap[l].priority<heap[i].priority) {
    		smallest=l;
    	}
    	else {
    		smallest=i;
    	}
    	if(r<=size&&heap[r].priority<heap[smallest].priority) {
    		smallest=r;
    	}
    	if(smallest!=i) {
    		swap(i,smallest);
    		heapify(heap[smallest]);
    	}
    }
}

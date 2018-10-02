public class PQHeap {

    Node[] data;
    int numNodes = 0;


    // resizes the underlying array by doubling the number
    // of elements that can be stored
    public void add(Node toAdd) {
    	
    if (numNodes+1 >= data.length) {
    	//System.out.println("YESS");
    	resize();
    }
    
	data[numNodes+1] = toAdd;
	
	numNodes++;
	siftUp(data);
	
	
	}

    public void siftDown(Node [] data) {
    	int max = 1;
    
    	
while (max*2 < data.length && (max*2+1) < data.length && data[max*2] != null && data[(max*2)+1] != null
&& (data[max].count > data[max*2].count || data[max].count > data[(max*2) +1].count))	{
   
    	
    	if(data[max].count > data[(max*2)].count && data[(max*2)].count < data[(max*2)+1].count) {
    		
    		Node temp = data[max];
        	data [max] = data[max*2];
        	data[max*2] = temp;
        	max = max * 2;
    	}
    	else {
    		
    		Node temp = data [max];
        	data [max] = data[(max*2)+1];
        	data[(max*2)+1] = temp;
        	
        	max = (2*max)+1;
    	}

    	
    	}
    }
    
    public void siftUp(Node [] data) {
    	int max = numNodes;
    
  
    	while (data[max]!=null && data[max/2]!=null && max > 1 && data[max].count < data[max/2].count) {
    		Node temp = data[max/2];
        	data[max/2] = data[max];
        	data[max] = temp;
        	max = max/2;
    	}
    }
    
    public void resize() {
	Node[] temp = new Node[numNodes * 2 + 1];

	for(int i = 1; i <= numNodes; i++) {
	    temp[i] = data[i];
	}
	data = temp;
    }

    public PQHeap() {
	data = new Node[2];
    }

	public Node remove() {
		if(numNodes == 0) return null;

		swap();
		Node toReturn = data[numNodes];
		data[numNodes] = null;
		numNodes--;
		siftDown(data);
		
		return toReturn;
	    }
	
	public int size() {
		return numNodes;
	    }

	public boolean isEmpty() {
		return numNodes == 0;
	    }

	public void swap() {
		
		Node temp = data[1];
		data[1] = data[numNodes];
		data[numNodes] = temp;
		
	}
	public void print() {
		System.out.print("[");
		for(int i = 1; i <= numNodes; i++) {
		    System.out.print(data[i].count + " ");
		}
		System.out.println("]");
	    }


} 
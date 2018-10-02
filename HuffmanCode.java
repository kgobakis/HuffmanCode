/* Answer the questions in the assignment here
//
// (a) I tested all three texts. The first text (by Poe) used 88,528 Bits(8 Bits per character were counted) in its original form,
 while it used 52,175 in its binary coded form. The second text (by Swift) originally used 152,856 Bits while after being coded it used about a third 56,251. 
 The third text (by Andersen) initially used 156,408 Bits while when coded it used only 56,281 Bits.
//
//
//
// (b) From above we see that the count of Bits, after coding all three longish files, are pretty close. Therefore,
 I conclude that most longish text files produce a very similar Huffman tree.
//
//
//
*/
import java.io.*;
import java.util.Queue;


public class HuffmanCode {

	String [] codes = new String[127];
	
    public void run() throws IOException {
	int[] frequencies = getFrequencies("telltaleheart_poe.txt");

    	

	Node tree = makeTree(frequencies);

	String interesting = "";
	createCodes(interesting, tree);


	String message = readMessage("telltaleheart_poe.txt");
	String toDecode = encode(message, codes);
	String result = decode(toDecode, tree);
       	System.out.print(toDecode);

	
    }
    

    // input: the name of a text file
    // output: a length-127 array containing the number of times each 
    // character appears in the input file
    public int[] getFrequencies(String fileName) throws IOException {
    	
    	String fileContent = readMessage(fileName);
    		
    		int [] spectrum = new int [127];
    		
    		char[] file_char = fileContent.toCharArray();
    		
    		for (int i = 0; i <file_char.length; i++) {
    			int temp = (int)file_char[i];

    		spectrum[temp]+=1;
    		}
    		
    		return spectrum;
    }


    // input: an array of character frequencies
    // output: a pointer to the root of a Huffman tree built using the 
    // specified frequencies
    public Node makeTree(int[] frequencies) {
    	
    	PQHeap queue = new PQHeap();
    	
   for (int i = 0; i < frequencies.length; i++ ) {
	   //if(frequencies[i]==0) {continue;}
	   char temp = (char)i;
	   //System.out.println(frequencies[i] + " - " + temp);
	   queue.add(new Node (temp, frequencies[i]));
   }
   
   //queue.print();

   while (queue.numNodes > 1) {
	 
	   Node left = queue.remove();
	   
	   Node right = queue.remove();
	 //System.out.println(left.count + " " + right.count);
	   Node temp = new Node ('\0',left.count + right.count );
	   temp.left = left;
	   temp.right = right;
	   queue.add(temp);
	   
	   
   }
  // System.out.println("Root Tree Count: " + queue.data[1].count);
   return queue.remove();
    }


    // input: codes: an empty array to be filled in with the binary encodings
    //               for each character
    //        tree: the Huffman tree from which to read the encodings
    // at the end of the method, codes should contain the binary encoding of
    // each character

    public void createCodes(String interesting, Node tree) {

    	if (tree.left == null && tree.right == null && (int)tree.symbol > -1 && (int)tree.symbol < 127) {

    		codes[(int)tree.symbol] = interesting;
    		
    		//System.out.println((int)tree.symbol);
    		//System.out.println(codes[(int)tree.symbol] + " - " + tree.symbol);
    		return;
    	}
    	else {
    		
    		createCodes(interesting + "0", tree.left);
    		createCodes(interesting + "1", tree.right);
    		
    	}
    	
    	
    }

 
    // input: message: a text string that is to be encoded
    //        code: an array of the binary encoding to use for each character
    // output: a binary string containing the encoded version of the message
    public String encode(String message, String[] codes) {
    	char[] file_char = message.toCharArray();
    	//System.out.println(file_char.length);
    	String encoding = "";
    	
    	for(int i=0; i < file_char.length; i++) {
    		int temp = (int)file_char[i];
    		encoding = encoding + codes[temp];
    		
    		//System.out.println(i);
    	}
    	
    //	System.out.println(encoding);
    	return encoding;
    }


    // input: message: a binary string that is to be decoded
    //        tree: the Huffman tree to use to decode the message
    // output: a String containing the text decoding of the message
    public String decode(String message, Node tree) {
    	Node tempTree = tree;
    	String toReturn = "";
    	char[] file_char = message.toCharArray();
    	//System.out.println(file_char[file_char.length-2]);

	for(int k = 0; k <file_char.length-3;) {
    	tempTree = tree;
	//	System.out.print(file_char[k]);
    	
    	while(tempTree.left != null && tempTree.right != null) {
    		
    		if(file_char[k] == '1') {
    			tempTree = tempTree.right;
    			
    			if(tempTree.symbol != '\0') {
    			toReturn = toReturn + tempTree.symbol;
			//	System.out.println(tempTree.symbol);
    			}

    		}else {
    			tempTree = tempTree.left;
    			
    			if(tempTree.symbol != '\0') {
        			toReturn = toReturn + tempTree.symbol;
        			//System.out.print(tempTree.symbol);
        			}
    		}
		k++;	
    	}
    }
    //	System.out.print(toReturn);
    	return toReturn;
    }


    // input: the name of the file to be read
    // output: a String containing the contents of the file
    public String readMessage(String fileName) throws IOException {
	FileReader fr = new FileReader(fileName);
	String message = "";
	int nextChar = fr.read();
	while(nextChar != -1) {
	    message = message + (char)nextChar;
	    nextChar = fr.read();
	}
	
	return message;
    }


    public static void main(String [] args) {
	try {
	    new HuffmanCode().run();
	}
	catch(IOException e) {
	    e.printStackTrace();
	    System.exit(0);
	}
    }

}
public class Node {

    char symbol;
    int count = 0; // frequency
    Node left;
    Node right;

    public Node(char s, int c) {
	symbol = s;
	count = c;

    }
}
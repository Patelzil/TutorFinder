// CLASS: Node
//
// Author: Patel Zil, 7876456
//
// REMARKS: Store information (includes different data objects)
//          for the system.
//
//-----------------------------------------

public class Node
{
    private ListData data;
    private Node next;

    //public Node() { }

    public Node(ListData data, Node next)
    {
        this.data = data;
        this.next = next;
    }

    // getter methods
    public ListData getData() { return data; }
    public Node getNext() { return next; }

    // prints the current node and all the other nodes that follow it
    public void printDetail()
    {
        data.printDetail();
        if(next != null)
        {
            next.printDetail();
        }
    }// end printDetail

}// end Node class

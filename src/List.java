// CLASS: LinkedList
//
// Author: Patel Zil, 7876456
//
// REMARKS: A list to store all the students and tutors in the system
//
//-----------------------------------------

public class List
{

    public class ListIterator
    {
        private Node curr;

        public ListIterator()
        {
            curr = top;// look at the first node when we start.
        }

        public boolean hasNext()
        {
            return curr != null;
        }

        public Node next()
        {
            Node temp = curr;
            curr = curr.getNext();
            return temp;
        }
    }//  end ListIterator class

    public ListIterator iterator() {
        return new ListIterator();
    }

    private Node top;

    public List()
    {
        top = null;
    }// end LinkedList
    public Node getTop() { return top; }

    public void printDetail()
    {
        if (top != null) {
            top.printDetail();
        }

    }// end printDetail

    /*
     * insertFront(Object)
     *
     * Insert at the front of the list.
     * It does not insert duplicates
     */
    public void insertFront(ListData data)
    {
        if( search(data.getName()) == null)
        {
            top = new Node(data, top);
        }
    }// end insert

    /*
     * search(String)
     *
     * parameter: string "userID" or "topicName"
     *
     * search if a particular tutor or student exists in our list.
     * It also searches if a particular tutor teaches the topic passed as the parameter
     *
     * returns null if the linked list does not contain the key
     * else it returns the node that contains the key (true)
     */
    public Node search(String key)
    {
        Node curr = top;
        boolean found = false;

        while(curr != null && !found)
        {
            if( curr.getData().equals(key))
            {
                found = true;
            }
            else
            {
                curr = curr.getNext();
            }
        }

        return curr;
    }// end search

    /*
     * length()
     *
     * calculates and returns the number of nodes in the linked list
     *
     */

    public int length()
    {
        List.ListIterator iterator = this.iterator();
        int count = 0;

        while(iterator.hasNext())
        {
            count++;
            iterator.next();
        }

        return count;
    }// end length

}// end LinkedList class

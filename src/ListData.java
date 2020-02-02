// CLASS: ListData
//
// Author: Patel Zil, 7876456
//
// REMARKS: Parent class of Student, Tutor and Topic.
//         Helps in storing any one these objects in one node.
//
//-----------------------------------------

public abstract class ListData
{
    public abstract String getName();

    public boolean equals(String key){ return false; }

    public abstract void printDetail();
}// end ListData class

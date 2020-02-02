// CLASS: Tutor
//
// Author: Patel Zil, 7876456
//
// REMARKS: Create a tutor and store the students who
//          will be taught by the tutor.
//          Prints tutor report
//
//-----------------------------------------

public class Tutor extends ListData
{
    //private String dataName;
    private int numHours; // total number of hours per term
    private List topics; // list that stores all the topics taught by the tutor

    // variables used during assigning Tutors
    private int hoursUsed; // hours that have been already been given out for tutoring
    private int totalRevenue; // revenue earned from tutoring
    private List studentList; // holds all the students the tutor is tutoring

    public Tutor(String newID, int hours)
    {
        super(newID);
        numHours = hoursUsed = hours;
        topics = new List();
        studentList = new List();
    }

    // getters
    public String getName() { return dataName; }
    public int getNumHours() { return numHours; }
    public List getTopics() { return topics; }
    public int getHoursUsed() { return hoursUsed; }
    public List getStudentList() { return studentList;}

    // setters
    public void setNumHours(int numHours) { this.numHours = numHours; }
    public void setHoursUsed(int hoursUsed) { this.hoursUsed = hoursUsed; }
    public void setTotalRevenue(int totalRevenue) { this.totalRevenue += totalRevenue; }

    public boolean equals(String key)
    {
        return dataName.equals(key);
    }// end equals

    public void addTopic(Topic topic)
    {
        if(topics.search(topic.getName()) == null) // ignores duplicate topic
        {
            topics.insertFront(topic);
            System.out.println("Topic " + topic.getName() + " added to Tutor " + dataName + " with price " + topic.getRatePerHour());
        }
        else
        {
            System.out.println("Duplicate topic " + topic.getName() + " for Tutor " + dataName);
        }
    }// end addTopic

    /*
     * hasTopic
     *
     * return true if the tutor teaches a particular topic
     */
    public boolean hasTopic(String key)
    {
        boolean result = false;

        if(topics.search(key) != null)
        {
            result = true;
        }

        return result;
    }

    public void tutorReport()
    {
        List.ListIterator iterator = studentList.iterator();
        int totalHours = 0;

        while(iterator.hasNext())
        {
            StudentReport myStudent = (StudentReport) iterator.next().getData();
            totalHours += myStudent.getHours();
            myStudent.printDetail();
        }

        System.out.println("Total number of hours of tutoring: " + totalHours + "\nTotal revenue from Tutoring: " + totalRevenue + "\n");
    }

    public void processTutor(List list)
    {
        if(list.search(dataName) == null)
        {
            System.out.println("Tutor with dataName " + dataName + " successfully created.");
            list.insertFront(this);
        }
        else
        {
            System.out.println("Duplicate Tutor with dataName " + dataName + ".");
        }
    }

    public void printDetail()
    {
        System.out.println("Tutor: " + dataName + " Hours: " + numHours);
    }// end printDetail

}// end Tutor class

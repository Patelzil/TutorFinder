// CLASS: Topic
//
// Author: Patel Zil, 7876456
//
// REMARKS: Create a topic to store and implement in Tutors and request
//
//-----------------------------------------

public class Topic extends ListData
{
    private int ratePerHour;

    // constructor
    public Topic(String topic, int rate)
    {
        super(topic);
        ratePerHour = rate;
    }

    public String getName() { return dataName; }
    public int getRatePerHour() { return ratePerHour; }

    public boolean equals(String key)
    {
        return dataName.equals(key);
    }// end equals

    public void processTopic(List ttrList, String tutorName)
    {
        if(ttrList.search(tutorName) != null)
        {
            Tutor temp = (Tutor) ttrList.search(tutorName).getData();
            temp.addTopic(this);
        }
        else
        {
            System.out.println("Tutor " + tutorName + " not found.");
        }
    }// end processTopic

    public void printDetail()
    {
        System.out.println("Topic " + dataName + " with price " + ratePerHour + ".");
    }// end printDetail

}// end Topic class


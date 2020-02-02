// CLASS: Topic
//
// Author: Patel Zil, 7876456
//
// REMARKS: Create a topic to store and implement in Tutors and request
//
//-----------------------------------------

public class Topic extends ListData
{
    private String topicName;
    private int ratePerHour;

    // constructor
    public Topic(String topic, int rate)
    {
        topicName = topic;
        ratePerHour = rate;
    }

    public String getName() { return topicName; }
    public int getRatePerHour() { return ratePerHour; }

    public boolean equals(String key)
    {
        return topicName.equals(key);
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
        System.out.println("Topic " + topicName + " with price " + ratePerHour + ".");
    }// end printDetail

}// end Topic class


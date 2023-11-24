package source.FileIO.Serializer.Text;

import source.Entity.Camp;
import source.Entity.CampInfo;
import source.Entity.Staff;
import source.Utility.SerializeBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * The CampSerializer class serializes a list of Camp objects into an array list of strings ready to be written
 * into a text file of choice.
 *
 * @author Isaac Chun
 * @version 1.0
 * @see source.Entity.Camp
 * @since 11/4/2023
 */
public class CampSerializer extends BaseSerializer implements TextDataSerializer {

    /**
     * Holds the headers of our csv files.
     */
    private final String[] campHeader = {
            "camp_name",
            "location",
            "current_slot",
            "max_slots",
            "committee_slots",
            "max_committee_slots",
            "description",
            "staff_in_charge",
            "start_date",
            "end_date",
            "closing_date",
            "faculty",
            "visibility"

    };
    private final String[] campAttendeesHeader = {
            "campid",
            "userid",
            "committee_members"};

    /**
     * A default constructor.
     */
    public CampSerializer() {
        super();
    }

    /**
     * An overloaded constructor that specifies the delimiter on how to seperate entries in our file
     *
     * @param delimiter seperating character between entries
     */
    public CampSerializer(char delimiter, boolean useHeader) {
        super(delimiter, useHeader);
    }

    /**
     * Serializes a list of objects into a string ready to be put into our other classes.
     *
     * @param objects An arraylist of objects to serialize
     */
    @Override
    public ArrayList<String> serialize(List objects) {
        //There are three we have to write into for camp,
        // one for camp list, one for camp attendees, one for enquiries.
        
        ArrayList<String> serializedData = new ArrayList<String>();
        if (useHeader) {
            String serializedHeader = SerializeBuilder.buildSerializedString(campHeader, delimiter);
            //Add the header line
            serializedData.add(serializedHeader);
        }
        //Loop through the objects
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i) instanceof Camp camp) {
                //Build our string
                CampInfo campInfo = camp.getCampInfo();
                String campData = SerializeBuilder.buildSerializedString(
                        new String[]{
                                campInfo.getName(),
                                campInfo.getLocation(),
                                String.valueOf(campInfo.getCurrentSlots()),
                                String.valueOf(campInfo.getMaxSlots()),
                                String.valueOf(campInfo.getCampCommitteeSlots()),
                                String.valueOf(campInfo.getMaxCampCommitteeSlots()),
                                campInfo.getDescription(),
                                campInfo.getStaffInCharge(),
                                campInfo.getStartDate().toString(),
                                campInfo.getEndDate().toString(),
                                campInfo.getClosingDate().toString(),
                                campInfo.getFaculty().getClass().getSimpleName(),
                                String.valueOf(camp.getVisibility())
                        },
                        delimiter
                );
                serializedData.add(campData);
            }
        }
        return serializedData;
    }
}

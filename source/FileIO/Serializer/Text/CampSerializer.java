package source.FileIO.Serializer.Text;

import source.Entity.Camp;
import source.Entity.CampInfo;
import source.Entity.Student;
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
            "visibility",
            "attendees",
            "camp_committee",
            "blacklisted"

    };

    /**
     * A default constructor.
     */
    public CampSerializer() {
        super();
    }

    /**
     * An overloaded constructor that specifies the delimiter on how to separate entries in our file
     *
     * @param delimiter separating character between entries
     */
    public CampSerializer(char delimiter, boolean useHeader) {
        super(delimiter, useHeader);
    }

    /**
     * Serializes a list of objects into a string ready to be put into our other classes.
     *
     * @param objects An arraylist of objects to serialize
     * @return list of strings in serialized format
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

                //Get the lists
                ArrayList<Student> attendees = camp.getAttendees();
                ArrayList<Student> committeeMembers = camp.getCampCommitteeMembers();
                ArrayList<Student> blacklistedMembers = camp.getBlacklisted();

                String[] attendeeNames = new String[attendees.size()];
                String[] committeeNames = new String[committeeMembers.size()];
                String[] blacklistedNames = new String[blacklistedMembers.size()];

                //Convert them into string[]
                for (int j = 0; j < attendees.size(); j++) {
                    attendeeNames[j] = attendees.get(j).getName();
                }
                for (int j = 0; j < committeeMembers.size(); j++) {
                    committeeNames[j] = committeeMembers.get(j).getName();
                }
                for (int j = 0; j < blacklistedMembers.size(); j++) {
                    blacklistedNames[j] = blacklistedMembers.get(j).getName();
                }
                //Build attendee string
                String serializedAttendees = SerializeBuilder.buildSerializedString(attendeeNames,
                        '|'
                );
                //Build committee string
                String serializedCommittee = SerializeBuilder.buildSerializedString(committeeNames,
                        '|'
                );
                //Build blacklist string
                String serializedBlacklist = SerializeBuilder.buildSerializedString(blacklistedNames,
                        '|'
                );
                if (serializedAttendees.isEmpty()) {
                    serializedAttendees = "N/A";
                }
                if (serializedCommittee.isEmpty()) {
                    serializedCommittee = "N/A";
                }
                if (serializedBlacklist.isEmpty()) {
                    serializedBlacklist = "N/A";
                }

                //Parse the description data
                String description = campInfo.getDescription();
                description = description.replace(',', '|');

                String campData = SerializeBuilder.buildSerializedString(
                        new String[]{
                                campInfo.getName(),
                                campInfo.getLocation(),
                                String.valueOf(campInfo.getCurrentSlots()),
                                String.valueOf(campInfo.getMaxSlots()),
                                String.valueOf(campInfo.getCampCommitteeSlots()),
                                String.valueOf(campInfo.getMaxCampCommitteeSlots()),
                                description,
                                campInfo.getStaffInCharge(),
                                campInfo.getStartDate().toString(),
                                campInfo.getEndDate().toString(),
                                campInfo.getClosingDate().toString(),
                                campInfo.getFaculty().getClass().getSimpleName(),
                                String.valueOf(camp.getVisibility()),
                                serializedAttendees,
                                serializedCommittee,
                                serializedBlacklist
                        },
                        delimiter
                );
                serializedData.add(campData);
            }
        }
        return serializedData;
    }
}

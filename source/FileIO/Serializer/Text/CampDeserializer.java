package source.FileIO.Serializer.Text;

import source.Controllers.EnquiryManager;
import source.Controllers.StaffManager;
import source.Controllers.StudentManager;
import source.Database.App;
import source.Database.DatabaseQuery;
import source.Entity.*;
import source.Faculty.Faculty;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The CampDeserializer class deserializes a hashmap of parsed data a list of Camp objects to be used.
 * NOTE: Requires a file read to go through the Parser!
 *
 * @author Isaac Chun
 * @version 1.0
 * @see Camp
 * @since 11/4/2023
 */
public class CampDeserializer implements TextDataDeserializer {
    /**
     * Deserializes the data to and creates camp objects based on a hash map of values.
     *
     * @param parsedData the parsed data
     * @return list of camp objects
     */
    @Override
    public ArrayList deserialize(HashMap<String, ArrayList<String>> parsedData) {
        StudentManager manager = App.getStudentManager();
        StaffManager staffManager = App.getStaffManager();
        EnquiryManager enquiryManager = App.getEnquiryManager();
        ArrayList campList = new ArrayList();
        int len = parsedData.get("camp_name").size();
        if (len == 0)
            return new ArrayList();
        for (int i = 0; i < len; i++) {
            //Get all the individual attributes
            String campName = parsedData.get("camp_name").get(i);
            String location = parsedData.get("location").get(i);
            int currentSlots = Integer.parseInt(parsedData.get("current_slot").get(i));
            int maxSlots = Integer.parseInt(parsedData.get("max_slots").get(i));
            int committeeSlots = Integer.parseInt(parsedData.get("committee_slots").get(i));
            int maxCommitteeSlots = Integer.parseInt(parsedData.get("max_committee_slots").get(i));
            String description = parsedData.get("description").get(i);
            description = description.replace('|', ',');
            String staffID = parsedData.get("staff_in_charge").get(i);
            LocalDate startDate = LocalDate.parse(parsedData.get("start_date").get(i));
            LocalDate endDate = LocalDate.parse(parsedData.get("end_date").get(i));
            LocalDate closingDate = LocalDate.parse(parsedData.get("closing_date").get(i));
            boolean visibility = Boolean.valueOf(parsedData.get("visibility").get(i));
            String faculty = "source.Faculty." + parsedData.get("faculty").get(i);
            Faculty f = null;
            try {
                f = (Faculty) Class.forName(faculty).getDeclaredConstructor().newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            //Make our camp object
            Camp camp = new Camp(new CampInfo(
                    campName,
                    location,
                    0,
                    maxSlots,
                    0,
                    maxCommitteeSlots,
                    description,
                    staffID,
                    startDate,
                    endDate,
                    closingDate,
                    f
            ),
                    visibility,
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new ArrayList<>());

            //Handle the staff camp list
            Staff staff = staffManager.readStaff(
                    new DatabaseQuery(staffID, "name"));
            if (staff != null) {
                staff.addCreatedCamp(camp);
            }

            //Handle the mapping of attendees and committee members
            String attendee = parsedData.get("attendees").get(i);
            String committee = parsedData.get("camp_committee").get(i);
            //Split by our specified delimiter
            String[] attendees = attendee.split("\\|");
            String[] committeeMembers = committee.split("\\|");

            //Then make our array list
            ArrayList<Student> attendeeList = new ArrayList<Student>();
            ArrayList<Student> committeeList = new ArrayList<Student>();

            if (!attendee.equals("N/A")) {
                //Handle attendees
                for (String s : attendees) {
                    //Use the dao to get our student object
                    Student student = manager.readStudent(
                            new DatabaseQuery(s, "name"));
                    if (student != null) {
                        student.addRegisteredCamps(camp);
                        attendeeList.add(student);
                    }
                }
            }
            if (!committee.equals("N/A")) {
                //Handle committee members
                for (String s : committeeMembers) {
                    //Use the dao to get our student object
                    Student student = manager.readStudent(
                            new DatabaseQuery(s, "name"));
                    if (student != null) {
                        student.setIsCampCommittee(camp);
                        committeeList.add(student);
                    }
                }
            }

            //Initialize enquiries into the camps
            ArrayList<Enquiry> enquiries = enquiryManager.readEnquiries(new DatabaseQuery(campName, "camp_name"));
            //Initialize all final lists
            camp.getCampInfo().setCurrentSlots(attendeeList.size());
            camp.getCampInfo().setCampCommitteeSlots(committeeList.size());
            camp.setAttendees(attendeeList);
            camp.setCampCommittee(committeeList);
            camp.setEnquiryList(enquiries);
            campList.add(camp);
        }
        return campList;
    }
}

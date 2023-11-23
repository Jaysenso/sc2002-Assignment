package source.Database;

import source.Entity.*;
import source.Faculty.ADM;
import source.Faculty.NBS;
import source.Faculty.SCSE;

import java.time.LocalDate;
import java.util.ArrayList;

public class CampFakeData {
    public static ArrayList<Camp> campList = new ArrayList<>() {
        {
            add(new Camp(
                    new CampInfo("lj camp", 30, 30, 3, "camp for lj", "DIMITRI USTIUGOV", LocalDate.of(2024, 1, 18), LocalDate.of(2024, 1, 23), LocalDate.of(2023, 12, 23), new SCSE()),
                    true,
                    new ArrayList<>() {{
                        add(new Enquiry("hihi", "hello", false));
                        add(new Enquiry("harlo", "halo", false));
                        add(new Enquiry("good morn", "good morning", false));
                        add(new Enquiry("gud nite", "good night", false));
                        add(new Enquiry("gud day", "good DAY", false));
                    }},
                    new ArrayList<>() {{
                        add(new Student());
                        add(new Student());
                        add(new Student());
                        add(new Student());
                    }},
                    new ArrayList<>() {{
                        add(new Student());
                        add(new Student());
                        add(new Student());
                    }}
            ));
            add(new Camp(
                    new CampInfo("cb camp", 30, 30, 3, "camp for cb", "CAI WEN TONG", LocalDate.of(2024, 2, 18), LocalDate.of(2024, 2, 23), LocalDate.of(2023, 12, 25), new NBS()),
                    true,
                    new ArrayList<>() {{
                        add(new Enquiry("1", "2", false));
                        add(new Enquiry("3", "4", false));
                        add(new Enquiry("5", "6", false));
                        add(new Enquiry("7", "8", false));
                        add(new Enquiry("9", "10", false));
                    }},
                    new ArrayList<>() {{
                        add(new Student());
                        add(new Student());
                        add(new Student());
                        add(new Student());
                    }},
                    new ArrayList<>() {{
                        add(new Student());
                        add(new Student());
                        add(new Student());
                    }}
            ));
            add(new Camp(
                    new CampInfo("tt camp", 30, 30, 3, "camp for tt", "ARVIND", LocalDate.of(2024, 3, 18), LocalDate.of(2024, 3, 23), LocalDate.of(2023, 12, 29), new ADM()),
                    true,
                    new ArrayList<>() {{
                        add(new Enquiry("a", "b", false));
                        add(new Enquiry("c", "d", false));
                        add(new Enquiry("e", "f", false));
                        add(new Enquiry("g", "h", false));
                        add(new Enquiry("i", "j", false));
                    }},
                    new ArrayList<>() {{
                        add(new Student());
                        add(new Student());
                        add(new Student());
                        add(new Student());
                    }},
                    new ArrayList<>() {{
                        add(new Student());
                        add(new Student());
                        add(new Student());
                    }}
            ));
        }
    };
}

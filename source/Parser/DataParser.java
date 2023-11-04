package source.Parser;

import source.FileIO.TextFormattedFile;

import java.util.ArrayList;
import java.util.HashMap;

public interface DataParser {
    HashMap<String, ArrayList<String>> parse(TextFormattedFile file);
}

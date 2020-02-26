package persistence;

import java.io.PrintWriter;

// Represents data that can be saved to file.
// *Source: TellerApp
public interface Saveable {

    // MODIFIES: printWriter
    // EFFECTS: writes the saveable to printWriter
    void save(PrintWriter printWriter);
}

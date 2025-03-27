package Features;

import java.sql.*;

public interface Actions {
    void showActionsInformation();
    String readUserInput();
    void executeAction(String command);
}

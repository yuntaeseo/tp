package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2526s1-cs2103t-t16-1.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Refer to the user guide: " + USERGUIDE_URL + "\n\n" + 
                "Add connection\n" + 
                "`add n/NAME p/PHONE_NUMBER e/EMAIL [a/ADDRESS] [t/TAG]…​ [r/NOTE]`\n" + 
                "e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/1 2 r/owes me lunch`\n\n" + 
                "Clear connection list \n" + 
                "`clear`\n\n" +
                "Delete connection \n" +
                "`delete ID` \n" +
                "e.g., `delete 3` \n\n" +
                "Edit connection \n" +
                "`edit ID [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​ [r/NOTE]` \n" +
                "e.g.,`edit 2 n/James Lee e/jameslee@example.com` \n\n" +
                "Find connection\n" +
                "`find KEYWORD [MORE_KEYWORDS]`\n" +
                "e.g., `find James Jake` \n\n" +
                "List connection \n" +
                "`list`\n\n" +
                "Add tag\n" +
                "`addtag n/NAME [d/DESCRIPTION] [c/RGB_COLOR]`\n" +
                "e.g. `addtag n/JC d/JC friends c/23f1cd` \n\n" +
                "Delete tag\n" +
                "`deletetag ID`\n" +
                "e.g. `deletetag 2` \n\n" +
                "Edit tag\n" +
                "`edittag ID [n/NAME] [d/DESCRIPTION] [c/RGB_COLOR]`\n" +
                "e.g. `edittag 1 d/my extended family c/099fca`\n\n" +
                "List tag\n" +
                "`listtag`\n\n" +
                "Add relationship\n" +
                "`addrel p1/CONNECTION_1 p2/CONNECTION_2 d/DESCRIPTION`\n" +
                "e.g. `addrel p1/1 p2/2 d/friends`\n\n" +
                "List relationships\n" +
                "`listrel p1/CONNECTION_1 [p2/CONNECTION_2]`\n" +
                "e.g. `listrel p1/1 p2/4` \n\n" +
                "Edit relationship\n" +
                "`editrel p1/CONNECTION_1 p2/CONNECTION_2 d/DESCRIPTION`\n" +
                "e.g. `editrel p1/1 p2/2 d/enemies` \n\n" +
                "Delete relationship\n" +
                "`deleterel p1/CONNECTION_1 p2/CONNECTION_2`\n" +
                "e.g. `deleterel p1/1 p2/2`\n\n" +
                "Exit program\n" +
                "`exit`\n\n" +
                "Help\n" +
                "`help`";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
}

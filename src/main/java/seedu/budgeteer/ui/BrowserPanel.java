package seedu.budgeteer.ui;

import static java.util.Objects.requireNonNull;

import java.net.URL;
import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.web.WebView;
import seedu.budgeteer.MainApp;
import seedu.budgeteer.commons.core.LogsCenter;
import seedu.budgeteer.model.entry.Entry;

/**
 * The Browser Panel of the App.
 */
public class BrowserPanel extends UiPart<Region> {

    public static final URL DEFAULT_PAGE =
            requireNonNull(MainApp.class.getResource(FXML_FILE_FOLDER + "default.html"));
    public static final String SEARCH_PAGE_URL = "https://se-education.org/dummy-search-page/?name=";

    private static final String FXML = "BrowserPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    private WebView browser;
    @FXML
    private Label nameLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label cashFlowLabel;
    @FXML
    private Label tagsLabel;

    public BrowserPanel(ObservableValue<Entry> selectedEntry) {
        super(FXML);

        // To prevent triggering events for typing inside the loaded Web page.
        getRoot().setOnKeyPressed(Event::consume);

        // Load entry page when selected entry changes.
        selectedEntry.addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                loadDefaultPage();
                return;
            }
            loadEntryPage(newValue);
        });

        loadDefaultPage();
    }

    /**
     * Loads a page based on given entry
     * @param entry
     */
    private void loadEntryPage(Entry entry) {
        nameLabel.setText("Name: " + entry.getName().fullName);
        dateLabel.setText("Date: " + entry.getDate().getValue());
        cashFlowLabel.setText("Cashflow: " + entry.getCashFlow().value);
        tagsLabel.setText("Tags: " + entry.getTags().toString());
    }



    /**
     * Loads a default HTML file with a background that matches the general theme.
     */
    private void loadDefaultPage() {
        nameLabel.setText("");
        dateLabel.setText("");
        cashFlowLabel.setText("");
        tagsLabel.setText("");
    }

}

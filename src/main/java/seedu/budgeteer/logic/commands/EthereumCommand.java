package seedu.budgeteer.logic.commands;

import static seedu.budgeteer.model.Model.PREDICATE_SHOW_ALL_ENTRYS;

import javafx.collections.ObservableList;
import seedu.budgeteer.commons.util.CryptoUtil;
import seedu.budgeteer.logic.CommandHistory;
import seedu.budgeteer.model.Model;
import seedu.budgeteer.model.entry.Entry;
import seedu.budgeteer.model.entry.ReportEntryList;


/**
 * Returns how many Ethereum you can buy at the current market price.
 */
public class EthereumCommand extends Command {

    public static final String COMMAND_WORD = "ethereum";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays how much ethereum you can buy.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS_HEADER = "You are able to buy ";

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        CryptoUtil cryptoUtil = CryptoUtil.getInstance();
        double price = cryptoUtil.getEth();

        model.updateFilteredEntryList(PREDICATE_SHOW_ALL_ENTRYS);
        ObservableList<Entry> filteredList = model.getFilteredEntryList();
        ReportEntryList reportList = new ReportEntryList(filteredList);
        Double total = reportList.getTotal();

        Double amount = total / price;
        amount = (double) Math.round(amount * 100.0) / 100.0;

        String successMessage = MESSAGE_SUCCESS_HEADER + amount.toString() + " ETH.";

        int roundOff = (int) Math.round(price);

        String currentPrice = " The current price of ethereum is $" + roundOff + ".";
        successMessage = successMessage + currentPrice;
        return new CommandResult(successMessage);
    }
}

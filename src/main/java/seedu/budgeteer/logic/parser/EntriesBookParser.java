package seedu.budgeteer.logic.parser;

import static seedu.budgeteer.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.budgeteer.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.budgeteer.logic.commands.AddCommand;
import seedu.budgeteer.logic.commands.BitcoinCommand;
import seedu.budgeteer.logic.commands.ClearCommand;
import seedu.budgeteer.logic.commands.Command;
import seedu.budgeteer.logic.commands.CryptoCommand;
import seedu.budgeteer.logic.commands.DeleteCommand;
import seedu.budgeteer.logic.commands.DisplayCommand;
import seedu.budgeteer.logic.commands.EditCommand;
import seedu.budgeteer.logic.commands.EthereumCommand;
import seedu.budgeteer.logic.commands.ExitCommand;
import seedu.budgeteer.logic.commands.ExportExcelCommand;
import seedu.budgeteer.logic.commands.FilterCommand;
import seedu.budgeteer.logic.commands.FindCommand;
import seedu.budgeteer.logic.commands.HelpCommand;
import seedu.budgeteer.logic.commands.HistoryCommand;
import seedu.budgeteer.logic.commands.InvestCommand;
import seedu.budgeteer.logic.commands.ListCommand;
import seedu.budgeteer.logic.commands.LitecoinCommand;
import seedu.budgeteer.logic.commands.LockCommand;
import seedu.budgeteer.logic.commands.RedoCommand;
import seedu.budgeteer.logic.commands.ReportCommand;
import seedu.budgeteer.logic.commands.SelectCommand;
import seedu.budgeteer.logic.commands.StockCommand;
import seedu.budgeteer.logic.commands.UndoCommand;
import seedu.budgeteer.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class EntriesBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());

        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case SelectCommand.COMMAND_WORD:
            return new SelectCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case DisplayCommand.COMMAND_WORD:
            return new DisplayCommandParser().parse(arguments);

        case HistoryCommand.COMMAND_WORD:
            return new HistoryCommand();

        case BitcoinCommand.COMMAND_WORD:
            return new BitcoinCommandParser().parse(arguments);

        case EthereumCommand.COMMAND_WORD:
            return new EthereumCommandParser().parse(arguments);

        case LitecoinCommand.COMMAND_WORD:
            return new LitecoinCommandParser().parse(arguments);

        case StockCommand.COMMAND_WORD:
            return new StockCommandParser().parse(arguments);

        case CryptoCommand.COMMAND_WORD:
            return new CryptoCommandParser().parse(arguments);

        case InvestCommand.COMMAND_WORD:
            return new InvestCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();

        case FilterCommand.COMMAND_WORD:
            return new FilterCommandParser().parse(arguments);

        case ReportCommand.COMMAND_WORD:
            return new ReportCommandParser().parse(arguments);

        case LockCommand.COMMAND_WORD:
            return new LockCommandParser().parse(arguments);

        case ExportExcelCommand.COMMAND_WORD:
            return new ExportExcelCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

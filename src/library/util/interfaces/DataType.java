package library.util.interfaces;

/**
 * DataType is an interface that ensures that all types of data collected in the system
 * can be shown to the console via a presenter.  This interface also neatly encapsulates data.
 * Any type that stores data should implement this interface.
 *
 * @author group_0236
 */
public interface DataType {

    /**
     * Show the contents of the data type.
     * @return String
     */
    String show();
}

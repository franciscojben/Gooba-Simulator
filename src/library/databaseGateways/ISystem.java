package library.databaseGateways;

import library.util.interfaces.IManager;

import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Interface for database gateways
 */
public abstract class ISystem implements PropertyChangeListener{
    protected final String filePath;
    protected final IManager manager;

    /**
     * Creates an instance of the ISystem
     * @param filePath String
     * @param manager IManager
     */
    protected ISystem(String filePath, IManager manager) {
        this.filePath = filePath;
        this.manager = manager;
    }

    /**
     * Overwrite the database with the new data
     * @param data data to be written to the database
     * @throws IOException file not found exception
     */
    public abstract void dbRefresh(Object data) throws IOException;

    /**
     * @param userDBFilePath File Path of database
     * @return Data object that is formatted to be usable by the program
     * @throws FileNotFoundException file not found exception
     */
    public abstract Object readFromFile(String userDBFilePath) throws FileNotFoundException;
}

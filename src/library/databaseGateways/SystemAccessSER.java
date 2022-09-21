package library.databaseGateways;

import library.util.interfaces.IManager;

import java.beans.PropertyChangeEvent;
import java.io.*;
import java.util.Objects;


/**
 * Gateway between database and program
 */
public class SystemAccessSER extends ISystem{
    /**
     * Create an instance of SystemAccess for serializable databases
     * @param filePath database file path
     * @param manager the manager that will hold this data in memory
     */
    public SystemAccessSER(String filePath, IManager manager) {
        super(filePath, manager);
    }

    /**
     * @param data data to be written to the database
     * @throws IOException file not found
     */
    @Override
    public void dbRefresh(Object data) throws IOException {
        this.saveToFile(this.filePath, data);
    }

    /**
     * @param filePath File Path of database
     * @return formatted object of data in the file
     */
    public Object readFromFile(String filePath) {
        try{
            InputStream file = new FileInputStream(filePath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            Object data = input.readObject();
            input.close();
            return data;
        }catch(EOFException e){
            System.out.println("New file");
        }catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * @param filePath Filepath of the database
     * @param object The object to be saved to the file
     * @throws IOException file not found exception
     */
    public void saveToFile(String filePath, Object object) throws IOException{
        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        output.writeObject(object);
        output.close();
    }


    /**
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(Objects.equals(evt.getPropertyName(), "userUpdate") || Objects.equals(evt.getPropertyName(), "petsUpdate")) {
            try {
                this.dbRefresh(evt.getNewValue());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

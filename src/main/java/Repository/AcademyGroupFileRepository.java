package Repository;

import Entities.AcademyGroup;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AcademyGroupFileRepository implements IRepository<AcademyGroup> {
    private final String FILENAME;
public AcademyGroupFileRepository(String fileName) {
    FILENAME = fileName;
    createFileIfDoesNotExist();

}

    private void createFileIfDoesNotExist() {
        File file = new File(FILENAME);
        try {
            if (file.exists()) {
                System.out.println(FILENAME + " is exists");

            } else {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
@SuppressWarnings("unchecked")
    @Override
    //this method is useful for retrieving a list of AcademyGroup objects that have been previously serialized to a file.


    public List<AcademyGroup> GetAll() {
        List<AcademyGroup> academyGroups = new ArrayList<>();
        try (ObjectInputStream objectInputStream  = new ObjectInputStream(new FileInputStream(FILENAME))) {
            academyGroups = (List<AcademyGroup>) objectInputStream.readObject(); //read and deserializes an object from the file
            //if the file contains smth else, or is corrupted, it could throw a ClassCastEx or IOException

        } catch (EOFException ex) { //if empty

        } catch (Exception ex) { //generic exception
            ex.printStackTrace();
//        } finally {
//            objectInputStream.close();
//            FileInputStream.close();
//
        }

            // we don't need finally block because it will automatically close in try catch block
        return academyGroups; //returns the list of academy groups, which could be empty or filled with the data read from the file
    }

    @Override
    // method to retrieve specific objects from a potentially larger dataset,
    // assuming that the id field uniquely identifies each AcademyGroup.
    // This method leverages the list of all groups provided by GetAll()
    // and performs a linear search to find the required item.

    public AcademyGroup GetById(int id) {
    List<AcademyGroup> academyGroups = GetAll(); //presumably retrieved from a file or a database
    for (AcademyGroup academyGroup : academyGroups) {
        if (academyGroup.getId() == id) {
            return academyGroup;
        }
    }
        return null;
    }

    @Override
    public void Add(AcademyGroup academyGroup) {
    List<AcademyGroup> academyGroups = GetAll(); //application heap
    academyGroups.add(academyGroup); // your changes affect only heap
    SaveChanges(academyGroups); //let's update our file with data from heap
    }

    private void SaveChanges(List<AcademyGroup> academyGroups) {
    try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
        objectOutputStream.writeObject(academyGroups);

    } catch (Exception ex) {
        ex.printStackTrace();
    }
    }

    @Override
    public void Update(AcademyGroup academyGroupToUpdate) {
    List<AcademyGroup> academyGroups = GetAll();
    for (int i = 0; i<academyGroups.size(); i++) {
        if (academyGroups.get(i).getId() == academyGroupToUpdate.getId()) {
            academyGroups.set(i, academyGroupToUpdate);
        }
    }
    SaveChanges(academyGroups);

    }

    @Override
    public void Remove(int id) {
    List<AcademyGroup> academyGroups = GetAll();
    academyGroups.removeIf(group ->group.getId() == id);
    SaveChanges(academyGroups);

    }
}

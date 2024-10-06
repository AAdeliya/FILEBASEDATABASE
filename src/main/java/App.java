import Entities.AcademyGroup;
import Repository.AcademyGroupFileRepository;

import Repository.IRepository;

public class App {
    public static void main(String[] args) throws Exception {
        IRepository<AcademyGroup> groupRepository = new AcademyGroupFileRepository("group.file");
//        groupRepository.Add(new AcademyGroup(0, "group 2024"));
//        groupRepository.Add(new AcademyGroup(1, "group 2025"));
//        groupRepository.Remove(0);
        AcademyGroup academyGroup = groupRepository.GetById(1);


//        for (AcademyGroup academyGroup : groupRepository.GetAll()) {
            System.out.println("id= " + academyGroup.getId() + "; name=" + academyGroup.getName());

        }

    }

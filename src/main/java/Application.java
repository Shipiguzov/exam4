import dao.AlpinistDao;
import dao.GroupDao;
import dao.MountainDao;
import entity.Alpinist;
import entity.Group;
import entity.Mountain;
import specification.AlpinistAgeBetween;
import specification.GroupListOpenAdditional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        Group group = new Group();
        Group group1 = new Group();
        Alpinist alpinist = new Alpinist("Ivan", 32, "SpbRussia");
        Alpinist alpinist2 = new Alpinist("Vlad", 23, "Moscow");
        Alpinist alpinist3 = new Alpinist("Nicolay", 43, "Kaliningrad");
        Mountain mountain = new Mountain("Elbrus", 5648);
        Mountain mountain1 = new Mountain("sadasd", 2456);
        Mountain mountain2 = new Mountain("12321", 6573, "Ukrain");
        mountain.setCountry("Russia");
        mountain1.setCountry("Russia");

        group.setDate(LocalDate.of(2021, 6, 10));
        group.addAlpinist(alpinist);
        group.addAlpinist(alpinist2);
        group.addAlpinist(alpinist3);
        group.setDuration(10);
        group.setMountain(mountain);

        group1.setDate(LocalDate.of(2021, 6, 15));
        group1.addAlpinist(alpinist2);
        group1.addAlpinist(alpinist3);
        group1.setDuration(20);
        group1.setMountain(mountain);

        Group group2 = new Group(mountain1, false, LocalDate.of(2021, 6, 12));

        EntityManagerFactory factory = Persistence.
                createEntityManagerFactory("entityManager");
        EntityManager manager = factory.createEntityManager();

        AlpinistDao alpinistDao = new AlpinistDao(manager);
        GroupDao groupDao = new GroupDao(manager);
        MountainDao mountainDao = new MountainDao(manager);

        manager.getTransaction().begin();
        alpinistDao.add(alpinist);
        alpinistDao.add(alpinist2);
        alpinistDao.add(alpinist3);
        groupDao.add(group);
        groupDao.add(group1);
        groupDao.add(group2);
        mountainDao.add(mountain);
        mountainDao.add(mountain1);
        manager.getTransaction().commit();

        List<Alpinist> alpinistList = alpinistDao.getBySpecification(new AlpinistAgeBetween(20, 35));
        List<Group> groupsByAdditional = groupDao.groupListOpenAdditional(new GroupListOpenAdditional());
        List<Group> groups = groupDao.getGroupsByMountain("Elbrus");
        List<Mountain> mountains = mountainDao.getMountainByCountry("Russia");

        for (Alpinist alpinist1 : alpinistList) {
            System.out.println(alpinist1.getName() + " " + alpinist1.getAge());
        }
        for (Group iter : groups) {
            System.out.println(iter.getDate());
        }
        for (Mountain iter : mountains) {
            System.out.println(iter.getName());
        }
        for (Group iter : groupsByAdditional) {
            System.out.println(iter.getDate());
        }
    }
}

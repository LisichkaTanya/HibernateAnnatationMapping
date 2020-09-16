package domain;

import bl.HibernateUtil;
import entity.Address;
import entity.Employee;
import entity.Project;
import org.hibernate.Session;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class Domain {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Address address = new Address();
        address.setCountry("Russia");
        address.setCity("Moscow");
        address.setStreet("Lisichka, 14");
        address.setPostCode("232547");

        Project project = new Project();
        project.setTitle("4856");

        Employee employee = new Employee();
        employee.setFirstName("Tatiana");
        employee.setLastName("Solomatina");
        Calendar calendar = Calendar.getInstance();
        calendar.set(1982, Calendar.JULY, 15);
        employee.setBirthday(new Date(calendar.getTime().getTime()));
        employee.setAddress(address);

        //В маппинге мы этого сета не делали
        Set<Employee> employees = new HashSet<>();
        employees.add(employee);
        project.setEmployees(employees);


        Set<Project> projects = new HashSet<>();
        projects.add(project);
        employee.setProjects(projects);

        session.save(address);
        session.save(employee);
        session.save(project);

        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }
}

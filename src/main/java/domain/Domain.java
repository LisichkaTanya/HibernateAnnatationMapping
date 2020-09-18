package domain;

import bl.HibernateUtil;
import entity.Address;
import entity.Employee;
import entity.Project;
import service.AddressService;
import service.EmployeeService;
import service.ProjectService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Domain {
    public static void main(String[] args) throws SQLException {
        AddressService addressService = new AddressService();
        EmployeeService employeeService = new EmployeeService();
        ProjectService projectService = new ProjectService();

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

        List<Address> a = addressService.getAll();
        System.out.println(a);
//        addressService.add(address);
//        employeeService.add(employee);

        //projectService.add(project); так как у нас Cascade.ALL то это можно удалить, иначе задвоится проект

        HibernateUtil.shutdown();
    }
}

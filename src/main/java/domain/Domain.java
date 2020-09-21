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
        address.setStreet("Lesnoy, 13");
        address.setPostCode("448337");

        Project project = new Project();
        project.setTitle("Generic");

        Employee employee = new Employee();
        employee.setFirstName("Daria");
        employee.setLastName("Solomatina");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2010, Calendar.NOVEMBER, 14);
        employee.setBirthday(new Date(calendar.getTime().getTime()));
        employee.setAddress(address);

        //В маппинге мы этого сета не делали
        Set<Employee> employees = new HashSet<>();
        employees.add(employee);
        project.setEmployees(employees);


        Set<Project> projects = new HashSet<>();
        projects.add(project);
        employee.setProjects(projects);

//        List<Address> a = addressService.getAll();
//        System.out.println(a);
        addressService.add(address);
        employeeService.add(employee);

        //projectService.add(project); так как у нас Cascade.ALL то это можно удалить, иначе задвоится проект

        HibernateUtil.shutdown();
    }
}

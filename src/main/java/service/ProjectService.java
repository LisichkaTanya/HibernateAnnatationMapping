package service;

import bl.SessionUtil;
import dao.ProjectDAO;
import entity.Project;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class ProjectService extends SessionUtil implements ProjectDAO {

    @Override
    public void add(Project project) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.save(project);
        closeTransactionSession();
    }

    @Override
    public List<Project> getAll() throws SQLException {
        openTransactionSession();
        String sql = "SELECT * FROM PROJECTS";
        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Project.class);
        List<Project> projectList = query.list();
        closeTransactionSession();
        return projectList;
    }

    @Override
    public Project getById(Long id) throws SQLException {
        openTransactionSession();
        String sql = "SELECT * FROM PROJECT WHERE ID = :id";
        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Project.class);
        query.setParameter("id", id);
        Project project = (Project) query.getSingleResult();
        closeTransactionSession();
        return project;
    }

    @Override
    public void update(Project project) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.update(project);
        closeTransactionSession();
    }

    @Override
    public void remove(Project project) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.remove(project);
        closeTransactionSession();
    }
}

package service;

import bl.SessionUtil;
import dao.GenericDAO;
import entity.Address;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class AddressService extends SessionUtil implements GenericDAO<Address, Long> {

    @Override
    public void add(Address address) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.save(address);
        closeTransactionSession();
    }

    @Override
    public List<Address> getAll() throws SQLException {
        openTransactionSession();
        String sql = "SELECT * FROM ADDRESS";
        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Address.class);
        List<Address> addressList = query.list();
        closeTransactionSession();
        return addressList;
    }

    @Override
    public Address getById(Long id) throws SQLException {
        openTransactionSession();
        String sql = "SELECT * FROM ADDRESS WHERE ID = :id";
        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Address.class);
        query.setParameter("id", id);
        Address address = (Address) query.getSingleResult();
        closeTransactionSession();
        return address;
    }

    @Override
    public void update(Address address) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.update(address);
        closeTransactionSession();
    }

    @Override
    public void remove(Address address) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.remove(address);
        closeTransactionSession();
    }
}

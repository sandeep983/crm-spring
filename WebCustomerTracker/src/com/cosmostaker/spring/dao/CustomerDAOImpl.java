package com.cosmostaker.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cosmostaker.spring.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	// Injecting the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<Customer> getCustomers() {
		
		// Getting the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Creating query + sort by first name
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by firstName",
															Customer.class);
		
		// Executing query and storing the result in list
		List<Customer> customers = theQuery.getResultList();
		
		// Returning the results
		return customers;
	}


	@Override
	public void saveCustomer(Customer theCustomer) {
		// Get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Save/Update the customer to database
		currentSession.saveOrUpdate(theCustomer);
	}


	@Override
	public Customer getCustomer(int theId) {
		
		// Get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Retrieve / read from database using the primary key
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		return theCustomer;
	}


	@Override
	public void deleteCustomer(int theId) {
		
		// Get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Delete object with id (primary key)
		Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();
	}
}

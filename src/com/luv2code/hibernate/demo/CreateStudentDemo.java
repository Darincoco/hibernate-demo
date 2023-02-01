package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			//start a transaction
			session.beginTransaction();
			
			// update the student object
			int id = 1;
			Student myStudent = session.get(Student.class, id);
			System.out.println("Reading the student...");
			System.out.println(myStudent);
			
			
			System.out.println("\nUpdating the student...");
			myStudent.setFirstName("Darin");
			
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done...");
			
			
			// new part
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// update email for all students
			System.out.println("update email for all students...");
			session.createQuery("update Student set email='swang@stevens.edu'")
				.executeUpdate();
			
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done...");
			
		} finally {
			factory.close();
		}
	}

}

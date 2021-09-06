package com.app;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.app.model.Person;


public class Main {

	public static void main(String[] args) {
		
		Configuration configuration=new Configuration().configure();
		StandardServiceRegistryBuilder builder=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
		SessionFactory factory=configuration.buildSessionFactory(builder.build());
		
		Session session=factory.openSession();
		Transaction transaction=session.beginTransaction();
		Person p1= new Person(1,"Shiva",23,"Male","Vizag");		
		session.save(p1);
		transaction.commit();
		session.close();
		factory.close();

	}

}

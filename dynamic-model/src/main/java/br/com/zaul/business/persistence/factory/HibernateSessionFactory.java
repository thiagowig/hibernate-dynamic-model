package br.com.zaul.business.persistence.factory;

import javax.enterprise.inject.Produces;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * 
 * @author thiago
 *
 */
public class HibernateSessionFactory {

	/** */
	private static final String RESOURCE_FILE = "/resources/hibernate.cfg.xml";
	/** */
	private static final SessionFactory SESSION_FACTORY;
	/** */
	private static final ServiceRegistry SERVICE_REGISTRY;
	
	private static final Session SESSION;
	
	private static final Configuration CONFIGURATION;
	
	/**
	 * 
	 */
	static {
		CONFIGURATION = new Configuration().configure(HibernateSessionFactory.RESOURCE_FILE);
		
		SERVICE_REGISTRY = new ServiceRegistryBuilder().applySettings(CONFIGURATION.getProperties()).buildServiceRegistry();
		
		SESSION_FACTORY = CONFIGURATION.buildSessionFactory(SERVICE_REGISTRY);
		
		SESSION = SESSION_FACTORY.openSession();
	}
	
	/**
	 * 
	 * @return
	 */
	@Produces
	public Session getSession() {
		return HibernateSessionFactory.SESSION;
	}
	
	@Produces
	public Configuration getConfiguration() {
		return HibernateSessionFactory.CONFIGURATION;
	}
	
}
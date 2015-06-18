package be.vdab.dao;
import javax.sql.DataSource;
abstract class AbstractDAO { 
	public final static String JNDI_NAME = "jdbc/cultuurhuis"; 
	protected DataSource dataSource; 
	public void setDataSource(DataSource dataSource) { 
		this.dataSource = dataSource;
	}
}

//Base class voor alle DAO classes
//Enkel bereikbaar in DAO classes
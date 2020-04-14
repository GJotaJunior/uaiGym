package uaiGym.service.DataBase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

	private String url;
	private String user;
	private String password;
	
	public DataSource dataSource;

	public ConnectionFactory() throws IOException {
		getProperties();
		
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl(url);
		comboPooledDataSource.setUser(user);
		comboPooledDataSource.setPassword(password);

		this.dataSource = comboPooledDataSource;
	}

	public Connection recuperarConexao() throws SQLException {
		return dataSource.getConnection();
	}

	public void getProperties() throws IOException {
		Properties prop = new Properties();
		prop.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
		
		String server = prop.getProperty("server");
		String port = prop.getProperty("port");
		String name = prop.getProperty("name");
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");
		
		this.url = "jdbc:mysql://" + server + ":" + port + "/" + name + "?useTimezone=true&serverTimezone=UTC";
		this.user = user;
		this.password = password;
	}

}

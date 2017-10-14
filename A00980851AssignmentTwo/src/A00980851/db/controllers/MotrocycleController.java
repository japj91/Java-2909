package A00980851.db.controllers;

import A00980851.data.Motorcycle;
import A00980851.db.dao.MotorCycleDao;
import A00980851.db.setup.DatabaseSetup;
import A00980851.db.setup.DbConstants;
import A00980851.util.ApplicationException;
import A00980851.util.ShareableData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;

/**
 * Name Japneet Johal A00 980 851 Project Name A00980851AssignmentTwo Class Name
 * MotrocycleController Date 2017-06-28
 */
public class MotrocycleController {
	private static DatabaseSetup databaseSetup;
	private Properties properties;

	private Connection connection;
	public LinkedList<Motorcycle> motorcycleLinkedList;

	MotorCycleDao motorCycleDao;

	private static final Logger logger = LogManager.getLogger();

	public void load() throws ApplicationException, SQLException, IOException, ClassNotFoundException {

		File DbPropFile = new File(DbConstants.DB_PROPERTIES_FILENAME);
		if (!DbPropFile.exists()) {
			logger.error(String.format("%s file does not exist", DbConstants.DB_DRIVER_KEY));
			throw new ApplicationException(String.format("%s file does not exist", DbConstants.DB_DRIVER_KEY));
		}
		setUpDB(DbPropFile);
		run();
	}

	private void run() {
		try {
			connect();
			if (!databaseSetup.tableExists("motorcycle")) {
				logger.debug("Their was no previous table named MotorCycle table was created and populated");
				dropTables("motorcycle");
				createTable();
				addData();
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}

	private void dropTables(String dbTableName) throws SQLException {
		motorCycleDao = new MotorCycleDao(databaseSetup);
		motorCycleDao.drop(dbTableName);
	}

	private void createTable() throws SQLException {
		String sql = "Create table Motorcycle (id varchar (30), make varchar(30), model varchar(30), years varchar(30), serialNumber varchar(30), mileage varchar(30))";
		motorCycleDao.create(sql);
	}

	public void update(Motorcycle motorcycle) throws SQLException {
		motorCycleDao = new MotorCycleDao(databaseSetup);
		motorCycleDao.update(motorcycle);
	}

	private void addData() throws SQLException {
		this.motorcycleLinkedList = ShareableData.getInstance().getMotorcycleList();
		motorCycleDao.add(motorcycleLinkedList);
	}

	private void connect() throws SQLException {
		this.connection = databaseSetup.getConnection();
	}

	private void setUpDB(File DbPropFile) throws IOException, ClassNotFoundException, SQLException {
		properties = new Properties();
		properties.load(new FileReader(DbPropFile));
		databaseSetup = new DatabaseSetup(properties);
	}

	private void delete(String sql) throws SQLException {
		motorCycleDao.delete(sql);
	}
}

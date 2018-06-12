package com.mss.csvProject;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class CsvDatabaseLoader {

	public static void main(String[] args) throws Exception {

		Reader in = new FileReader(args[0]);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in); //

		Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db"); // establishes connection
		Statement statement = connection.createStatement();
		// Creates table
		statement.executeUpdate(
				"CREATE TaBLe if not exists csvTable ( firstName VARCHAR (50)," + " lastName   VARCHAR (50), "
						+ "emalId  VARCHAR (50), " + "sex  VARCHAR (10), " + "image  VARCHAR (50), "
						+ "dataType VARCHAR (50), " + "transacMethod VARCHAR (50), " + "amount  VARCHAR (50), "
						+ "boolean1   VARCHAR (50), " + "boolean2 VARCHAR (50), " + "location VARCHAR (50))");

		int n = 0; // initializing to get record

		for (CSVRecord record : records) {

			String firstName = record.get(0);
			String lastName = record.get(1);

			String emailId = record.get(2);
			String sex = record.get(3);
			String image = record.get(4);

			String dataType = record.get(5);
			String transacMethod = record.get(6);
			String amount = record.get(7);

			String boolean1 = record.get(8);
			String boolean2 = record.get(9);
			String location = record.get(10);
			// Below is the web site I used as reference to do the assignment.
			// https://github.com/xerial/sqlite-jdbc

			statement.setQueryTimeout(30); // set timeout to 30 sec.

			// the following inserts data to the table.
			statement.executeUpdate("INSERT INTO csvTable VALUES('" + firstName + "', '" + lastName + "', '" + sex
					+ "', '" + emailId + "', '" + image + "',  '" + dataType + "','" + transacMethod + "','" + amount
					+ "'," + "'" + boolean1 + "','" + boolean2 + "','" + location + "')");

			n++;

			statement.executeUpdate("SELECT COUNT(*) FROM csvTable");
			System.out.println(n);
			System.out.println(firstName + "  " + lastName + "   " + sex + "" + emailId + image + dataType
					+ transacMethod + "    " + amount + "    " + boolean1 + "    " + boolean2 + "    " + location);
		}

	}

}

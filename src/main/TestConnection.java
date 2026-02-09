package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestConnection {

	public static void main(String[] args) {

		final String connectString = "(description= (retry_count=20)(retry_delay=3)" +
				"(address=(protocol=tcps)(port=1522)(host=adb.ap-tokyo-1.oraclecloud.com))" +
				"(connect_data=(service_name=g8c09a6a8bcac32_z3u87o1g28n1sl7w_medium.adb.oraclecloud.com))" +
				"(security=(ssl_server_dn_match=yes)))";
		final String url = "jdbc:oracle:thin:@" + connectString;

		final String username = "<your-username-here>";
		final String password = "<your-password-here>";
		
		try(Connection conn = DriverManager.getConnection(url, username, password);) {
			
			System.out.println("Connected to Oracle database");
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT employee_id, first_name, last_name ");
			query.append("FROM EMPLOYEES ");
			
			ResultSet rs = conn.createStatement().executeQuery(query.toString());
			while(rs.next()) {
				int employeeId = rs.getInt("employee_id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				System.out.printf("ID: %d, Name: %s %s%n", employeeId, firstName, lastName);
			}
			
			
		} catch (SQLException e) {
			System.out.println("Failed to connect to Oracle database");
			e.printStackTrace();
		}
		
	}

}

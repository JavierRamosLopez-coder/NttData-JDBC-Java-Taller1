package com.nttdata.nttdata_jdbc;

/**
 * Librerías importadas
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase NttDataJDBCMain
 *
 */
public class NttDataJDBCMain {

	/**
	 * Método main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Llamada al método estático que conecta con la BBDD y realiza la consulta
		establishConnectionMySql();

	}

	/**
	 * Conexión con la BBDD y muestra de datos
	 */
	private static void establishConnectionMySql() {

		try {

			// Creación de atributos que almacenan el usuario, la contraseña y la url de la
			// conexión a la BBDD
			final String user = "root";
			final String passwd = "";
			final String url = "jdbc:mysql://localhost:3306/nttdata_jrl_javajdbc";

			// Establecimiento del driver para la conexión con la BBDD
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Creación de un atributo de tipo Connection, recibiendo los datos para la
			// conexión con la BBDD
			final Connection bbddConnection = DriverManager.getConnection(url, user, passwd);

			// Consulta a ejecutar
			final Statement st = bbddConnection.createStatement();
			final String query = "SELECT * FROM nttdata_mysql_basque_players";
			final ResultSet queryResult = st.executeQuery(query);

			// Resultados
			StringBuilder basque_player = new StringBuilder();
			while (queryResult.next()) {

				basque_player.append("Nombre : ");
				basque_player.append(queryResult.getString("name_basque_player"));

				basque_player.append(" - Posición : ");
				basque_player.append(queryResult.getString("position_basque_player"));

				basque_player.append(" - Cumpleaños : ");
				basque_player.append(queryResult.getDate("birth_date"));

				basque_player.append("\n");
			}

			System.out.println(basque_player.toString());

			// Cierre de conexión con la BBDD
			bbddConnection.close();

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();

		}

	}

}

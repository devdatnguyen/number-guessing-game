package nguyenquocdat.gamedoanso.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import nguyenquocdat.gamedoanso.connection.JDBCConnection;
import nguyenquocdat.gamedoanso.model.Player;

public class AccountDAO {

	public List<Player> findAll() throws SQLException {
		List<Player> listPlayers = new LinkedList<Player>();
		Connection connection = JDBCConnection.getConnection();
		String query = "SELECT * FROM game_doan_so.player";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				Player player = new Player();

				player.setPlayerName(resultset.getString("fullname"));
				player.setPassword(resultset.getString("password"));
				player.setUserEmail(resultset.getString("email"));
				listPlayers.add(player);
			}
		} catch (SQLException e) {
			System.out.println("Unable to query find all player to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return listPlayers;
	}

	public Player findByEmail(String email) throws SQLException {
		Player player = new Player();
		String query = "SELECT * FROM game_doan_so.player p where p.email = ?";
		Connection connection = JDBCConnection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultset = statement.executeQuery();
			statement.setString(1, email);
			player.setPlayerName(resultset.getString("fullname"));
			player.setPassword(resultset.getString("password"));
			player.setUserEmail(resultset.getString("email"));
		} catch (SQLException e) {
			System.out.println("Unable to query by Email player to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return player;
	}

	public void add(Player player) throws SQLException {
		String query = "INSERT INTO game_doan_so.player(email, fullname, password)VALUE(?,?,?)";
		Connection connection = JDBCConnection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, player.getUserEmail());
			statement.setString(2, player.getPlayerName());
			statement.setString(3, player.getPassword());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Unable to add player to database");
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	public void deleteByEmail(String email) throws SQLException {
		String query = "DELETE FROM game_doan_so.player p WHERE p.email = ?";
		Connection connection = JDBCConnection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Unable to delete player to database");
		} finally {
			connection.close();
		}
	}

	public Player findByEmailAndPassword(String email, String password) throws SQLException {
		Player player = new Player();
		String query = "SELECT * FROM game_doan_so.player p where p.email =? AND p.password =?";
		Connection connection = JDBCConnection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet resultset = statement.executeQuery();
			if (resultset.next()) {
				player.setUserEmail(resultset.getString("email"));
				player.setPlayerName(resultset.getString("fullname"));
				player.setPassword(resultset.getString("password"));	
			}
		} catch (Exception e) {
			System.out.println("Not find account");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return player;
	}
}

package nguyenquocdat.gamedoanso.model;

public class Player {
	private String password;
	private String userEmail;
	private String playerName;
	
	public Player() {
		
	}
	
	public Player( String displayName ,String userEmail, String password) {
		this.password = password;
		this.userEmail = userEmail;
		this.playerName = displayName;
	}
	
	

	/**
	 * @return the displayName
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setPlayerName(String displayName) {
		this.playerName = displayName;
	}

	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

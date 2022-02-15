package nguyenquocdat.gamedoanso.service;

import java.time.LocalDateTime;
import java.util.List;

import nguyenquocdat.gamedoanso.model.GameRecord;
import nguyenquocdat.gamedoanso.model.Player;
import nguyenquocdat.gamedoanso.util.DateUtils;

public class GameService {
	private List<GameRecord> records;
	private List<Player> players;

	public GameService() {
		records = StoreService.records;
		players = StoreService.players;
	}

	public GameRecord loadGame(String displayName, String userEmail, String password) {
		Player loggedUser = null;

		// check existed user
		for (Player p : players) {
			if (p.getUserEmail().equalsIgnoreCase(userEmail)) {
				if (p.getPassword().equals(password)) {
					loggedUser = p;
					break;
				}

				return null;
			}
		}

		if (loggedUser == null) {
			loggedUser = new Player(displayName, userEmail, password);
			players.add(loggedUser);
		}

		GameRecord game = null;
		// load existed unfinished game record
		for (GameRecord record : records) {
			if (record.getPlayer().getUserEmail().equals(loggedUser.getUserEmail())
					&& record.getIsFinished() == false) {
				game = record;
				return game;
			}
		}

		game = new GameRecord(loggedUser);
		records.add(game);

		return game;
	}

	public GameRecord playGame(int recordId, int tryNumber) {
		GameRecord record = null;

		for (GameRecord r : records) {
			if (r.getId() == recordId && !r.getIsFinished()) {
				record = r;
				break;
			}
		}

		if (record == null) {
			return null;
		}

		record.setPoint(record.getPoint() + 1);

		if (record.getNumber() == tryNumber) {
			record.setFinishDate(DateUtils.toString(LocalDateTime.now()));
			record.setIsFinished(true);
		}

		return record;
	}
}
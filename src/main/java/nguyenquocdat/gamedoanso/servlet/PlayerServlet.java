package nguyenquocdat.gamedoanso.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nguyenquocdat.gamedoanso.model.Player;
import nguyenquocdat.gamedoanso.service.StoreService;
import nguyenquocdat.gamedoanso.util.JspConst;
import nguyenquocdat.gamedoanso.util.UrlConst;

@WebServlet(name = "playerServlet", urlPatterns = { UrlConst.PLAYER_LOGIN, UrlConst.PLAYER_REGISTER })
public class PlayerServlet extends HttpServlet {
	private List<Player> players;

	@Override
	public void init() throws ServletException {
		super.init();
		players = StoreService.players;
		players.add(new Player("admin", "admin@gmail.com", "123"));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.PLAYER_REGISTER:
			req.getRequestDispatcher(JspConst.PLAYER_REGISTER).forward(req, resp);
			break;

		case UrlConst.PLAYER_LOGIN:
			Cookie[] cookies = req.getCookies();
			String userEmail = null;

			if (cookies != null) {
				Optional<Cookie> usernameCookieOpt = Arrays.asList(cookies).stream()
						.filter(t -> t.getName().equals("userEmail")).findFirst();
				if (usernameCookieOpt.isPresent()) {
					userEmail = usernameCookieOpt.get().getValue();
				}
			}

			if (userEmail != null) {
				req.setAttribute("lastUsername", userEmail);
			}

			req.getRequestDispatcher(JspConst.PLAYER_LOGIN).forward(req, resp);
			break;
		default:
			resp.getWriter().append("Đi sai đường rồi anh êy!!!");
			break;
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		String username, password, message, userEmail;
		req.setCharacterEncoding("UTF-8");

		switch (path) {
		case UrlConst.PLAYER_LOGIN:
			password = req.getParameter("password");
			userEmail = req.getParameter("userEmail");

			Optional<Player> curPlayerOpt = players.stream().filter(t -> t.getUserEmail().equals(userEmail))
					.filter(t -> t.getPassword().equals(password)).findFirst();

			if (curPlayerOpt.isPresent()) {
				HttpSession session = req.getSession();
				session.setAttribute("player", curPlayerOpt.get());
				resp.sendRedirect(req.getContextPath() + UrlConst.GAME_ROOT);
			} else {
				resp.sendRedirect(req.getContextPath() + UrlConst.PLAYER_LOGIN);
			}

			break;

		case UrlConst.PLAYER_REGISTER:
			userEmail = req.getParameter("userEmail");
			password = req.getParameter("password");
			String playerName = req.getParameter("playerName");
			String rPassword = req.getParameter("rPassword");

			boolean isExistedUserEmail = players.stream().anyMatch(t -> t.getUserEmail().equalsIgnoreCase(userEmail));

			if (isExistedUserEmail) {
				message = "Email is used.";
				req.setAttribute("massage", message);
				resp.sendRedirect(req.getContextPath() + UrlConst.PLAYER_REGISTER);
			} else if (!password.equals(rPassword)) {
				resp.sendRedirect(req.getContextPath() + UrlConst.PLAYER_REGISTER);
			} else {
				players.add(new Player(playerName, userEmail, password));
				req.getRequestDispatcher(UrlConst.PLAYER_LOGIN).forward(req, resp);
			}

			break;

		default:
			resp.getWriter().append("Đi sai đường rồi anh êy!!!");
			break;
		}
	}
}

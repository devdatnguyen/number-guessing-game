package nguyenquocdat.gamedoanso.servlet;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nguyenquocdat.gamedoanso.model.GameRecord;
import nguyenquocdat.gamedoanso.service.StoreService;
import nguyenquocdat.gamedoanso.util.JspConst;
import nguyenquocdat.gamedoanso.util.UrlConst;

@WebServlet(name = "rankingServlet", urlPatterns = UrlConst.GAME_RANKING)
public class RankingServlet extends HttpServlet {
	private List<GameRecord> records;
	
	@Override
	public void init() throws ServletException {
		super.init();
		records = StoreService.records;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/* Java 8 stream, lambda, predicate */
		List<GameRecord> rankings = records.stream()
										.filter(t -> t.getIsFinished())
										.sorted((o1, o2) -> {
											return o1.getPoint() - o2.getPoint();
										}).collect(Collectors.toList());
										
		req.setAttribute("ranks", rankings);
		
		req.getRequestDispatcher(JspConst.GAME_RANKING)
			.forward(req, resp);
	}
}

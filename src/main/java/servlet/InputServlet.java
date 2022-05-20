package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDao;
import entity.Product;
import util.DbUtil;

@WebServlet("/InputServlet")
public class InputServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InputServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String result = "";
		request.removeAttribute(result);
		ProductDao productDao;
		Connection connection;
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("product_id");

		connection = DbUtil.getConnection();
		//		connection.setAutoCommit(false);

		productDao = new ProductDao(connection);

		List<Product> list = productDao.findAll();
		if (id != null && !id.isEmpty()) {
			int proId = Integer.parseInt(id);

			for(Product i:list) {

				if(i.getProductId() == proId) {

					result = "product_id:"+i.getProductId()
					+"<br>product_name:"+i.getProductName()
					+"<br>price:"+i.getPrice();
					request.setAttribute("result", result);
					request.getRequestDispatcher("searchResult.jsp").forward(request, response);
				}
			}
			result = "対象のデータはありません";
		}
		else {
			result = "product_idを入力してください";
		}
		request.setAttribute("result", result);
		request.getRequestDispatcher("top.jsp").forward(request, response);			
	
	}
}

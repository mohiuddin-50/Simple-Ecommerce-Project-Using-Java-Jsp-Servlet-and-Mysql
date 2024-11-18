package com.mohiuddin.miniecommerceproject.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.mohiuddin.miniecommerceproject.model.Cart;



/**
 * Servlet implementation class AddToCart
 */
/* @WebServlet("/AddToCart") */
@WebServlet(name = "AddToCartServlet", urlPatterns = "/add-to-cart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/*
	 * protected void doGet(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { // TODO Auto-generated
	 * method stub response.setContentType("text/html;charset=UTF-8");
	 * 
	 * try (PrintWriter out = response.getWriter()) { //
	 * out.print("add to cart servlet");
	 * 
	 * ArrayList<Cart> cartList = new ArrayList<>(); int id =
	 * Integer.parseInt(request.getParameter("id")); Cart cm = new Cart();
	 * cm.setId(id); cm.setQuantity(1); HttpSession session = request.getSession();
	 * ArrayList<Cart> cart_list = (ArrayList<Cart>)
	 * session.getAttribute("cart-list"); if (cart_list == null) { cartList.add(cm);
	 * session.setAttribute("cart-list", cartList);
	 * response.sendRedirect("index.jsp"); } else { cartList = cart_list;
	 * 
	 * boolean exist = false; for (Cart c : cart_list) { if (c.getId() == id) {
	 * exist = true; out.
	 * println("<h3 style='color:crimson; text-align: center'>Item Already in Cart. <a href='cart.jsp'>GO to Cart Page</a></h3>"
	 * ); } }
	 * 
	 * if (!exist) { cartList.add(cm); response.sendRedirect("index.jsp"); } } } }
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cart-list");

            try {
                int id = Integer.parseInt(request.getParameter("id"));
                Cart cm = new Cart();
                cm.setId(id);
                cm.setQuantity(1);

                if (cartList == null) {
                    cartList = new ArrayList<>();
                    cartList.add(cm);
                    session.setAttribute("cart-list", cartList);
                    response.sendRedirect("index.jsp");
                } else {
                    boolean exist = false;
                    for (Cart c : cartList) {
                        if (c.getId() == id) {
                            exist = true;
                            out.println("<h3 style='color:crimson; text-align: center'>Item Already in Cart. <a href='cart.jsp'>GO to Cart Page</a></h3>");
                            return;
                        }
                    }
                    if (!exist) {
                        cartList.add(cm);
                        response.sendRedirect("index.jsp");
                    }
                }
            } catch (NumberFormatException e) {
                out.println("<h3 style='color:red; text-align:center'>Invalid Product ID. <a href='index.jsp'>Back to Home</a></h3>");
            }
        }
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

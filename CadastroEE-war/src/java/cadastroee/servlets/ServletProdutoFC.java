/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package cadastroee.servlets;

import cadastroee.controller.ProdutoFacadeLocal;
import cadastroee.model.Produto;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author paulowueliton
 */
public class ServletProdutoFC extends HttpServlet {

    @EJB
    ProdutoFacadeLocal facade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");
        String destino = "ProdutoLista.jsp";;

        if (acao == null) {
            acao = "listar";
        }

        switch (acao) {
            case "listar" -> {
                request.setAttribute("produtos", facade.findAll());
                break;
            }
            case "incluir" -> {
                Produto produto = this.getProduto(request);

                facade.create(produto);

                request.setAttribute("produtos", facade.findAll());

                break;
            }
            case "alterar" -> {
                Produto produto = this.getProduto(request);
                
                facade.edit(produto);
                
                request.setAttribute("produtos", facade.findAll());
                
                break;
            }
            case "excluir" -> {
                Integer id = Integer.valueOf(request.getParameter("id"));
                Produto produto = facade.find(id);
                facade.remove(produto);
                request.setAttribute("produtos", facade.findAll());
                break;
            }
            case "formIncluir" -> {
                destino = "ProdutoDados.jsp";
                break;

            }
            case "formAlterar" -> {
                destino = "ProdutoDados.jsp";
                Integer id;
                id = Integer.valueOf(request.getParameter("id"));
                request.setAttribute("produto", facade.find(id));
                break;
            }

        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destino);
        dispatcher.forward(request, response);
    }

    private Produto getProduto(HttpServletRequest request) {
        String idParametro = request.getParameter("id");
        Integer id = Integer.valueOf(idParametro);
        String nome = request.getParameter("nome");
        Integer quantidade = Integer.valueOf(request.getParameter("quantidade"));
        Float precoVenda = Float.valueOf(request.getParameter("precoVenda"));

        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setPrecoVenda(precoVenda);
        produto.setQuantidade(quantidade);
        
        if(idParametro != null) { produto.setIdProduto(id); }
        return produto;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

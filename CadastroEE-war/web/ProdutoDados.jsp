<%-- 
    Document   : ProdutoDados
    Created on : 12 de mai. de 2024, 15:52:34
    Author     : paulowueliton
--%>

<%@page import="cadastroee.model.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String acao = "incluir"; %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= acao == "incluir" ? "Adicionar" : "Editar" %> Produto</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </head>
    <body class="container">
        <h1>Dados do Produto</h1>
        
        <% Produto produto = (Produto) request.getAttribute("produto"); %>

        <% String nome = ""; %>
        <% String quantidade = ""; %>
        <% String precoVenda = ""; %>
        
        <form action="ServletProdutoFC" method="POST" class="form">
            <% if(produto != null) { %>
                <% nome = produto.getNome(); %>
                <% quantidade = String.valueOf(produto.getQuantidade()); %>
                <% precoVenda = String.valueOf(produto.getPrecoVenda()); %>
                <% acao = "alterar"; %>
                <input type="hidden" name="id" value="<%= request.getParameter("id") %>" />
            <% } %>

            <div class="mb-3">
                <label for="nome" class="form-label">Nome: </label>
                <input id="nome" name="nome" value="<%= nome %>" class="form-control" autofocus />
            </div>

            <div class="mb-3">
                <label for="quantidade" class="form-label">Quantidade </label>
                <input type="number" min="1" id="quantidade" name="quantidade" value="<%= quantidade %>" class="form-control" />
            </div>

            <div class="mb-3">
                <label for="precoVenda" class="form-label">Preço de Venda </label>
                <input type="number" step="0.01" min="0" id="precoVenda" name="precoVenda" value="<%= precoVenda %>" class="form-control" />
            </div>

            <input type="hidden" name="acao" value="<%= acao %>" />
            
            <a href="ServletProdutoFC" class="btn btn-secondary">Voltar</a>
            <button type="submit" class="btn btn-primary"><%= acao == "incluir" ? "Adicionar" : "Editar" %> Produto</button>
        </form>
    </body>
</html>

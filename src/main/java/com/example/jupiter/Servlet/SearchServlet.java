package com.example.jupiter.Servlet;


import com.example.jupiter.externals.TwitchClient;
import com.example.jupiter.externals.TwitchException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SearchServlet", urlPatterns = {"/search"})
public class SearchServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String gameId = request.getParameter("game_id");
        if (gameId == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        TwitchClient client = new TwitchClient();
        try {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().print(new ObjectMapper().writeValueAsString(client.searchItems(gameId)));
        } catch (TwitchException e) {
            throw new ServletException(e);
        }
    }
}
package com.example.lr1.servlet;

import com.example.lr1.entity.Position;
import com.example.lr1.service.LikedService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/favorites")
public class FavoritesServlet extends HttpServlet {

    @EJB
    private LikedService likedService;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = Long.valueOf(req.getParameter("userId"));
        List<Position> likedPositions = likedService.getLikedPositions(userId);

        req.setAttribute("likedPositions", likedPositions);
        req.setAttribute("userId", userId);

        req.getRequestDispatcher("/views/favorites.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = Long.valueOf(req.getParameter("userId"));
        Long positionId = Long.valueOf(req.getParameter("positionId"));

        likedService.unlikePosition(userId, positionId);

        // Перерисовываем текущую страницу избранного без redirect
        doGet(req, resp);
    }
}

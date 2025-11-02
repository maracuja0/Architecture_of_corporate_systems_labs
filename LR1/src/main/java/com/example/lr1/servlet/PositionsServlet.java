package com.example.lr1.servlet;

import com.example.lr1.entity.Position;
import com.example.lr1.service.LikedService;
import com.example.lr1.service.PositionService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/positions")
public class PositionsServlet extends HttpServlet {

    @EJB
    private PositionService positionService;

    @EJB
    private LikedService likedService;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = Long.valueOf(req.getParameter("userId"));
        List<Position> positions = positionService.getAllPositions();
        List<Long> likedIds = likedService.getLikedPositionIds(userId);

        req.setAttribute("positions", positions);
        req.setAttribute("likedIds", likedIds);
        req.setAttribute("userId", userId);

        req.getRequestDispatcher("/views/positions.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = Long.valueOf(req.getParameter("userId"));
        Long positionId = Long.valueOf(req.getParameter("positionId"));
        String action = req.getParameter("action");

        if ("like".equals(action)) likedService.likePosition(userId, positionId);
        if ("unlike".equals(action)) likedService.unlikePosition(userId, positionId);

        resp.sendRedirect("positions?userId=" + userId);
    }
}
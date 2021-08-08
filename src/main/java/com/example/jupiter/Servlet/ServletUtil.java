package com.example.jupiter.Servlet;

import com.example.jupiter.entity.FavoriteRequestBody;
import com.example.jupiter.entity.Item;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ServletUtil {
    public static void writeItemMap(HttpServletResponse response, Map<String, List<Item>> itemMap) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(new ObjectMapper().writeValueAsString(itemMap));
    }

    public static String encryptPassword(String userId, String password) throws IOException {
        return DigestUtils.md5Hex(userId + DigestUtils.md5Hex(password)).toLowerCase();//加密操作
    }
    public static String getUserIDFromSession(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
        return (String) session.getAttribute("user_id");
    }
    public static FavoriteRequestBody getFavoriteRequestBody(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        FavoriteRequestBody body = mapper.readValue(request.getReader(), FavoriteRequestBody.class);
        if (body == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        return body;
    }
}

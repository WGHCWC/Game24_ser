package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bean.DB;
import bean.Result;
import bean.UserInfo;

public class SignIn extends HttpServlet {
    PreparedStatement pstmt;


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        BufferedReader reader = request.getReader();
        StringBuffer sb = new StringBuffer();
        Connection con = DB.getConn();
        Gson gson = new Gson();
        PrintWriter pw = response.getWriter();
        String line = "";
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        line = sb.toString();
        UserInfo userInfo = gson.fromJson(line, UserInfo.class);
        Result<UserInfo> result = new Result<>();
        try {
            String sql = "select * from userInfo where userName=?and userPassword=?;";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userInfo.getUserName());
            pstmt.setString(2, userInfo.getUserPassword());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result.code = 200;

            } else {
                result.code = 300;
            }
            result.data = userInfo;


            reader.close();
        } catch (Exception e) {
            result.code = 400;
            result.msg = e.toString();
            e.printStackTrace();
        }

        String rep = gson.toJson(result);
        pw.println(rep);

    }
}

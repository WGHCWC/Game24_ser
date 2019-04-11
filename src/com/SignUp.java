package com;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import bean.DB;
import bean.Result;
import bean.UserInfo;

public class SignUp extends HttpServlet {

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

        UserInfo here = gson.fromJson(line, UserInfo.class);
        Result<UserInfo> result = new Result<>();
        String sql0 = "insert into userInfo (userName,userPassword)values(?,?);";// 注册信息
        try {
            PreparedStatement pstmt = DB.prepareStmt(con, sql0);
            pstmt.setString(1, here.getUserName());
            pstmt.setString(2, here.getUserPassword());
            pstmt.executeUpdate();
            pstmt.close();
            result.code = 200;


        } catch (Exception e) {
          //  result.msg = "已经被注册";
            result.code = 301;
            e.printStackTrace();
        }
        result.data = here;
        reader.close();
        String rep = gson.toJson(result);
        pw.println(rep);
    }

}
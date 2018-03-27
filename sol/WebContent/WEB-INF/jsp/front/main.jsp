<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ page import="java.sql.*"%>
 <%
  Connection conn=null;
 try{
  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
  String jdbcDriver = "jdbc:sqlserver://192.168.1.156:1433;databaseName=leedb;";
  String dbUser = "sa";
  String dbPass = "sa";
  conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
  //비번 제대로 입력 했습니다.
  out.println("연결 성공");
  } catch(Exception e){
   out.println("연결 실패");
   e.printStackTrace();
  } finally {
    conn.close();
 }
 %>

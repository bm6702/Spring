package net.websnet.dbtest;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTest {
 static {
	 try {
		 Class.forName("oracle.jdbc.driver.OracleDriver");
	 }catch(Exception e) {
		 e.printStackTrace();
	 }
 }
 
 //커넥션 객체 생성을 확인(rootcontext를 사용하지 않았다)
 @Test
 public void testConn() {
	 try {
		 Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","1234");
		 log.info(con);
	 }catch(Exception e) {
		 e.printStackTrace();
	 }
 }
}

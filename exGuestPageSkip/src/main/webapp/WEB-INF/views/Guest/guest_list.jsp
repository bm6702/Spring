<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head><title>방명록 읽기</title>
<link rel="stylesheet" type="text/css" href="/css/stylesheet.css">
<style type="text/css">
  a.list {text-decoration:none;color:black;font-size:10pt;}
</style>
</head>
<body bgcolor="#FFFFFF" topmargin="0" leftmargin="0">
<table border="0" width="800">
  <tr>
    <td width="20%" height="500" valign="top" bgcolor="#ecf1ef">
   <!-- 다음에 추가할 부분 --></td>
    <td width="80%" valign="top">   
    <br>
    <table border="0" cellspacing="1" width="100%" align="center">
      <tr>
        <td colspan="7" align="center" valign="center" height="20">
        <font size="4" face="돋움" color="blue">
        <img src="/img/bullet-01.gif"> <b>방 명 록</b></font></td></tr>
      <tr>
        <td colspan="5" align="right" valign="middle" height="20">
      <font size="2" face="고딕">전체 : ${list.size()}</b>건 - 3 Pages</font></td></tr>
       <tr bgcolor="e3e9ff">
          <td width="10%"align="center" height="20"><font face="돋움" size="2">번호</font></td>
          <td width="50%" align="center" height="20"><font face="돋움" size="2">제목</font></td>
          <td width="15%" align="center" height="20"><font face="돋움" size="2">글쓴이</font></td>
          <td width="15%" align="center" height="20"><font face="돋움" size="2">작성일</font></td>
          <td width="10%" align="center" height="20"><font face="돋움" size="2">조회수</font></td>
       </tr>
       
      <c:forEach var="guest" items="${list }">
         <tr onMouseOver="style.backgroundColor='#D1EEEE'" onMouseOut="style.backgroundColor=''">
              <td align="center" height="25">
              <font face="돋움" size="2" color="#000000">${guest.idx}</font></td>
              <td align="left" height="20">
              <font face="돋움" size="2" color="#000000">
                   <a href="guestReadCnt?idx=${guest.idx}&page=${nowpage } ">${guest.subject }</a></td>
              <td align="center" height="20"><font face="돋움" size="2">
              <a class="list" href="mailto:ein1027@nate.com">${guest.name }</a></font></td>
              <td align="center" height="20"><font face="돋움" size="2">${guest.regdate.substring(0,10) }</font></td>
              <td align="center" height="20"><font face="돋움" size="2">${guest.readcnt }</font></td>
         </tr>
      </c:forEach>         
         
     <div align="center">
        <table width="700" border="0" cellspacing="0" cellpadding="5">
          <tr>&nbsp;</tr><tr>
             <td colspan="5">        
                <div align="center">${listpage }</div>
           </td>
          </tr>
         <tr align=right><td colspan="5">
            <a href="guest_write"><img src="/img/write.gif" border="0"></a><!-- href="맵핑이름 -->
         </td>
         </tr>
      </table>
      </div>
   </body>
   </html>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="w3-sidebar w3-bar-block w3-white w3-collapse w3-top" style="z-index:3;width:250px" id="mySidebar">
  <div class="w3-container w3-display-container w3-padding-16">
    <i onclick="w3_close()" class="fa fa-remove w3-hide-large w3-button w3-display-topright"></i>
    <h3 class="w3-wide" onclick="location.href='./'">LOGO<b></b></h3>
  </div>
  <div class="w3-padding-64 w3-large w3-text-grey" style="font-weight:bold">
  
    <a href="#" class="w3-bar-item w3-button">Best</a>
    <a href="#" class="w3-bar-item w3-button">Sale</a>
    <a href="#" class="w3-bar-item w3-button">New</a>
    <hr>
    <a onclick="myAccFunc()" href="javascript:void(0)" class="w3-button w3-block w3-white w3-left-align" id="myBtn">
      운동화 <i class="fa fa-caret-down"></i>
    </a>
    <div id="demoAcc" class="w3-bar-block w3-hide w3-padding-large w3-medium">
      <a href="#" class="w3-bar-item w3-button w3-light-grey">나이키 &nbsp;<i class="fa fa-caret-right w3-margin-right"></i></a>
      <a href="#" class="w3-bar-item w3-button">뉴발란스 &nbsp;<i class="fa fa-caret-right w3-margin-right"></i></a>
      <a href="#" class="w3-bar-item w3-button">컨버스 &nbsp;<i class="fa fa-caret-right w3-margin-right"></i></a>
      <a href="#" class="w3-bar-item w3-button">아디다스 &nbsp;<i class="fa fa-caret-right w3-margin-right"></i></a>
      <a href="#" class="w3-bar-item w3-button">슈펜 &nbsp;<i class="fa fa-caret-right w3-margin-right"></i></a>
    </div>

  </div>
  
</nav>
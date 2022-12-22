<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="w3-sidebar w3-bar-block w3-white w3-collapse w3-top" style="z-index:3;width:250px" id="mySidebar">
  <div class="w3-container w3-display-container w3-padding-16">
    <i onclick="w3_close()" class="fa fa-remove w3-hide-large w3-button w3-display-topright"></i>
    <h3 class="w3-wide" onclick="location.href='./'"><b>SHOOKREAM</b></h3>
  </div>
  <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
      <div class="input-group">
          <input class="form-control" type="text" placeholder="Search for..." aria-label="Search for..." aria-describedby="btnNavbarSearch" />
          <button class="btn btn-primary" id="btnNavbarSearch" type="button" ><i class="fa fa-search"></i></button>
      </div>
  </form>
  <div class="w3-padding-64 w3-large w3-text-grey" style="font-weight:bold">
  
    <a href="Best.MAIN" class="w3-bar-item w3-button">Best</a>
    <a href="New.MAIN" class="w3-bar-item w3-button">New</a>
    <a href="Sale.MAIN" class="w3-bar-item w3-button">Sale</a>
    <hr>
    <a onclick="myAccFunc()" href="javascript:void(0)" class="w3-button w3-block w3-white w3-left-align" id="myBtn">
      운동화 <i class="fa fa-caret-down"></i>
    </a>
    <div id="demoAcc" class="w3-bar-block w3-hide w3-padding-large w3-medium">
      <a href="BrandCG.MAIN?cg=나이키" class="w3-bar-item w3-button">나이키 &nbsp;<i class="fa fa-caret-right w3-margin-right"></i></a>
      <a href="BrandCG.MAIN?cg=뉴발란스" class="w3-bar-item w3-button">뉴발란스 &nbsp;<i class="fa fa-caret-right w3-margin-right"></i></a>
      <a href="BrandCG.MAIN?cg=컨버스" class="w3-bar-item w3-button">컨버스 &nbsp;<i class="fa fa-caret-right w3-margin-right"></i></a>
      <a href="BrandCG.MAIN?cg=아디다스" class="w3-bar-item w3-button">아디다스 &nbsp;<i class="fa fa-caret-right w3-margin-right"></i></a>
      <a href="BrandCG.MAIN?cg=슈펜" class="w3-bar-item w3-button">슈펜 &nbsp;<i class="fa fa-caret-right w3-margin-right"></i></a>
    </div>

  </div>
  
</nav>
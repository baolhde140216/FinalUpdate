<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Learn &mdash; Free Website Template, Free HTML5 Template by freehtml5.co</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="Free HTML5 Website Template by freehtml5.co" />
  <meta name="keywords" content="free website templates, free html5, free template, free bootstrap, free website template, html5, css3, mobile first, responsive" />
  <meta name="author" content="freehtml5.co" />

  <!--
  //////////////////////////////////////////////////////

  FREE HTML5 TEMPLATE
  DESIGNED & DEVELOPED by FreeHTML5.co

  Website: 		http://freehtml5.co/
  Email: 			info@freehtml5.co
  Twitter: 		http://twitter.com/fh5co
  Facebook: 		https://www.facebook.com/fh5co

  //////////////////////////////////////////////////////
   -->

  <!-- Facebook and Twitter integration -->
  <meta property="og:title" content=""/>
  <meta property="og:image" content=""/>
  <meta property="og:url" content=""/>
  <meta property="og:site_name" content=""/>
  <meta property="og:description" content=""/>
  <meta name="twitter:title" content="" />
  <meta name="twitter:image" content="" />
  <meta name="twitter:url" content="" />
  <meta name="twitter:card" content="" />

  <link href="https://fonts.googleapis.com/css?family=Work+Sans:300,400,500,700,800" rel="stylesheet">

  <!-- Animate.css -->
  <link rel="stylesheet" href="Common/test/css/animate.css">
  <!-- Icomoon Icon Fonts-->
  <link rel="stylesheet" href="Common/test/css/icomoon.css">
  <!-- Bootstrap  -->
  <link rel="stylesheet" href="Common/test/css/bootstrap.css">

  <!-- Magnific Popup -->
  <link rel="stylesheet" href="Common/test/css/magnific-popup.css">

  <!-- Owl Carousel  -->
  <link rel="stylesheet" href="Common/test/css/owl.carousel.min.css">
  <link rel="stylesheet" href="Common/test/css/owl.theme.default.min.css">

  <!-- Theme style  -->
  <link rel="stylesheet" href="Common/test/css/style.css">

  <!-- Modernizr JS -->
  <script src="Common/test/js/modernizr-2.6.2.min.js"></script>
  <!-- FOR IE9 below -->
  <!--[if lt IE 9]>
  <script src="Common/test/js/respond.min.js"></script>
  <![endif]-->

</head>
<body>


<div class="fh5co-loader"></div>

<div id="page">
  <nav class="fh5co-nav" role="navigation">
    <div class="top">
      <div class="container">
        <div class="row">
          <div class="col-xs-12 text-right">
            <p class="num">Call: +01 123 456 7890</p>
            <ul class="fh5co-social">
              <li><a href="#"><i class="icon-twitter"></i></a></li>
              <li><a href="#"><i class="icon-dribbble"></i></a></li>
              <li><a href="#"><i class="icon-github"></i></a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <div class="top-menu">
      <div class="container">
        <div class="row">
          <div class="col-xs-1">
            <div id="fh5co-logo"><a href="index.jsp">Learn<span>.</span></a></div>
          </div>
          <div class="col-xs-11 text-right menu-1">
            <ul>
              <li class="active"><a href="index.html">Home</a></li>
              <li><a href="contactus.jsp">Contact</a></li>
              <c:if test="${sessionScope.name!=null}">
                <c:if test="${sessionScope.type.equals('1')}">
                  <li class="btn-cta" ><a href="loginGet" ><span style="font-size: 120%!important;padding: 4px 4rem!important;">Admin Panel</span></a></li>
                </c:if>

                <c:if test="${sessionScope.type.equals('0')}">
                  <li class="btn-cta" ><a href="loginGet" ><span style="font-size: 120%!important;padding: 4px 4rem!important;">Student Panel</span></a></li>
                </c:if>
              </c:if>

              <c:if test="${sessionScope.name==null}">
                <li class="btn-cta" ><a href="home?action=login" ><span style="font-size: 120%!important;padding: 4px 4rem!important;">Login</span></a></li>
              </c:if>

            </ul>
          </div>
        </div>

      </div>
    </div>
  </nav>

  <header id="fh5co-header" class="fh5co-cover" role="banner" style="background-image:url(Common/test/images/img_bg_1.jpg);" data-stellar-background-ratio="0.5">
    <div class="overlay"></div>
    <div class="container">
      <div class="row">
        <div class="col-md-8 col-md-offset-2 text-center">
          <div class="display-t">
            <div class="display-tc animate-box" data-animate-effect="fadeIn">
              <h1 style="font-size: 80px">Examination Online</h1>
              <h2>Free And Made by <span style="color: navajowhite">Team 2</span> </h2>
              <p>
                <a class="btn btn-primary btn-lg popup-vimeo btn-video" href="https://www.youtube.com/watch?v=uzLSYc-Ydio">
                  <i class="icon-play"></i> Watch Video</a>
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </header>

  <div id="fh5co-counter" class="fh5co-counters">
    <div class="container">
      <div class="row">
        <div class="col-md-3 text-center animate-box">
          <span class="fh5co-counter js-counter" data-from="0" data-to="10000" data-speed="1000" data-refresh-interval="5"></span>
          <span class="fh5co-counter-label">Questions</span>
        </div>
        <div class="col-md-3 text-center animate-box">
          <span class="fh5co-counter js-counter" data-from="0" data-to="3000" data-speed="600" data-refresh-interval="5"></span>
          <span class="fh5co-counter-label">Courses</span>
        </div>
        <div class="col-md-3 text-center animate-box">
          <span class="fh5co-counter js-counter" data-from="0" data-to="1404" data-speed="50" data-refresh-interval="50"></span>
          <span class="fh5co-counter-label">SE</span>
        </div>
        <div class="col-md-3 text-center animate-box">
          <span class="fh5co-counter js-counter" data-from="0" data-to="5" data-speed="1" data-refresh-interval="50"></span>
          <span class="fh5co-counter-label">Member</span>
        </div>
      </div>
    </div>
  </div>



</div>
</footer>
</div>

<div class="gototop js-top">
  <a href="#" class="js-gotop"><i class="icon-arrow-up"></i></a>
</div>

<!-- jQuery -->
<script src="Common/test/js/jquery.min.js"></script>
<!-- jQuery Easing -->
<script src="Common/test/js/jquery.easing.1.3.js"></script>
<!-- Bootstrap -->
<script src="Common/test/js/bootstrap.min.js"></script>
<!-- Waypoints -->
<script src="Common/test/js/jquery.waypoints.min.js"></script>
<!-- Stellar Parallax -->
<script src="Common/test/js/jquery.stellar.min.js"></script>
<!-- Carousel -->
<script src="Common/test/js/owl.carousel.min.js"></script>
<!-- countTo -->
<script src="Common/test/js/jquery.countTo.js"></script>
<!-- Magnific Popup -->
<script src="Common/test/js/jquery.magnific-popup.min.js"></script>
<script src="Common/test/js/magnific-popup-options.js"></script>
<!-- Main -->
<script src="Common/test/js/main.js"></script>

</body>
</html>
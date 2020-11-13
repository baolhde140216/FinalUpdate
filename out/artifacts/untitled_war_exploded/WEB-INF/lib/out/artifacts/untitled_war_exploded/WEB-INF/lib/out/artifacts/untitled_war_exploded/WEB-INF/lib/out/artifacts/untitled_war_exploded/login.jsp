<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login V1</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="Common/test/images/icons/favicon.ico"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="Common/test/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" href="Common/test/fonts/font-awesome-4.7.0/css/font-awesome.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="Common/test/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="Common/test/vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="Common/test/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="Common/test/css/util.css">
    <link rel="stylesheet" type="text/css" href="Common/test/css/main.css">
    <!--===============================================================================================-->
    <style>
        @media (min-width: 768px) {
            .omb_row-sm-offset-3 div:first-child[class*="col-"] {
                margin-left: 25%;
            }
        }

        .omb_login .omb_authTitle {
            text-align: center;
            line-height: 300%;
        }

        .omb_login .omb_socialButtons a {
            color: white; // In yourUse @body-bg
        opacity:0.9;
        }
        .omb_login .omb_socialButtons a:hover {
            color: white;
            opacity:1;
        }
        .omb_login .omb_socialButtons .omb_btn-facebook {background: #3b5998;}
        .omb_login .omb_socialButtons .omb_btn-twitter {background: #00aced;}
        .omb_login .omb_socialButtons .omb_btn-google {background: #c32f10;}


        .omb_login .omb_loginOr {
            position: relative;
            font-size: 1.5em;
            color: #aaa;
            margin-top: 1em;
            margin-bottom: 1em;
            padding-top: 0.5em;
            padding-bottom: 0.5em;
        }
        .omb_login .omb_loginOr .omb_hrOr {
            background-color: #cdcdcd;
            height: 1px;
            margin-top: 0px !important;
            margin-bottom: 0px !important;
        }
        .omb_login .omb_loginOr .omb_spanOr {
            display: block;
            position: absolute;
            left: 50%;
            top: -0.6em;
            margin-left: -1.5em;
            background-color: white;
            width: 3em;
            text-align: center;
        }

        .omb_login .omb_loginForm .input-group.i {
            width: 2em;
        }
        .omb_login .omb_loginForm  .help-block {
            color: red;
        }


        @media (min-width: 768px) {
            .omb_login .omb_forgotPwd {
                text-align: right;
                margin-top:10px;
            }
        }
    </style>
</head>
<body>

<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100" style="padding-top: 100px">
            <div class="login100-pic js-tilt" data-tilt>
                <img src="Common/test/images/img-01.png" alt="IMG">
            </div>

            <form class="login100-form validate-form" action="LoginController" method="post">
					<span class="login100-form-title">
						<img src="Common/login/IMG/login.png" height="70" width="70" style="margin-bottom: .5rem"><br>
                        Login
					</span>

                <div class="wrap-input100 validate-input" data-validate = "Valid email is required: ex@abc.xyz">
                    <input class="input100" type="text" name="username" placeholder="username">
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
							<i class="fa fa-envelope" aria-hidden="true"></i>
						</span>
                </div>

                <div class="wrap-input100 validate-input" data-validate = "Password is required">
                    <input class="input100" type="password" name="password" placeholder="Password">
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
							<i class="fa fa-lock" aria-hidden="true"></i>
						</span>
                </div>

                <div class="container-login100-form-btn">
                    <button class="login100-form-btn" id="login">
                        Login
                    </button>

                    <a href="index.jsp" class="login100-form-btn btn btn-danger" style="margin-top: 1rem;background-color: #00acc1" >
                       Home
                    </a>
                </div>

                <div class="text-center p-t-12" id="alo">
						<span class="txt1" style="font-size: 1.2rem">
							Forgot
						</span>
                    <a class="txt2" href="HomeController?action=resetPassword" style="font-size: 1.2rem">
                        Username / Password?
                    </a>

                </div>
<%--                <div class="text-center p-t-136" style="padding-top: 2rem;">--%>
<%--                    <a class="txt2" href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/login-google&response_type=code--%>
<%--		&client_id=208411658054-6f63qudpv6nafjq9qihbog8sgc4dht0e.apps.googleusercontent.com&approval_prompt=force" style="font-size: 1.5rem">--%>
<%--                        Login with Google--%>
<%--                        <i class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>--%>

<%--                    </a>--%>
<%--                </div>--%>


                 <h3 class="omb_authTitle">Login or Sign up with</h3>
                 <div class="row omb_row-sm-offset-3 omb_socialButtons" style="margin: 2rem 0">
                     <div class="col-4" style="background-color: #4267B2; margin-left: 2rem">
                         <a href="#" class="btn btn-lg btn-block omb_btn-facebook">
                             <i class="fa fa-facebook visible-xs"></i>
                             <span class="hidden-xs">Facebook</span>
                         </a>
                     </div>

                     <div class="col-4">
                         <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/login-google&response_type=code
		&client_id=208411658054-6f63qudpv6nafjq9qihbog8sgc4dht0e.apps.googleusercontent.com&approval_prompt=force" class="btn btn-lg btn-block omb_btn-google">
                             <i class="fa fa-google-plus visible-xs"></i>
                             <span class="hidden-xs">Google+</span>
                         </a>
                     </div>
                 </div>
<%--                 <div class="col-xs-4 col-sm-2">--%>
<%--                     <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/login-google&response_type=code--%>
<%--		&client_id=208411658054-6f63qudpv6nafjq9qihbog8sgc4dht0e.apps.googleusercontent.com&approval_prompt=force" class="btn btn-lg btn-block omb_btn-facebook">--%>
<%--                         <i class="fa fa-facebook visible-xs"></i>--%>
<%--                         <span class="hidden-xs">Facebook</span>--%>
<%--                     </a>--%>
<%--                 </div>--%>
<%--                 <div class="col-xs-4 col-sm-2">--%>
<%--                     <a href="#" class="btn btn-lg btn-block omb_btn-google">--%>
<%--                         <i class="fa fa-google-plus visible-xs"></i>--%>
<%--                         <span class="hidden-xs">Google+</span>--%>
<%--                     </a>--%>
<%--                 </div>--%>




                <p class="alert alert-warning" >
                    <a href="HomeController?action=register" class="btn btn-primary" style="right: -23px; position: relative;">Create New Account!</a>
                </p>


            </form>
        </div>
    </div>
<%--    <%--%>
<%--        if (request.getSession().getAttribute("userStatus") != null) {--%>
<%--            System.out.println("its called");--%>
<%--            if (request.getSession().getAttribute("userStatus").equals("-1")) {--%>
<%--                System.out.println("now inside");--%>
<%--    %>--%>
<%--    <script>--%>
<%--        alert("Invalid username or password")--%>
<%--    </script>--%>
<%--    <%--%>
<%--            }--%>
<%--        }--%>
<%--    %>--%>

    <script>
        document.getElementById("login").addEventListener("click",()=> {

        })
    </script>
</div>




<!--===============================================================================================-->
<script src="/Common/test/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="/Common/test/vendor/bootstrap/js/popper.js"></script>
<script src="/Common/test/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="/Common/test/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
<script src="/Common/test/vendor/tilt/tilt.jquery.min.js"></script>
<script >
    $('.js-tilt').tilt({
        scale: 1.1
    })


</script>
<!--===============================================================================================-->
<script src="test/js/main.js"></script>

</body>
</html>





<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign Up Form by Colorlib</title>
    <link href="Common/register/fonts/material-icon/css/material-design-iconic-font.min.css">
    <link rel="stylesheet" href="Common/register/css/style.css">
</head>
<body>

<div class="main">
    <section class="signup">
        <div class="container">
            <div class="signup-content">
                <form method="POST" action="user.action" id="signup-form" class="signup-form">
                    <h2 class="form-title">Reset password</h2>
                    <div class="form-group">
                        <input type="text" class="form-input" name="email" id="email" placeholder="Enter email to reset password"/>
                    </div>
                    <div class="form-group">
                        <input type="submit" style="cursor: pointer;" name="submit" id="submit" class="form-submit" value="Send mail reset password"/>
                    </div>
                    <input type="hidden" name="action" value="sendMailReset">
                </form>
            </div>
        </div>
    </section>

</div>

<!-- JS -->
<script src="Common/register/vendor/jquery/jquery.min.js"></script>
<script src="Common/register/js/main.js"></script>
<script>
    document.getElementById("submit")
</script>
</body>
</html>
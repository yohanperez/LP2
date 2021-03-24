<%-- 
    Document   : alertcap
    Created on : 23/03/2021, 08:32:54 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
        <style>
            body{

                background: url(dar.jpg);


            }

        </style>

        <script src="https://www.google.com/recaptcha/api.js" async defer></script>

    </head>
    <body>

        <div class="container w-50 bg-warning mt-5 rounded shadow">
            <div class="row align-items-stretch bg-white">
                <div class="col bg d-none d-lg-nlock col-md-5 col-lg-5 col-xl-6 rounded" >

                </div>



                <h1 class="fw-bold text-center py-5">Login</h1>

                <!---login--->
                <form action="login" method="post" >

                    <div class="mb-4">
                        <label for="email" class="form-label">Correo electronico</label>
                        <input type="email" class="form-control" required autocomplete="off" name="correo" placeholder="example@gmail.com">
                    </div>
                    <div class="mb-4">  
                        <label for="password" class="form-label" >Contraseña</label>
                        <input type="password" class="form-control" name="contrasena" required autocomplete="off">
                    </div>
                    
                    <div class="alert alert-danger" role="alert">
                            El captcha no fue completado, porfavor verifique.
                    </div>
                    <div class="mb-4">
                        
                        <div class="g-recaptcha" data-sitekey="6LdPEoYaAAAAAEsvMtAn5Gs1_ZLwvXhv40BYgpAT" ></div>
                       
                    </div>
                    
                    
                    <div class="d-grid">
                        <button type="submit" class="btn btn-dark" >Ingresar</button>

                    </div>

                    <div class="my-3">
                        <span>No tienes cuenta? <a href="Registro.jsp">Registrate</a></span>
                        <br>
                        <span>Olvidaste tu contraseña? <a href="#">Recuperar Contraseña</a></span>
                    </div>
                </form>

            </div> 
        </div>


        <!--- scripts de bootstrap--->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js" integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js" integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG" crossorigin="anonymous"></script>
    </body>

</html>

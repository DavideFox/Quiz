<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<!--
	Hielo by TEMPLATED
	templated.co @templatedco
	Released for free under the Creative Commons Attribution 3.0 license (templated.co/license)
-->
<html>
	<head>
		<title>Quiz | Libro</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="<c:url value="assets/css/main.css" />" />
	</head>
	<body>

		<!-- Header -->
			<header id="header" class="alt">
				<div class="logo">Benvenuto, <%= request.getSession().getAttribute("username") %></div>
				<a href="#menu">Menu</a>
			</header>

		<!-- Nav -->
			<nav id="menu">
				<ul class="links">
					<li><a href="/">Home</a></li>
					<li><a href="/storico">Storico</a></li>
					<li><a href="/logout">Log out</a></li>
				</ul>
			</nav>


			<!-- Two -->
			<section id="two" class="wrapper style3">
				<div class="inner">
					<header class="align-center">
						<p>Java quiz</p>
						<h2>Nome libro</h2>
					</header>
				</div>
			</section>

		<!-- One -->
			<section id="one" class="wrapper style2">
				<div class="inner">
					<!-- Form -->
                    <form method="post" action="/domanda">
                        <div class="row uniform">
                            <div class="6u 12u$(small)">
                                <h3>Seleziona capitoli</h3>
                                <input type="checkbox" id="capitolo1" name="capitolo1">
                                <label for="capitolo1">Capitolo 1</label>
                            </div>
                            <div class="6u 12u$(small)">
                                <h3>Random?</h3>
                                <input type="checkbox" id="random" name="random">
                                <label for="random">random</label>
                            </div>
                            <div class="12u$">
                                <input type="checkbox" id="capitolo2" name="capitolo2">
                                <label for="capitolo2">Capitolo 2</label>
                            </div>
                            <div class="12u$">
                                <input type="checkbox" id="capitolo3" name="capitolo3">
                                <label for="capitolo3">Capitolo 3</label>
                            </div>
                            <div class="12u$">
                                <input type="checkbox" id="capitolo4" name="capitolo4">
                                <label for="capitolo4">Capitolo 4</label>
                            </div>
                            <!-- Break -->
                            <div class="12u$">
                                <h3>Quiz name:</h3>
                                <input type="text" name="quizName" id="quizName" value="" placeholder="Quiz name" />
                            </div>
                            <!-- Break -->
                            <div class="12u$">
                                <ul class="actions">
                                    <li><input type="submit" value="Start test" /></li>
                                </ul>
                            </div>
                        </div>
                    </form>
				</div>
			</section>

	

		


		<!-- Footer -->
		<footer id="footer">
			<div class="copyright">
				&copy; Beije Consulting. All rights reserved.
			</div>
		</footer>


		<!-- Scripts -->
			<script src="<c:url value="assets/js/jquery.min.js" />"></script>
			<script src="<c:url value="assets/js/jquery.scrollex.min.js" />"></script>
			<script src="<c:url value="assets/js/skel.min.js" />"></script>
			<script src="<c:url value="assets/js/util.js" />"></script>
			<script src="<c:url value="assets/js/main.js" />"></script>

	</body>
</html>
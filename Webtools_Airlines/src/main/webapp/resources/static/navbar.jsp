<nav class="navbar navbar-light" style="background-color: #e3f2fd;">

	<c:choose>
		<c:when test="${sessionScope.username == null}">
			<h1><a class="navbar-brand " href="#">Book My Flights</a></h1>
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="home.htm">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="register.htm">
						<span class="glyphicon glyphicon-user">Sign Up</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="login.htm">
						<span class="glyphicon glyphicon-log-in"></span>Login
				</a></li>
			</ul>

		</c:when>
		<c:when test="${sessionScope.username =='admin'}">
			<a class="navbar-brand" href="#">Book My Flights</a>
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="AdminHome.htm">Home</a></li>
				<li class="nav-item"><span class="navbar-text"> Logged
						in as : ${sessionScope.username} </span></li>
				<li class="nav-item"><a class="nav-link" href="logout.htm"><span
						class="glyphicon glyphicon-log-in"></span> Signout</a></li>
			</ul>


		</c:when>
		<c:otherwise>
			<a class="navbar-brand" href="#">Book My Flights</a>
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="bookingCart.htm">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="bookingHistory.htm">Booked History</a></li>
				<li class="nav-item"><span class="navbar-text"> Logged
						in as : ${sessionScope.username} </span></li>
				<li class="nav-item"><a class="nav-link" href="logout.htm"><span
						class="glyphicon glyphicon-log-in"></span> Signout</a></li>
			</ul>
		</c:otherwise>
	</c:choose>

</nav>


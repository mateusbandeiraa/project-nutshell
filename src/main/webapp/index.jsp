<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- BOOTSTRAP -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
	integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb"
	crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.2.1.min.js"
	integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
	crossorigin="anonymous"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"
	integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"
	integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ"
	crossorigin="anonymous"></script>
<!-- /BOOTSTRAP -->

<script src="./script/script.js"></script>

<title>Nutshell</title>
</head>
<body>
	<div class="container-fluid">

		<div class="row justify-content-center">
			<div class="carousel slide" data-ride="carousel"
				data-interval="false" id="mainCarousel">
				<div class="container carousel-inner">
					<div class="carousel-item active">

						<div class="card text-center" id="mainCard">
							<h4 class="card-header">Your tweets in a nutshell!</h4>
							<div class="card-body">
								<div class="card-title">
									<h5>We will need to read your 100 most recent tweets.</h5>
								</div>
								<p>We promise we won't record any of it in our server.</p>
								<form action="#" id="mainForm">
									<div class="input-group input-group-md">
										<span class="input-group-addon">@</span> <input type="text"
											class="form-control" placeholder="Username"
											aria-label="Username" aria-describedby="basic-addon1"
											id="twUsername" required="required" autocomplete="off"
											autocorrect="off" autocapitalize="off" spellcheck="false">
										<span class="input-group-btn">
											<button class="btn btn-primary" type="submit">Go!</button>
										</span>
									</div>
								</form>

							</div>
							<div class="card-footer text-muted" id="infoText">
								<a data-toggle="modal" data-target="#infoModal" href="#"><small>What's
										this?</small></a>
							</div>
						</div>
					</div>

					<div class="carousel-item">
						<div class="card text-center" id="secondaryCard">
							<div class="card-header">
								<h4>
									<i class="fa fa-arrow-left" id="backButton"></i> Your tweet in
									a nutshell is...
								</h4>
							</div>

							<div class="card-body">
								<blockquote class="blockquote">
									<p class="mb-0" id="tweetText">I really hope this works.</p>
									<footer class="blockquote-footer" id="quoteUsername">

									</footer>
								</blockquote>
								<a href="https://twitter.com/share?ref_src=twsrc%5Etfw"
									class="twitter-share-button" data-size="large"
									data-text="" data-url=""
									data-dnt="true" data-show-count="false">Tweet</a>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
	<div class="modal fade" tabindex="-1" role="dialog" id="loadingModal">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-body text-center">
					<i class="fa fa-refresh fa-spin fa-5x"></i>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" tabindex="-1" role="dialog" id="infoModal">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Hello!</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<blockquote class="blockquote">
						<p class="mb-0" id="tweetText">
							My name is Mateus Bandeira, a 17 year old student. I made this
							project in my free time on the weekend. I had so much fun reading
							my random tweets that I decided to show it to the world. As I'm
							still learning, there WILL be a lot of naive mistakes. So please
							be kind to me :-). Also, I'm <a
								href="https://www.twitter.com/mateusbandeiraa">@mateusbandeiraa</a>
							on twitter.
						</p>
					</blockquote>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
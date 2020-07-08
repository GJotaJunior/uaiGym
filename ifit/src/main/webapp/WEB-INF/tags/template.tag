<%@ tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="title" fragment="true" required="true"%>
<!DOCTYPE html>
<html>

<head>
<!-- Bootstrap core CSS -->
<link
	href="https://getbootstrap.com/docs/4.5/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
	
<link href="../../assets/css/template.css" rel="stylesheet">
<meta charset="UTF-8">
<jsp:invoke fragment="title"/>
</head>
<body>
	<header>
		<div class="collapse bg-dark" id="navbarHeader">
			<div class="container">
				<div class="row">
					<div class="col-sm-8 col-md-7 py-4">
						<h4 class="text-white">Sobre nós</h4>
						<p class="text-muted">Lorem ipsum dolor sit amet consectetur
							adipisicing elit. Aperiam aliquid porro nobis beatae, quibusdam
							repellendus expedita eius. Molestias asperiores impedit sint,
							provident non ad minima, beatae amet eaque totam reprehenderit.</p>
					</div>
					<div class="col-sm-4 offset-md-1 py-4">
						<h4 class="text-white">Mais</h4>
						<ul class="list-unstyled">
							<li><a href="https://github.com/GJotaJunior/uaiGym"
								class="text-white">Veja no Github</a></li>
							<li><a href="mailto:uaigymacad@gmail.com" class="text-white">Nos
									envie um email</a></li>
							<li><a href="/" class="text-white">Página inicial</a></li>
							<li><a href="/web/sair" class="text-white">Sair</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="navbar navbar-dark bg-dark shadow-sm">
			<div class="container d-flex justify-content-between">
				<a href="/web/" class="navbar-brand d-flex align-items-center"> <strong>UAIGYM</strong>
				</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarHeader" aria-controls="navbarHeader"
					aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
			</div>
		</div>
	</header>

	<div class="content p-4">
		<jsp:doBody />
	</div>

	<footer id="myFooter">
		<div class="container">
            <div class="row">
                <div class="col-sm-12 text-center p-4">
                	&copy; UaiGym 2020 - Todos os direitos reservados!
                </div>
            </div>
        </div>
        <!-- <p class="float-right">
			<a href="#">Voltar ao início</a>
		</p> -->
	</footer>
</body>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script>
	window.jQuery
			|| document
					.write('<script src="https://getbootstrap.com/docs/4.5/assets/js/vendor/jquery.slim.min.js"><\/script>')
</script>
<script
	src="https://getbootstrap.com/docs/4.5/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-1CmrxMRARb6aLqgBO7yyAxTOQE2AKb9GfXnEo760AUcUmFx3ibVJJAzGytlQcNXd"
	crossorigin="anonymous"></script>

</html>


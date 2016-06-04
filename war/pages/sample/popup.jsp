<%@ page contentType="text/html; Charset=EUC-KR" %> 

<html>
<body>
	<h1>Sample Popup</h1>

	<button type="button" onclick="goAjax()">go AJAX</button>

	<script type="text/javascript">
		function goAjax() {
			if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlhttp = new XMLHttpRequest();
			} else {// code for IE6, IE5
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					//alert(xmlhttp.responseText);
					//document.getElementById("txtHint").innerHTML = xmlhttp.responseText;
					//alert(window.opener.document.getElementById("result").innerHTML);
					window.opener.document.getElementById("result").innerHTML += xmlhttp.responseText;
					self.close();
					
				}
			}
			xmlhttp.open("GET", "ajax", true);
			xmlhttp.send();
		}
	</script>
</body>
</html>
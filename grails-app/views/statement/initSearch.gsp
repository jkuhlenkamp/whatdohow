<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<!--<meta name="layout" content="main"/>-->
<title>Insert title here</title>
</head>
<body>
  <div class="body">

	<g:form name="question_form" action="getflexible">

		<label>What?</label>
		<g:textField name="statewhat" id="what_input" class="query_input" placeholder="e.g. Salsa" value="${statementInstance?.statewhat}"/>
		
		<label>Action?</label>
		<g:textField name="statedo" id="do_input" class="query_input" placeholder="e.g. dance" value="${statementInstance?.statedo}"/>

		<label>How?</label>
		<g:textField name="statehow" id="how_input" class="query_input" placeholder="e.g. freely" value="${statementInstance?.statehow}"/>

		<label>Where?</label>
		<g:textField name="location" id="location_input" class="query_input" placeholder="e.g. New York" value="${statementInstance?.location}"/>

	</g:form>

	<div class="result_container">
	
	</div>

  </div>
  
  <g:javascript library="jquery"/>
  <script type="text/javascript">
	jQuery(".query_input").on("keyup", function(){ getResults(); });


	function getResults(){
		var l = jQuery("#location_input").val(),
			w = jQuery("#what_input").val(),
			d = jQuery("#do_input").val(),
			h = jQuery("#how_input").val();
		
		console.log(location);
		jQuery.post("getflexible", {location: l, statementwhat: w, statementdo: d, statementhow: h}, function(data){
			for(var i = 0; 0 < data.length; i++){
				console.log(data[i]);
			}

			jQuery(".result_container").text(data.location);
		});
			
	}
  </script>
  
</body>
</html>
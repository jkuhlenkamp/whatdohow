<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<!--<meta name="layout" content="main"/>-->
<title>Insert title here</title>
</head>
<body>
	<div class="own_container">
	
		<div class="row">
			<g:form name="question_form" action="getflexible">
				<div class="span3">
					<label>What?</label>
					<g:textField name="statewhat" id="what_input" class="query_input" placeholder="e.g. Salsa" value="${statementInstance?.statewhat}"/>
				</div>
				
				<div class="span3">
					<label>Action?</label>
					<g:textField name="statedo" id="do_input" class="query_input" placeholder="e.g. dance" value="${statementInstance?.statedo}"/>
				</div>
				
				<div class="span3">
					<label>How?</label>
					<g:textField name="statehow" id="how_input" class="query_input" placeholder="e.g. freely" value="${statementInstance?.statehow}"/>
				</div>
				
				<div class="span3">
					<label>Where?</label>
					<g:textField name="location" id="location_input" class="query_input" placeholder="e.g. New York" value="${statementInstance?.location}"/>
				</div>
		
			</g:form>
		</div>
	
		<div class="result_container"></div>

	</div>
  
  <g:javascript library="jquery"/>
  <script type="text/javascript">
	jQuery(".query_input").on("keyup", function(){ getResults(); });

	function getResults(){
		var l = jQuery("#location_input").val(),
			w = jQuery("#what_input").val(),
			d = jQuery("#do_input").val(),
			h = jQuery("#how_input").val();

		jQuery.post("getflexible", {statewhat: w, statedo: d, statehow: h, location: l}, function(data){
		    jQuery(".result_container").html("");
			$.each(data, function(i, e) {
				var html =	"<div class=\"result_row\">" +
								"<div class=\"result_entry\">" + e.statewhat + "</div>" +
								"<div class=\"result_entry\">" + e.statedo + "</div>" +	
								"<div class=\"result_entry\">" + e.statehow + "</div>" +
								"<div class=\"result_entry\">" + e.location + "</div>" +
							"</div>"
				jQuery(".result_container").append(html);
			});
		});
			
	}
  </script>
  
</body>
</html>
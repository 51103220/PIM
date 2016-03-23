/*******************************************************************************
 * *SELECT ON CHANGE
 ******************************************************************************/
function selectHandler() {
	$("select").change(function() {
		var class_name = $(this).attr('class').replace(/empty/g, "");
		$(this).attr('class', class_name);
	});

};
function fixBodyHeight() {
	var header_height = $(".header").outerHeight();
	var body_height = $(window).height() - header_height;
	$("#main").css("height", body_height + "px");
};

/*******************************************************************************
 * * SET AND UNSET SELECTED LINKS
 ******************************************************************************/
function setLinkSelected(link) {
	var newClass = link.attr("class") + " selected";
	link.attr("class", newClass);
};
function unsetLinkSelected(link) {
	var newClass = link.attr("class").replace(/selected/g, "");

	link.attr("class", newClass);
};
function setErrorInput(input,isSet){
	if (isSet){
		var newClass= input.attr("class") + " errorInput";
		input.attr("class",newClass);
	}
	else{
		var class_name = input.attr("class").replace(/errorInput/g, "");
		input.attr("class").attr(class_name);
	}
};
$(document).ready(function() {

	/***************************************************************************
	 * * Language Options * clicks
	 **************************************************************************/
	$(".header #langOption li a").click(function(e) {
		e.preventDefault();
		unsetLinkSelected($(this).parent().parent().find("a.selected"));
		setLinkSelected($(this));
	});
	/***************************************************************************
	 * * Menu item clicks
	 **************************************************************************/
	$("#main #menuList #selectList li a").click(function(e) {
		e.preventDefault();
		unsetLinkSelected($(this).parent().parent().find("a.selected"));
		var class_str = $(this).attr("class");
		if (class_str && class_str.indexOf("notAffected") >= 0) {
			setLinkSelected($(this).parent().next().find("a"));
		} else {
			setLinkSelected($(this));
		}
		// AJAX NAVIGATOR
		var url = $(this).attr("href");
		$.ajax({
			method : "GET",
			url : url
		}).done(function(data) {
			$("#main #contentBody").html(data);
		}).fail(function() {
			alert("error");
		});
	});

	/***************************************************************************
	 * *Date Picking event
	 **************************************************************************/
	$('.datePicker').datetimepicker();
	fixBodyHeight();

	/***************************************************************************
	 * * Form Handling
	 **************************************************************************/
	$(".general-content .processBtn").click(function(e) {
		e.preventDefault();
		var form = $(this).parent().parent().find(".general-form").first();
		var $inputs = form.find("input");
		var $selects = form.find("select");
		var values = {};
		$inputs.each(function() {
			alert($(this).attr("class"));
			setErrorInput($(this),false);
			values[$(this).attr("name")] = $(this).val();
		});
		$selects.each(function() {
			setErrorInput($(this),false);
			values[$(this).attr("name")] = $(this).val();
		});
		var new_values = {
			name : '',
			id : '1'
		};

		$.ajax({
			contentType : 'application/json',
			type : form.attr("method"),
			url : form.attr("action"),
			async : false,
			dataType : 'json',
			data : JSON.stringify(new_values),
			success : function(data) {
				if (data.status == "FAIL") {
					for (var i = 0; i < data.result.length; i++) {
						var field = data.result[i].field;
						var code = data.result[i].code;
						$inputs.each(function(){
							if($(this).attr("name") == field){
								setErrorInput($(this),true);
								$(this).parent().find("p.hiddenError").html(code);
							}
						});
						$selects.each(function(){
							if($(this).attr("name") == field){
								setErrorInput($(this),true);
								$(this).parent().find("p.hiddenError").html(code);
							}
						});
					}
				} else {
					location.reload(); // Only used for PIM
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("Status: " + textStatus);
				alert("Error: " + errorThrown);
			}
		})
	});
	selectHandler();
	$(".errorPanel .closePanel").click(function(e) {

		e.preventDefault();
		$(this).parent().hide();
	});

});
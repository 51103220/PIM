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
	;
	link.attr("class", newClass);
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
	selectHandler();
});
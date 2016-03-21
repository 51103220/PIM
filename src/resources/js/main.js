
/**
 * HANDLE FORMS
 */
$("select").change(function() {
	var class_name = $(this).attr('class').replace(/empty/g, "");
	$(this).attr('class', class_name);
});
$('.datePicker').datetimepicker();
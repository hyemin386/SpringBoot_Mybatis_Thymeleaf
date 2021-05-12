/**
 * 
 */
$(".pager").click(function(){
	let p = $(this).attr("title");
	$("#curPage").val(p);
	$("#frm").submit(); /* form 강제 전송 */
});
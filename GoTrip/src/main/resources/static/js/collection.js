
function insertDays(tourSelected){
	var days = $(tourSelected).val();
	var dayList = $('#dayList');
	$('.dayClass').remove();
	for(var i = 1; i <= days; i++){
		$(dayList).append('<option class="dayClass" value=' + i + '>' + '第' + i + '天' + '</option>');
	}
}

function getCheckedValue(){
	var tourId = $("select[name='tourSelected'] option:selected").attr('id');
	var day = $("select[name='daySelected'] option:selected").val();
	
	if(!tourId || day == "-1")
		alert("請選擇行程及天數");
	else{
		var arr = [];
		$.each($("input[name='selectedCol']:checked"), function(){
			var v = $(this).val();
			arr.push(v);
		});
		var colList = arr.join(',');
		if(arr.length === 0)
			alert("請勾選景點");
		else{
			$.ajax({
				url: '/user/saveToBasket',
				type: 'POST',
				contentType: 'application/json; charset=utf-8',
				data: JSON.stringify({'colList': colList, 'tourId':tourId, 'day': day}),
				success: function(response){alert("加入成功");},
				error: function(response){alert("加入失敗");}
			});
		}
	}

}

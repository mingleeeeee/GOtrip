//更動天數選擇(下拉式選單)
function insertDays(tourSelected){
	var days = $(tourSelected).val();
	var dayList = $('#dayList');
	$('.dayClass').remove();
	for(var i = 1; i <= days; i++){
		$(dayList).append('<option class="dayClass" value=' + i + '>' + '第' + i + '天' + '</option>');
	}
}

//送出欲新增至行程的景點清單
function getCheckedValue(){
	var tourId = $("select[name='tourSelected'] option:selected").attr('id');
	var day = $("select[name='daySelected'] option:selected").val();
	
	//check欄位不為空值
	if(!tourId || day == "-1")
		alert("請選擇行程及天數");
	else{
		var arr = [];
		$.each($("input[name='selectedCol']:checked"), function(){ //選擇所有被checked的checkbox
			var v = $(this).val();
			arr.push(v);
		});
		var colList = arr.join(','); //加入","回傳String
		if(arr.length === 0)  //尚未勾選景點
			alert("請勾選景點");
		else{
			//送出欲新增的詳細資料
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

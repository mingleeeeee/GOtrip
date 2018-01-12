$(document).ready(function() {
	//jquery validate套件驗證form
    $("#createAdminForm").validate({
      rules:{ //設定驗證規則
        username:{required:true},
        password:{required:true},
        password_again:{required:true, equalTo:"#password"},
        name:{required:true},
        phone:{required:true},
        email:{required:true, email:true}
      },
      messages:{ //設定錯誤訊息
        username:{required:"(必填)"},
        password:{required:"(必填)"},
        password_again:{required:"(必填)", equalTo:"(密碼不一致)"},
        name:{required:"(必填)"},
        phone:{required:"(必填)"},
        email:{required:"(必填)", email:"(請填入正確格式)"}   
      },
      errorPlacement: function(error, element) {   //設定錯誤訊息顯示位置
    	    $(element).closest('form').find("label[for='" + element.attr("name") + "']").append(error);
    	}
    });
});

//更新會員資料
function setModalValue(edit){
		var tr = $(edit).closest('tr');
		var username = $(tr).children('td').first().text();
		
		//回傳會員Primary key
		$.ajax({ 
            url: "/admin/memberUpdate",
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify({'username': username}),
            success: function(s){
  				var arr = s.split(",");
  				//設定input tag初值
  				$('#username').attr("value", arr[0]);
  				$('#name').attr("value", arr[1]);
  				$('#email').attr("value", arr[2]);
  				$('#phone').attr("value", arr[3]);
            },
            error: function(e){
              alert('儲存失敗');
            }
          });
	}
	
	//刪除會員帳號
	function deleteUser(delIcon){
		
		if(confirm("確定刪除帳號?")){			
			var tr = $(delIcon).closest('tr');
			var username = $(tr).children('td').first().text();
			
			//回傳會員Primary key
			$.ajax({ 
            	url: "/admin/memberDelete",
            	type: "POST",
            	contentType: 'application/json; charset=utf-8',
            	data: JSON.stringify({'username': username}),
            	success: function(s){
  					location.reload(); //重載頁面
            	},
            	error: function(e){
              		alert('儲存失敗');
            	}
			});
		}
	}
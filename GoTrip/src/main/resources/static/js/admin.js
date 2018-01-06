function setModalValue(edit){
		var tr = $(edit).closest('tr');
		var username = $(tr).children('td').first().text();
		
		$.ajax({ 
            url: "/admin/memberUpdate",
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify({'username': username}),
            success: function(s){
  				var arr = s.split(",");
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
	
	function deleteUser(delIcon){
		
		if(confirm("確定刪除帳號?")){			
			var tr = $(delIcon).closest('tr');
			var username = $(tr).children('td').first().text();
		
			$.ajax({ 
            	url: "/admin/memberDelete",
            	type: "POST",
            	contentType: 'application/json; charset=utf-8',
            	data: JSON.stringify({'username': username}),
            	success: function(s){
  					location.reload();
            	},
            	error: function(e){
              		alert('儲存失敗');
            	}
			});
		}
	}
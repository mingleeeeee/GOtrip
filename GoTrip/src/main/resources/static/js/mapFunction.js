      var map;
      var infowindow;
      var placeName;
      var lat = 0;
      var lng = 0;

      /*以裝置位置定位*/
      function initialLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function(position) {
              var pyrmont = {lat: 0, lng: 0};
              pyrmont.lat = position.coords.latitude;
              pyrmont.lng = position.coords.longitude;
              lat = position.coords.latitude;
              lng = position.coords.longitude;

              //以裝置位置初始化map
              initMap(pyrmont, 16);
          });
        }
      }

      /*以中心位置及縮放程度為參數，初始化map*/
      function initMap(pyrmont, zoom){
        map = new google.maps.Map(document.getElementById('map'), {
          center: pyrmont,
          zoom: zoom
        });

        infowindow = new google.maps.InfoWindow();
      }

      /*關鍵字搜尋*/
      function textSearch(){
        var service = new google.maps.places.PlacesService(map);
        var value = document.getElementById("input").value;
        var pyrmont = {lat: lat, lng: lng}; //給予初值
        var request = {
              location: pyrmont,
              radius: '1000',
              query: value
            };
        service.textSearch(request, callback1); //將查詢結果傳給callback1()
      }

      /*位置搜尋:以目前地圖中心搜尋參數query*/
      function positionSearch(query){
        var pyrmont = map.getCenter(); //取得地圖中心座標
        var zoom = map.getZoom();      //取得目前縮放大小
        initMap(pyrmont, zoom);        //初始化map
        //performSearch(query);          
        //map.addListener('idle', performSearch(query));
        if(query == "0")
          clearMarker();
        else{
          if(query == "1") query = "景點";
          else if(query == "2")query = "美食";
          else if(query == "3")query = "夜市";
          else if(query == "4")query = "購物";
          else if(query == "5")query = "古蹟";
          else if(query == "6")query = "公園";
          else query = "景點";
       
          var request = {
            bounds: map.getBounds(),
            keyword: query
          };
          var service = new google.maps.places.PlacesService(map);
          service.radarSearch(request, callback2);
        }
      
      }

      /*處理關鍵字搜尋的結果*/
      function callback1(results, status) {
        if (status === google.maps.places.PlacesServiceStatus.OK) {
          var markers = [];  //markers陣列存放每個結果的marker物件
          pyrmont = results[0].geometry.location; //以第一個景點為地圖中心
          initMap(pyrmont, 12);
          for (var i = 0; i < results.length; i++)
            markers.push(createMarker(results[i])); //將每個結果包裝為marker物件並push到markers陣列
          
          //以markers陣列為參數，加入群集
          var markerCluster = new MarkerClusterer(map, markers, {imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});
        }
      }

      /*處理位置搜尋的結果*/
      function callback2(results, status){
        var markers = [];
        if (status === google.maps.places.PlacesServiceStatus.OK) {
          for (var i = 0; i < results.length; i++)
            markers.push(createMarker(results[i]));
          
          var markerCluster = new MarkerClusterer(map, markers, {imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});
        }
      }

      /*以單一結果製成標記(marker)*/
      function createMarker(place){
        //var labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
        var marker = new google.maps.Marker({
          position: place.geometry.location,        //設定標記位置
          //draggable: true,                        //標記可拖曳
          //animation: google.maps.Animation.DROP   //標記出現動畫效果
        });
        createInfoWindow(marker, place);      //以標記及該地點資訊製成資訊視窗

        return marker;
      }

      /*以標記及該地點資訊製成資訊視窗*/
      function createInfoWindow(marker, place){
        var service = new google.maps.places.PlacesService(map);

        //標記被點選，出現資訊視窗
        google.maps.event.addListener(marker, 'click', function(){
          //取得詳細地點資料
          service.getDetails({placeId: place.place_id}, function(detail, status){
            
            if(status !== google.maps.places.PlacesServiceStatus.OK){
              console.error(status);
              alert("error");
              return;
            }
            else{
              placeName = detail.name;
              var isOpen = detail.opening_hours ? "營業中" : "非營業時間";  
              var base = '名稱: ' + detail.name + '<br>地址: ' + detail.formatted_address + '<br>' + isOpen + '<br>';           
              var create = '<span class="glyphicon glyphicon-plus-sign" style="font-size:16px"' + 
                           'onclick="createAttraction(\'' + detail.place_id + '\')">' + '</span>';      
              var website = detail.website;
              var content;

              //決定資訊視窗內容(少部分detail沒提供website資訊)
              if(typeof website == "undefined")
                content = base + create;
              else{
                website = '連結: <a href=' + website + ' + target="_blank">相關網站</a><br>';
                content = base + website + create;
              }
              
              infowindow.setContent(content);   //設定資訊視窗內容
              infowindow.open(map, marker);     //開啟資訊視窗
            }
          });
        });
        
      }

      /*使用者點選新增景點紐*/
      function createAttraction(placeId){
        var basket = $('#basket');
        //將景點加入SpotSearch.html右欄
        basket.append('<li class="dd-item" id=' + placeId + '><h5 class="title dd-handle" >' + placeName + 
          /*'<i class=" material-icons ">filter_none</i>*/'</h5><span style="display:none">' + placeId + 
          '</span><span style="display:none">-1</span><span class="glyphicon glyphicon-trash" onclick="removeAttraction(this)" style="color:red"></span></li>');
      }

      /*移除SpotSearch.html右欄的某一景點*/
      function removeAttraction(delBtn){
        $(delBtn).parent().remove();
      }

      /*清除目前地圖所有標記*/
      function clearMarker(){
        initMap(map.getCenter(), map.getZoom());
      }

      /*下拉式選單特定關鍵字搜尋*/
      function specialSearch(){
        //var kind = document.querySelector('input[name="kind"]:checked').value;
        var kind = $("#kind").val();  //取得選取的特定關鍵字
        positionSearch(kind);         //以此特定關鍵字搜尋
      }

      /*更新提籃內容(儲存到資料庫)*/
      function UpdateBasket(){
        var basket = [];
        var i = 1;
        $("ol li").each(function(){                       			 //取得<ol>中所有<li>
          var name = $(this).find('h5').text().trim();               //取得<li>中<h5>的文字，此處為place name
          var placeId = $(this).children('span').text().trim();      //取得<li>中<span>的文字，此處為place Id(隱藏)
          var id = $(this).children('span').next().text().trim();
          basket.push({id: id, name: name, placeId: placeId, sequence: i});     //以name及placeId為屬性初始化物件，push到basket陣列
          i++;     
        });

        //以ajax post動態傳送basket陣列回controller
        $.ajax({
          url: "/SaveBasket",
          type: "POST",
          contentType: 'application/json; charset=utf-8',
          data: JSON.stringify({'things': basket}),
          success: function(response){
            alert("儲存成功");
          },
          error: function(e){
            alert('儲存失敗');
          }
        });
      }
/*滚动固定顶部*/

function fixedTop(){
  var scrollH = $(document).scrollTop();
  var headerH = $(".yj-scroll-header").outerHeight();
  var fixedTopH = $(".yj-fixedTop-main").height();
  if(scrollH>headerH){
    $(".yj-fixedTop-main").addClass("yj-fixed-top");
    $(".yj-scrollBottom-container").css("padding-top",headerH);
  }else{
    $(".yj-fixedTop-main").removeClass("yj-fixed-top");
    $(".yj-scrollBottom-container").css("padding-top",0);
  }
}

/*选择input 增加样式*/
function checkedInput(){
  $(".yj-input-list").each(function(){
    var ischecked = $(this).find("input").prop("checked");
    if(ischecked == true){
      $(this).addClass("yj-active");
    }else{
      $(this).removeClass("yj-active");
    }
  })
}

/*选项卡*/
function tab(obj,id){
  $("."+obj+id).show().siblings().hide();
  $(".yj-tab"+id).addClass("yj-active").siblings().removeClass("yj-active");
}


/*弹出层*/
function maskIn(id){
  $(".yj-mask-main"+id).addClass("in");
  $(".yj-mask-body").addClass("fade");
  $("body").css("overflow","hidden");
}
function maskOut(){
  $(".yj-mask-body").removeClass("fade");
  $(".yj-mask-main").removeClass("in");
  $("body").css("overflow","auto");
}

/*重置*/
function formReset(nameId){
  document.getElementById(nameId).reset();
}

function attrTxt(){
  $("input:radio").change(function(){
    //console.log("我点击了radio");
    var labelTxt = $(this).siblings("label").text();
    var inputVal = $(this).val();
    var inputName = $(this).attr("name");
    var aa = "<span class='"+inputName+"'>"+labelTxt+"，</span>";
    var bb = $(".yj-selected-attr-txt").find("."+inputName).length;
    if(bb>0){
    $("."+inputName).text(labelTxt+"，");
    }else{
    $(".yj-selected-attr-txt").append(aa);
    }
    var attr2label = $(".yj-form-attr2 input:checked").siblings("label").text();
    var price2 = $(".yj-form-attr2 input:checked").val();
    var price1 = $(".yj-form-attr1 input:checked").val();
    if(price2==undefined){
      price2='0';
    }if(price1==undefined){
      price1='0';
    }
    $(".yj-price2").html(attr2label);
    var countPrice = parseFloat(price1)+parseFloat(price2);
    $(".yj-price-count").text(countPrice);
  });
 }


 /*引用layui 的插件*/
 layui.use('form', function(){
  var form = layui.form;
});


/*登录弹窗*/
function login_popup(){
  layer.open({
    type: 2,
    title: '登录',
    area: ['80%', '3.5rem'],
    shadeClose: true, //点击遮罩关闭
    shade: .5,
    btnAlign: 'c',
    content: ['loginPopup.html','no']
  });
}

/*购买月卡 弹窗*/
function month_buy(){
  layer.open({
    type: 2,
    title: '购买月卡',
    area: ['80%'],
    shadeClose: true, //点击遮罩关闭
    shade: .5,
    btnAlign: 'c',
    content: ['month_buy.html','no']

  });
}
/*付款 弹窗*/
function paystyle(){
  layer.open({
    type: 2,
    title: '请选择付款方式',
    area: ['80%'],
    shadeClose: true, //点击遮罩关闭
    shade: .5,
    btnAlign: 'c',
    content: ['paystyle.html','no']

  });
}

/*评价星际*/
function star(that,id){
  for(var i=0; i<=id; i++){
    $(".yj-star"+i).addClass("yj-color-red").removeClass("yj-color-gray-light").html("&#xe658;");
    $(".yj-star"+i).nextAll().addClass("yj-color-gray-light").removeClass("yj-color-red").html("&#xe600;");
  }
}

/*图片自适应居中*/
function picCenter(){
  var picW = $(".yj-picBox").width();
  console.log(picW);
  $(".yj-picBox").css({
    "text-align": "center",
    "height":picW+"px",
    "line-height":picW+"px"
  });
  $(".yj-picBox img").css({
    "max-width":"100%",
    "max-height": "100%",
    "width": "auto",
    "height": "auto"
  });
}


function daoqi(){
  layer.open({
    type: 1,
    title: '您有机器即将到期 ！', 
    closeBtn: false,
    area: '300px;',
    shade: 0.8,
    id: 'LAY_layuipro', //设定一个id，防止重复弹出
    btn: ['续费', '忽略'],
    btnAlign: 'c',
    //moveType: 1, //拖拽模式，0或者1
    content: `
      <div class="yj-padding yj-text-small yj-bg-gray-light">
        <div class="yj-order-basicinfo yj-pad-tb yj-mar-b">
          <span class="yj-goods-thumbnail" style="width: 5em; height: 5em;"><img src="http://print.jsgj360.com/Upload/20180320/20180320170738_40822.jpg"></span>
          <div class="yj-confirm-goods-textinfo">
            <p class="yj-elip-2 yj-text-small">爱普生（EPSON）L310 墨仓式 彩色打印机</p>
            <p class="yj-color-gray yj-mar-t-big">选择："押金￥199.00" ，"100小时"</p>
          </div>
        </div>
        <div class="yj-pad-lr yj-line-height-2">
          <p class="yj-color-gray">租机时间：2015-03-08 14:34:20</p>
          <p class="yj-color-red">到期时间：2015-03-08 14:34:20</p>
        </div>
      </div>
      `,
    success: function(layero){
      var btn = layero.find('.layui-layer-btn');
      btn.find('.layui-layer-btn0').attr({
        href: '#',
        target: '_blank'
      });
    }
  });
}
/**
 * Created by hong on 2017/11/5.
 */
$(function(){

    function getSensorState(){
        $.ajax({
            url:'/getSensorState',
            type:'POST',
            dataType:'json',
            success:function(data){
                console.log(data);
                if(data.CODE='200'){
                    var sensorData = data.DATA[0];
                    $(".update_time").text("更新时间为："+format(sensorData["update_time"]));
                    for(property in sensorData){
                        $(".show_table input:radio[name='"+property+"'][value='"+sensorData[property]+"']").prop("checked", "checked");
                    }
                }

            },
            error:function(xhr,textStatus){
                console.log('错误');
                console.log(xhr);
                console.log(textStatus);
            }
        })
    }

    function add0(m){return m<10?'0'+m:m }

    function format(timestamp)
    {
        var time = new Date(timestamp);
        var year = time.getFullYear();
        var month = time.getMonth()+1;
        var date = time.getDate();
        var hours = time.getHours();
        var minutes = time.getMinutes();
        var seconds = time.getSeconds();
        return year+'-'+add0(month)+'-'+add0(date)+' '+add0(hours)+':'+add0(minutes)+':'+add0(seconds);
    }



    setInterval(getSensorState,1000);
})
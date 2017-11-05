/**
 * Created by hong on 2017/11/5.
 */
$(function(){
    function getConstant(){
        $(".constantTable tr:gt(0)").remove();
        $.ajax({
            url:'/getConstant',
            type:'POST',
            dataType:'json',
            success:function(data){
                console.log(data);
                if(data.CODE='200'){
                    var sensorData = data.DATA;
                    for(index in sensorData){
                        constant = sensorData[index];
                        var str = "<tr class='"+constant["constant_type"]+"'>" +
                                    "<td><input name='id'' type='text' value='"+constant["id"]+"' readonly='true'/></td>" +
                                    "<td><input name='constant_type'' type='text' value='"+constant["constant_type"]+"' readonly='true'/></td>" +
                                    "<td><input name='constant_low'' type='text' value='"+constant["constant_low"]+"'/></td> " +
                                    "<td><input name='constant_hight'' type='text' value='"+constant["constant_hight"]+"'/></td>" +
                                "</tr>";
                        $(".constantTable").append(str);
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

    getConstant();


    function updateConstant(){
        var args = {};
        var dataset = [];
        $(".constantTable tr:gt(0)").each(function(){
            var obj={};
            $(this).find("input").each(function(){
                var key = $(this).attr("name");
                var value = $(this).val();
                obj[key] = value;
            });
            dataset.push(obj);
        })

        $.ajax({
            url:'/updateConstant',
            type:'POST',
            data:{
                dataset:JSON.stringify(dataset)
            },
            dataType:'json',
            success:function(data){
                if(data.CODE='200'){
                    alert(data.DESC);
                    getConstant();
                }
            },
            error:function(xhr,textStatus){
                console.log('错误');
                console.log(xhr);
                console.log(textStatus);
            }
        })
    }

    $(".updataButton").click(function(){
        updateConstant();
    })
    $(".resetButton").click(function(){
        getConstant();
    })
})
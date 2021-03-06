function query_Date(msgString,type){
		var msg= eval("(" + msgString + ")");
		if(type==1){
			good_queryData_window_chat("销售统计",msg.names,msg.values,msg.pieValue,"销售客户比例");
		}
		if(type==2){
			good_queryData_window_chat("进货统计",msg.names,msg.values,msg.pieValue,"进货厂商比例");
		}
		if(type==3){
			good_queryData_window_chat2("价格趋势",msg.date,msg.price,msg.inprice,msg.num,msg.profit);
		}
		if(type==4){
			good_queryData_window_chat3("仓库存放量",msg.hName,msg.hNum,msg.hCount,msg.hPer,"仓库存放比例");
		}
	}
function good_queryData_window_chat(title,names,data1,data2,title2){
	$('#container').highcharts({                                      
        chart: {                                                          
        },                                                                
        title: {                                                          
            text: title                                    
        },                                                                
        xAxis: {                                                          
            categories:names
        },                                                                
        tooltip: {                                                        
            formatter: function() {                                       
                var s;                                                    
                if (this.point.name) { // the pie chart                   
                    s = ''+                                               
                        this.point.name +': '+ this.y +' %';         
                } else {                                                  
                    s = ''+                                               
                        this.x  +': '+ this.y;                            
                }                                                         
                return s;                                                 
            }                                                             
        },                                                                
        labels: {                                                         
            items: [{                                                     
                html: title2,                          
                style: {                                                  
                    left: '25px',                                         
                    top: '3px',                                           
                    color: 'black'                                        
                }                                                         
            }]                                                            
        },                                                                
        series: [{                                                        
            type: 'column',                                               
            name: title,                                                 
            data: data1                                         
        },{                                                              
            type: 'pie',                                                  
            name: title2,                                    
            data: data2,                                                           
            center: [30, 40],                                            
            size: 70,                                                    
            showInLegend: false,                                          
            dataLabels: {                                                 
                enabled: false                                            
            }                                                             
        }]                                                                
    });                                     
}
function good_queryData_window_chat3(title,names,data1,data2,data3,title2){
	$('#container').highcharts({                                      
        chart: {                                                          
        },                                                                
        title: {                                                          
            text: title                                    
        },                                                                
        xAxis: {                                                          
            categories:names
        },                                                                
        tooltip: {                                                        
            formatter: function() {                                       
                var s;                                                    
                if (this.point.name) { // the pie chart                   
                    s = ''+                                               
                        this.point.name +': '+ this.y +' %';         
                } else {                                                  
                    s = ''+                                               
                        this.x  +': '+ this.y;                            
                }                                                         
                return s;                                                 
            }                                                             
        },                                                                
        labels: {                                                         
            items: [{                                                     
                html: title2,                          
                style: {                                                  
                    left: '25px',                                         
                    top: '3px',                                           
                    color: 'black'                                        
                }                                                         
            }]                                                            
        },                                                                
        series: [{                                                        
            type: 'column',                                               
            name: '库存量',                                                 
            data: data1                                         
        },{                                                        
            type: 'column',                                               
            name: '历史库存最大值',                                                 
            data: data2                                         
        },{                                                              
            type: 'pie',                                                  
            name: title2,                                    
            data: data3,                                                           
            center: [30, 40],                                            
            size: 70,                                                    
            showInLegend: false,                                          
            dataLabels: {                                                 
                enabled: false                                            
            }                                                             
        }]                                                                
    });                                     
}
function good_queryData_window_chat2(title,categories,price,inprice,num,profit){
	$('#container').highcharts({                                          
        chart: {
        	
        },                                                                
        title: {                                                          
            text: title                                    
        },                                                                
        xAxis: {                                                          
            categories:categories
        },                                                                                                                                                            
        series: [
                 {                                                        
            type: 'column',                                               
            name: "销量",                                                 
            data: num                                         
        },{                                                              
            type: 'spline',                                               
            name: '进价',                                              
            data: inprice,                               
            marker: {                                                     
            	lineWidth: 2,                                               
            	lineColor: Highcharts.getOptions().colors[1],               
            	fillColor: 'white'                                          
            }                                                             
        },{                                                              
            type: 'spline',                                               
            name: '销售价',                                              
            data: price,                               
            marker: {                                                     
            	lineWidth: 2,                                               
            	lineColor: Highcharts.getOptions().colors[2],               
            	fillColor: 'white'                                          
            }                                                             
        },{                                                              
            type: 'spline',                                               
            name: '总毛利润',                                              
            data: profit,                               
            marker: {                                                     
            	lineWidth: 2,                                               
            	lineColor: Highcharts.getOptions().colors[3],               
            	fillColor: 'white'                                          
            }                                                             
        }]                                                                
    });                                     
}
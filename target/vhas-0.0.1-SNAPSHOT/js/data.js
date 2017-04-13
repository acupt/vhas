window.onload = function(){
	dongman();
	dianshijiu();
}
function dongman() {
	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById('dongman'));

	//var myChart = echarts.init(document.getElementById('main'));
	// 显示标题，图例和空的坐标轴
	myChart.setOption({
		title: {
			text: '异步数据加载示例'
		},
		tooltip: {},
		legend: {
			data: ['销量']
		},
		xAxis: {
			data: []
		},
		yAxis: {},
		series: [{
			name: '销量',
			type: 'bar',
			data: []
		}]
	});

	// 异步加载数据
	var xData = [];
	var yData = [];
	$.ajax({
		type:"get",
		url:"json/dataTest.json",
		async:true,
		dataType:"json",
		success:function(data){
			var newdata = data.data.typeup;
			//console.log(newdata);
			for(var i=0;i<newdata.length;i++){
				yData.push(newdata[i].click);
				xData.push(newdata[i].uptime);
				//y.push({,})
			}
			myChart.setOption({
			xAxis: {
				data: xData
			},
			series: [{
				// 根据名字对应到相应的系列
				name: '销量',
				data: yData
			}]
		});
		}
	});
	myChart.setOption(option);
}

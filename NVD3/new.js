d3.json("new.json",function(data) {
 nv.addGraph(function() {
      var chart = nv.models.scatterChart()
    ;
chart.width(900);
        chart.xAxis
		.tickFormat(d3.format('d'))
		.axisLabel("x");
        chart.yAxis
		.tickFormat(d3.format('d'))
		.axisLabel("y");
        d3.select('#test1 svg')
            .datum(data)
            .call(chart);

		chart.tooltip.contentGenerator(
		function (e) {   
		var data = e; 
		var result="";
		var p = data.point;
			for (var key in p) {
				var attrName = key;
				var attrValue = p[key];
				if(attrName!= "series" && attrName!= "color" && attrName!= "size" && attrName!= "shape"){
				result=result+"<h3>"+ attrName + ' : ' + attrValue +"</h3>";
				}
			}
 
			return result;
		});
		
		chart.tooltip.hideDelay(30000);
        nv.utils.windowResize(chart.update);
		chart.dispatch.on('stateChange', function(e) { ('New State:', JSON.stringify(e)); });
        return chart;
});
});
<%--
  Created by IntelliJ IDEA.
  User: Shengwei_Wang
  Date: 11/21/16
  Time: 6:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Company History</title>
</head>
<style>
    .axis path,
    .axis line {
        fill: none;
        stroke: black;
        shape-rendering: crispEdges;
    }

    .axis text {
        font-family: sans-serif;
        font-size: 11px;
    }

</style>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <h3>
                Stock Monitor
            </h3>
        </div>
    </div>
    <div class="row-fluid">
        <div class="span12">
            <script src="http://d3js.org/d3.v3.min.js" charset="utf-8"></script>
            <script>
                var width = 400;
                var height = 400;

                var list = ${it}
                var padding = {left: 30, right: 30, top: 20, bottom: 20};

                var svg = d3.select("body")
                        .append("svg")
                        .attr("width", width)
                        .attr("height", height)


                //define data array
                var dataset = [];
                if (list.length < 10) {
                    for (var p in list) {
                        dataset[p] = {x: p, y: Math.round(list[p].price)};
                    }
                } else {
                    for (var p = 1; p <= 10; ++p) {
                        dataset[10 - p] = {x: 10 - p, y: Math.round(list[list.length - p].price)};
                    }
                }

                var xScale = d3.scale.linear()
                        .domain(d3.extent(dataset, function (d) {
                            return d.x;
                        }))
                        .range([0, width - padding.left - padding.right]);
                var yScale = d3.scale.linear()
                        .domain([100, d3.max(dataset, function (d) {
                            return d.y + 30;
                        })])
                        .range([height - padding.top - padding.bottom, 0]);


                //define y axis
                var xAxis = d3.svg.axis()
                        .scale(xScale)
                        .orient("bottom");

                //define x axis
                var yAxis = d3.svg.axis()
                        .scale(yScale)
                        .orient("left");

                //add x axis
                svg.append("g")
                        .attr("class", "axis")
                        .attr("transform", "translate(" + padding.left + "," + (height - padding.bottom) + ")")
                        .call(xAxis);

                //add y axis
                svg.append("g")
                        .attr("class", "axis")
                        .attr("transform", "translate(" + padding.left + "," + padding.top + ")")
                        .call(yAxis);

                //add points
                svg.selectAll("circle")
                        .data(dataset)
                        .enter()
                        .append('circle')
                        .attr("transform", "translate(" + padding.left + "," + padding.top + ")")
                        .attr('cx', function (d) {
                            return xScale(d.x);
                        })
                        .attr('cy', function (d) {
                            return yScale(d.y);
                        })
                        .attr('r', 2);


            </script>
        </div>
    </div>
</div>
</body>
</html>

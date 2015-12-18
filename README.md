# getting-started
Examples for using OpenCompare API and services

Our project is about OpenCompare & Product Charts. Our Mission is to generate from  a comparison matrix a productCharts, using Java, JavaScript, Json, Html and CSS. The result must be displayed on a web page and usable on any browser and mobile device (cellphones , tablets ... )

At first, we did tests with the ”pcm” available in getting-started on github in order to see how the “pcm” works with java. After That, we have created a json file using java. We have created a JsonExport Class that contains functions that work with any pcm file given in parameter. A pcm file contains the datas of the matrix.  

Our project is based on Client/Server Architecture.

In our web page, we have two parts, the chart and the filters.

We Use NVD3 for the formatting of the chart.  For each product, we can have specific datas by positioning on this product on the graph. We can have many informations for each product and also links and photos. NVD3 has an Apache Licence.

The filter is done in html and permit us to choose one or many properties of a product.

We have used Eclipse, WampServer and Notepad++ for this project.

In our project we have two main parts: a web part that is in the NVD3 folder and the other one for java part is in the src folder of the project. We display the final result with WampServer so you can just copy the NVD3 folder in the server and launch index.html to see the final result.











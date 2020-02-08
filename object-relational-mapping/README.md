# How to map object inheritance to relational tables


## Single table inheritance
Map all fields in the object graph to [one table](https://martinfowler.com/eaaCatalog/singleTableInheritance.html)
 
![alt text](https://martinfowler.com/eaaCatalog/singleInheritanceTableSketch.gif "Single table inheritance")

## Class table inheritance
Map each class fields to [a table](https://martinfowler.com/eaaCatalog/classTableInheritance.html)

![alt text](https://martinfowler.com/eaaCatalog/classInheritanceTableSketch.gif "Class table inheritance")


## Concrete table inheritance
For each object map all the fields plus the inherited fields to [a table](https://martinfowler.com/eaaCatalog/concreteTableInheritance.html)

![alt text](https://martinfowler.com/eaaCatalog/leafInheritanceTableSketch.gif "Concrete table inheritance")
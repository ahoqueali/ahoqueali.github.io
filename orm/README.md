# How to map class inheritance to relational tables


## Single table inheritance
Map all the fields in the graph to a single table.
 
![alt text](https://ahoque.org/orm/single-table-inheritance.png "Single table inheritance")

## Class table inheritance
For each class create a table and map the fields.

![alt text](https://ahoque.org/orm/class-table-inheritance.png "Class table inheritance")


## Concrete table inheritance
For each concrete class, map all the fields plus the inherited fields into the table.

![alt text](https://ahoque.org/orm/concrete-class-table-inheritance.png "Concrete table inheritance")


## References
* Single, Class and Concrete table inheritance [Martin Fowler](https://martinfowler.com/eaaCatalog/index.html)

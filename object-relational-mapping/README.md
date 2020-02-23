# How to map object inheritance to relational tables


## Single table inheritance
Map all the fields in the object graph to a single table.
 
![alt text](https://ahoque.org/object-relational-mapping/single-table-inheritance.png "Single table inheritance")

## Class table inheritance
For each class create a table and map the fields.

![alt text](https://ahoque.org/object-relational-mapping/class-table-inheritance.png "Class table inheritance")


## Concrete table inheritance
For each concrete class, map all the fields plus the inherited fields into the table.

![alt text](https://ahoque.org/object-relational-mapping/concrete-class-table-inheritance.png "Concrete table inheritance")


## References
* Single, Class and Concrete table inheritance [Martin Fowler](https://martinfowler.com/eaaCatalog/index.html)
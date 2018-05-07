

=== The Simple library Object Oriented Design ==
The Simple library system consists of the following classes:
1. Library - the main facade to the library system
2. Inventory - the titles repository
3. Member - the user of the library who can borrow a copy of a title
4. Title - the title is the book, DVD, CD
5. TitleCopy - each title can have many title copies and they can be of different media formats.. e.g. book, dvd, cd

 == Extensibility ==
The system allows extensibility through adding of new media types by extending the AbstractTitleItem. and implementing a media specific interface for specific functionalities.

== Development Process == 
The system was developed using TDD with BDD acceptance criteria. Programming by interface.

== Thread Safety and scalability ==  
The system is made thread safe and scalable by:
1. using ConcurrentHashMap for storing titles and members. The ConcurrentHashMap is used as it offers thread-safety and high throughput as it uses CAS. 
2. The CopyOnWriteArray is used for tracking the state of the copies of titles and the copies borrowed by a member.
3. The immutable loan class is used to track the loaning of a title copy and is managed using an AtomicReference.
 
 

== How To run ==
The program uses the gradle build tool to build and test.  To the run the unit tests:
1. unzip the file simple-library.zip
2. from the terminal, cd into the unzipped folder.
3. execute the following command the terminal 

	./gradlew clean build test  


Below is a programming assignment. You are allowed to use third-party libraries and tools in order for developing, building, and testing purposes (please note exclusions below). Please include an explanation of your design, assumptions as well as detailed instructions on how to run the application along with your code (no more than one side of A4). We don't require a front-end or a command-line interactive interface. We are most interested in how you design your classes and solve the problem at hand.

The code is assessed for:

* the design

* object-oriented programming skills

* production quality

* maintainability

* extensibility

* scalability

Please compress only the source (including the build files) into a single zip. There should be no jarfiles or executables in the submitted zip file.

The Problem

You have been tasked with building part of a simple library system that allows customers to borrow books, DVDs, VHSes, and CDs. Your code should provide the following functionality:

* Borrow and return items - items are loaned out for a period of one week.

o For example, a customer can borrow WarGames on 21st February and they will be expected to return it by 28th February.

* Determine current inventory - this should show you the current items that are loanable. You should make allowances for multiple copies of the same item (i.e. there can be multiple copies of the same book/movie).

o For example, if you choose to use the initial inventory, the current inventory should return the titles.

* Determine overdue items. i.e. all items that should have been returned before today.

o For example, if a book was due on 12th February and today is 15th February, that book should be flagged as overdue.

* Determine the borrowed items for a user.

* Determine if a book is available to borrow.

Notes

* No JDBC

* No Hibernate or anything similar.

* No distributed caches.

* Make it thread safe.
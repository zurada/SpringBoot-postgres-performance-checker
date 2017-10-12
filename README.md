#Postrges Performance Checker:

Checks performance of database when using different entity ID types.

Application compares performance between two the same entities when using different ID types:
- Entity "Transaction" with ID as Long using Hibernate strategy GenerationType.IDENTITY
- Entity "Transaction" with ID as UUID String using Hibernate 5 with "uuid-2" generators

More about Hibernate generation strategies at: http://docs.jboss.org/hibernate/core/3.6/reference/en-US/html/mapping.html#d0e5294

package com.route.todosappc38online.designPatterns

val database1 = Database.getInstance() // Single object
val database2 = Database.getInstance() //

object DatabaseInstance2 {
    val name: String? = null

}


class Database private constructor() {

    // static -> java
    // static
    companion object {
        //        Database?     => nullable
//        Conditional Access null // Null Pointer Exception - java
//        database ?: Database()
//
//        Non Null Assertion  !! -> If variable null else
        private var databaseInstance: Database? = null
        fun getInstance(): Database {
            // Not null object
            if (databaseInstance == null) {
                databaseInstance = Database()
            }
            return databaseInstance!!

        }

    }

}


/**
 *  Design Patterns :- A set of OOP solutions for common problems
 *      Creational :-  the way you create objects
 *          singleton , Factory , Builder , Prototype
 *      Structural :- The communication between Classes
 *          data classes -> Views
 *          SuraNameModel -> SuraNameAdapter -> View (XML )
 *      Behavoiral :- The Communication Between Objects and Each other
 *
 *
 * /
/*
 * Singleton  (Creational Patterns) :- I create only single Object - Global Accessible
 *
 *          Database :- Single Object
 *          Whatsapp :- Can't have more than account
 *                      Single Object - Global accessible
 *
 *
 *    // 2-  Database Concept - Tables - Column & Rows  - Room Database
 *     // Dao (Data Access Object) -
 *
 *     Log.e("TAG", "onCreate:  ${database1.hashCode()}")
 *     Log.e("TAG", "onCreate:  ${database2.hashCode()}")
 *     Log.e("TAG", "onCreate:  ${DatabaseInstance2.hashCode()}")
 *     Log.e("TAG", "onCreate:  ${DatabaseInstance2.hashCode()}")
 *
 *     //Todos save memory -> List<Todo> = listOf(Todo) X
 *     // Isalmi () -> Files
 *     // File Manipulation (CRUD Operations ) Create - Read From File - Update - Delete
 *     // Database (Posts , Customers - News )
 *     // Database :- A Set of relational Tables
 *     // Table :- (Columns and Rows )
 *     // Database Inspector
 *
 *     // Primary Key :- Unique Key for A single Record (Row)
 *     // Room
 *
 *     //MySql - sql serve - Oracle Server - Sqlite ()
 *
 *     // Web - Desktop Apps -                 Mobile phones  (Lack Of Resources )
 *     // SQLite (
 *     //
 *     // )
 * //        1- val query = "SELEC * FROM CUSTOMERS"  // Can't Detect error because it's string
 * //        you find error in runtime not compile time
 *     // 2- Boilerplate Code
 *     // Customers Table => class
 *     // id , first_name , last_name , date_created => attributes inside a class
 *     data class Customers(
 *         val id: Int? = null,
 *         val firstName: String? = null,
 *         val lastName: String? = null,
 *     )
 *     // ORM (Object Relational Mapping )
 *
 *     val customer1 = Customers(1, "Mohamed", "Ahmed")
 *     val customer2 = Customers(2, "Kareem", "Mohamed")
 *
 *     // Contacts
 *     // Data loss
 * }
**/
 **/
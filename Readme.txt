Overview:

The Book Store Application is a graphical user interface (GUI) based software designed to manage a virtual bookstore. It offers two distinct user roles: Owner and Customer. The app provides a seamless, single-window interface where users can access various features such as adding, removing, and purchasing books, managing customers, and tracking loyalty points.

Key Features:

* Login System:
    * The application starts with a login screen.
    * Owners can log in using the default credentials (username: admin, password: admin).
    * Customers log in using their unique username and password.

* Owner Dashboard:
    * Owners have access to the owner-start-screen upon login.
    * From here, they can navigate to the ‘Books’ or ‘Customers’ sections or log out.

* Books Management:
    * Owners can add and delete books from the inventory.
    * The ‘Books’ section displays the current inventory with book names and prices.
    * Owners can add new books by specifying the name and price.
    * Deletion of books is also supported.

* Customers Management:
    * Owners can add and remove customer profiles.
    * The ‘Customers’ section lists registered customers along with their usernames, passwords, and loyalty points.
    * Owners can add new customers by providing a username and password.
    * Deletion of customer profiles is also supported.

* Customer Dashboard:
    * Registered customers access their dashboard upon login.
    * The dashboard displays their name, earned points, and status (Silver or Gold) based on points.

* Book Purchases:
    * Customers can browse available books in the bookstore.
    * They can select one or more books for purchase.
    * The ‘Buy’ button allows customers to make a purchase with currency.
    * The ‘Redeem points and Buy’ button lets customers use their loyalty points for discounts.
    * Loyalty points accumulate based on the amount spent and are redeemed for discounts.

Technical Details:
* The application is implemented in Java using JavaFX for the graphical user interface.
* Data for books and customers is stored in the ‘books.txt’ and ‘customers.txt’ files.
* Upon exit, current data is written to these files, and upon startup, data is loaded from these files.

Conclusion:
The Book Store Application is an intuitive and efficient way to manage a virtual bookstore. Owners can easily control the inventory and customer database, while customers can make purchases and track their loyalty points. This project demonstrates proficiency in Java and JavaFX for building practical, user-friendly applications.

Tools Used:
* Java
* JavaFX
* Visual Paradigm for UML Diagrams

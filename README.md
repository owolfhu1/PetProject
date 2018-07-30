<strong>Connect to database on compass:</strong><br/>
    + Hostname: ds147411.mlab.com<br/>
    + Port: 47411<br/>
    + Authentication: Username/Password<br/>
    + Username: orion<br/>
    + Password: pass12<br/>
    + Authentication Database: heroku_m46f7v7k<br/>
<br/>
<strong>To run API:</strong><br/>
    + Clone project: 'git clone https://github.com/owolfhu1/PetProject.git'<br/>
    + Import maven files<br/>
    + Run main/java/com/catalyte/OrionsPets/AppRunner.java<br/>
<br/>
<strong>To set up the database:</strong><br/>
    * password in header = "password", for all SetupController<br/>
    + clear: localhost:8080/setup/clear<br/>
    + create dummy data: localhost:8080/setup/create-dummy<br/>
    ---- In header: pets(int, MAX = 200)<br/>
    ---- In header: customers(int, MAX = 100)<br/>
    ---- In header: purchases(int, MAX = pets/2)<br/>
    + create empty database with one admin: localhost:8080/setup/create-empty<br/>
    ---- In header: adminUsername : String<br/>
    ---- In header: adminPassword : String<br/>
<br/>
<strong>View all other paths with swagger:</strong><br/>
    + localhost:8080/swagger-ui.html#/<br/>
<br/>
<strong>access POST/PUT/DELETE endpoints requires user/pass:</strong><br/>
    + add to header:<br/>
    ----(for dummy database) username : admin<br/>
    ----(for dummy database) password : password
    
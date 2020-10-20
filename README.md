# Recipe-book

## Working with Recipe book in your IDE

### Prerequisites
The following items should be installed in your system:
* Java 8 or newer
* Spring Tools Suite
* Maven
* Appache TomCat v9.0 
* Git command line tool (https://help.github.com/articles/set-up-git)
* Your preffered IDEA
  * Eclipse
  * IntelliJ IDEA

optional

* MySQL
 
 ### Steps:
 
 1) On the command line
    ```
    git clone https://github.com/ProgIsLove/Recipe-book.git
    ```
  2) Inside Eclipse
    ```
    File -> Import -> Maven -> Existing Maven project
    ```
 3) Compile and navigate to Recipe book
  
  Visit http://localhost:8080 in your browser.
  
###  Optional 
You can use MySQL database insted of H2 database. Go into ```src/main/resources/application.properties``` and comment whole section h2 database.
  
  
# <img alt="section" src="https://imgur.com/yYttCLt.png">
  
  4) Open MySql in CMD prompt and login
  ```
  ${your_path}\MySQL\MySQL Server 8.0\bin>mysql -u root -p
  ```  
# <img alt="login" src="https://imgur.com/zASSNV7.png">  
  
  5) Create databases, user and add user privileges
  ```diff
  ! All commands you can find in the file 'recipe_database_create.sql'
  ```
  
  
   ```
   create database sfg_dev;
   ```
   ```
   create user 'sfg_dev_user'@'localhost' identified by 'tom';
   ```
   ```
   grant all on sfg_dev.* to 'sfg_dev_user'@'localhost';
   ```
#  <img alt="databases" src="https://imgur.com/Dd76HDo.png">
#  <img alt="user" src="https://imgur.com/Dljarlo.png">
#  <img alt="grant" src="https://imgur.com/VY6YRTg.png">

   6) Create tables
   ```
   create table category (id bigint not null auto_increment, description varchar(255), primary key (id)) engine=InnoDB;
   create table ingredient (id bigint not null auto_increment, amount decimal(19,2), description varchar(255), recipe_id bigint, uom_id bigint, primary key (id)) engine=InnoDB;
   create table notes (id bigint not null auto_increment, recipe_notes longtext, recipe_id bigint, primary key (id)) engine=InnoDB; 
   create table recipe (id bigint not null auto_increment, source varchar(255), cook_time integer, description longtext, difficulty varchar(255), directions longtext, image longblob, prep_time integer, servings integer, url varchar(255), notes_id bigint, primary key (id)) engine=InnoDB;
   create table recipe_category (recipe_id bigint not null, category_id bigint not null, primary key (recipe_id, category_id)) engine=InnoDB;
   create table unit_of_measure (id bigint not null auto_increment, description varchar(255), uom varchar(255), primary key (id)) engine=InnoDB;
   alter table ingredient add constraint FKj0s4ywmqqqw4h5iommigh5yja foreign key (recipe_id) references recipe (id);
   alter table ingredient add constraint FK6iv5l89qmitedn5m2a71kta2t foreign key (uom_id) references unit_of_measure (id);
   alter table notes add constraint FKdbfsiv21ocsbt63sd6fg0t3c8 foreign key (recipe_id) references recipe (id);
   alter table recipe add constraint FK37al6kcbdasgfnut9xokktie9 foreign key (notes_id) references notes (id);
   alter table recipe_category add constraint FKqsi87i8d4qqdehlv2eiwvpwb foreign key (category_id) references category (id);
   alter table recipe_category add constraint FKcqlqnvfyarhieewfeayk3v25v foreign key (recipe_id) references recipe (id);
   ```
  # <img alt="data" src="https://imgur.com/83Wl66w.png">
  
  7) Enjoy :)
  
  # <img alt="recipe" src="https://imgur.com/XvXwtkG.png">
  
  # <img alt="guacamole" src="https://imgur.com/SDsRDwl.png">

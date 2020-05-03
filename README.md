objective of this project
1.integrate spring boot with mysql database using jdbc template
2.there are 2 ways to use jdbc template 
	1.using simpleJdbcCall
	2.using callable statement
3.use of objectmapper for converting json to object and vice versa
4.reading stored procedure name using configuration file(application.property) file

store procedure without input parameter
---------------------------------------

DELIMITER $$

DROP PROCEDURE IF EXISTS `fetch_student` $$
CREATE  PROCEDURE `fetch_student`(
 -- IN loc_username VARCHAR(255),
  -- IN loc_password VARCHAR(255)
)
BEGIN

SELECT JSON_ARRAYAGG(JSON_OBJECT('id', id, 'firstname', firstname,'lastname',lastname)) from student;

END $$

stored procedure using input parameter
----------------------------------------
DELIMITER $$

DROP PROCEDURE IF EXISTS `fetch_student_with_input_param` $$
CREATE  PROCEDURE `fetch_student_with_input_param`(
 IN first_name VARCHAR(255)
  -- IN loc_password VARCHAR(255)
)
BEGIN

SELECT JSON_ARRAYAGG(JSON_OBJECT('id', id, 'firstname', firstname,'lastname',lastname)) from student where firstname = first_name;

END $$

DELIMITER ;

SELECT JSON_ARRAYAGG(JSON_OBJECT('id', id, 'firstname', firstname,'lastname',lastname)) from student where firstname = first_name;
the above statement gives json array as a query output

create statement
--------------------
create table student(
   id INT NOT NULL AUTO_INCREMENT,
   firstname  VARCHAR(100) NOT NULL,
   lastname VARCHAR(40) NOT NULL,
  
   PRIMARY KEY ( id )
);
DELIMITER ;	

CREATE TABLE users(
  id bigint(8) NOT NULL AUTO_INCREMENT,
  fname varchar(15) NOT NULL ,
  lname varchar(150),
  email varchar(250) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

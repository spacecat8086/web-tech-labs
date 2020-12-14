CREATE DATABASE CarRentalSystem;

CREATE TABLE `CarRentalSystem`.`CLIENT` (
  `USER_ID` INT NOT NULL AUTO_INCREMENT,
  `USERNAME` VARCHAR(45) NULL,
  `PASS` VARCHAR(100) NULL,
  `SURNAME` VARCHAR(45) NULL,
  `NAME` VARCHAR(45) NULL,
  `PASSPORT_NUMBER` VARCHAR(45) NULL,
  `EMAIL` VARCHAR(45) NULL,
  `CLIENT_TYPE` VARCHAR(45) NULL,
  `ACTIVE` INT NULL,
  `CREDIT` INT NULL,
  PRIMARY KEY (`USER_ID`));

  CREATE TABLE `CarRentalSystem`.`ORDERC` (
  `order_id` INT NOT NULL,
  `user_id` INT NULL,
  `car_id` INT NULL,
  `sum_to_pay` INT NULL,
  `CONFIRMED` INT NULL,
  `PAIDFOR` INT NULL,
  `RETURNED` INT NULL,
  PRIMARY KEY (`order_id`));

  CREATE TABLE `CarRentalSystem`.`CAR` (
  `car_id` INT NOT NULL,
  `CARNAME` VARCHAR(45) NULL,
  `CARPRICE` INT NULL,
  `CARIMAGE` VARCHAR(200) NULL,
  `ACTIVE` INT NULL,
  PRIMARY KEY (`car_id`));

  CREATE TABLE `CarRentalSystem`.`damagebill` (
  `bill_id` INT NOT NULL,
  `damage` VARCHAR(200) NULL,
  `sum` INT NULL,
  `order_id` INT NULL,
  `repaired` INT NULL,
  PRIMARY KEY (`bill_id`));

  CREATE TABLE `CarRentalSystem`.`bill` (
  `bill_id` INT NOT NULL,
  `order_id` INT NULL,
  `period` INT NULL,
  `price` INT NULL,
  `startdate` DATETIME NULL,
  PRIMARY KEY (`bill_id`));


  ALTER TABLE `CarRentalSystem`.`CAR`
CHANGE COLUMN `CARPRICE` `price` INT(11) NULL DEFAULT NULL ;

ALTER TABLE `CarRentalSystem`.`CAR`
CHANGE COLUMN `CARIMAGE` `image` VARCHAR(200) NULL DEFAULT NULL ;

ALTER TABLE `CarRentalSystem`.`CAR`
CHANGE COLUMN `car_id` `car_id` INT(11) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `CarRentalSystem`.`ORDERC`
CHANGE COLUMN `order_id` `order_id` INT(11) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `CarRentalSystem`.`bill`
CHANGE COLUMN `bill_id` `bill_id` INT(11) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `CarRentalSystem`.`damagebill`
CHANGE COLUMN `bill_id` `bill_id` INT(11) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `CarRentalSystem`.`ORDERC`
ADD COLUMN `reason_for_refusal` VARCHAR(300) NULL AFTER `RETURNED`;

-- admin admin1234
INSERT INTO `CarRentalSystem`.`CLIENT` (`USER_ID`, `USERNAME`, `PASS`, `SURNAME`, `NAME`, `PASSPORT_NUMBER`, `EMAIL`, `CLIENT_TYPE`, `ACTIVE`, `CREDIT`) VALUES ('1', 'admin', 'ac9689e2272427085e35b9d3e3e8bed88cb3434828b43b86fc0596cad4c6e270', 'admin', 'admin', '1234567890', 'admin@admin.com', 'ADMIN', '1', '1000');

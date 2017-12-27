create database batch;

use batch;

CREATE TABLE `batch`.`user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `full_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `mobile_number` VARCHAR(15) NOT NULL,
  `created_timestamp` TIMESTAMP NOT NULL,
  `updated_timestamp` TIMESTAMP NULL,
  `is_active` BIT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC));


CREATE TABLE `batch`.`user_task` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `due_date` DATETIME NOT NULL,
  `priority` VARCHAR(10) NOT NULL,
  `user_id` INT(11) NOT NULL,
  `user_notified` BIT NOT NULL DEFAULT 0,
  `created_timestamp` TIMESTAMP NOT NULL,
  `updated_timestamp` TIMESTAMP NULL,
  `is_active` BIT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_task_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_task`
    FOREIGN KEY (`user_id`)
    REFERENCES `batch`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


#Populating User table
INSERT INTO `batch`.`user` (`id`, `full_name`, `email`, `mobile_number`, `created_timestamp`, `updated_timestamp`, `is_active`) VALUES ('1', 'Waylon Dalton', 'waylon.dalton@gmail.com', '123456789', current_timestamp(), null, 1);
INSERT INTO `batch`.`user` (`id`, `full_name`, `email`, `mobile_number`, `created_timestamp`, `updated_timestamp`, `is_active`) VALUES ('2', 'Justine Henderson', 'justine.henderson@gmail.com', '123456798', current_timestamp(), null, 1);
INSERT INTO `batch`.`user` (`id`, `full_name`, `email`, `mobile_number`, `created_timestamp`, `updated_timestamp`, `is_active`) VALUES ('3', 'Abdullah Ali', 'abdullah.ali@yahoo.com', '236985741', current_timestamp(), null, 1);
INSERT INTO `batch`.`user` (`id`, `full_name`, `email`, `mobile_number`, `created_timestamp`, `updated_timestamp`, `is_active`) VALUES ('4', 'Marcus Cruz', 'marcus.cruz@hotmail.com', '369852147', current_timestamp(), null, 1);
INSERT INTO `batch`.`user` (`id`, `full_name`, `email`, `mobile_number`, `created_timestamp`, `updated_timestamp`, `is_active`) VALUES ('5', 'Thalia Cobb', 'thalia.cobb', '741258963', current_timestamp(), null, 1);
INSERT INTO `batch`.`user` (`id`, `full_name`, `email`, `mobile_number`, `created_timestamp`, `updated_timestamp`, `is_active`) VALUES ('6', 'Mathias Little', 'mathias.little@live.com', '123654789', current_timestamp(), null, 1);
INSERT INTO `batch`.`user` (`id`, `full_name`, `email`, `mobile_number`, `created_timestamp`, `updated_timestamp`, `is_active`) VALUES ('7', 'Eddie Randolph', 'eddie.randolph@yahoo.com', '412563987', current_timestamp(), null, 1);
INSERT INTO `batch`.`user` (`id`, `full_name`, `email`, `mobile_number`, `created_timestamp`, `updated_timestamp`, `is_active`) VALUES ('8', 'Angela Walker', 'angela.walker@live.com', '965874123', current_timestamp(), null, 1);


#Populating User Task table
INSERT INTO `batch`.`user_task` (`name`, `due_date`, `priority`, `user_id`, `user_notified`, `created_timestamp`, `updated_timestamp`, `is_active`) VALUES ('Submit Application', NOW() - INTERVAL 2 DAY, 'NORMAL', 1, 0, current_timestamp(), null, 1);
INSERT INTO `batch`.`user_task` (`name`, `due_date`, `priority`, `user_id`, `user_notified`, `created_timestamp`, `updated_timestamp`, `is_active`) VALUES ('Review Application', NOW() - INTERVAL 1 DAY, 'HIGH', 2, 0, current_timestamp(), null, 1);
INSERT INTO `batch`.`user_task` (`name`, `due_date`, `priority`, `user_id`, `user_notified`, `created_timestamp`, `updated_timestamp`, `is_active`) VALUES ('Assign Reviewers', NOW() - INTERVAL 1 DAY, 'LOW', 3, 0, current_timestamp(), null, 1);
INSERT INTO `batch`.`user_task` (`name`, `due_date`, `priority`, `user_id`, `user_notified`, `created_timestamp`, `updated_timestamp`, `is_active`) VALUES ('Vet Budget', NOW() - INTERVAL 1 DAY, 'HIGH', 4, 0, current_timestamp(), null, 1);
INSERT INTO `batch`.`user_task` (`name`, `due_date`, `priority`, `user_id`, `user_notified`, `created_timestamp`, `updated_timestamp`, `is_active`) VALUES ('Score Test', NOW() + INTERVAL 3 DAY, 'NORMAL', 4, 0, current_timestamp(), null, 1);
INSERT INTO `batch`.`user_task` (`name`, `due_date`, `priority`, `user_id`, `user_notified`, `created_timestamp`, `updated_timestamp`, `is_active`) VALUES ('Submit Proposal to Supervisor', NOW() - INTERVAL 1 DAY, 'LOW', 8, 0, current_timestamp(), null, 1);




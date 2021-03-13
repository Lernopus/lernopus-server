------------------------------------------------------------------------------ USER TABLE FOR LERNOPUS --------------------------------------------------------------------------------------------

CREATE TABLE `la_learn_user` (
  `la_user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `la_user_name` varchar(8) NOT NULL,
  `la_user_full_name` varchar(90) NOT NULL,
  `la_mail_id` varchar(100) NOT NULL,
  `la_phone_number` bigint(10) NOT NULL,
  `la_image_path` longtext NOT NULL,
  `la_password` varchar(250) NOT NULL,
  `la_created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `la_updated_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `la_created_user` bigint(20) DEFAULT NULL,
  `la_updated_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`la_user_id`),
  UNIQUE KEY `la_learn_user_username` (`la_user_name`),
  UNIQUE KEY `la_learn_user_email` (`la_mail_id`),
  UNIQUE KEY `la_learn_user_phone_number` (`la_phone_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------ ROLE TABLE FOR LERNOPUS --------------------------------------------------------------------------------------------

CREATE TABLE `la_learn_role` (
  `la_role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `la_role_name` varchar(60) NOT NULL,
  `la_role_description` varchar(180) NOT NULL,
  `la_created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `la_updated_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `la_created_user` bigint(20) DEFAULT NULL,
  `la_updated_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`la_role_id`),
  UNIQUE KEY `la_learn_role_role_name` (`la_role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------ USER - ROLE LINK TABLE FOR LERNOPUS --------------------------------------------------------------------------------
------------------------------------------------------------------------------ MANY TO MANY MAPPING - USER ROLE MAPPING ---------------------------------------------------------------------------
CREATE TABLE `la_learn_user_roles` (
  `la_role_id` bigint(20) NOT NULL,
  `la_user_id` bigint(20) NOT NULL,
  `la_created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `la_updated_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `la_created_user` bigint(20) DEFAULT NULL,
  `la_updated_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`la_user_id`,`la_role_id`),
  KEY `fk_la_learn_user_roles_role_id` (`la_role_id`),
  CONSTRAINT `fk_la_learn_user_roles_role_id` FOREIGN KEY (`la_role_id`) REFERENCES `la_learn_role` (`la_role_id`),
  CONSTRAINT `fk_la_learn_user_roles_user_id` FOREIGN KEY (`la_user_id`) REFERENCES `la_learn_user` (`la_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------ COURSE TABLE FOR LERNOPUS ------------------------------------------------------------------------------------------
CREATE TABLE `la_learn_course` (
  `la_course_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `la_course_name` varchar(250) NOT NULL,
  `la_course_description` varchar(1000) NOT NULL,
  `la_course_content_text` longtext NOT NULL,
  `la_course_content_html` longtext NOT NULL,
  `la_author_id` bigint(20) NOT NULL,
  `la_is_note` binary default false,
  `la_allow_comment` binary default false,
  `la_allow_rating` binary default false,
  `la_what_will_i_learn` longtext DEFAULT NULL,
  `la_prerequisite` longtext DEFAULT NULL,
  `la_url_reference` longtext DEFAULT NULL,
  `la_slide_show_url_reference` DEFAULT NULL,
  `la_video_url_reference` longtext DEFAULT NULL,
  `la_course_background_image` longtext DEFAULT NULL,
  `la_file_and_url_mapping` longtext DEFAULT NULL,
  `la_course_parent_id` bigint(20) NOT NULL,
  `la_course_root_id` bigint(20) NOT NULL,
  `la_created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `la_updated_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `la_created_user` bigint(20) DEFAULT NULL,
  `la_updated_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`la_course_id`),
  CONSTRAINT `fk_la_learn_course_author_id` FOREIGN KEY (`la_author_id`) REFERENCES `la_learn_user` (`la_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------ TECHNOLOGY TABLE FOR LERNOPUS --------------------------------------------------------------------------------------
CREATE TABLE `la_learn_technology` (
  `la_tech_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `la_tech_group` varchar(140),
  `la_tech_category` varchar(140),
  `name` varchar(140) NOT NULL,
  `id` varchar(140) NOT NULL,
  `la_created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `la_updated_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `la_created_user` bigint(20) DEFAULT NULL,
  `la_updated_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`la_tech_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------ RATING TABLE FOR LERNOPUS ------------------------------------------------------------------------------------------
CREATE TABLE `la_learn_course_rating` (
  `la_rating_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `la_course_id` bigint(20) NOT NULL,
  `la_upvote_count` bigint(20) NOT NULL,
  `la_downvote_count` bigint(20) NOT NULL,
  `la_user_rating` bigint(20) NOT NULL,
  `la_no_of_views` bigint(20) NOT NULL,
  `la_user_id` bigint(20) NOT NULL,
  `la_user_id_reference` bigint(20) NOT NULL,
  `la_course_id_reference` bigint(20) NOT NULL,
  `la_created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `la_updated_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `la_created_user` bigint(20) DEFAULT NULL,
  `la_updated_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`la_rating_id`),
  UNIQUE KEY `la_learn_course_rating_course_id` (`la_course_id`),
  UNIQUE KEY `la_learn_course_rating_user_id` (`la_user_id`)
  CONSTRAINT `fk_la_learn_course_rating_course_id` FOREIGN KEY (`la_course_id`) REFERENCES `la_learn_course` (`la_course_id`)
  CONSTRAINT `fk_la_learn_course_rating_user_id` FOREIGN KEY (`la_user_id`) REFERENCES `la_learn_user` (`la_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------ USER - AUTHOR LINK TABLE FOR LERNOPUS --------------------------------------------------------------------------------
------------------------------------------------------------------------------ MANY TO MANY MAPPING - USER AUTHOR MAPPING ---------------------------------------------------------------------------
CREATE TABLE `la_learn_author_subscription` (
  `la_author_id` bigint(20) NOT NULL,
  `la_user_id` bigint(20) NOT NULL,
  `la_created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `la_updated_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `la_created_user` bigint(20) DEFAULT NULL,
  `la_updated_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`la_user_id`,`la_author_id`),
  KEY `fk_la_learn_author_subscription_author_id` (`la_author_id`),
  CONSTRAINT `fk_la_learn_author_subscription_author_id` FOREIGN KEY (`la_author_id`) REFERENCES `la_learn_course` (`la_user_id`),
  CONSTRAINT `fk_la_learn_author_subscription_user_id` FOREIGN KEY (`la_user_id`) REFERENCES `la_learn_user` (`la_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------ USER - COURSE LINK TABLE FOR LERNOPUS --------------------------------------------------------------------------------
------------------------------------------------------------------------------ MANY TO MANY MAPPING - USER COURSE MAPPING ---------------------------------------------------------------------------
CREATE TABLE `la_learn_course_subscription` (
  `la_course_id` bigint(20) NOT NULL,
  `la_user_id` bigint(20) NOT NULL,
  `la_created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `la_updated_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `la_created_user` bigint(20) DEFAULT NULL,
  `la_updated_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`la_user_id`,`la_course_id`),
  KEY `fk_la_learn_course_subscription_course_id` (`la_course_id`),
  CONSTRAINT `fk_la_learn_course_subscription_course_id` FOREIGN KEY (`la_course_id`) REFERENCES `la_learn_course` (`la_course_id`),
  CONSTRAINT `fk_la_learn_course_subscription_user_id` FOREIGN KEY (`la_user_id`) REFERENCES `la_learn_user` (`la_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------ COURSE - TECH LINK TABLE FOR LERNOPUS --------------------------------------------------------------------------------
------------------------------------------------------------------------------ MANY TO MANY MAPPING - COURSE TECH MAPPING ---------------------------------------------------------------------------
CREATE TABLE `la_learn_course_tech` (
  `la_course_id` bigint(20) NOT NULL,
  `la_tech_id` bigint(20) NOT NULL,
  `la_created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `la_updated_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `la_created_user` bigint(20) DEFAULT NULL,
  `la_updated_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`la_course_id`,`la_tech_id`),
  KEY `fk_la_learn_course_tech_course_id` (`la_course_id`),
  CONSTRAINT `fk_la_learn_course_tech_course_id` FOREIGN KEY (`la_course_id`) REFERENCES `la_learn_course` (`la_course_id`),
  CONSTRAINT `fk_la_learn_course_tech_tech_id` FOREIGN KEY (`la_tech_id`) REFERENCES `la_learn_technology` (`la_tech_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------ COURSE - COMMENT LINK TABLE FOR LERNOPUS --------------------------------------------------------------------------------
------------------------------------------------------------------------------ MANY TO MANY MAPPING - COURSE COMMENT MAPPING ---------------------------------------------------------------------------
CREATE TABLE `la_learn_course_comments` (
  `la_course_id` bigint(20) NOT NULL,
  `la_comments_id` bigint(20) NOT NULL,
  `la_comments_content` longtext NOT NULL,
  `la_created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `la_updated_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `la_created_user` bigint(20) DEFAULT NULL,
  `la_updated_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`la_comments_id`),
  KEY `fk_la_learn_course_comments_comments_id` (`la_comments_id`),
  CONSTRAINT `fk_la_learn_course_comments_course_id` FOREIGN KEY (`la_course_id`) REFERENCES `la_learn_course` (`la_course_id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------ USER - FOLLOWERS LINK TABLE FOR LERNOPUS --------------------------------------------------------------------------------
------------------------------------------------------------------------------ MANY TO MANY MAPPING - USER - FOLLOWERS MAPPING ---------------------------------------------------------------------------
CREATE TABLE `la_learn_user_followers` (
  `la_followers_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `la_user_id` bigint(20) NOT NULL,
  `la_user_follower_id` bigint(20) NOT NULL,
  `la_created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `la_updated_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `la_created_user` bigint(20) DEFAULT NULL,
  `la_updated_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`la_followers_id`),
  KEY `fk_la_learn_user_followers_follower_id` (`la_followers_id`),
  CONSTRAINT `fk_la_learn_user_followers_user_id` FOREIGN KEY (`la_user_id`) REFERENCES `la_learn_user` (`la_user_id`),
  CONSTRAINT `fk_la_learn_user_followers_user_follower_id` FOREIGN KEY (`la_user_follower_id`) REFERENCES `la_learn_user` (`la_user_id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
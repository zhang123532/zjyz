CREATE TABLE zjyz.`user` (
	uid varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
	cid varchar(100) NOT NULL,
	user_name varchar(20) NOT NULL,
	password varchar(20) NOT NULL,
	company_name varchar(50) NULL,
	CONSTRAINT user_pk PRIMARY KEY (uid),
	CONSTRAINT user_unique UNIQUE KEY (user_name)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_bin;

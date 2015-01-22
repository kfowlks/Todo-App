-- Created by Vertabelo (http://vertabelo.com)
-- Script type: create
-- Scope: [tables, references, sequences, views, procedures]
-- Generated at Wed Jan 21 20:39:24 UTC 2015






-- tables
-- Table: Lists
CREATE TABLE Lists (
    id int  NOT NULL,
    title varchar(50)  NOT NULL,
    description varchar(255)  NOT NULL,
    completeby timestamp  NOT NULL,
    createdon timestamp  NOT NULL,
   CONSTRAINT Lists_pk PRIMARY KEY (id)
);

CREATE INDEX Lists_idx_1 on Lists (title ASC);



-- Table: Tasks
CREATE TABLE Tasks (
    id int  NOT NULL,
    list_id int  NOT NULL,
    description varchar(255)  NOT NULL,
    completed boolean  NOT NULL,
    createdon timestamp  NOT NULL,
   CONSTRAINT Tasks_pk PRIMARY KEY (id)
);

CREATE INDEX Tasks_idx_1 on Tasks (id ASC);







-- foreign keys
-- Reference:  Tasks_Lists (table: Tasks)


ALTER TABLE Tasks ADD CONSTRAINT Tasks_Lists 
    FOREIGN KEY (list_id)
    REFERENCES Lists (id)
;





-- End of file.


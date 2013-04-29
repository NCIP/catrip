/*L
  Copyright Duke Comprehensive Cancer Center

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/catrip/LICENSE.txt for details.
L*/

DROP DATABASE IF EXISTS deid;
CREATE DATABASE deid;
USE deid;

CREATE TABLE users (id INT NOT NULL AUTO_INCREMENT, userName VARCHAR(255), tableName VARCHAR(255), PRIMARY KEY(id), INDEX (userName), INDEX(tableName));
CREATE TABLE deid (id INT NOT NULL AUTO_INCREMENT, phi VARCHAR(255), val VARCHAR(255), PRIMARY KEY(id), INDEX (phi), INDEX(val));
INSERT INTO users (userName, tableName) VALUES ("root", "deid");
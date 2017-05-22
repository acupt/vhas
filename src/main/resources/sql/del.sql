CREATE DATABASE vhas DEFAULT CHARACTER SET utf8mb4;
DELETE FROM `vhas`.`video` WHERE `id`>0;
DELETE FROM `vhas`.`videoupdate` WHERE `id`>0;
DELETE FROM `vhas`.`author` WHERE `id`>0;
DELETE FROM `vhas`.`authorupdate` WHERE `id`>0;

ALTER TABLE `users` DROP `admin`;
ALTER TABLE `users` DROP `canmodifyworld`;
ALTER TABLE `users` DROP `ignoresrestrictions`;

ALTER TABLE `users` ADD `admin/unrestricted` tinyint(1) DEFAULT NULL;
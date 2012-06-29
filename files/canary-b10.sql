ALTER TABLE `homes`  ADD `dimension` tinyint(1) NOT NULL DEFAULT '0';
ALTER TABLE `homes`  ADD `world` VARCHAR(32) NOT NULL DEFAULT 'world';
ALTER TABLE `warps`  ADD `world` VARCHAR(32) NOT NULL DEFAULT 'world';

/* Change the world name to your world */
UPDATE homes SET world = 'world' WHERE world = '';
UPDATE warps SET world = 'world' WHERE world = '';
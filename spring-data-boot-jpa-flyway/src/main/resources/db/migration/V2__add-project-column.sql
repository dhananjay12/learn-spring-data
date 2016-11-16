ALTER TABLE `employee` ADD COLUMN (project varchar(200));

UPDATE `employee` SET project = 'Unassigned' WHERE project is null;
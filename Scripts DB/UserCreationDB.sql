/*---------------------------------------------------*/
-- Creacion del usuario Administrador para conectar
-- el proyecto.
/*---------------------------------------------------*/
CREATE USER 'parkitAdmin'@'%' IDENTIFIED BY 'admin';
GRANT SELECT, INSERT, UPDATE, DELETE ON `parkitdb`.* TO 'parkitAdmin'@'%';


DELIMITER $$

CREATE PROCEDURE `sp_inserirContato`(_idAluno INT, _nome VARCHAR(255) NOT NULL, _telefone VARCHAR(20), _parentesco VARCHAR(15))

BEGIN

	INSERT INTO Endereco(idAluno, nome, telefone, parentesco) VALUES (_idAluno, _nome, _telefone, _parentesco);

END $$
DELIMITER ;
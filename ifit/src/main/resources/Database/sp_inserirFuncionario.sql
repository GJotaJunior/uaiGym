DELIMITER $$

CREATE PROCEDURE `sp_inserirFuncionario`(IN _idUsuario INT, _contrato VARCHAR(255), _dtAdmissao DATE)

BEGIN

	INSERT INTO Funcionario(idUsuario, contrato, dtAdmissao) VALUES (_idUsuario, _contrato, _dtAdmissao);

END $$
DELIMITER ;
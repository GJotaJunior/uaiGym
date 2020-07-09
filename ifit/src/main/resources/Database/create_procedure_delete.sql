#sp_deleta_contato
DELIMITER //
CREATE PROCEDURE `sp_deleta_contato` (IN _idContato INT, OUT _status INT)
BEGIN
	IF (SELECT idContato FROM contato WHERE idContato = _idContato) is null
	THEN
		SET _status = 100;
	ELSE
		SET _status = 200;
		DELETE FROM Contato WHERE idContato = _idContato;
	END IF;
END; //

DELIMITER ;

#sp_deleta_equipamento
DELIMITER //
CREATE PROCEDURE `sp_deleta_equipamento` (IN _idEquipamento INT, OUT _status INT)
BEGIN
	IF (SELECT idEquipamento FROM equipamento WHERE idEquipamento = _idEquipamento) is null
	THEN
		SET _status = 100;
	ELSE
		IF (SELECT idEquipamento FROM exercicio WHERE idEquipamento = _idEquipamento) is not null
			THEN
				SET _status = 301;
			ELSE
				SET _status = 200;
                DELETE FROM equipamento WHERE idEquipamento = _idEquipamento;
			END IF;
		END IF;
END; //

DELIMITER ;

#sp_deleta_exercicio
DELIMITER //
CREATE PROCEDURE `sp_deleta_exercicio` (IN _idExercicio INT, OUT _status INT)
BEGIN
	IF((SELECT idExercicio FROM exercicio WHERE idExercicio = _idExercicio) is null)
    THEN
		SET _status = 100;
	ELSE
		IF((SELECT idExercicio FROM exerciciostreino WHERE idExercicio = _idExercicio) is null )
        THEN
			SET _status = 200;
            DELETE FROM exercicio WHERE idExercicio = _idExercicio; 
		ELSE
			SET _status = 401;
		END IF;
	END IF;
END; //

DELIMITER ;

#sp_deleta_telefone
DELIMITER //
CREATE PROCEDURE `sp_deleta_telefone` (IN _idTelefone INT, OUT _status INT)
BEGIN
	IF (SELECT idTelefone FROM telefone WHERE idTelefone = _idTelefone) is null
	THEN
		SET _status = 100;
	ELSE
		SET _status = 200;
		DELETE FROM Telefone WHERE idTelefone = _idTelefone;
	END IF;
END; //

DELIMITER ;

#sp_deleta_treino
DELIMITER //
CREATE PROCEDURE `sp_deleta_treino` (IN _idTreino INT, OUT _status INT)
BEGIN
	IF((SELECT idTreino FROM treino WHERE idTreino = _idTreino) is null)
    THEN
		SET _status = 100;
	ELSE
		IF((SELECT idTreino FROM exerciciostreino  WHERE idTreino = _idTreino) is not null )
        THEN
			SET _status = 501;
		ELSE
			IF((SELECT idTreino FROM alunotreino WHERE idTreino = _idTreino) is null )
            THEN
				SET _status = 200;
				DELETE FROM treino WHERE idTreino = _idTreino; 
			ELSE
				SET _status = 502;
            END IF;
		END IF;
	END IF;
END; //

DELIMITER ;

#sp_desativa_aluno
DELIMITER //
CREATE PROCEDURE `sp_desativa_aluno` (IN _idAluno INT, OUT _status INT)
BEGIN
	IF (SELECT idAluno FROM aluno WHERE idAluno = _idAluno) is null
	THEN
		SET _status = 100;
	ELSE
		SET _status = 200;
		UPDATE aluno SET status = 'INATIVO' WHERE idAluno = _idAluno ;
	END IF;
END; //

DELIMITER ;

#sp_desativa_funcionario
DELIMITER //
CREATE PROCEDURE `sp_desativa_funcionario` (IN _idFuncionario INT, OUT _status INT)
BEGIN
	IF (SELECT idFuncionario FROM funcionario WHERE idFuncionario = _idFuncionario) is null
	THEN
		SET _status = 100;
	ELSE
		SET _status = 200;
		UPDATE funcionario SET dtDemissao = CURDATE() WHERE idFuncionario = _idFuncionario ;
	END IF;
END; //

DELIMITER ;

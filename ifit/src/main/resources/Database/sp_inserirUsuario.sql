DELIMITER $$

CREATE PROCEDURE `sp_inserirUsuario`(IN _perfil ENUM('ALUNO', 'INSTRUTOR', 'RECEPCAO', 'GERENTE'), _nome VARCHAR(255), _cpf CHAR(11), _dtNascimento DATE, _sexo ENUM('M', 'F'), _email VARCHAR(255),
_senha VARCHAR(255), _logradouro VARCHAR(255), _numero CHAR(6), _complemento VARCHAR(100), _bairro VARCHAR(50), _cidade VARCHAR(80), _uf CHAR(2), _cep CHAR(8))

BEGIN

	INSERT INTO Usuario(perfil, nome, cpf, dtNascimento, sexo, email, senha) VALUES (_perfil, _nome, _cpf, _dtNascimento, _sexo, _email, _senha);
	
	CALL sp_inserirEndereco(@idUsuario, @_logradouro, @_numero, @_complemento, 
                                   @_bairro, @_cidade, @_uf, @_cep);
END $$
DELIMITER ;
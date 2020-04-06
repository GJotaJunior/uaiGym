CREATE TABLE Usuario(
	idUsuario INT AUTO_INCREMENT PRIMARY KEY,
	perfil ENUM('ALUNO', 'INSTRUTOR', 'RECEPCAO', 'GERENTE'),
	nome VARCHAR(255) NOT NULL,
	cpf CHAR(11),
	dtNascimento DATE,
	sexo ENUM('M', 'F'),
	email VARCHAR(255),
	senha VARCHAR(255));
	
CREATE TABLE Telefone(
	idTelefone INT AUTO_INCREMENT PRIMARY KEY,
	idUsuario INT,
	telefone VARCHAR(15),
 	FOREIGN KEY(idUsuario) REFERENCES Usuario (idUsuario));
	
CREATE TABLE Endereco(
	idEndereco INT AUTO_INCREMENT PRIMARY KEY,
	idUsuario INT,
	logradouro VARCHAR(255),
	numero CHAR(6),
	complemento VARCHAR(100),
	bairro VARCHAR(50),
	cidade VARCHAR(80),
	uf CHAR(2),
	cep CHAR(8),
	FOREIGN KEY(idUsuario) REFERENCES Usuario (idUsuario));
	
CREATE TABLE Aluno(
	idAluno INT AUTO_INCREMENT PRIMARY KEY,
	idUsuario INT,
	matricula VARCHAR(255),
	status ENUM('ATIVO', 'INATIVO'),
	FOREIGN KEY(idUsuario) REFERENCES Usuario (idUsuario));
	
CREATE TABLE Funcionario(
	idFuncionario INT AUTO_INCREMENT PRIMARY KEY,
	idUsuario INT,
	contrato VARCHAR(255),
	dtAdmissao DATE,
	dtDemissao DATE,
	FOREIGN KEY(idUsuario) REFERENCES Usuario (idUsuario));
	
CREATE TABLE Contato(
	idContato INT AUTO_INCREMENT PRIMARY KEY,
	idAluno INT,
	nome VARCHAR(255) NOT NULL,
	telefone VARCHAR(20),
	parentesco VARCHAR(15),
	FOREIGN KEY(idAluno) REFERENCES Aluno (idAluno));
	
CREATE TABLE Equipamento(
	idEquipamento INT AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(255) NOT NULL);
	
CREATE TABLE Exercicio(
 	idExercicio INT AUTO_INCREMENT PRIMARY KEY,
	idEquipamento INT,
	nome VARCHAR(255) NOT NULL,
	FOREIGN KEY(idEquipamento) REFERENCES Equipamento (idEquipamento));
	
CREATE TABLE Treino(
	idTreino INT AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(255));

CREATE TABLE ExerciciosTreino(
	idExercicioTreino INT AUTO_INCREMENT PRIMARY KEY,
	idTreino INT,
	idExercicio INT,
	qtRepeticao INT,
	qtSerie INT,
	FOREIGN KEY(idTreino) REFERENCES Treino (idTreino),
 	FOREIGN KEY(idExercicio) REFERENCES Exercicio (idExercicio));
	
CREATE TABLE AlunoTreino(
	idAlunoTreino INT AUTO_INCREMENT PRIMARY KEY,
	idAluno INT,
	idTreino INT,
	idFuncionario INT,
	dtTreino DATE,
	FOREIGN KEY(idAluno) REFERENCES Aluno (idAluno),
	FOREIGN KEY(idTreino) REFERENCES Treino (idTreino),
	FOREIGN KEY(idFuncionario) REFERENCES Funcionario (idFuncionario));
	
CREATE TABLE Avaliacao(
	idAvaliacao INT AUTO_INCREMENT PRIMARY KEY,
	idAluno INT,
	idFuncionario INT,
	dtAvaliacao DATE,
	altura FLOAT,
	peso FLOAT,
	percGordura FLOAT,
	percResiduos FLOAT,
	percMusculo FLOAT,
	FOREIGN KEY(idAluno) REFERENCES Aluno (idAluno),
	FOREIGN KEY(idFuncionario) REFERENCES Funcionario (idFuncionario));
USE siga;

DROP PROCEDURE IF EXISTS sp_build_quadro_notas;

DELIMITER $$

CREATE PROCEDURE sp_build_quadro_notas(disciplina_id INT)
BEGIN
	DECLARE done INT DEFAULT FALSE;
	DECLARE ra_aluno VARCHAR(20) DEFAULT '1';
	DECLARE nome_aluno VARCHAR(255);
    DECLARE nota DEC(3,1) DEFAULT 0.0;
	DECLARE num_nota INT DEFAULT 1;
	DECLARE coluna_nota VARCHAR(10);
    DECLARE representacao VARCHAR(5);
    DECLARE query_string VARCHAR(255);
    
	DECLARE cursor_alunos CURSOR FOR
		SELECT ra, nome
		FROM aluno, disciplina_aluno
		WHERE aluno.ra = disciplina_aluno.aluno_ra
			AND disciplina_aluno.disciplina_id = disciplina_id
		ORDER BY nome;
            
	DECLARE cursor_notas CURSOR FOR 
		SELECT nota
		FROM nota
		WHERE aluno_ra = ra_aluno AND disciplina_id = disciplina_id
		ORDER BY nota.avaliacao_id;
    
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    DROP TABLE IF EXISTS quadro_notas;
	CREATE TABLE quadro_notas (
		ra_aluno VARCHAR(20),
		nome_aluno VARCHAR(20)
	);
    
    OPEN cursor_alunos;
    
    get_aluno_loop: LOOP
		FETCH cursor_alunos INTO ra_aluno, nome_aluno;
        
		IF done = 1 THEN
			LEAVE get_aluno_loop;
        END IF;
        
        INSERT INTO quadro_notas
            (ra_aluno, nome_aluno)
        VALUES
            (ra_aluno, nome_aluno);
            
        OPEN cursor_notas;

        get_nota_loop : LOOP
			FETCH cursor_notas INTO nota;
            
            IF done = 1 THEN
				LEAVE get_nota_loop;
            END IF;
            
            SET coluna_nota = CONCAT('nota_', CONVERT(num_nota, CHAR));
            
            IF COL_LENGTH('dbo.quadro_notas', coluna_nota) IS NULL THEN
				SET query_string = CONCAT('ALTER TABLE quadro_notas ADD ', coluna_nota, ' DECIMAL(3,1)');
				PREPARE stmt FROM @query_string;
                EXECUTE stmt;
            END IF;

            SET representacao = CONVERT(nota, CHAR);

			SET query_string = CONCAT('UPDATE quadro_notas SET ', coluna_nota , ' = ''',  representacao, ''' WHERE ra_aluno = ', ra_aluno);
            PREPARE stmt FROM @query_string;
            EXECUTE stmt;
            
            SET num_nota = num_nota + 1;
        END LOOP get_nota_loop;

        CLOSE cursor_notas;
    END LOOP get_aluno_loop;

    CLOSE cursor_alunos;
END$$

DELIMITER ;

-- teste
CALL sp_build_quadro_notas(1);

SELECT 
    *
FROM
    quadro_notas;

USE siga;

DELIMITER $$

/* 
    Converte total de faltas (INT) em para sua representação (VARCHAR) 
    Exemplo: 4 para FFFF
    Exemplo: 2 para FFPP
*/
CREATE FUNCTION convert_total_faltas(
    total_faltas INT,
    total_aulas INT -- 4 AS DEFAULT
)
RETURNS VARCHAR(4) 
BEGIN
    DECLARE count INT DEFAULT 1;
    DECLARE result VARCHAR(4) DEFAULT CASE total_aulas WHEN 4 THEN 'PPPP' ELSE 'PP' END;

    WHILE count <= total_faltas DO
        SET result = INSERT(result, count, 1, 'F');
        SET count = count + 1;
    END WHILE;

    RETURN result;
END$$

/* 
	Retorna o total horas aula de uma Disciplina (4 ou 2)
*/
CREATE FUNCTION get_horas_aula(disciplina_id INT)
RETURNS INT
BEGIN
    RETURN (SELECT total_aulas
    FROM disciplina
    WHERE disciplina.id = disciplina_id) / 20;
END$$

DELIMITER ;

-- Monta a tabela quadro_faltas  
DROP PROCEDURE IF EXISTS sp_build_quadro_faltas;

DELIMITER $$

CREATE PROCEDURE sp_build_quadro_faltas(disciplina_id INT)
BEGIN
	DECLARE total_aulas INT DEFAULT dbo.get_horas_aula(disciplina_id);
    DECLARE ra_aluno VARCHAR(20);
    DECLARE nome_aluno VARCHAR(255);
    
    DECLARE data VARCHAR(15);
	DECLARE total_faltas_aula, total_faltas_semestre INT DEFAULT 0;
    DECLARE data_aula VARCHAR(10);
    
    DECLARE query_string VARCHAR(255);
    DECLARE dia, mes CHAR;

	DECLARE cursor_faltas CURSOR FOR 
	SELECT data, total_faltas
	FROM falta
	WHERE aluno_ra = ra_aluno AND disciplina_id = disciplina_id
	ORDER BY data;
    
    DECLARE cursor_alunos CURSOR FOR
    SELECT ra, nome
    FROM aluno, disciplina_aluno
    WHERE aluno.ra = disciplina_aluno.aluno_ra
        AND disciplina_aluno.disciplina_id = disciplina_id
    ORDER BY nome;

	DROP TABLE IF EXISTS quadro_faltas;
    CREATE TABLE quadro_faltas (
        ra_aluno VARCHAR(20),
        nome_aluno VARCHAR(20)
    );
    
    OPEN cursor_alunos;
    
    get_aluno_loop: LOOP
		FETCH  cursor_alunos INTO ra_aluno, nome_aluno;
        
		IF done = 1 THEN
			LEAVE get_aluno_loop;
        END IF;
        
        INSERT INTO quadro_faltas
            (ra_aluno, nome_aluno)
        VALUES
            (ra_aluno, nome_aluno);
            
        OPEN cursor_faltas;

        get_falta_loop: LOOP
			FETCH cursor_faltas INTO data, total_faltas_aula;
            
            IF done = 1 THEN
				LEAVE get_falta_loop;
			END IF;
           
            SET data_aula = CONCAT('d_', CONVERT(DAY(data), CHAR), '_',  CONVERT(MONTH(data), CHAR);

            IF COL_LENGTH('dbo.quadro_faltas', data_aula) IS NULL THEN
				SET query_string = CONCAT('ALTER TABLE quadro_faltas ADD ' + data_aula + ' CHAR(4) NULL');
				PREPARE stmt FROM query_string;
                EXECUTE stmt;
            END IF;

            DECLARE representacao VARCHAR(20) = dbo.convert_total_faltas(total_faltas_aula, total_aulas);

            EXEC ('UPDATE quadro_faltas SET ' + data_aula +  ' = ''' + representacao +  ''' WHERE ra_aluno = ' + ra_aluno);

            SET total_faltas_semestre = total_faltas_semestre + total_faltas_aula;

            
        END LOOP get_falta_loop;

        CLOSE cursor_faltas;

        IF COL_LENGTH('dbo.quadro_faltas', 'total_faltas') IS NULL
        BEGIN
            EXEC ('ALTER TABLE quadro_faltas ADD total_faltas INT NULL');
        END

        DECLARE aux VARCHAR(MAX) =  CONVERT(VARCHAR(MAX), total_faltas_semestre);
        EXEC ('UPDATE quadro_faltas SET total_faltas = ' + aux + ' WHERE ra_aluno = ' + ra_aluno);

        FETCH NEXT FROM cursor_alunos INTO ra_aluno, nome_aluno
    END;

    CLOSE cursor_alunos;
    DEALLOCATE cursor_alunos;
END$$
   
DELIMITER ;

-- teste
CALL sp_build_quadro_faltas(4); 
SELECT * FROM quadro_faltas; 

SELECT DAY('12-02-2000');



    
BEGIN TRANSACTION;

SET SCHEMA 'public';

-- GESTORES
INSERT INTO usuario (cpf, nome,email, cargo, senha, status) VALUES
('07296531533', 'Eduardo Fillipe da Silva Reis', 'eduardo@prevsep.com', 1, '$2y$10$HI7.rV14eRMyuuvAGVjvF.leE0hxnagyWLgjcxdo68Z4RHHXGMHye', 1),
('47961767053', 'Lucas Lopes Souza', 'lucas@prevsep.com', 1, '$2y$10$HI7.rV14eRMyuuvAGVjvF.leE0hxnagyWLgjcxdo68Z4RHHXGMHye', 1),
('15482817008', 'Jadson Carvalho', 'jadson@prevsep.com', 1, '$2y$10$HI7.rV14eRMyuuvAGVjvF.leE0hxnagyWLgjcxdo68Z4RHHXGMHye', 1),
('54471920057', 'Thiago Nascimento', 'thiago@prevsep.com', 1, '$2y$10$HI7.rV14eRMyuuvAGVjvF.leE0hxnagyWLgjcxdo68Z4RHHXGMHye', 1),
('07337259026', 'Adicnéia Oliveira', 'adicineia@dcomp.ufs.br', 1, '$2y$10$HI7.rV14eRMyuuvAGVjvF.leE0hxnagyWLgjcxdo68Z4RHHXGMHye', 1);

INSERT INTO gestor (cpf) VALUES
('07296531533'),
('47961767053'),
('15482817008'),
('54471920057'),
('07337259026');


-- MEDICO
INSERT INTO usuario (cpf, nome, email, cargo, senha, status) VALUES
('11019169079', 'João Marcos Oliveira', 'joaomarcos@prevsep.com', 2, '$2y$10$HI7.rV14eRMyuuvAGVjvF.leE0hxnagyWLgjcxdo68Z4RHHXGMHye', 1),
('45923443082', 'Helena Manuela Prata Reis', 'helenaprata@prevsep.com', 2, '$2y$10$HI7.rV14eRMyuuvAGVjvF.leE0hxnagyWLgjcxdo68Z4RHHXGMHye', 1);

INSERT INTO medico (cpf, crm) VALUES
('11019169079',54321),
('45923443082', 54322);


-- ENFERMEIRO
INSERT INTO usuario (cpf, nome, email, cargo, senha, status) VALUES
('09951729002', 'Joana Pereira Silva', 'joanapereira@prevsep.com', 3, '$2y$10$HI7.rV14eRMyuuvAGVjvF.leE0hxnagyWLgjcxdo68Z4RHHXGMHye', 1),
('22475524049', 'Maria Carla Santos', 'mariacarla@prevsep.com', 3, '$2y$10$HI7.rV14eRMyuuvAGVjvF.leE0hxnagyWLgjcxdo68Z4RHHXGMHye', 1);

INSERT INTO enfermeiro (cpf, cre) VALUES
('09951729002', 12345),
('22475524049', 12346);

COMMIT;
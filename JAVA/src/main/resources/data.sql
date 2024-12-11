-- Insérer les corporations
INSERT INTO Corporation (id, name) VALUES
(1, 'Corporation_1'),
(2, 'Corporation_2'),
(3, 'Corporation_3');

-- Insérer les conventions
INSERT INTO Convention (id, name, subvention, max_salary, corporation_id) VALUES
(1, 'Convention_1', 10000.0, 10, 1),
(2, 'Convention_2', 5000.0, 5, 1),
(3, 'Convention_3', 15000.0, 20, 2),
(4, 'Convention_4', 2000.0, 2, 3);

-- Insérer les utilisateurs sans le champ 'role'
INSERT INTO User (email, password, corporation_id) VALUES
('admin@e.com', 'root', NULL),
('a@e.com', 'root', 1),
('b@e.com', 'root', 2),
('c@e.com', 'root', 3);

INSERT INTO Salary (matricule, bar_code, user_id, convention_id) VALUES
('SAL01', 'toto', 1, 1),
('SAL02', 'titi', 2, 2),
('SAL03', 'tata', 3, 3),
('SAL04', 'tutu', 4, 4);
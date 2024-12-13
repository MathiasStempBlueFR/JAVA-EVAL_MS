-- Insérer les droits
INSERT INTO droit (name) VALUES
('admin'),
('corpo');

-- Insérer les corporations
INSERT INTO Corporation (name) VALUES
('Corporation_1'),
('Corporation_2'),
('Corporation_3');

-- Insérer les conventions
INSERT INTO Convention (name, subvention, max_salary, corporation_id) VALUES
('Convention_1', 10000.0, 10, 1),
('Convention_2', 5000.0, 5, 1),
('Convention_3', 15000.0, 20, 2),
('Convention_4', 2000.0, 2, 3);

-- Insérer les utilisateurs sans le champ 'role'
INSERT INTO User (email, password, corporation_id, droit_id) VALUES
('admin@e.com', 'root', NULL, 1),
('a@e.com', 'root', 1, 1),
('b@e.com', 'root', 2, 2),
('c@e.com', 'root', 3, 2);

-- Insérer les salaires
INSERT INTO Salary (matricule, bar_code, user_id, convention_id) VALUES
('SAL01', 'toto', 1, 1),
('SAL02', 'titi', 2, 2),
('SAL03', 'tata', 3, 3),
('SAL04', 'tutu', 4, 4);


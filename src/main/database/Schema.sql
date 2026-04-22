
-- 1. Table des collectivités
CREATE TABLE IF NOT EXISTS collectivities (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    specialty VARCHAR(255) NOT NULL,
    creation_date DATE NOT NULL,
    authorized BOOLEAN DEFAULT FALSE
    );

-- 2. Table des membres
CREATE TABLE IF NOT EXISTS members (
    id INT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    gender VARCHAR(10) NOT NULL,
    address VARCHAR(255),
    job VARCHAR(255),
    phone VARCHAR(20),
    email VARCHAR(255) UNIQUE,
    membership_date DATE NOT NULL,
    collectivity_id BIGINT,
    fees_paid BOOLEAN DEFAULT FALSE,
    annual_contributions_paid BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (collectivity_id) REFERENCES collectivities(id) ON DELETE SET NULL
    );

-- 3. Table de liaison (membres fondateurs d'une collectivité)
CREATE TABLE IF NOT EXISTS collectivity_founding_members (
    collectivity_id BIGINT NOT NULL,
    member_id INT NOT NULL,
    PRIMARY KEY (collectivity_id, member_id),
    FOREIGN KEY (collectivity_id) REFERENCES collectivities(id) ON DELETE CASCADE,
    FOREIGN KEY (member_id) REFERENCES members(id) ON DELETE CASCADE
    );

-- 4. Table des parrains (sponsors)
CREATE TABLE IF NOT EXISTS member_sponsors (
    member_id INT NOT NULL,
    sponsor_member_id BIGINT NOT NULL,
    relation VARCHAR(50),
    PRIMARY KEY (member_id, sponsor_member_id),
    FOREIGN KEY (member_id) REFERENCES members(id) ON DELETE CASCADE,
    FOREIGN KEY (sponsor_member_id) REFERENCES members(id) ON DELETE CASCADE
    );

-- 5. Table des cotisations
CREATE TABLE IF NOT EXISTS contributions (
    id INT PRIMARY KEY,
    collectivity_id BIGINT NOT NULL,
    amount DECIMAL(15,2) NOT NULL,
    periodicity VARCHAR(20) NOT NULL, -- MENSUELLE, ANNUELLE, PONCTUELLE
    start_date DATE NOT NULL,
    end_date DATE,
    FOREIGN KEY (collectivity_id) REFERENCES collectivities(id) ON DELETE CASCADE
    );

-- 6. Table des encaissements (paiements)
CREATE TABLE IF NOT EXISTS payments (
    id INT  PRIMARY KEY,
    member_id BIGINT NOT NULL,
    collectivity_id BIGINT NOT NULL,
    amount DECIMAL(15,2) NOT NULL,
    payment_date DATE NOT NULL,
    payment_mode VARCHAR(20) NOT NULL, -- ESPECE, VIREMENT_BANCAIRE, MOBILE_MONEY
    reference VARCHAR(255),
    FOREIGN KEY (member_id) REFERENCES members(id) ON DELETE CASCADE,
    FOREIGN KEY (collectivity_id) REFERENCES collectivities(id) ON DELETE CASCADE
    );

-- 7. Table des comptes (trésorerie)
CREATE TABLE IF NOT EXISTS accounts (
    id INT PRIMARY KEY,
    entity_type VARCHAR(20) NOT NULL, -- COLLECTIVITY, FEDERATION
    entity_id BIGINT NOT NULL,
    account_type VARCHAR(20) NOT NULL, -- CAISSE, BANCAIRE, MOBILE_MONEY
    holder_name VARCHAR(255) NOT NULL,
    -- Pour comptes bancaires
    bank_name VARCHAR(50),
    bank_code VARCHAR(5),
    branch_code VARCHAR(5),
    account_number VARCHAR(11),
    rib_key VARCHAR(2),
    -- Pour mobile money
    mobile_operator VARCHAR(20),
    phone_number VARCHAR(20),
    -- Solde actuel
    current_balance DECIMAL(15,2) DEFAULT 0,
    last_update DATE
    );

-- 8. Table des activités
CREATE TABLE IF NOT EXISTS activities (
    id INT PRIMARY KEY,
    entity_type VARCHAR(20) NOT NULL, -- COLLECTIVITY, FEDERATION
    entity_id BIGINT,
    title VARCHAR(255) NOT NULL,
    activity_date DATE NOT NULL,
    mandatory BOOLEAN DEFAULT FALSE,
    target_audience VARCHAR(50) DEFAULT 'TOUS', -- TOUS, JUNIORS, PRESIDENTS, etc.
    description TEXT
    );

-- 9. Table des présences (assiduité)
CREATE TABLE IF NOT EXISTS attendances (
    id INT PRIMARY KEY,
    activity_id BIGINT NOT NULL,
    member_id BIGINT NOT NULL,
    is_present BOOLEAN DEFAULT TRUE,
    excuse_reason TEXT,
    FOREIGN KEY (activity_id) REFERENCES activities(id) ON DELETE CASCADE,
    FOREIGN KEY (member_id) REFERENCES members(id) ON DELETE CASCADE
    );

-- 10. Table des rapports mensuels des collectivités (pour la fédération)
CREATE TABLE IF NOT EXISTS monthly_reports (
    id INT PRIMARY KEY,
    collectivity_id BIGINT NOT NULL,
    report_month DATE NOT NULL, -- premier jour du mois
    global_attendance_rate DECIMAL(5,2),
    active_members_count INT,
    FOREIGN KEY (collectivity_id) REFERENCES collectivities(id) ON DELETE CASCADE
    );

-- 11. Table des mandats (pour les postes spécifiques)
CREATE TABLE IF NOT EXISTS mandates (
    id INT PRIMARY KEY,
    member_id BIGINT NOT NULL,
    collectivity_id BIGINT,
    federation BOOLEAN DEFAULT FALSE,
    role VARCHAR(50) NOT NULL, -- PRESIDENT, ADJOINT, TRESORIER, SECRETAIRE
    start_year INT NOT NULL,
    end_year INT NOT NULL,
    FOREIGN KEY (member_id) REFERENCES members(id) ON DELETE CASCADE,
    FOREIGN KEY (collectivity_id) REFERENCES collectivities(id) ON DELETE CASCADE
    );
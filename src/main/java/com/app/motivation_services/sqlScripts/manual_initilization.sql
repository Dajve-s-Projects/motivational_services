-- Drop all tables
DROP TABLE IF EXISTS author, book, foodstat, note, emotion;

-- Add all tables
CREATE TABLE Author
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    authorName VARCHAR(255) NOT NULL
);

CREATE TABLE Book
(
    id               BIGINT PRIMARY KEY AUTO_INCREMENT,
    title            VARCHAR(255) NOT NULL,
    author_id        BIGINT       NOT NULL,
    description      VARCHAR(255) NOT NULL,
    totalChapters    INT          NOT NULL,
    currentChapter   INT          NOT NULL,
    totalPages       INT          NOT NULL,
    currentPage      INT          NOT NULL,
    rating DOUBLE NOT NULL,
    isAlreadyRead    BOOLEAN      NOT NULL,
    startReadingDate DATETIME     NOT NULL,
    endReadingDate   DATETIME     NOT NULL,
    bookOpinion      VARCHAR(255) NOT NULL,
    specialNotes     VARCHAR(255) NOT NULL,
    FOREIGN KEY (author_id) REFERENCES Author (id)
);

CREATE TABLE Emotion
(
    id      BIGINT PRIMARY KEY AUTO_INCREMENT,
    emotion VARCHAR(255) NOT NULL
);

CREATE TABLE FoodStat
(
    id            BIGINT PRIMARY KEY AUTO_INCREMENT,
    dailyStatus   VARCHAR(255) NOT NULL,
    dateSubmitted DATETIME     NOT NULL
);

CREATE TABLE Note
(
    id                   BIGINT PRIMARY KEY AUTO_INCREMENT,
    title                VARCHAR(255) NOT NULL,
    actualNote           VARCHAR(255) NOT NULL,
    emotion_id           BIGINT       NOT NULL,
    dateOfNoteSubmission DATETIME     NOT NULL,
    FOREIGN KEY (emotion_id) REFERENCES Emotion (id)
);

CREATE TABLE "LEBEN" (
    "id" INTEGER PRIMARY KEY,
    "name" TEXT NOT NULL UNIQUE,
    "kcal" NUMERIC,
    "image" TEXT,
    "price" INTEGER,
    "description" VARCHAR(100)
);
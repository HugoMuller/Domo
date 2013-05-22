CONNECT "jdbc:derby://localhost:1527/EcologU_DB;create=true;"

CREATE TABLE IF NOT EXISTS heating(
    "date" date not null primary key,
    "consommation" double
);

CREATE TABLE IF NOT EXISTS light(
    "date" date not null primary key,
    "consommation" double
);

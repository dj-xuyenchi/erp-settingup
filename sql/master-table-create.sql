CREATE TABLE cat_nguoidung (
    nguoidung_id        NUMBER(19) PRIMARY KEY,
    code                VARCHAR(20) UNIQUE,
    email               VARCHAR(50),
    hovatendem          VARCHAR2(20) NOT NULL,
    ten                 VARCHAR2(10) NOT NULL,
    sodienthoaicanhan   VARCHAR(15),
    sodienthoaiip       VARCHAR(15),
    diachichitiet       VARCHAR2(100)
);

CREATE TABLE cat_lichsumatkhau (
    lichsumatkhau_id   NUMBER(19) PRIMARY KEY,
    nguoidung_id       NUMBER(19) NOT NULL,
    passwordhash       VARCHAR(100) NOT NULL,
    ngaytao            DATE NOT NULL,
    dangsudung         NUMBER(1) NOT NULL
);

CREATE TABLE cat_eif (
    employeeinfofile_id number(19) PRIMARY KEY,
    nguoidung_id          NUMBER(19) NOT NULL,
    ngaytao               DATE NOT NULL,
    ngaycapnhat           DATE
);

CREATE TABLE cat_phongban (
    phongban_id       NUMBER(19) PRIMARY KEY,
    code              VARCHAR(10) UNIQUE NOT NULL,
    tenphongban       VARCHAR2(50) UNIQUE NOT NULL,
    soluongnhanvien   NUMBER(19) NOT NULL
);
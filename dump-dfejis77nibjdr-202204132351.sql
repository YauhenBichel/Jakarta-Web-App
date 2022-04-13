--
-- PostgreSQL database dump
--

-- Dumped from database version 13.6 (Ubuntu 13.6-1.pgdg20.04+1)
-- Dumped by pg_dump version 14.2

-- Started on 2022-04-13 23:51:52 BST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 6 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA public;


--
-- TOC entry 4052 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 202 (class 1259 OID 46034255)
-- Name: account; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.account (
    id uuid NOT NULL,
    password character varying NOT NULL,
    email character varying NOT NULL,
    created timestamp without time zone DEFAULT now() NOT NULL,
    modified timestamp without time zone DEFAULT now() NOT NULL,
    auth_roleid uuid NOT NULL,
    active boolean DEFAULT true NOT NULL
);


--
-- TOC entry 205 (class 1259 OID 46109061)
-- Name: authorization_role; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.authorization_role (
    id uuid NOT NULL,
    name character varying
);


--
-- TOC entry 201 (class 1259 OID 45990588)
-- Name: employee; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.employee (
    id uuid NOT NULL,
    created timestamp without time zone DEFAULT now() NOT NULL,
    modified timestamp without time zone DEFAULT now() NOT NULL,
    firstname character varying,
    lastname character varying,
    accountid uuid,
    department character varying,
    role character varying,
    years integer
);


--
-- TOC entry 4053 (class 0 OID 0)
-- Dependencies: 201
-- Name: COLUMN employee.created; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.employee.created IS 'Record created';


--
-- TOC entry 4054 (class 0 OID 0)
-- Dependencies: 201
-- Name: COLUMN employee.modified; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.employee.modified IS 'Record modified';


--
-- TOC entry 206 (class 1259 OID 46109067)
-- Name: holiday_details; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.holiday_details (
    id uuid NOT NULL,
    employeeid uuid,
    totaldays integer,
    takendays integer,
    created timestamp without time zone,
    modified timestamp without time zone
);


--
-- TOC entry 203 (class 1259 OID 46036925)
-- Name: holiday_request; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.holiday_request (
    id uuid,
    employeeid uuid,
    status character varying,
    created timestamp without time zone,
    modified timestamp without time zone,
    startdate timestamp without time zone,
    enddate timestamp without time zone
);


--
-- TOC entry 204 (class 1259 OID 46036936)
-- Name: request_alert_queue; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.request_alert_queue (
    id uuid,
    requestid uuid,
    created timestamp without time zone,
    modified timestamp without time zone,
    date timestamp without time zone
);


--
-- TOC entry 4042 (class 0 OID 46034255)
-- Dependencies: 202
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.account VALUES ('2e1293be-a549-4464-aa18-09a714a1ef1c', '$2a$08$pHbCW91EwTcXE1IB0Q0htO4JcUxnogC9iVs5HWE5s3U7agsK8wEqe', 'tom.litvin@belarus.com', '2022-04-02 23:26:52.743141', '2022-04-02 23:26:52.743419', '8872a6f4-c2fc-4e67-9ffc-34912950607a', true);
INSERT INTO public.account VALUES ('d6ad83d3-3f2b-49f1-ac2c-93ff7be4173a', '$2a$08$L2aplEOeRoKx6WMzDO.zVeH9sbl3WDWZc8pTsyWaI7WTErpgxwhIm', 'yauhen.bichel@belarus.com', '2022-04-09 11:18:21.127403', '2022-04-09 11:18:21.129565', '8872a6f4-c2fc-4e67-9ffc-34912950607a', true);
INSERT INTO public.account VALUES ('44dcdc47-d7d1-4988-8d22-e71aed8387c8', '$2a$08$QtSO5kc0Vsw1UrU1yQ5Wq.dwmKm9hreE9UtPQEtUwmpkmPaZ/Wy1.', 'dinodkeno@gmail.com', '2022-04-10 14:45:53.176727', '2022-04-10 14:45:53.176727', '8872a6f4-c2fc-4e67-9ffc-34912950607a', true);
INSERT INTO public.account VALUES ('cc032983-9dbe-4ebe-99a0-31e025e6bd5b', '$2a$08$2lKYm0kTJaEqvfx20ws50OzsCcQc8DlaNuq5S9LSrmVYO2eesMrla', 'yauhen.bichel@belarus.by', '2022-04-12 14:02:44.552506', '2022-04-12 14:02:44.555076', '8872a6f4-c2fc-4e67-9ffc-34912950607a', true);
INSERT INTO public.account VALUES ('fccf1640-fe40-4f68-9416-22ce6767007c', '$2a$08$MLEvQ73rZB.QCjWyG4zTz.YpK2Vp87NoArYE.2HbElmpRKYUum1BC', 'yauhen.bichel@belarus.litvin', '2022-04-12 14:06:32.211878', '2022-04-12 14:06:32.214293', '8872a6f4-c2fc-4e67-9ffc-34912950607a', true);
INSERT INTO public.account VALUES ('dcd4080c-3c08-4937-b884-4e093715528e', '$2a$08$mRy6z94oI2gsl950jk2JeOBNOWDRq5pwT5uxnDG8y1qAU5fBiXZ.6', 'eugene.bichel@belarus.litvin', '2022-04-12 16:56:38.120285', '2022-04-12 16:56:38.126065', '8872a6f4-c2fc-4e67-9ffc-34912950607a', true);
INSERT INTO public.account VALUES ('9c620156-20f4-4e29-95a9-93cb7b89a82b', '$2a$08$2lltmDKwX/g9so.ImOhcnuxeouduwkZw7fIvF9R4q2EgAq8iZS3cW', 'test.test@belarus.org', '2022-04-12 18:31:42.545872', '2022-04-12 18:31:42.546252', '8872a6f4-c2fc-4e67-9ffc-34912950607a', true);
INSERT INTO public.account VALUES ('0784b684-41ae-4994-8f49-b2163e60362e', '$2a$08$TMjOWFhDQ7U6DkJx8DuSP.juG65Ol0y/FD314RtxzSIFsUoVCj9o2', 'test1.test1@belarus.org', '2022-04-12 18:41:18.725307', '2022-04-12 18:41:18.725433', '8872a6f4-c2fc-4e67-9ffc-34912950607a', true);
INSERT INTO public.account VALUES ('2fdbb630-2660-4b26-9db5-3bcafd85df9b', '$2a$08$LPLaW6Sn3BvV.ShuCcUduOmC/F4XlyVFIaxmkW4ryVOyrzlEI28bm', 'test2.test2@belarus.org', '2022-04-12 22:18:07.517172', '2022-04-12 22:18:07.517295', '8872a6f4-c2fc-4e67-9ffc-34912950607a', true);
INSERT INTO public.account VALUES ('8e98b12d-6662-4160-b4c5-96434432114d', '$2a$08$il557Dy1l6Da665mgR0/lueY1jmpUgJX6HBTFW8P8WAOFZnjh4OsW', 'test3.test3@belarus.org', '2022-04-13 16:18:11.15054', '2022-04-13 16:18:11.154255', '8872a6f4-c2fc-4e67-9ffc-34912950607a', true);
INSERT INTO public.account VALUES ('ff2f0a20-f0bf-4c87-833e-ed6e1c222b53', '$2a$08$MTHSkKAve9r6T7PC9hQ2uOAA1Iogzx/wCEvz6Wc.nr1dJQ.SX0DHG', 'admin.admin@holidaysystem.org', '2022-04-13 16:32:45.220004', '2022-04-13 16:32:45.221722', '1e1a72a2-b498-4e31-9c2f-1933c864c355', true);


--
-- TOC entry 4045 (class 0 OID 46109061)
-- Dependencies: 205
-- Data for Name: authorization_role; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.authorization_role VALUES ('8872a6f4-c2fc-4e67-9ffc-34912950607a', 'USER');
INSERT INTO public.authorization_role VALUES ('1e1a72a2-b498-4e31-9c2f-1933c864c355', 'ADMIN');


--
-- TOC entry 4041 (class 0 OID 45990588)
-- Dependencies: 201
-- Data for Name: employee; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.employee VALUES ('e51b3404-810f-49ba-a725-2f0a1e7d1180', '2022-04-03 14:33:39.473066', '2022-04-03 14:33:39.473097', 'Tom', 'Litvin', '2e1293be-a549-4464-aa18-09a714a1ef1c', 'ENGINEERING', 'HEAD', 0);
INSERT INTO public.employee VALUES ('b5cc8e11-2306-4ae1-af3a-af757da82304', '2022-04-09 11:24:17.131683', '2022-04-09 11:24:17.131708', 'Yauhen', 'Bichel', '93328909-12f7-42e9-82fb-97a871f07f29', 'ENGINEERING', 'MANAGER', 0);
INSERT INTO public.employee VALUES ('0f2a1513-91eb-4a4b-9dbe-f3a2e3ee7c62', '2022-04-10 14:40:36.846814', '2022-04-10 14:40:36.848811', 'John', 'Mackay', 'd6ad83d3-3f2b-49f1-ac2c-93ff7be4173a', 'ENGINEERING', 'JUNIOR_MEMBER', NULL);
INSERT INTO public.employee VALUES ('7242375a-e791-40a1-b58e-719e227cb915', '2022-04-10 14:42:25.871463', '2022-04-10 14:42:25.871463', 'Noah', 'Wise', 'ec59ef74-80e9-4de4-8e81-aa3a8b43d806', 'ENGINEERING', 'APPRENTICE', NULL);
INSERT INTO public.employee VALUES ('2c494f67-6fd0-496e-a254-442282bd3fcb', '2022-04-10 14:53:43.507462', '2022-04-10 14:53:43.509438', 'Yasir', 'Pena', '44dcdc47-d7d1-4988-8d22-e71aed8387c8', 'ENGINEERING', 'APPRENTICE', NULL);
INSERT INTO public.employee VALUES ('d2d5be14-8c8c-4ff7-ac66-403d9cac7ffd', '2022-04-12 16:57:23.963477', '2022-04-12 16:57:23.963507', 'Yauhen', 'Bichel', 'dcd4080c-3c08-4937-b884-4e093715528e', 'PLUMBING', 'HEAD', 0);
INSERT INTO public.employee VALUES ('6505d298-6eab-48c7-b3e8-ff44515ae790', '2022-04-12 18:32:24.779624', '2022-04-12 18:32:24.779662', 'Eugene', 'Bichel', '9c620156-20f4-4e29-95a9-93cb7b89a82b', 'OFFICE', 'HEAD', 0);
INSERT INTO public.employee VALUES ('d800892b-4ac4-46ed-b60e-bd89b14fd445', '2022-04-12 18:33:50.732712', '2022-04-12 18:33:50.732729', 'Eugene', 'Bichel', '9c620156-20f4-4e29-95a9-93cb7b89a82b', 'OFFICE', 'HEAD', 0);
INSERT INTO public.employee VALUES ('25231212-bcd6-44d2-95c6-256562a81749', '2022-04-12 18:48:48.396453', '2022-04-12 18:48:48.396487', 'Eugene', 'Bichel', '0784b684-41ae-4994-8f49-b2163e60362e', 'BRICKLAYING', 'HEAD', 0);


--
-- TOC entry 4046 (class 0 OID 46109067)
-- Dependencies: 206
-- Data for Name: holiday_details; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.holiday_details VALUES ('f6ee35af-1457-4631-bcdc-54d95d5452fe', 'e51b3404-810f-49ba-a725-2f0a1e7d1180', 30, 0, '2022-04-03 14:33:39.473', '2022-04-03 14:33:39.473');
INSERT INTO public.holiday_details VALUES ('c21cc914-68de-4fe8-b957-304440433e94', 'b5cc8e11-2306-4ae1-af3a-af757da82304', 30, 0, '2022-04-03 14:33:39.473', '2022-04-03 14:33:39.473');
INSERT INTO public.holiday_details VALUES ('c130617c-9345-4491-88e1-e2312f2bc799', '0f2a1513-91eb-4a4b-9dbe-f3a2e3ee7c62', 30, 0, '2022-04-10 14:40:36.846', '2022-04-10 14:40:36.846');
INSERT INTO public.holiday_details VALUES ('7346ebc8-9d4f-489b-b636-c5508ddb91a1', '7242375a-e791-40a1-b58e-719e227cb915', 30, 0, '2022-04-10 14:42:25.871', '2022-04-10 14:42:25.871');
INSERT INTO public.holiday_details VALUES ('131fd42f-2ec4-4031-a6dc-94b816443273', '2c494f67-6fd0-496e-a254-442282bd3fcb', 30, 0, '2022-04-10 14:53:43.507', '2022-04-10 14:53:43.507');
INSERT INTO public.holiday_details VALUES ('e162d653-7087-49ab-ab8b-9ed7577cb07e', 'd2d5be14-8c8c-4ff7-ac66-403d9cac7ffd', 30, 0, '2022-04-10 14:53:43.507', '2022-04-10 14:53:43.507');
INSERT INTO public.holiday_details VALUES ('5f46ad34-347f-496f-b760-6450d60f3e3b', '6505d298-6eab-48c7-b3e8-ff44515ae790', 30, 0, '2022-04-12 18:32:24.779', '2022-04-12 18:32:24.779');
INSERT INTO public.holiday_details VALUES ('29e2422d-bd9e-4093-a915-704af9aaf16f', 'd800892b-4ac4-46ed-b60e-bd89b14fd445', 30, 0, '2022-04-12 18:33:50.732', '2022-04-12 18:33:50.732');
INSERT INTO public.holiday_details VALUES ('089a5db3-fb59-4d2a-9c8e-56f60db50b70', '25231212-bcd6-44d2-95c6-256562a81749', 30, 0, '2022-04-12 18:48:48.428279', '2022-04-12 18:48:48.42832');


--
-- TOC entry 4043 (class 0 OID 46036925)
-- Dependencies: 203
-- Data for Name: holiday_request; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.holiday_request VALUES ('1c0e7611-4869-45cc-b08a-140f12fdfa39', 'e51b3404-810f-49ba-a725-2f0a1e7d1180', 'PENDING', '2022-04-03 17:30:55.035561', '2022-04-03 17:30:55.035596', '2022-06-01 00:00:00', '2022-06-15 00:00:00');
INSERT INTO public.holiday_request VALUES ('fbaa23b4-442c-4b99-a127-2c5199414fff', 'e51b3404-810f-49ba-a725-2f0a1e7d1180', 'PENDING', '2022-04-06 14:18:50.963607', '2022-04-06 14:18:50.96932', '2022-07-01 00:00:00', '2022-07-15 00:00:00');
INSERT INTO public.holiday_request VALUES ('d1b38d4e-069e-49ee-801c-ca0f9cc8ca69', 'e51b3404-810f-49ba-a725-2f0a1e7d1180', 'PENDING', '2022-04-06 15:13:03.403069', '2022-04-06 15:13:03.404754', '2022-09-01 00:00:00', '2022-09-15 00:00:00');
INSERT INTO public.holiday_request VALUES ('4319129a-8f6f-433e-a465-2ea141f2643c', 'e51b3404-810f-49ba-a725-2f0a1e7d1180', 'PENDING', '2022-04-06 17:38:44.687517', '2022-04-06 17:38:44.687656', '2022-10-01 00:00:00', '2022-10-15 00:00:00');
INSERT INTO public.holiday_request VALUES ('b616c0e6-3e00-4edb-b081-d16cc6bfc6ae', 'e51b3404-810f-49ba-a725-2f0a1e7d1180', 'PENDING', '2022-04-06 17:46:47.641279', '2022-04-06 17:46:47.641355', '2022-10-01 00:00:00', '2022-10-15 00:00:00');
INSERT INTO public.holiday_request VALUES ('40d99d29-e769-4877-aadb-738d9fbb8785', 'e51b3404-810f-49ba-a725-2f0a1e7d1180', 'PENDING', '2022-04-06 17:52:46.936997', '2022-04-06 17:52:46.937224', '2022-10-01 00:00:00', '2022-10-15 00:00:00');
INSERT INTO public.holiday_request VALUES ('063a13b5-9df6-456a-a370-bf55cdf32034', 'e51b3404-810f-49ba-a725-2f0a1e7d1180', 'PENDING', '2022-04-06 17:53:29.087657', '2022-04-06 17:53:29.087693', '2022-10-01 00:00:00', '2022-10-15 00:00:00');
INSERT INTO public.holiday_request VALUES ('41edb5fd-0c81-4a1d-a73e-33d3fc27bcd2', 'e51b3404-810f-49ba-a725-2f0a1e7d1180', 'PENDING', '2022-04-06 17:56:19.885202', '2022-04-06 17:56:19.885461', '2022-10-01 00:00:00', '2022-10-15 00:00:00');
INSERT INTO public.holiday_request VALUES ('1a60e3e8-0f2c-46f4-96bb-3419709acd4f', 'e51b3404-810f-49ba-a725-2f0a1e7d1180', 'PENDING', '2022-04-06 17:56:46.978679', '2022-04-06 17:56:46.978705', '2022-10-01 00:00:00', '2022-10-15 00:00:00');
INSERT INTO public.holiday_request VALUES ('be7c7329-b2b8-419e-acde-555ae660acee', 'e51b3404-810f-49ba-a725-2f0a1e7d1180', 'PENDING', '2022-04-06 17:57:43.442026', '2022-04-06 17:57:43.442048', '2022-10-01 00:00:00', '2022-10-15 00:00:00');
INSERT INTO public.holiday_request VALUES ('0ec392f8-0eb6-4e41-b76d-6606a0b5e661', 'e51b3404-810f-49ba-a725-2f0a1e7d1180', 'PENDING', '2022-04-06 18:01:47.528095', '2022-04-06 18:01:47.528493', '2022-10-01 00:00:00', '2022-10-15 00:00:00');
INSERT INTO public.holiday_request VALUES ('0532729a-d8f7-4b4d-baaf-b68f4a124ee5', 'e51b3404-810f-49ba-a725-2f0a1e7d1180', 'PENDING', '2022-04-06 18:04:22.838779', '2022-04-06 18:04:22.838807', '2022-10-01 00:00:00', '2022-10-15 00:00:00');
INSERT INTO public.holiday_request VALUES ('5429fa73-87a8-4f41-8f80-9b4c264adedc', 'e51b3404-810f-49ba-a725-2f0a1e7d1180', 'PENDING', '2022-04-06 18:09:01.102912', '2022-04-06 18:09:01.103062', '2022-10-01 00:00:00', '2022-10-15 00:00:00');
INSERT INTO public.holiday_request VALUES ('674dbcb7-9fd1-48d4-b976-713375306db0', 'e51b3404-810f-49ba-a725-2f0a1e7d1180', 'BROKEN', '2022-04-03 19:53:09.973428', '2022-04-07 13:37:21.610148', '2022-06-01 00:00:00', '2022-06-15 00:00:00');
INSERT INTO public.holiday_request VALUES ('72f35873-d3d8-4a9e-b078-8bde7187c4e9', 'e51b3404-810f-49ba-a725-2f0a1e7d1180', 'PENDING', '2022-04-08 21:33:05.417529', '2022-04-08 21:33:05.419159', '2022-11-01 00:00:00', '2022-11-15 00:00:00');
INSERT INTO public.holiday_request VALUES ('3c237b0b-7332-4cc0-9894-0a6dc89e524d', 'e51b3404-810f-49ba-a725-2f0a1e7d1180', 'PENDING', '2022-04-08 21:37:00.268207', '2022-04-08 21:37:00.268279', '2022-11-01 00:00:00', '2022-11-15 00:00:00');
INSERT INTO public.holiday_request VALUES ('3a0d2fc5-bdae-44a0-abd9-374717024f28', 'e51b3404-810f-49ba-a725-2f0a1e7d1180', 'PENDING', '2022-04-08 21:37:51.871735', '2022-04-08 21:37:51.871756', '2022-11-01 00:00:00', '2022-11-15 00:00:00');
INSERT INTO public.holiday_request VALUES ('f0e52016-e62f-475b-b48a-172c4fa79fb2', 'e51b3404-810f-49ba-a725-2f0a1e7d1180', 'PENDING', '2022-04-08 21:39:26.515886', '2022-04-08 21:39:26.516939', '2022-11-01 00:00:00', '2022-11-15 00:00:00');
INSERT INTO public.holiday_request VALUES ('71c4791d-9c30-4dce-9642-a4914eaf8442', 'b5cc8e11-2306-4ae1-af3a-af757da82304', 'PENDING', '2022-04-12 14:16:45.716821', '2022-04-12 14:16:45.719819', '2022-06-10 00:00:00', '2022-06-20 00:00:00');


--
-- TOC entry 4044 (class 0 OID 46036936)
-- Dependencies: 204
-- Data for Name: request_alert_queue; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.request_alert_queue VALUES ('680af5ba-a814-44c8-a288-164e115ca564', 'f0e52016-e62f-475b-b48a-172c4fa79fb2', '2022-04-08 21:42:32.966325', '2022-04-08 21:42:32.966365', '2022-04-08 21:42:32.963629');
INSERT INTO public.request_alert_queue VALUES ('9493e0fa-b159-430d-b04c-362d2edb417f', '71c4791d-9c30-4dce-9642-a4914eaf8442', '2022-04-12 14:16:46.139415', '2022-04-12 14:16:46.139415', '2022-04-12 14:16:46.139415');


--
-- TOC entry 3904 (class 2606 OID 46034320)
-- Name: account account_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pk PRIMARY KEY (id);


--
-- TOC entry 3906 (class 2606 OID 46127621)
-- Name: authorization_role authorization_role_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.authorization_role
    ADD CONSTRAINT authorization_role_pk PRIMARY KEY (id);


--
-- TOC entry 3902 (class 2606 OID 46034322)
-- Name: employee employee_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_pk PRIMARY KEY (id);


--
-- TOC entry 3908 (class 2606 OID 46112249)
-- Name: holiday_details holiday_details_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.holiday_details
    ADD CONSTRAINT holiday_details_pk PRIMARY KEY (id);


--
-- TOC entry 3910 (class 2606 OID 46112251)
-- Name: holiday_details holiday_details_un; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.holiday_details
    ADD CONSTRAINT holiday_details_un UNIQUE (employeeid);


-- Completed on 2022-04-13 23:51:54 BST

--
-- PostgreSQL database dump complete
--


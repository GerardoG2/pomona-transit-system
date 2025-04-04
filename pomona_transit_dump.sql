--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2
-- Dumped by pg_dump version 17.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: actual_trip_stop_info; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.actual_trip_stop_info (
    trip_number integer NOT NULL,
    date date NOT NULL,
    scheduled_start_time time without time zone NOT NULL,
    stop_number integer NOT NULL,
    scheduled_arrival_time time without time zone,
    actual_start_time time without time zone,
    number_of_passengers_in integer,
    number_of_passengers_out integer
);


ALTER TABLE public.actual_trip_stop_info OWNER TO postgres;

--
-- Name: bus; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bus (
    bus_id character varying(30) NOT NULL,
    model character varying(30),
    year integer
);


ALTER TABLE public.bus OWNER TO postgres;

--
-- Name: driver; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.driver (
    driver_id character varying(30) NOT NULL,
    driver_name character varying(30),
    driver_phone_number character varying(30)
);


ALTER TABLE public.driver OWNER TO postgres;

--
-- Name: stop; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.stop (
    stop_number integer NOT NULL,
    stop_address text
);


ALTER TABLE public.stop OWNER TO postgres;

--
-- Name: trip; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.trip (
    trip_number integer NOT NULL,
    start_location_name text,
    destination_name text
);


ALTER TABLE public.trip OWNER TO postgres;

--
-- Name: trip_offering; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.trip_offering (
    trip_number integer NOT NULL,
    date date NOT NULL,
    scheduled_start_time time without time zone NOT NULL,
    scheduled_arrival_time time without time zone,
    driver_id character varying(30),
    bus_id character varying(30)
);


ALTER TABLE public.trip_offering OWNER TO postgres;

--
-- Name: trip_stop_info; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.trip_stop_info (
    trip_number integer NOT NULL,
    stop_number integer NOT NULL,
    sequence_number integer,
    driving_time interval hour to minute
);


ALTER TABLE public.trip_stop_info OWNER TO postgres;

--
-- Data for Name: actual_trip_stop_info; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.actual_trip_stop_info (trip_number, date, scheduled_start_time, stop_number, scheduled_arrival_time, actual_start_time, number_of_passengers_in, number_of_passengers_out) FROM stdin;
1	2025-04-02	08:00:00	105	08:10:00	08:02:34	22	4
\.


--
-- Data for Name: bus; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.bus (bus_id, model, year) FROM stdin;
BUS001	New Flyer XN40	2018
BUS002	Gillig Low Floor	2019
BUS003	Nova Bus LFS	2020
BUS004	Proterra Catalyst	2021
BUS005	BYD K9S	2022
BUS006	Blue Bird All American	2017
BUS007	Thomas Saf-T-Liner	2018
BUS008	IC Bus CE Series	2020
BUS009	Van Hool A330	2021
BUS010	Alexander Dennis Enviro500	2023
BUS011	New Flyer XDE60	2017
BUS012	Gillig BRT Plus	2018
BUS013	Proterra ZX5	2021
BUS014	BYD C10MS	2022
BUS015	Nova Bus Hybrid	2019
BUS016	IC Bus RE Series	2016
BUS017	Blue Bird Vision	2020
BUS019	Ford E-Transit	2023
BUS020	ElDorado Axess	2021
BUS999	SuperBus	2025
\.


--
-- Data for Name: driver; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.driver (driver_id, driver_name, driver_phone_number) FROM stdin;
ID001	Alan Turing	(909)111-2345
ID002	Ada Lovelace	(909)222-3456
ID003	Katherine Johnson	(909)333-4567
ID004	Albert Einstein	(909)444-5678
ID005	Grace Hopper	(909)555-6789
ID006	Isaac Newton	(909)666-7890
ID007	Sofia Kovalevskaya	(909)777-8901
ID008	Marie Curie	(909)888-9012
ID009	Stephen Hawking	(909)999-0123
ID010	Leonhard Euler	(909)000-1234
ID011	Lionel Messi	(909)123-4567
ID012	Cristiano Ronaldo	(909)234-5678
ID013	Neymar Jr.	(909)345-6789
ID014	Erling Haaland	(909)456-7890
ID015	Kylian Mbappé	(909)567-8901
ID016	Marie-Sophie Germain	(909)678-9012
ID017	Hypatia	(909)789-0123
ID018	James Clerk Maxwell	(909)890-1234
ID019	Carl Gauss	(909)901-2345
ID020	Galileo Galilei	(909)012-3456
ID999	John Cena	(619)808-9090
\.


--
-- Data for Name: stop; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.stop (stop_number, stop_address) FROM stdin;
101	1234 1st Street
102	2345 2nd Street
103	3456 3rd Street
104	4567 4th Street
105	5678 5th Street
106	6789 6th Street
107	7890 7th Street
108	8901 8th Street
109	9012 9th Street
110	1012 10th Street
111	1113 11th Street
112	1214 12th Street
113	1315 13th Street
114	1416 14th Street
115	1517 15th Street
116	1618 16th Street
117	1719 17th Street
118	1810 18th Street
119	1911 19th Street
120	2020 20th Street
\.


--
-- Data for Name: trip; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.trip (trip_number, start_location_name, destination_name) FROM stdin;
1	El Super	Sprouts Farmers Market
2	Eastland Center	Vista Market at the Suites
3	California State Polytechnic University - Pomona	South Hills High School
4	El Mercado del Pueblo	Smart & Final
5	Carnicería San Gabriel	Fletcher Park
6	Kindred Hospital San Gabriel Valley	Superior Grocers
7	Emanate Health Inter-Community Hospital	Julia McNeill Senior Center
8	Stater Bros. Markets	Target
9	Mt. San Antonio Community College	Tri-Community Adult Education - Griswold Center
10	Ramirez Meat Market Tortillería	El Mambi Market
11	Rio Hondo Bike Path	City Of Baldwin Park Department Of Recreation And Community Services
12	Opportunities for Learning Baldwin Park	City Of Baldwin Park Department Of Recreation And Community Services - Arts And Recreation Center
13	Carlton’s Market	Premiere Career College
14	Regional Occupational Program - El Monte Union High School District	El Monte/Rosemead Adult School
15	City Of Baldwin Park Department Of Recreation And Community Services - Arts And Recreation Center	Kindred Hospital San Gabriel Valley
16	Sprouts Farmers Market	Stater Bros. Markets
17	Superior Grocers	El Super
18	Smart & Final	Target
19	Target	Eastland Center
20	California State Polytechnic University - Pomona	Mt. San Antonio Community College
\.


--
-- Data for Name: trip_offering; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.trip_offering (trip_number, date, scheduled_start_time, scheduled_arrival_time, driver_id, bus_id) FROM stdin;
1	2025-04-03	08:00:00	08:20:00	ID001	BUS001
1	2025-04-04	08:00:00	08:20:00	ID001	BUS001
1	2025-04-05	08:00:00	08:20:00	ID001	BUS001
1	2025-04-06	08:00:00	08:20:00	ID001	BUS001
2	2025-04-01	09:00:00	09:20:00	ID002	BUS002
3	2025-04-01	10:00:00	10:25:00	ID003	BUS003
3	2025-04-02	10:00:00	10:25:00	ID003	BUS003
3	2025-04-03	10:00:00	10:25:00	ID003	BUS003
3	2025-04-04	10:00:00	10:25:00	ID003	BUS003
3	2025-04-05	10:00:00	10:25:00	ID003	BUS003
4	2025-04-01	11:00:00	11:25:00	ID004	BUS004
4	2025-04-02	11:00:00	11:25:00	ID004	BUS004
4	2025-04-03	11:00:00	11:25:00	ID004	BUS004
4	2025-04-04	11:00:00	11:25:00	ID004	BUS004
4	2025-04-05	11:00:00	11:25:00	ID004	BUS004
5	2025-04-06	12:00:00	12:25:00	ID005	BUS005
6	2025-04-06	13:00:00	13:30:00	ID006	BUS006
7	2025-04-06	14:00:00	14:25:00	ID007	BUS007
8	2025-04-07	08:00:00	08:20:00	ID008	BUS008
9	2025-04-07	09:00:00	09:30:00	ID009	BUS009
10	2025-04-07	10:00:00	10:30:00	ID010	BUS010
11	2025-04-08	08:30:00	08:50:00	ID011	BUS011
12	2025-04-08	09:30:00	09:55:00	ID012	BUS012
13	2025-04-08	10:30:00	11:00:00	ID013	BUS013
14	2025-04-08	11:30:00	12:00:00	ID014	BUS014
15	2025-04-08	12:30:00	13:00:00	ID015	BUS015
16	2025-04-09	08:00:00	08:30:00	ID016	BUS016
17	2025-04-09	09:00:00	09:25:00	ID017	BUS017
19	2025-04-09	11:00:00	11:20:00	ID019	BUS019
20	2025-04-10	08:00:00	08:25:00	ID020	BUS020
5	2025-04-10	09:00:00	09:20:00	ID001	BUS001
6	2025-04-10	10:00:00	10:30:00	ID002	BUS002
7	2025-04-11	08:00:00	08:25:00	ID003	BUS003
8	2025-04-11	09:00:00	09:30:00	ID004	BUS004
9	2025-04-12	08:30:00	09:00:00	ID005	BUS005
10	2025-04-12	09:30:00	10:00:00	ID006	BUS006
11	2025-04-13	10:00:00	10:30:00	ID007	BUS007
12	2025-04-13	11:00:00	11:30:00	ID008	BUS008
13	2025-04-14	08:00:00	08:30:00	ID009	BUS009
14	2025-04-14	09:00:00	09:30:00	ID010	BUS010
15	2025-04-14	10:00:00	10:25:00	ID011	BUS011
1	2025-04-01	08:00:00	08:20:00	ID001	BUS001
2	2025-04-02	09:00:00	09:20:00	ID015	BUS002
1	2025-04-02	08:00:00	08:20:00	ID001	BUS011
18	2025-04-09	10:00:00	10:30:00	ID018	BUS999
\.


--
-- Data for Name: trip_stop_info; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.trip_stop_info (trip_number, stop_number, sequence_number, driving_time) FROM stdin;
1	101	1	00:05:00
1	105	2	00:10:00
1	109	3	00:15:00
2	102	1	00:06:00
2	106	2	00:12:00
2	110	3	00:18:00
3	103	1	00:05:00
3	107	2	00:10:00
3	111	3	00:15:00
4	104	1	00:07:00
4	108	2	00:14:00
4	112	3	00:21:00
5	105	1	00:08:00
5	109	2	00:16:00
5	113	3	00:24:00
6	106	1	00:06:00
6	110	2	00:12:00
6	114	3	00:18:00
7	107	1	00:09:00
7	111	2	00:18:00
7	115	3	00:27:00
8	108	1	00:06:00
8	112	2	00:12:00
8	116	3	00:18:00
9	109	1	00:05:00
9	113	2	00:10:00
9	117	3	00:15:00
10	110	1	00:06:00
10	114	2	00:12:00
10	118	3	00:18:00
11	111	1	00:07:00
11	115	2	00:14:00
11	119	3	00:21:00
12	112	1	00:08:00
12	116	2	00:16:00
12	120	3	00:24:00
13	113	1	00:06:00
13	117	2	00:12:00
13	101	3	00:18:00
14	114	1	00:07:00
14	118	2	00:14:00
14	102	3	00:21:00
15	115	1	00:05:00
15	119	2	00:10:00
15	103	3	00:15:00
16	116	1	00:06:00
16	120	2	00:12:00
16	104	3	00:18:00
17	117	1	00:05:00
17	101	2	00:10:00
17	105	3	00:15:00
18	118	1	00:06:00
18	102	2	00:12:00
18	106	3	00:18:00
19	119	1	00:07:00
19	103	2	00:14:00
19	107	3	00:21:00
20	120	1	00:06:00
20	104	2	00:12:00
20	108	3	00:18:00
\.


--
-- Name: actual_trip_stop_info actual_trip_stop_info_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.actual_trip_stop_info
    ADD CONSTRAINT actual_trip_stop_info_pkey PRIMARY KEY (trip_number, date, scheduled_start_time, stop_number);


--
-- Name: bus bus_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bus
    ADD CONSTRAINT bus_pkey PRIMARY KEY (bus_id);


--
-- Name: driver driver_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.driver
    ADD CONSTRAINT driver_pkey PRIMARY KEY (driver_id);


--
-- Name: trip products_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.trip
    ADD CONSTRAINT products_pkey PRIMARY KEY (trip_number);


--
-- Name: stop stop_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.stop
    ADD CONSTRAINT stop_pkey PRIMARY KEY (stop_number);


--
-- Name: trip_offering trip_offering_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.trip_offering
    ADD CONSTRAINT trip_offering_pkey PRIMARY KEY (trip_number, date, scheduled_start_time);


--
-- Name: trip_stop_info trip_stop_info_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.trip_stop_info
    ADD CONSTRAINT trip_stop_info_pkey PRIMARY KEY (trip_number, stop_number);


--
-- Name: actual_trip_stop_info actual_trip_stop_info_stop_number_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.actual_trip_stop_info
    ADD CONSTRAINT actual_trip_stop_info_stop_number_fkey FOREIGN KEY (stop_number) REFERENCES public.stop(stop_number) ON UPDATE CASCADE;


--
-- Name: actual_trip_stop_info actual_trip_stop_info_trip_number_date_start_time_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.actual_trip_stop_info
    ADD CONSTRAINT actual_trip_stop_info_trip_number_date_start_time_fkey FOREIGN KEY (trip_number, date, scheduled_start_time) REFERENCES public.trip_offering(trip_number, date, scheduled_start_time) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: trip_offering trip_offering_bus_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.trip_offering
    ADD CONSTRAINT trip_offering_bus_id_fkey FOREIGN KEY (bus_id) REFERENCES public.bus(bus_id) ON UPDATE CASCADE;


--
-- Name: trip_offering trip_offering_driver_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.trip_offering
    ADD CONSTRAINT trip_offering_driver_id_fkey FOREIGN KEY (driver_id) REFERENCES public.driver(driver_id) ON UPDATE CASCADE;


--
-- Name: trip_offering trip_offering_trip_number_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.trip_offering
    ADD CONSTRAINT trip_offering_trip_number_fkey FOREIGN KEY (trip_number) REFERENCES public.trip(trip_number) ON UPDATE CASCADE;


--
-- Name: trip_stop_info trip_stop_info_stop_number_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.trip_stop_info
    ADD CONSTRAINT trip_stop_info_stop_number_fkey FOREIGN KEY (stop_number) REFERENCES public.stop(stop_number) ON UPDATE CASCADE;


--
-- Name: trip_stop_info trip_stop_info_trip_number_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.trip_stop_info
    ADD CONSTRAINT trip_stop_info_trip_number_fkey FOREIGN KEY (trip_number) REFERENCES public.trip(trip_number) ON UPDATE CASCADE;


--
-- PostgreSQL database dump complete
--


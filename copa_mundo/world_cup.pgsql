--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.14
-- Dumped by pg_dump version 9.5.14

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- Name: aprov; Type: TYPE; Schema: public; Owner: eduardo
--

CREATE TYPE public.aprov AS (
	nome character varying(30),
	partidas integer,
	pontuacao integer,
	aproveitamento integer
);


ALTER TYPE public.aprov OWNER TO eduardo;

--
-- Name: aproveitamento(); Type: FUNCTION; Schema: public; Owner: eduardo
--

CREATE FUNCTION public.aproveitamento() RETURNS SETOF public.aprov
    LANGUAGE plpgsql
    AS $$
DECLARE
	a aprov%ROWTYPE;
	historico RECORD;
	pnts int;
BEGIN
	FOR historico IN SELECT h.nome_selecao AS nome, h.partidas AS partidas, h.pontuacao AS pontuacao FROM historico h LOOP
		IF historico.pontuacao > 0 THEN
			a.nome = historico.nome;
			a.partidas = historico.partidas;
			a.pontuacao = historico.pontuacao;
			pnts = 3*historico.partidas;
			a.aproveitamento = historico.pontuacao/pnts;
		END IF;
	END LOOP;
	RETURN NEXT a;
	RETURN;
	END
	$$;


ALTER FUNCTION public.aproveitamento() OWNER TO eduardo;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: historico; Type: TABLE; Schema: public; Owner: eduardo
--

CREATE TABLE public.historico (
    nome_selecao character varying(30) NOT NULL,
    titulos integer DEFAULT 0,
    participacoes integer NOT NULL,
    total_cartoes integer NOT NULL,
    cartoes_amarelos integer NOT NULL,
    cartoes_vermelhos integer NOT NULL,
    partidas integer NOT NULL,
    pontuacao integer NOT NULL,
    vitorias integer NOT NULL,
    derrotas integer NOT NULL,
    empates integer NOT NULL
);


ALTER TABLE public.historico OWNER TO eduardo;

--
-- Name: selecao; Type: TABLE; Schema: public; Owner: eduardo
--

CREATE TABLE public.selecao (
    nome character varying(30) NOT NULL,
    bandeira character varying(50)
);


ALTER TABLE public.selecao OWNER TO eduardo;

--
-- Data for Name: historico; Type: TABLE DATA; Schema: public; Owner: eduardo
--

COPY public.historico (nome_selecao, titulos, participacoes, total_cartoes, cartoes_amarelos, cartoes_vermelhos, partidas, pontuacao, vitorias, derrotas, empates) FROM stdin;
Germany	4	19	117	110	5	106	218	66	20	20
Brazil	5	21	108	97	10	104	227	70	17	17
Italy	4	18	98	90	7	83	156	45	17	21
Netherlands	0	10	97	90	3	50	93	27	11	12
Mexico	0	16	74	68	4	53	56	14	25	14
Uruguay	2	13	69	60	8	51	72	20	19	12
Spain	1	15	65	64	1	59	99	29	18	12
Korea Republic	0	10	63	61	2	31	24	5	17	9
France	2	15	62	56	5	59	96	28	19	12
England	1	15	54	51	3	62	98	26	16	20
Cameroon	0	7	54	46	7	23	19	4	12	7
USA	0	10	52	48	2	33	30	8	19	6
Serbia	0	12	51	46	3	43	59	17	18	8
Portugal	0	7	46	40	3	26	43	13	9	4
Chile	0	9	46	43	2	33	40	11	15	7
Russia	0	11	41	38	2	40	59	17	15	8
Poland	0	8	41	40	0	31	50	15	11	5
Sweden	0	12	41	38	1	46	61	16	17	13
Paraguay	0	8	41	39	1	27	31	7	10	10
Belgium	0	13	39	36	2	41	51	14	18	9
Croatia	0	5	37	33	2	16	23	7	7	2
Bulgaria	0	7	36	33	0	26	17	3	15	8
Denmark	0	5	34	31	3	16	26	8	6	2
Ghana	0	3	34	33	0	12	15	4	5	3
Romania	0	7	33	32	1	21	29	8	8	5
Switzerland	0	11	32	31	1	33	39	11	16	6
Japan	0	6	31	31	0	17	16	4	9	4
Costa Rica	0	5	31	30	0	15	19	5	6	4
Tunisia	0	5	29	28	0	12	7	1	7	4
Czech Republic	0	9	29	23	5	33	41	12	16	5
Nigeria	0	6	28	27	1	18	18	5	10	3
Australia	0	5	28	24	3	13	9	2	8	3
Austria	0	7	24	23	1	29	40	12	13	4
Ecuador	0	3	24	23	1	10	13	4	5	1
South Africa	0	3	23	21	2	9	10	2	3	4
Saudi Arabia	0	5	23	22	1	13	8	2	9	2
Colombia	0	6	22	22	0	18	23	7	9	2
Côte d'Ivoire	0	3	20	19	0	9	10	3	5	1
Greece	0	3	20	19	0	10	8	2	6	2
Slovenia	0	2	20	19	1	6	4	1	4	1
Turkey	0	2	19	17	1	10	16	5	4	1
Morocco	0	5	19	19	0	13	10	2	7	4
IR Iran	0	5	18	18	0	12	6	1	8	3
Scotland	0	8	18	17	1	23	19	4	12	7
Republic of Ireland	0	3	16	16	0	13	14	2	3	8
Algeria	0	4	16	14	0	13	12	3	7	3
Honduras	0	3	15	13	1	9	3	0	6	3
Senegal	0	2	15	14	1	5	8	2	1	2
Norway	0	3	13	13	0	8	9	2	3	3
Ukraine	0	1	13	12	1	5	7	2	2	1
Slovakia	0	1	11	11	0	4	4	1	2	1
Hungary	0	9	11	6	5	32	48	15	14	3
Northern Ireland	0	3	10	9	1	13	14	3	5	5
Peru	0	5	10	9	1	15	15	4	8	3
German DR	0	1	10	10	0	6	8	2	2	2
Angola	0	1	10	9	0	3	2	0	1	2
Iraq	0	1	9	8	0	3	0	0	3	0
Togo	0	1	9	8	0	3	0	0	3	0
El Salvador	0	2	9	9	0	6	0	0	6	0
Trinidad and Tobago	0	1	9	8	0	3	1	0	2	1
Bolivia	0	3	8	6	1	6	1	0	5	1
China PR	0	1	6	5	1	3	0	0	3	0
United Arab Emirates	0	1	6	5	0	3	0	0	3	0
New Zealand	0	2	6	6	0	6	3	0	3	3
Jamaica	0	1	5	4	0	3	3	1	2	0
Egypt	0	3	4	4	0	4	2	0	2	2
Israel	0	1	4	4	0	3	2	0	1	2
Congo DR	0	1	3	2	1	3	0	0	3	0
Kuwait	0	1	3	3	0	3	1	0	2	1
Haiti	0	1	3	3	0	3	0	0	3	0
Canada	0	1	3	2	1	3	0	0	3	0
Bosnia and Herzegovina	0	1	3	3	0	3	4	1	2	0
Wales	0	1	0	0	0	5	6	1	1	3
Cuba	0	1	0	0	0	3	4	1	1	1
Indonesia	0	1	0	0	0	1	0	0	1	0
Iceland	0	1	0	0	0	0	0	0	0	0
Panama	0	1	0	0	0	0	0	0	0	0
Argentina	2	17	120	111	8	77	140	42	21	14
\.


--
-- Data for Name: selecao; Type: TABLE DATA; Schema: public; Owner: eduardo
--

COPY public.selecao (nome, bandeira) FROM stdin;
Chile	chile.jpg
CamarÃµes	bandeira.jpg
Germany	\N
Brazil	\N
Italy	\N
Netherlands	\N
Mexico	\N
Uruguay	\N
Spain	\N
Korea Republic	\N
France	\N
England	\N
Cameroon	\N
USA	\N
Serbia	\N
Portugal	\N
Russia	\N
Poland	\N
Sweden	\N
Paraguay	\N
Belgium	\N
Croatia	\N
Bulgaria	\N
Denmark	\N
Ghana	\N
Romania	\N
Switzerland	\N
Japan	\N
Costa Rica	\N
Tunisia	\N
Czech Republic	\N
Nigeria	\N
Australia	\N
Austria	\N
Ecuador	\N
South Africa	\N
Saudi Arabia	\N
Colombia	\N
Côte d'Ivoire	\N
Greece	\N
Slovenia	\N
Turkey	\N
Morocco	\N
IR Iran	\N
Scotland	\N
Republic of Ireland	\N
Algeria	\N
Honduras	\N
Senegal	\N
Norway	\N
Ukraine	\N
Slovakia	\N
Hungary	\N
Northern Ireland	\N
Peru	\N
German DR	\N
Angola	\N
Iraq	\N
Togo	\N
El Salvador	\N
Trinidad and Tobago	\N
Bolivia	\N
China PR	\N
United Arab Emirates	\N
New Zealand	\N
Jamaica	\N
Egypt	\N
Israel	\N
Congo DR	\N
Kuwait	\N
Haiti	\N
Canada	\N
Bosnia and Herzegovina	\N
Wales	\N
Cuba	\N
Indonesia	\N
Iceland	\N
Panama	\N
Argentina	asds
\.


--
-- Name: historico_pkey; Type: CONSTRAINT; Schema: public; Owner: eduardo
--

ALTER TABLE ONLY public.historico
    ADD CONSTRAINT historico_pkey PRIMARY KEY (nome_selecao);


--
-- Name: selecao_pkey; Type: CONSTRAINT; Schema: public; Owner: eduardo
--

ALTER TABLE ONLY public.selecao
    ADD CONSTRAINT selecao_pkey PRIMARY KEY (nome);


--
-- Name: historico_ibfk_1; Type: FK CONSTRAINT; Schema: public; Owner: eduardo
--

ALTER TABLE ONLY public.historico
    ADD CONSTRAINT historico_ibfk_1 FOREIGN KEY (nome_selecao) REFERENCES public.selecao(nome) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--


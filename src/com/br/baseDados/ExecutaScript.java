package com.br.baseDados;


public class ExecutaScript {
	public ExecutaScript() {

	}

	public String[] insertEstados() {
		// valores que o banco deve ter ao ser criado
		// pela primeira vez!
		return new String[] {
				"INSERT INTO [FV_ESTADO](CodEstado,Nome,UF) VALUES (1,'ACRE','AC');",
				"INSERT INTO [FV_ESTADO](CodEstado,Nome,UF) VALUES (2,'ALAGOAS','AL');",
				"INSERT INTO [FV_ESTADO](CodEstado,Nome,UF) VALUES (3,'AMAPA','AP');",
				"INSERT INTO [FV_ESTADO](CodEstado,Nome,UF) VALUES (4,'AMAZONAS','AM');",
				"INSERT INTO [FV_ESTADO](CodEstado,Nome,UF) VALUES (5,'BAHIA','BA');",
				"INSERT INTO [FV_ESTADO](CodEstado,Nome,UF) VALUES (6,'CEAR�','CE');",
				"INSERT INTO [FV_ESTADO](CodEstado,Nome,UF) VALUES (7,'DISTRITO FEDERAL','DF');",
				"INSERT INTO [FV_ESTADO](CodEstado,Nome,UF) VALUES (8,'ESPIRITO SANTO','ES');",
				"INSERT INTO [FV_ESTADO](CodEstado,Nome,UF) VALUES (9,'GOI�S','GO');",
				"INSERT INTO [FV_ESTADO](CodEstado,Nome,UF) VALUES (10,'MARANH�O','MA');",
				"INSERT INTO [FV_ESTADO](CodEstado,Nome,UF) VALUES (11,'MATO GROSSO','MT');",
				"INSERT INTO [FV_ESTADO](CodEstado,Nome,UF) VALUES (12,'MATO GROSSO DO SUL','MS');",
				"INSERT INTO [FV_ESTADO](CodEstado,Nome,UF) VALUES (13,'MINAS GERAIS','MG');",
				"INSERT INTO [FV_ESTADO](CodEstado,Nome,UF) VALUES (14,'PARAN�','PR');",
				"INSERT INTO [FV_ESTADO](CodEstado,Nome,UF) VALUES (15,'PARA�BA','PB');",
				"INSERT INTO [FV_ESTADO](CodEstado,Nome,UF) VALUES (16,'PAR�','PA');",
				"INSERT INTO [FV_ESTADO](CodEstado,Nome,UF) VALUES (17,'PERNAMBUCO','PE');",
				"INSERT INTO [FV_ESTADO](CodEstado,Nome,UF) VALUES (18,'PIAU�','PI');",
				"INSERT INTO [FV_ESTADO](CodEstado,Nome,UF) VALUES (19,'RIO DE JANEIRO','RJ');",
				"INSERT INTO [FV_ESTADO](CodEstado,Nome,UF) VALUES (20,'RIO GRANDE DO NORTE','RN');",
				"INSERT INTO [FV_ESTADO](CodEstado,Nome,UF) VALUES (21,'RIO GRANDE DO SUL','RS');",
				"INSERT INTO [FV_ESTADO](CodEstado,Nome,UF) VALUES (22,'ROND�NIA','RO');",
				"INSERT INTO [FV_ESTADO](CodEstado,Nome,UF) VALUES (23,'RORAIMA','RR');",
				"INSERT INTO [FV_ESTADO](CodEstado,Nome,UF) VALUES (24,'SANTA CATARINA','SC');",
				"INSERT INTO [FV_ESTADO](CodEstado,Nome,UF) VALUES (25,'SERGIPE','SE');",
				"INSERT INTO [FV_ESTADO](CodEstado,Nome,UF) VALUES (26,'S�O PAULO','SP');",
				"INSERT INTO [FV_ESTADO](CodEstado,Nome,UF) VALUES (27,'TOCANTINS','TO');" };
	}

	public String[] insertProdutos() {
		return new String[] {
				
				//                                cod     descri��o             peso pre�o  estoque
				" INSERT INTO FV_PRODUTO VALUES  (100102,'XAMPU BABOSA GL 4800',4.8,13.26,900);",
				" INSERT INTO FV_PRODUTO VALUES  (100103,'XAMPU CERAMIDAS GL 4800',4.8,13.26,900);",
				" INSERT INTO FV_PRODUTO VALUES  (100104,'XAMPU ERVA DOCE GL 4800',4.8,13.26,900);",
				" INSERT INTO FV_PRODUTO VALUES  (100108,'XAMPU LEITE DE CABRA GL 4800',4.8,13.26,900);",
				" INSERT INTO FV_PRODUTO VALUES  (100110,'XAMPU PESSEGO GL 4800',4.8,13.26,900);",
				" INSERT INTO FV_PRODUTO VALUES  (100111,'XAMPU KARITE GL 4800',4.8,13.26,900);",
				" INSERT INTO FV_PRODUTO VALUES  (100112,'XAMPU KERATINA GL 4800',4.8,13.26,900);",
				" INSERT INTO FV_PRODUTO VALUES  (100114,'XAMPU NEUTRO GL 4800',4.8,13.26,900);",
				" INSERT INTO FV_PRODUTO VALUES  (100117,'XAMPU ANTI RESIDUOS GL 4800',4.8,17.05,900);",
				" INSERT INTO FV_PRODUTO VALUES  (100118,'XAMPU ANTI AGE GL 4800',4.8,13.26,900);",
				" INSERT INTO FV_PRODUTO VALUES  (100119,'XAMPU COL�GENO GL 4800',4.8,13.26,900);",
				" INSERT INTO FV_PRODUTO VALUES  (100120,'XAMPU COLOR GL 4800',4.8,13.26,900);",
				" INSERT INTO FV_PRODUTO VALUES  (100121,'XAMPU REFRESCANTE GL 4800',4.8,13.26,900);",
				" INSERT INTO FV_PRODUTO VALUES  (100122,'XAMPU SEMI DI LINO GL 4800',4.8,13.26,900);",	
				" INSERT INTO FV_PRODUTO VALUES  (100204,'CONDICIONADOR CERAMIDAS 4800',4.8,13.26,900);",
				" INSERT INTO FV_PRODUTO VALUES  (100209,'CONDICIONADOR KARITE 4800',4.8,13.26,900);",		
				" INSERT INTO FV_PRODUTO VALUES  (100210,'CONDICIONADOR KERATINA 4800',4.8,13.26,900);",
				" INSERT INTO FV_PRODUTO VALUES  (100212,'CONDICIONADOR LEITE DE CABRA 4800',4.8,13.26,900);",
				" INSERT INTO FV_PRODUTO VALUES  (100214,'CONDICIONADOR NEUTRO 4800',4.8,13.26,900);",
				" INSERT INTO FV_PRODUTO VALUES  (100219,'CONDICIONADOR ANTI AGE 4800',4.8,13.26,900);",
				" INSERT INTO FV_PRODUTO VALUES  (100220,'CONDICIONADOR COL�GENO 4800',4.8,13.26,900);",
				" INSERT INTO FV_PRODUTO VALUES  (100221,'CONDICIONADOR COLOR 4800',4.8,13.26,900);",
				" INSERT INTO FV_PRODUTO VALUES  (100222,'CONDICIONADOR SEMI DI LINO 4800',4.8,13.26,900);",
				" INSERT INTO FV_PRODUTO VALUES  (100312,'SABONETE ERVA DOCE 4800',4.8,17.04,900);",
				" INSERT INTO FV_PRODUTO VALUES  (100314,'SABONETE FRUTAS VERMELHAS 4800',4.8,17.04,900);",
				" INSERT INTO FV_PRODUTO VALUES  (100315,'SABONETE ORQUIDEA 4800',4.8,17.04,900);",
				
				" INSERT INTO FV_PRODUTO VALUES  (110102,'XAMPU BABOSA 1900',1.9,5.63,900);",
				" INSERT INTO FV_PRODUTO VALUES  (110103,'XAMPU CERAMIDAS 1900',1.9,5.63,900);",					
				" INSERT INTO FV_PRODUTO VALUES  (110108,'XAMPU LEITE DE CABRA 1900',1.9,5.63,54);",
				" INSERT INTO FV_PRODUTO VALUES  (110110,'XAMPU PESSEGO 1900',1.9,5.63,54);",
				" INSERT INTO FV_PRODUTO VALUES  (110111,'XAMPU KARITE 1900',1.9,5.63,54);",
				" INSERT INTO FV_PRODUTO VALUES  (110112,'XAMPU KERATINA 1900',1.9,5.63,54);",
				" INSERT INTO FV_PRODUTO VALUES  (110114,'XAMPU NEUTRO 1900',1.9,5.63,54);",
				" INSERT INTO FV_PRODUTO VALUES  (110117,'XAMPU ANTI RESIDUOS 1900',1.9,6.68,54);",
				" INSERT INTO FV_PRODUTO VALUES  (110119,'XAMPU SEM SAL 1900',1.9,8.58,54);",
				" INSERT INTO FV_PRODUTO VALUES  (110121,'XAMPU ANTI AGE 1900',1.9,5.63,54);",
				" INSERT INTO FV_PRODUTO VALUES  (110122,'XAMPU COL�GENO 1900',1.9,5.63,54);",
				" INSERT INTO FV_PRODUTO VALUES  (110123,'XAMPU COLOR 1900',1.9,5.63,54);",
				" INSERT INTO FV_PRODUTO VALUES  (110124,'XAMPU REFRESCANTE 1900',1.9,5.63,54);",
				" INSERT INTO FV_PRODUTO VALUES  (110125,'XAMPU SEMI DI LINO 1900',1.9,5.63,54);",
				" INSERT INTO FV_PRODUTO VALUES  (110126,'XAMPU PREMIUM ABACATE E BABA�U 1900',1.9,8.44,54);",
				" INSERT INTO FV_PRODUTO VALUES  (110127,'XAMPU PREMIUM SILICONES 1900',1.9,8.44,54);",
				" INSERT INTO FV_PRODUTO VALUES  (110128,'XAMPU PREMIUM ARGAN 1900',1.9,8.44,54);", 
				" INSERT INTO FV_PRODUTO VALUES  (110129,'XAMPU PREMIUM CHIA E OLIVA 1900',1.9,8.44,54);", 
				" INSERT INTO FV_PRODUTO VALUES  (110204,'CONDICIONADOR CERAMIDAS 1900',1.9,5.63,54);",
				" INSERT INTO FV_PRODUTO VALUES  (110209,'CONDICIONADOR KARITE 1900',1.9,5.63,54);",		
				" INSERT INTO FV_PRODUTO VALUES  (110210,'CONDICIONADOR KERATINA 1900',1.9,5.63,54);",
				" INSERT INTO FV_PRODUTO VALUES  (110212,'CONDICIONADOR LEITE DE CABRA 1900',1.9,5.63,54);",				
				" INSERT INTO FV_PRODUTO VALUES  (110214,'CONDICIONADOR NEUTRO 1900',1.9,5.63,54);",								
				" INSERT INTO FV_PRODUTO VALUES  (110215,'CONDICIONADOR PESSEGO 1900',1.9,5.63,54);",								
				" INSERT INTO FV_PRODUTO VALUES  (110220,'CONDICIONADOR ANTI AGE 1900',1.9,5.63,25);",
				" INSERT INTO FV_PRODUTO VALUES  (110221,'CONDICIONADOR COL�GENO 1900',1.9,5.63,25);",	
				" INSERT INTO FV_PRODUTO VALUES (110222,'CONDICIONADOR COLORS 1900',1.9,5.63,32);",
				" INSERT INTO FV_PRODUTO VALUES (110223,'CONDICIONADOR SEMI DI LINO 1900',1.9,5.63,32);",
				" INSERT INTO FV_PRODUTO VALUES (110224,'CONDICIONADOR PREMIUM ABACATE E BAB 1900',1.9,8.44,32);",
				" INSERT INTO FV_PRODUTO VALUES (110225,'CONDICIONADOR PREMIUM SILICONES 1900',1.9,8.44,32);",
				" INSERT INTO FV_PRODUTO VALUES (110226,'CONDICIONADOR PREMIUM CHIA E OLIVA 1900',1.9,8.44,32);",
				" INSERT INTO FV_PRODUTO VALUES (110227,'CONDICIONADOR PREMIUM ARGAN 1900',1.9,8.44,32);", 
				
				
				
				" INSERT INTO FV_PRODUTO VALUES (110311,'SABONETE CALENDULA 1900',1.9,6.35,32);",
				" INSERT INTO FV_PRODUTO VALUES (110312,'SABONETE ERVA DOCE 1900',1.9,6.35,154);",
				" INSERT INTO FV_PRODUTO VALUES (110314,'SABONETE FRUTAS VERMELHAS 1900',1.9,6.35,154);",
				" INSERT INTO FV_PRODUTO VALUES (110315,'SABONETE NEUTRO 1900',1.9,6.35,154);",
				" INSERT INTO FV_PRODUTO VALUES (110316,'SABONETE PESSEGO 1900',1.9,6.35,154);",
				" INSERT INTO FV_PRODUTO VALUES (110317,'SABONETE PITANGA 1900',1.9,6.35,154);",
				" INSERT INTO FV_PRODUTO VALUES (110318,'SABONETE TANGERINA 1900',1.9,6.35,154);",
				" INSERT INTO FV_PRODUTO VALUES (110319,'SABONETE VANILLA 1900',1.9,6.35,154);",
				" INSERT INTO FV_PRODUTO VALUES (110320,'SABONETE ORQUIDEA 1900',1.9,6.35,154);",
				
				
				" INSERT INTO FV_PRODUTO VALUES (120103,'XAMPU BABOSA 1000',1,3.29,900);",
				" INSERT INTO FV_PRODUTO VALUES (120104,'XAMPU CERAMIDAS 1000',1,3.29,900);",
				" INSERT INTO FV_PRODUTO VALUES (120109,'XAMPU KARITE 1000',1,3.29,900);",
				" INSERT INTO FV_PRODUTO VALUES (120110,'XAMPU KERATINA 1000',1,3.29,900);",
				" INSERT INTO FV_PRODUTO VALUES (120112,'XAMPU LEITE DE CABRA 1000',1,3.29,900);",
				" INSERT INTO FV_PRODUTO VALUES (120114,'XAMPU NEUTRO 1000',1,3.29,900);",
				" INSERT INTO FV_PRODUTO VALUES (120115,'XAMPU PESSEGO 1000',1,3.29,900);",
				" INSERT INTO FV_PRODUTO VALUES (120117,'XAMPU SEM SAL 1000',1,4.79,900);",
				" INSERT INTO FV_PRODUTO VALUES (120120,'XAMPU ANTI AGE 1000',1,3.29,900);",
				" INSERT INTO FV_PRODUTO VALUES (120102,'XAMPU ANTI RESIDUOS 1000',1,3.93,900);",
				" INSERT INTO FV_PRODUTO VALUES (120124,'XAMPU SEMI DI LINO  1000',1,3.29,900);",
				" INSERT INTO FV_PRODUTO VALUES (120121,'XAMPU COL�GENO 1000',1,3.29,900);",
				" INSERT INTO FV_PRODUTO VALUES (120122,'XAMPU COLOR 1000',1,3.29,900);",
				" INSERT INTO FV_PRODUTO VALUES (120123,'XAMPU REFRESCANTE 1000',1,3.29,900);",
				" INSERT INTO FV_PRODUTO VALUES (120204,'CONDICIONADOR CERAMIDAS 1000',1,3.29,900);",
				" INSERT INTO FV_PRODUTO VALUES (120210,'CONDICIONADOR KERATINA 1000',1,3.29,900);",
				" INSERT INTO FV_PRODUTO VALUES (120212,'CONDICIONADOR LEITE DE CABRA 1000',1,3.29,900);",
				" INSERT INTO FV_PRODUTO VALUES (120214,'CONDICIONADOR NEUTRO 1000',1,3.29,900);",
				" INSERT INTO FV_PRODUTO VALUES (120220,'CONDICIONADOR ANTI AGE 1000',1,3.29,900);",
				" INSERT INTO FV_PRODUTO VALUES (120221,'CONDICIONADOR COL�GENO 1000',1,3.29,900);",
				" INSERT INTO FV_PRODUTO VALUES (120222,'CONDICIONADOR COLOR 1000',1,3.29,900);",
				" INSERT INTO FV_PRODUTO VALUES (120223,'CONDICIONADOR SEMI DI LINO 1000',1,3.29,900);",
				
				" INSERT INTO FV_PRODUTO VALUES (130102,'XAMPU ANTI RESIDUOS 1000 VALVULA',1,4.24,900);",
				" INSERT INTO FV_PRODUTO VALUES (130104,'XAMPU CERAMIDAS 1000 VALVULA',1,4.08,900);",
				" INSERT INTO FV_PRODUTO VALUES (130114,'XAMPU NEUTRO 1000 VALVULA',1,4.08,900);",
				" INSERT INTO FV_PRODUTO VALUES (130117,'XAMPU SEM SAL 1000 VALVULA',1,5.54,900);",
				" INSERT INTO FV_PRODUTO VALUES (130120,'XAMPU ANTI AGE 1000 VALVULA',1,4.08,900);",
				" INSERT INTO FV_PRODUTO VALUES (130121,'XAMPU COL�GENO 1000 VALVULA',1,4.08,900);",
				" INSERT INTO FV_PRODUTO VALUES (130123,'XAMPU REFRESCANTE 1000 VALVULA',1,4.08,900);",
				" INSERT INTO FV_PRODUTO VALUES (130124,'XAMPU SEMI DI LINO 1000 VALVULA',1,4.08,900);",
				" INSERT INTO FV_PRODUTO VALUES (130204,'CONDICIONADOR CERAMIDAS 1000 VALVULA',1,4.08,900);",
				" INSERT INTO FV_PRODUTO VALUES (130214,'CONDICIONADOR NEUTRO 1000 VALVULA',1,4.08,900);",
				" INSERT INTO FV_PRODUTO VALUES (130220,'CONDICIONADOR ANTI AGE 1000 VALVULA',1,4.08,900);",
				" INSERT INTO FV_PRODUTO VALUES (130221,'CONDICIONADOR COL�GENO 1000 VALVULA',1,4.08,900);",
				" INSERT INTO FV_PRODUTO VALUES (130223,'CONDICIONADOR SEMI DI LINO 1000 VALVULA',1,4.08,900);",
				" INSERT INTO FV_PRODUTO VALUES (140102,'XAMPU THERAPYA CEREAIS 500ML',0.5,5.96,900);",
				" INSERT INTO FV_PRODUTO VALUES (140103,'XAMPU THERAPYA KERATINA 500ML',0.5,5.96,900);",
				" INSERT INTO FV_PRODUTO VALUES (140105,'XAMPU THERAPYA SEMI DI LINO 500ML',0.5,5.96,900);",
				" INSERT INTO FV_PRODUTO VALUES (140106,'XAMPU THERAPYA SILICONES ESP 500ML',0.5,5.96,900);",
				" INSERT INTO FV_PRODUTO VALUES (140108,'XAMPU THERAPYA TURMALINA 500ML',0.5,5.96,900);",
				" INSERT INTO FV_PRODUTO VALUES (140109,'XAMPU THERAPYA ALOE VERA 500ML',0.5,5.96,900);",
				" INSERT INTO FV_PRODUTO VALUES (140131,'XAMPU THERAPYA POS PROGRESIVA 1000',1,11.31,900);",
				" INSERT INTO FV_PRODUTO VALUES (140134,'XAMPU THERAPYA CACHEADOS 1000',1,11.21,900);",
				" INSERT INTO FV_PRODUTO VALUES (140135,'XAMPU THERAPYA SEMI D LINO 1000',1,11.00,900);",
				" INSERT INTO FV_PRODUTO VALUES (140136,'XAMPU THERAPYA SILILCONE ESP 1000',1,11.00,900);",
				" INSERT INTO FV_PRODUTO VALUES (140202,'CONDICIONADOR THERAPYA CEREAIS 500ML',0.5,5.96,900);",
				" INSERT INTO FV_PRODUTO VALUES (140203,'CONDICIONADOR THERAPYA KERATINA 500ML',0.5,5.96,900);",
				" INSERT INTO FV_PRODUTO VALUES (140205,'CONDICIONADOR THERAPYA SEMI DE LINO 500',0.5,5.96,900);",
				" INSERT INTO FV_PRODUTO VALUES (140206,'CONDICIONADOR THERAPYA SILICONES ESP 500',0.5,5.96,900);",
				" INSERT INTO FV_PRODUTO VALUES (140208,'CONDICIONADOR THERAPYA TURMALINA 500',0.5,5.96,900);",
				" INSERT INTO FV_PRODUTO VALUES (140209,'CONDICIONADOR THERAPYA ALOE VERA 500',0.5,5.96,900);",
				" INSERT INTO FV_PRODUTO VALUES (140217,'CONDICIONADOR TH TEEN LISOS PERF. 250ML',0.25,4.72,900);",
				" INSERT INTO FV_PRODUTO VALUES (140231,'CONDICIONADOR THERAPYA POS PROGRESSI 750',0.75,11.31,900);",
				" INSERT INTO FV_PRODUTO VALUES (140234,'CONDICIONADOR THERAPYA CACHEADOS 750',0.75,11.21,900);",
				" INSERT INTO FV_PRODUTO VALUES (140235,'CONDICIONADOR THERAPYA SEMI D LINO 750',0.75,11.00,900);",
				" INSERT INTO FV_PRODUTO VALUES (140236,'CONDICIONADOR THERAPYA SILICONE ESP 750',0.75,11.00,900);",
				" INSERT INTO FV_PRODUTO VALUES (140412,'MASCARA THERAPYA CEREAIS 500',0.5,5.96,900);",
				" INSERT INTO FV_PRODUTO VALUES (140413,'MASCARA THERAPYA KERATINA 500',0.5,5.96,900);",
				
				" INSERT INTO FV_PRODUTO VALUES (140431,'MASCARA THERAPYA POS PROGRESSIVA 500',0.5,5.96,900);",
				" INSERT INTO FV_PRODUTO VALUES (140434,'MASCARA THERAPYA CACHOS 500',0.5,5.96,900);",
				
				
				" INSERT INTO FV_PRODUTO VALUES (140415,'MASCARA THERAPYA SEMI DI LINO 500',0.5,5.96,900);",
				" INSERT INTO FV_PRODUTO VALUES (140416,'MASCARA THERAPYA SILICONES ESP 500',0.5,5.96,900);",
				" INSERT INTO FV_PRODUTO VALUES (140417,'MASCARA THERAPYA ALOE E VERA 500',0.5,5.96,900);",
				" INSERT INTO FV_PRODUTO VALUES (140418,'MASCARA THERAPYA TURMALINA 500',0.5,5.96,900);",
				
				" INSERT INTO FV_PRODUTO VALUES (140503,'MASCARA PREMIUN THERAPYA KERATINA',1,8.16,900);",
				" INSERT INTO FV_PRODUTO VALUES (140505,'MASCARA PREMIUN THERAPYA SEMI DI LINO',1,8.16,900);",
				" INSERT INTO FV_PRODUTO VALUES (140506,'MASCARA PREMIUN THERAPYA SILICONES ESP',1,8.16,900);",
				" INSERT INTO FV_PRODUTO VALUES (140612,'HIDR. S.ENXAGUE THERAPYA CEREAIS',0.25,5.96,900);",
				" INSERT INTO FV_PRODUTO VALUES (140613,'HIDR. S.ENXAGUE THERAPYA KERATINA',0.25,5.96,900);",
			
				" INSERT INTO FV_PRODUTO VALUES (140615,'HIDR S.ENXAGUE THERAPYA SEMI D LINO',0.25,5.96,900);",
				" INSERT INTO FV_PRODUTO VALUES (140616,'HIDR S.ENXAGUE THERAPYA SILICONE ES',0.25,5.96,900);",
				" INSERT INTO FV_PRODUTO VALUES (140619,'HIDR S.ENXAGUE THERAPYA ALOE E VERA',0.25,5.96,900);",
				" INSERT INTO FV_PRODUTO VALUES (140620,'HIDR S.ENXAGUE THERAPYA TURMALINA',0.25,5.96,900);",
				" INSERT INTO FV_PRODUTO VALUES (140631,'ATIVADOR DE CACHOS TH POS PROGRESSIV 300',0.3,5.96,900);",
				
				" INSERT INTO FV_PRODUTO VALUES (140702,'KERATINA LIQUIDA 120ML',0.1,4.95,900);",
				
				" INSERT INTO FV_PRODUTO VALUES (140711,'POMADA FIN.  THERAPYA SILICONES ESP',0.12,4.87,900);",
				" INSERT INTO FV_PRODUTO VALUES (140830,'TRAT. CHOQUE THERAPYA ALOE E VERA',0.05,21.20,900);",
				" INSERT INTO FV_PRODUTO VALUES (140832,'TRAT. CHOQUE THERAPYA CEREAIS',0.05,21.20,900);",
				" INSERT INTO FV_PRODUTO VALUES (140833,'TRAT. CHOQUE THERAPYA KERATINA',0.05,21.20,900);",
				
				" INSERT INTO FV_PRODUTO VALUES (140835,'TRAT. CHOQUE THERAPYA SEMI DI LINO',0.05,21.20,900);",
				" INSERT INTO FV_PRODUTO VALUES (140836,'TRAT. CHOQUE THERAPYA SILICONES ESP',0.05,21.20,900);",
				" INSERT INTO FV_PRODUTO VALUES (140837,'TRAT. CHOQUE THERAPYA TURMALINA',0.05,21.20,900);",
				" INSERT INTO FV_PRODUTO VALUES (142304,'LO�AO SPA ALGODAO 500 ML',0.5,3.96,900);",
				" INSERT INTO FV_PRODUTO VALUES (142305,'LO�AO SPA ARGAN 500 ML',0.5,3.96,900);",
				" INSERT INTO FV_PRODUTO VALUES (142306,'LO�AO SPA FRUTAS VER 500ML',0.5,3.96,900);",
				" INSERT INTO FV_PRODUTO VALUES (142307,'LO�AO SPA ORQUIDEA 500 ML',0.5,3.96,900);",
				" INSERT INTO FV_PRODUTO VALUES (142308,'LO�AO SPA OLIVA 500 ML',0.5,3.96,900);",
				" INSERT INTO FV_PRODUTO VALUES (142309,'LO�AO SPA ROSAS 500 ML',0.5,3.96,900);",
				" INSERT INTO FV_PRODUTO VALUES (142310,'LO�AO SPA MORANGO 500 ML',0.5,3.96,900);",
				" INSERT INTO FV_PRODUTO VALUES (142311,'LO�AO SPA VANILA 500 ML',0.5,3.96,900);",
				" INSERT INTO FV_PRODUTO VALUES (142312,'LOCAO SPA PESSEGO 500ML',0.5,3.96,900);",
				" INSERT INTO FV_PRODUTO VALUES (142344,'LOCAO SPA ALGODAO 120ML',0.12,1.68,900);",
				" INSERT INTO FV_PRODUTO VALUES (142345,'LOCAO SPA ARGAN 120ML',0.12,1.68,900);",
				" INSERT INTO FV_PRODUTO VALUES (142346,'LOCAO SPA FRUTAS VER 120ML',0.12,1.68,900);",
				" INSERT INTO FV_PRODUTO VALUES (142347,'LOCAO SPA ORQUIDEA 120ML',0.12,1.68,900);",
				" INSERT INTO FV_PRODUTO VALUES (142348,'LOCAO SPA OLIVA 120ML',0.12,1.68,900);",
				" INSERT INTO FV_PRODUTO VALUES (142350,'LOCAO SPA MORANGO 120ML',0.12,1.68,900);",
				" INSERT INTO FV_PRODUTO VALUES (142351,'LOCAO SPA VANILLA 120ML',0.12,1.68,900);",
				
				
				
				
				
				" INSERT INTO FV_PRODUTO VALUES (151112,'GEL FIX 10 EXTRA 1KG',1,5.17,900);",
				" INSERT INTO FV_PRODUTO VALUES (151113,'GEL FIX 12 MEGA 1KG',1,5.17,900);",
				" INSERT INTO FV_PRODUTO VALUES (151115,'GEL KELCOLA MODELADOR 1KG',1,9.53,900);",
				" INSERT INTO FV_PRODUTO VALUES (151212,'GEL FIX 10 EXTRA 500',0.5,3.56,900);",
				" INSERT INTO FV_PRODUTO VALUES (151213,'GEL FIX 12 MEGA 500',0.5,3.56,900);",
				" INSERT INTO FV_PRODUTO VALUES (151312,'GEL FIX 10 EXTRA 250',0.25,2.11,900);",
				" INSERT INTO FV_PRODUTO VALUES (151313,'GEL FIX 12 MEGA 250',0.25,2.11,900);",
				" INSERT INTO FV_PRODUTO VALUES (151412,'GEL FIX 10 EXTRA 500 POTE',0.5,3.56,900);",
				" INSERT INTO FV_PRODUTO VALUES (151413,'GEL FIX 12 MEGA 500 POTE',0.5,3.56,900);",
				" INSERT INTO FV_PRODUTO VALUES (151415,'GEL KELCOLA MODELADOR 500',0.5,5.99,900);",
				" INSERT INTO FV_PRODUTO VALUES (151512,'GEL FIX 10 EXTRA 250 POTE',0.25,1.90,900);",
				" INSERT INTO FV_PRODUTO VALUES (151513,'GEL FIX 12 MEGA 250 POTE',0.25,1.90,900);",
				" INSERT INTO FV_PRODUTO VALUES (151515,'GEL KELCOLA MODELADOR 250',0.25,3.59,900);",
				" INSERT INTO FV_PRODUTO VALUES (151812,'GEL FIX 10 EXTRA 1000 VAL',1,5.87,900);",
				" INSERT INTO FV_PRODUTO VALUES (151813,'GEL FIX  12 MEGAL 1000 VAL',1,5.87,900);",
				" INSERT INTO FV_PRODUTO VALUES (161121,'CREME HIDRAKEL ABACATE',1,3.70,900);",
				" INSERT INTO FV_PRODUTO VALUES (161122,'CREME HIDRAKEL ANTI-FRIZZ',1,3.70,900);",
				" INSERT INTO FV_PRODUTO VALUES (161123,'CREME HIDRAKEL BABOSA',1,3.70,900);",
				" INSERT INTO FV_PRODUTO VALUES (161125,'CREME HIDRAKEL CERAMIDAS',1,3.70,900);",
				" INSERT INTO FV_PRODUTO VALUES (161128,'CREME HIDRAKEL KARITE',1,3.70,900);",
				" INSERT INTO FV_PRODUTO VALUES (161129,'CREME HIDRAKEL KERATINA',1,3.70,900);",
				" INSERT INTO FV_PRODUTO VALUES (161130,'CREME HIDRAKEL LEITE DE CABRA',1,3.70,900);",
				" INSERT INTO FV_PRODUTO VALUES (161133,'CREME HIDRAKEL MAX FRUTAS',1,3.70,900);",
				" INSERT INTO FV_PRODUTO VALUES (161135,'CREME HIDRAKEL SILICONE',1,3.70,900);",
				" INSERT INTO FV_PRODUTO VALUES (161136,'CREME HIDRAKEL TUTANO',1,3.70,900);",
				
				
				
				" INSERT INTO FV_PRODUTO VALUES (171007,'SABONETE ORQUIDEA 500 VAL',0.5,3.90,900);",
				" INSERT INTO FV_PRODUTO VALUES (171009,'SABONETE KIWI 500 VAL',0.5,3.90,900);",
				" INSERT INTO FV_PRODUTO VALUES (171011,'SABONETE CUPCAKE 500 VAL',0.5,3.90,900);",
				" INSERT INTO FV_PRODUTO VALUES (171012,'SABONETE ERVA DOCE 500 VAL',0.5,3.90,900);",
				" INSERT INTO FV_PRODUTO VALUES (171013,'SABONETE FRUTAS VERMELHA 500 VAL',0.5,3.90,900);",
				" INSERT INTO FV_PRODUTO VALUES (171014,'SABONETE NEUTRO 500 VAL',0.5,3.90,900);",
				" INSERT INTO FV_PRODUTO VALUES (171015,'SABONETE PESSEGO 500 VAL',0.5,3.90,900);",
				" INSERT INTO FV_PRODUTO VALUES (171016,'SABONETE PITANGA 500 VAL',0.5,3.90,900);",
				" INSERT INTO FV_PRODUTO VALUES (171018,'SABONETE COCO E LIMAO  500 VAL',0.5,3.90,900);",
				" INSERT INTO FV_PRODUTO VALUES (171020,'SABONETE ROMA  500 VAL',0.5,3.90,900);",	
				" INSERT INTO FV_PRODUTO VALUES (171021,'SABONETE CALENDULA 500 VAL',0.5,3.90,900);",
				
				
				" INSERT INTO FV_PRODUTO VALUES (171711,'SABONETE CALENDULA 250',0.25,2.27,900);",
				" INSERT INTO FV_PRODUTO VALUES (171712,'SABONETE ERVA DOCE 250',0.25,2.27,900);",
				" INSERT INTO FV_PRODUTO VALUES (171713,'SABONETE PESSEGO 250',0.25,2.27,900);",
				" INSERT INTO FV_PRODUTO VALUES (171714,'SABONETE PITANGA 250',0.25,2.27,900);",
				" INSERT INTO FV_PRODUTO VALUES (171715,'SABONETE ORQUIDEA 250',0.25,2.27,900);",
				
				" INSERT INTO FV_PRODUTO VALUES (171811,'SABONETE CALENDULA 1000',0.1,5.24,900);",
				" INSERT INTO FV_PRODUTO VALUES (171812,'SABONETE ERVA DOCE 1000',0.1,5.24,900);",
				" INSERT INTO FV_PRODUTO VALUES (171813,'SABONETE FRUTAS VERMELHAS 1000',0.1,5.24,900);",
				" INSERT INTO FV_PRODUTO VALUES (171814,'SABONETE PESSEGO 1000',0.1,5.24,900);",
				" INSERT INTO FV_PRODUTO VALUES (171815,'SABONETE PITANGA 1000',0.1,5.24,900);",
				" INSERT INTO FV_PRODUTO VALUES (171816,'SABONETE VANILLA 1000',0.1,5.24,900);",
				" INSERT INTO FV_PRODUTO VALUES (171817,'SABONETE ORQUIDEA 1000',0.1,5.24,900);",
				
				
				
				

				
				" INSERT INTO FV_PRODUTO VALUES (180301,'SABONETE MORANGO 500',0.5,3.90,900);",
				" INSERT INTO FV_PRODUTO VALUES (180302,'SABONETE SECRETS PERA 500',0.5,3.90,900);",
				" INSERT INTO FV_PRODUTO VALUES (180306,'SABONETE SPA ERVA DOCE 500',0.5,3.90,900);",
				" INSERT INTO FV_PRODUTO VALUES (180313,'SABONETE SECRETS TANGERINA 500',0.5,3.90,900);",
				" INSERT INTO FV_PRODUTO VALUES (180314,'SABONETE VANILLA 500',0.5,3.90,900);",
				
				
			
				
				" INSERT INTO FV_PRODUTO VALUES (190101,'XAMPU ANTI-FRIZZ ',0.3,4.69,900);",
				" INSERT INTO FV_PRODUTO VALUES (190201,'CONDICIONADOR ANTI-FRIZZ 300',0.3,4.69,900);",
				" INSERT INTO FV_PRODUTO VALUES (190411,'MASCARA ACAO COND ANTI FRIZZ',0.25,4.69,900);",
				" INSERT INTO FV_PRODUTO VALUES (190611,'HIDRATANTE ANTI-FRIZZ',0.25,4.69,900);",
				" INSERT INTO FV_PRODUTO VALUES (190911,'FINALIZADOR ANTI FRIZZ',0.3,4.69,900);",
				" INSERT INTO FV_PRODUTO VALUES (200101,'KELMINHA KIDS SHAMPOO TUTTI-FRUTTI',0.25,3.38,900);",
				" INSERT INTO FV_PRODUTO VALUES (200102,'KELMINHA KIDS SHAMPOO MA�� VERDE',0.25,3.38,900);",
				" INSERT INTO FV_PRODUTO VALUES (200103,'KELMINHA KIDS SHAMPOO MORANGO',0.25,3.38,900);",
				" INSERT INTO FV_PRODUTO VALUES (200201,'KELMINHA KIDS COND. TUTTI-FRUTTI',0.25,3.38,900);",
				" INSERT INTO FV_PRODUTO VALUES (200202,'KELMINHA KIDS COND. MA�� VERDE',0.25,3.38,900);",
				" INSERT INTO FV_PRODUTO VALUES (200203,'KELMINHA KIDS COND. MORANGO',0.25,3.38,900);",
				" INSERT INTO FV_PRODUTO VALUES (201901,'COND SEM ENXAGUE (CREME.P) K.KIDS AMAREL',0.3,3.38,900);",
				" INSERT INTO FV_PRODUTO VALUES (201902,'COND SEM ENXAGUE (CREME.P) K.KIDS VERDE',0.3,3.38,900);",
				" INSERT INTO FV_PRODUTO VALUES (201903,'COND SEM ENXAGUE (CREME.P) K.KIDS VERMEL',0.3,3.38,900);",
				
				
			
				
				
				" INSERT INTO FV_PRODUTO VALUES (219000,'CX-REP.DE PONTA PREM. ORQUIDIA',0.42,34.36,900);",
				" INSERT INTO FV_PRODUTO VALUES (219001,'CX-REP.DE PONTA ALOEVERA',0.42,23.36,900);",
				" INSERT INTO FV_PRODUTO VALUES (219002,'CX-REP.DE PONTA CERAMIDA',0.42,23.36,900);",
				" INSERT INTO FV_PRODUTO VALUES (219003,'CX-REP.DE PONTA CHOCOLATE',0.42,34.36,900);",
				" INSERT INTO FV_PRODUTO VALUES (219004,'CX-REP.DE PONTA COLOR',0.42,23.36,900);",
				" INSERT INTO FV_PRODUTO VALUES (219005,'CX-REP.DE PONTA FILTROSOLAR',0.42,23.36,900);",
				" INSERT INTO FV_PRODUTO VALUES (219006,'CX-REP.DE PONTA KARITE',0.42,23.36,900);",
				" INSERT INTO FV_PRODUTO VALUES (219007,'CX-REP.DE PONTA KERATINA',0.42,23.36,900);",
				" INSERT INTO FV_PRODUTO VALUES (219008,'CX-REP.DE PONTA PROTEINA',0.42,23.36,900);",
				" INSERT INTO FV_PRODUTO VALUES (219009,'CX-REP.DE PONTA SILICONE',0.42,23.36,900);",
				" INSERT INTO FV_PRODUTO VALUES (219010,'CX-REP.DE PONTA TUTANO',0.42,23.36,900);",
				
				
				
				" INSERT INTO FV_PRODUTO VALUES (222111,'SILICONE ESPECIAL P PRANCHA ACAO COND',0.035,3.07,900);",
				" INSERT INTO FV_PRODUTO VALUES (242205,'CREME PES 960',0.96,5.21,900);",
				" INSERT INTO FV_PRODUTO VALUES (242206,'CREME MAOS 960',0.96,4.81,900);",
				
				" INSERT INTO FV_PRODUTO VALUES (242207,'CREME ESFOLIANTE CORPO 960',0.96,6.38,900);",
				" INSERT INTO FV_PRODUTO VALUES (242208,'CREME ESFOLIANTE PES 960',0.96,6.38,900);",
				
				" INSERT INTO FV_PRODUTO VALUES (242213,'CREME PES 250 - VERDE',0.25,2.96,900);",
				" INSERT INTO FV_PRODUTO VALUES (242214,'CREME MAOS 250 - AZUL',0.25,2.96,900);",
				
				" INSERT INTO FV_PRODUTO VALUES (242316,'LUVAS DE SEDA COLAGENO 60',0.06,3.15,900);",
				" INSERT INTO FV_PRODUTO VALUES (242317,'LUVAS DE SEDA SILICONE 60',0.06,3.15,900);",
		
				" INSERT INTO FV_PRODUTO VALUES (252406,'DESODORANTE ROLL-ON SPORT',0.07,2.21,900);",
				" INSERT INTO FV_PRODUTO VALUES (252407,'DESODORANTE ROLL-ON STILLO',0.07,2.21,900);",
				
				
				" INSERT INTO FV_PRODUTO VALUES (262511,'CREME UMIDI. KELMASTEROL 300',0.3,2.94,900);",
				" INSERT INTO FV_PRODUTO VALUES (262512,'UMIDIKEL DEFIRZANTE SILIC',0.39,4.62,900);",
				" INSERT INTO FV_PRODUTO VALUES (262513,'UMIDIKEL MANTEIGAS ESPEC',0.39,4.62,900);",
				" INSERT INTO FV_PRODUTO VALUES (262514,'UMIDIKEL KERATINA',0.39,4.62,900);",
				" INSERT INTO FV_PRODUTO VALUES (270103,'XAMPU HIDRAKEL MACIEZ',0.4,4.25,900);",
				" INSERT INTO FV_PRODUTO VALUES (270104,'XAMPU HIDRAKEL LISOS',0.4,4.25,900);",
				" INSERT INTO FV_PRODUTO VALUES (270105,'XAMPU HIDRAKEL RECONSTRU��O',0.4,4.25,900);",
				" INSERT INTO FV_PRODUTO VALUES (270101,'XAMPU HIDRAKEL BRILHO',0.4,4.25,900);",
				" INSERT INTO FV_PRODUTO VALUES (270102,'XAMPU HIDRAKEL FOR�A',0.4,4.25,900);",
				" INSERT INTO FV_PRODUTO VALUES (270203,'CONDICIONADOR HIDRAKEL MACIEZ',0.4,4.25,900);",
				" INSERT INTO FV_PRODUTO VALUES (270204,'CONDICIONADOR HIDRAKEL LISOS',0.4,4.25,900);",
				" INSERT INTO FV_PRODUTO VALUES (270205,'CONDICIONADOR HIDRAKEL RECONSTRU��O',0.4,4.25,900);",
				" INSERT INTO FV_PRODUTO VALUES (270201,'CONDICIONADOR HIDRAKEL BRILHO',0.4,4.25,900);",
				" INSERT INTO FV_PRODUTO VALUES (270202,'CONDICIONADOR HIDRAKEL FOR�A',0.4,4.25,900);",
				
				" INSERT INTO FV_PRODUTO VALUES (280102,'XAMPU POPKEL CERAMIDAS 300',0.3,2.30,900);",
				" INSERT INTO FV_PRODUTO VALUES (280202,'CONDICIONADOR POPKEL CERAMIDAS 300',0.3,2.30,900);",
				" INSERT INTO FV_PRODUTO VALUES (281902,'CREME PENTEAR POPKEL CERAMIDAS 300',0.3,2.30,900);",
				
				" INSERT INTO FV_PRODUTO VALUES (280301,'SABONETE POPKEL A�AI',1.9,5.93,900);",
				" INSERT INTO FV_PRODUTO VALUES (280302,'SABONETE POPKEL ERVA DOCE',1.9,5.93,900);",
				" INSERT INTO FV_PRODUTO VALUES (280303,'SABONETE POPKEL FRUTAS CITRICAS',1.9,5.93,900);",
				" INSERT INTO FV_PRODUTO VALUES (280304,'SABONETE POPKEL JABUTICABA',1.9,5.93,900);",
				" INSERT INTO FV_PRODUTO VALUES (280305,'SABONETE POPKEL MELAO',1.9,5.93,900);",
				
				
				" INSERT INTO FV_PRODUTO VALUES (289605,'CX-LOCAO POPKEL AMOR PERFEITO',0.500,30.96,900);",
				" INSERT INTO FV_PRODUTO VALUES (289601,'CX-LOCAO POPKEL MACADAMIA SENSUAL',0.500,30.96,900);",
				" INSERT INTO FV_PRODUTO VALUES (289604,'CX-LOCAO POPKEL ORQUIDEA ENCANTADA',0.500,30.96,900);",
				" INSERT INTO FV_PRODUTO VALUES (289602,'CX-LOCAO POPKEL PAIXAO ABSOLUTA',0.500,30.96,900);",
				
				
				" INSERT INTO FV_PRODUTO VALUES (292102,'OLEO DE AMENDOAS POPKEL 100ML ',0.1,2.66,900);",
				
				
				" INSERT INTO FV_PRODUTO VALUES (310101,'XAMPU MANDIOCA PLUS',0.3,5.49,900);",
				" INSERT INTO FV_PRODUTO VALUES (310201,'CONDICIONADOR MANDIOCA PLUS',0.3,5.49,900);",
				" INSERT INTO FV_PRODUTO VALUES (310411,'MASCARA MANDIOCA PLUS',0.25,5.49,900);",
				" INSERT INTO FV_PRODUTO VALUES (310611,'CREME MANDIOCA PLUS',0.25,5.49,900);",

				" INSERT INTO FV_PRODUTO VALUES (312011,'RESTAURADOR CAPILAR MANDIOCA',0.25,16.21,900);",
				" INSERT INTO FV_PRODUTO VALUES (320103,'XAMPU COLORS SILVER',0.3,6.49,900);",
				" INSERT INTO FV_PRODUTO VALUES (320104,'XAMPU COLORS CLEAN',0.3,4.61,900);",
				" INSERT INTO FV_PRODUTO VALUES (320203,'CONDICIONADOR COLORS SILVER',0.3,6.49,900);",
				
		
				" INSERT INTO FV_PRODUTO VALUES (320403,'COND HIDRATANTE (MASCARA) COLORS SILVER',0.25,6.49,900);",
				" INSERT INTO FV_PRODUTO VALUES (341003,'GEL HIGIENIZANTE NEUTRO 450G',0.46,6.88,900);",
				
				" INSERT INTO FV_PRODUTO VALUES (351602,'KIT ACQUA SECRETS - ALFAZEMA & PERA',0.86,12.98,900);",
				" INSERT INTO FV_PRODUTO VALUES (351603,'KIT ACQUA SECRETS - VANILLA & VANILLA',12,12.98,900);",
				" INSERT INTO FV_PRODUTO VALUES (351604,'KIT ACQUA SECRETS - CEREJEIRA & MORANGO',0.86,12.98,900);",
				" INSERT INTO FV_PRODUTO VALUES (352801,'DEO COLONIA ALFAZEMA',0.25,7.75,900);",
				" INSERT INTO FV_PRODUTO VALUES (352802,'DEO COLONIA FLOR CEREJEIRA',0.25,7.98,900);",
				" INSERT INTO FV_PRODUTO VALUES (352803,'DEO COLONIA FLORAL',0.25,7.98,900);",
				" INSERT INTO FV_PRODUTO VALUES (352804,'DEO COLONIA LAVANDA',0.25,7.98,900);",
				" INSERT INTO FV_PRODUTO VALUES (352805,'DEO COLONIA VANILLA',0.25,7.98,900);",
				" INSERT INTO FV_PRODUTO VALUES (370100,'XAMPU ANTI AGE 3000',3,8.19,900);",
				" INSERT INTO FV_PRODUTO VALUES (370101,'XAMPU ANTI RES�DUOS 3000',3,10.53,900);",
				" INSERT INTO FV_PRODUTO VALUES (370104,'XAMPU SEMI DI LINO 3000',3,8.19,900);",
				" INSERT INTO FV_PRODUTO VALUES (370105,'XAMPU NEUTRO 3000',3,8.19,900);",
				" INSERT INTO FV_PRODUTO VALUES (370107,'XAMPU SEM SAL 3000',3,11.28,900);",
				" INSERT INTO FV_PRODUTO VALUES (370109,'XAMPU LEITE DE CABRA 3000',3,8.19,900);",
				" INSERT INTO FV_PRODUTO VALUES (370312,'SABONETE DOCE 3000',3,10.37,900);",
				" INSERT INTO FV_PRODUTO VALUES (370322,'SABONETE ORQUIDEAS 3000',3,10.37,900);",
				" INSERT INTO FV_PRODUTO VALUES (380201,'CONDICIONADOR CREME RINSE 1900',1.9,5.63,900);",
				" INSERT INTO FV_PRODUTO VALUES (381601,'KIT LOCAO - SAB. MOMENTOS - DIA DAS M�ES',1,8.16,900);",
				" INSERT INTO FV_PRODUTO VALUES (381602,'KIT LOCAO - SAB. MOMENTOS - NATAL',1,8.16,900);",
				" INSERT INTO FV_PRODUTO VALUES (390101,'XAMPU ARGAN',0.3,6.15,900);",
				" INSERT INTO FV_PRODUTO VALUES (390201,'CONDICIONADOR ARGAN',0.3,6.15,900);",
				" INSERT INTO FV_PRODUTO VALUES (390401,'COND HIDRATANTE (MASCARA) ARGAN',0.25,6.15,900);",
				" INSERT INTO FV_PRODUTO VALUES (390601,'CONDICIONADOR LEITE (LEAVE IN) ARGAN',0.2,6.15,900);",
				
				
				" INSERT INTO FV_PRODUTO VALUES (399001,'CX - REPARADOR ACAO PREMIUM ARGAN ',0.42,35.88,900);",
				" INSERT INTO FV_PRODUTO VALUES (399701,'CX - OLEO ARGAN',0.035,52.14,900);",
				" INSERT INTO FV_PRODUTO VALUES (400101,'XAMPU REPARA��O ABSOLUTA',0.3,5.05,900);",
				" INSERT INTO FV_PRODUTO VALUES (400201,'CONDICIONADOR REPARA��O ABSOLUTA',0.3,5.48,900);",
				" INSERT INTO FV_PRODUTO VALUES (400401,'MASCARA REPARA��O ABSOLUT',0.25,5.98,900);",
				" INSERT INTO FV_PRODUTO VALUES (401901,'COND.SEM ENXAGUE REPARA��O ABSOLUTA',0.25,5.48,900);",
				" INSERT INTO FV_PRODUTO VALUES (410101,'XAMPU ARGININA FORCE 300',0.3,5.05,900);",
				" INSERT INTO FV_PRODUTO VALUES (410201,'CONDICIONADOR  ARGININA FORCE 300',0.3,5.48,900);",
				" INSERT INTO FV_PRODUTO VALUES (410401,'MASCARA CONDICIONADORA  ARGININA 250',0.25,5.98,900);",
				" INSERT INTO FV_PRODUTO VALUES (411901,'COND S ENXAGUE  ARGININA FORCE 900',0.25,5.87,900);",
				" INSERT INTO FV_PRODUTO VALUES (421601,'KIT 05 LO��ES SPA HIDRAT 120ML ',0.6,8.94,900);",
				" INSERT INTO FV_PRODUTO VALUES (421606,'KIT SPA FRUTAS VER - LOCAO E SAB LIQ 500',1.25,8.16,900);",
				" INSERT INTO FV_PRODUTO VALUES (421607,'KIT SPA ORQUIDEA - LOCAO E SAB LIQ 500',1.25,8.16,900);",
				" INSERT INTO FV_PRODUTO VALUES (421610,'KIT SPA MORANGO - LOCAO E SAB LIQ 500',1.25,8.16,900);",
				" INSERT INTO FV_PRODUTO VALUES (421611,'KIT SPA VANILA - LOCAO E SAB LIQ 500',1.25,8.16,900);",
				
				//  Insere os planos default .....
				"INSERT INTO FV_Planos (codigo,Descricao) VALUES (1,'7');" ,
				"INSERT INTO FV_Planos (codigo,Descricao) VALUES (2,'10');" ,
				"INSERT INTO FV_Planos (codigo,Descricao) VALUES (3,'14');" ,
				"INSERT INTO FV_Planos (codigo,Descricao) VALUES (4,'21');" ,
				"INSERT INTO FV_Planos (codigo,Descricao) VALUES (5,'28');" ,
				"INSERT INTO FV_Planos (codigo,Descricao) VALUES (6,'7/14/21');" ,
				"INSERT INTO FV_Planos (codigo,Descricao) VALUES (7,'7/14 (TAB 14)');" ,
				"INSERT INTO FV_Planos (codigo,Descricao) VALUES (8,'14/21( A VISTA)');" ,
				"INSERT INTO FV_Planos (codigo,Descricao) VALUES (9,'14/21(TAB 14)');" ,
				"INSERT INTO FV_Planos (codigo,Descricao) VALUES (10,'14/21/28');" ,
				"INSERT INTO FV_Planos (codigo,Descricao) VALUES (11,'21/28 (TAB 21)');" ,
				"INSERT INTO FV_Planos (codigo,Descricao) VALUES (12,'21/28 (TAB 14)');" ,
				"INSERT INTO FV_Planos (codigo,Descricao) VALUES (13,'21 (A Vista)');" ,
				"INSERT INTO FV_Planos (codigo,Descricao) VALUES (14,'21/28/35 (TAB 21)');" ,
				"INSERT INTO FV_Planos (codigo,Descricao) VALUES (15,'21/28/35 (TAB14)');" ,
				"INSERT INTO FV_Planos (codigo,Descricao) VALUES (16,'28/35 (TAB 28)');" ,
				"INSERT INTO FV_Planos (codigo,Descricao) VALUES (17,'35/42(TAB 35)');" ,
				"INSERT INTO FV_Planos (codigo,Descricao) VALUES (18,'42/60(TAB 42)');" ,
				"INSERT INTO FV_Planos (codigo,Descricao) VALUES (19,'42');" ,
				

		// Insere configura�oes iniciais
		"INSERT INTO FV_CONFIGURA(representante,senha,ipFTP,UserFTP,SenhaFTP,dirFTP,dirFotoProdutos,dirFotoExtra) values ('1','vendas','lojakelma.com.br','site1368285974','kelma@12','/Pedidos/Mauricio','/storage/extSdCard/fotoProdutos/','/storage/extSdCard/fotoExtra/');"};
	}
}

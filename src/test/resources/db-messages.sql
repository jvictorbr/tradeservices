SET MODE ORACLE;
  
CREATE TABLE TL_002_ERROR_LOG 
   (	
   	TL0002_COD_ERRO NUMBER, 
	TL0002_REQ_ID VARCHAR2(255) NOT NULL PRIMARY KEY, 
	TL0002_DEAL_PK NUMBER(10), 
	TL0002_BACEN_CONTRACT_PK NUMBER(12), 
	TL0002_BACEN_CONTRACT_YEAR VARCHAR2(2), 
	TL0002_CONTRATO_FX NUMBER(10), 
	TL0002_DESC_ERRO VARCHAR2(2000), 
	TL0002_DATA_ERRO TIMESTAMP
   );
   
   
INSERT INTO TL_002_ERROR_LOG (TL0002_COD_ERRO, TL0002_REQ_ID, TL0002_DEAL_PK, TL0002_BACEN_CONTRACT_PK, TL0002_BACEN_CONTRACT_YEAR, TL0002_CONTRATO_FX, TL0002_DESC_ERRO, TL0002_DATA_ERRO)
VALUES (100, 'DUMMY_0101', 1001, 1002, 15, 1003, 'Sucesso.', SYSDATE);

INSERT INTO TL_002_ERROR_LOG (TL0002_COD_ERRO, TL0002_REQ_ID, TL0002_DEAL_PK, TL0002_BACEN_CONTRACT_PK, TL0002_BACEN_CONTRACT_YEAR, TL0002_CONTRATO_FX, TL0002_DESC_ERRO, TL0002_DATA_ERRO)
VALUES (101, 'DUMMY_0202', 2001, 2002, 15, 2003, 'Erro.', SYSDATE);

CREATE TABLE TL_003_LOG
	(
		TL0003_REQ_ID VARCHAR2(255) NOT NULL PRIMARY KEY,
		TL0003_DATA_LOG TIMESTAMP,
		TL0003_TYPE VARCHAR2(255),
		TL0003_CONTENT CLOB		
	);
	
	
	
INSERT INTO TL_003_LOG VALUES ('DUMMY_0101', SYSDATE, 'update-payment-order', '<?xml version="1.0" encoding="UTF-8"?><update-payment-order xmlns="http://www.citigroup.com/tradelayer/schemas" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.citigroup.com/tradelayer/schemas payment-order.xsd "> <msg_pk>DUMMY_0101</msg_pk> <user_maker>999</user_maker> <user_checker>999</user_checker> <ind_valida>true</ind_valida> <deal>7192260</deal> <contract_fx>1151050004</contract_fx> <MT103> <contabilizada>false</contabilizada> <trade_data>  <order_name>Citibank SA</order_name>  <contract_bacen>  <numero>2175960</numero>  <ano>5</ano>  </contract_bacen>  <COC>999</COC>  <COM>999</COM>  <COV>999</COV> </trade_data> <senders_reference>IMP.2175960</senders_reference> <bank_operation_code>CRED</bank_operation_code> <valuedate_ccy_amount>  <value_date>2015-04-15</value_date>  <fccy>USD</fccy>  <settled_amount>164000.00</settled_amount> </valuedate_ccy_amount> <ordering_customer>  <account>3441</account>  <name_and_address>/61064929000179</name_and_address>  <name_and_address>DU PONT DO BRASIL S/A</name_and_address>  <name_and_address>AL ITAPECURU 506</name_and_address>  <name_and_address>Brazil BARUERI - SP</name_and_address> </ordering_customer> <intermediary_institution>  <bic_pk>CITIUS33XXX</bic_pk> </intermediary_institution> <account_with_institution>  <cc_clear>/10925939</cc_clear>  <bank_name>DRESDNER BANK AG</bank_name>  <bank_address>FRANKFURT GERMANY</bank_address> </account_with_institution> <beneficiary_customer>  <cc_clear>/DE49342800320622502000</cc_clear>  <bank_name>MCS PROMOTION GMBH</bank_name>  <bank_address>GERMANY</bank_address> </beneficiary_customer> <charges_detail>OUR</charges_detail> </MT103></update-payment-order>');
INSERT INTO TL_003_LOG VALUES ('msg_response_DUMMY_0101', SYSDATE, 'update-payment-order-response', ' <update-payment-order-response xmlns="http://www.citigroup.com/tradelayer/schemas"> <msg_pk>msg_response_DUMMY_0101</msg_pk> <reference_msg_pk>DUMMY_0101</reference_msg_pk> <status> <success> <pk>100</pk> <deal_pk>0</deal_pk> <contract> <numero>0</numero> <ano>15</ano> </contract> <contract_fx>1151050004</contract_fx> <description>Ordem de Pagamento Tipo: 103 - Validacao OK.</description> </success> </status></update-payment-order-response>');

INSERT INTO TL_003_LOG VALUES ('DUMMY_0202', SYSDATE, 'pay-contract', '<?xml version="1.0" encoding="UTF-8"?><pay-contract xmlns="http://www.citigroup.com/tradelayer/schemas" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.citigroup.com/tradelayer/schemas payment-order.xsd "> <msg_pk>DUMMY_0202</msg_pk> <user_maker>999</user_maker> <user_checker>999</user_checker> <deal>7565568</deal> <contract_fx>1151030581</contract_fx> <CC> <payment_date>2015-04-15</payment_date> </CC></pay-contract> ');
INSERT INTO TL_003_LOG VALUES ('msg_response_DUMMY_0202', SYSDATE, 'pay-contract-response', '<pay-contract-response xmlns="http://www.citigroup.com/tradelayer/schemas"> <msg_pk>msg_response_DUMMY_0202</msg_pk> <reference_msg_pk>DUMMY_0202</reference_msg_pk> <status> <error> <pk>500</pk> <deal_pk>0</deal_pk> <contract> <numero>0</numero> <ano>15</ano> </contract> <contract_fx>1151030581</contract_fx> <description>Contrato: 1151030581 não existe ou código inválido ou pendente.</description> </error> </status></pay-contract-response>');

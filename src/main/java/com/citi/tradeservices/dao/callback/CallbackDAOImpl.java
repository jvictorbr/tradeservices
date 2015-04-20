package com.citi.tradeservices.dao.callback;

import java.sql.Types;
import java.util.Date;
import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.citi.tradeservices.domain.callback.AbstractCallbackInformation.AccountInstitution;
import com.citi.tradeservices.domain.callback.AbstractCallbackInformation.BeneficiaryCustomer;
import com.citi.tradeservices.domain.callback.AbstractCallbackInformation.IntermediaryInstitution;
import com.citi.tradeservices.domain.callback.Callback;
import com.citi.tradeservices.domain.callback.CallbackRequest;

@Repository
public class CallbackDAOImpl implements CallbackDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	@Override
	public Callback getCallback(CallbackRequest request) {
		
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(getJdbcTemplate()).withCatalogName("PCK_TL_CALLBACK_OP").withProcedureName("PRINCIPAL");
		jdbcCall.setAccessCallParameterMetaData(false);
		
		jdbcCall.addDeclaredParameter(new SqlParameter(Parameters.P_MSG_ID_PARAM, Types.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlParameter(Parameters.P_CNPJ_ORDENANTE, Types.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlParameter(Parameters.P_CC_BENEFICIARIO, Types.VARCHAR));
		
		jdbcCall.addDeclaredParameter(new SqlOutParameter(Parameters.P_56_CONTA_BIC, Types.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlOutParameter(Parameters.P_56_COD_BIC, Types.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlOutParameter(Parameters.P_56_CC_CLEAR, Types.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlOutParameter(Parameters.P_56_NOME_BANCO, Types.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlOutParameter(Parameters.P_56_END_1, Types.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlOutParameter(Parameters.P_56_END_2, Types.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlOutParameter(Parameters.P_56_END_3, Types.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlOutParameter(Parameters.P_57_CONTA_BIC, Types.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlOutParameter(Parameters.P_57_COD_BIC, Types.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlOutParameter(Parameters.P_57_CC_CLEAR, Types.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlOutParameter(Parameters.P_57_NOME_BANCO, Types.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlOutParameter(Parameters.P_57_END_1, Types.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlOutParameter(Parameters.P_57_END_2, Types.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlOutParameter(Parameters.P_57_END_3, Types.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlOutParameter(Parameters.P_59_CONTA_BIC, Types.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlOutParameter(Parameters.P_59_COD_BIC, Types.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlOutParameter(Parameters.P_59_CC_CLEAR, Types.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlOutParameter(Parameters.P_59_NOME_BANCO, Types.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlOutParameter(Parameters.P_59_END_1, Types.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlOutParameter(Parameters.P_59_END_2, Types.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlOutParameter(Parameters.P_59_END_3, Types.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlOutParameter(Parameters.P_DATA_ULTIMA_ORDEM, Types.DATE));
		jdbcCall.addDeclaredParameter(new SqlOutParameter(Parameters.P_QUANTIDADE, Types.INTEGER));
		jdbcCall.addDeclaredParameter(new SqlOutParameter(Parameters.P_CURSOR_ERRORS, OracleTypes.CURSOR));
				
		SqlParameterSource in = new MapSqlParameterSource()
			.addValue(Parameters.P_MSG_ID_PARAM, request.getId(), Types.VARCHAR)
			.addValue(Parameters.P_CNPJ_ORDENANTE, request.getCustomerCNPJ(), Types.VARCHAR)
			.addValue(Parameters.P_CC_BENEFICIARIO, request.getAccount(), Types.VARCHAR);
		
		
		Map<String, Object> output = jdbcCall.execute(in);
		
		return assemble(output, request);
	}
	
	private Callback assemble(Map<String, Object> output, CallbackRequest input) { 
		
		Callback callback = new Callback();
		
		callback.setId(input.getId());
		callback.setCustomerId(input.getCustomerCNPJ());
		callback.setAccount(input.getAccount());
		
		assembleIntermediaryInstitution(callback, output);
		assembleAccountInstitution(callback, output);
		assembleBeneficiaryCustomer(callback, output);		
		
		callback.setLastOrderDate((Date) output.get(Parameters.P_DATA_ULTIMA_ORDEM));
		callback.setOrderCount((Integer) output.get(Parameters.P_QUANTIDADE));
				
		return callback;
		
	}
	
	private IntermediaryInstitution assembleIntermediaryInstitution(Callback callback, Map<String, Object> output) {
		IntermediaryInstitution ii = callback.addNewIntermediaryInstitution();
		ii.setBicAccount((String) output.get(Parameters.P_56_CONTA_BIC));
		ii.setBicCode((String) output.get(Parameters.P_56_COD_BIC));
		ii.setCcClear((String) output.get(Parameters.P_56_CC_CLEAR));
		ii.setBankName((String) output.get(Parameters.P_56_NOME_BANCO));
		if (output.get(Parameters.P_56_END_1) != null) {
			ii.addAddress((String) output.get(Parameters.P_56_END_1));
		}
		if (output.get(Parameters.P_56_END_2) != null) {
			ii.addAddress((String) output.get(Parameters.P_56_END_2));
		}
		if (output.get(Parameters.P_56_END_3) != null) {
			ii.addAddress((String) output.get(Parameters.P_56_END_3));
		}
		return ii;
	}
	
	private AccountInstitution assembleAccountInstitution(Callback callback, Map<String, Object> output) { 
		AccountInstitution ai = callback.addNewAccountInstitution();
		ai.setBicAccount((String) output.get(Parameters.P_57_CONTA_BIC));
		ai.setBicCode((String) output.get(Parameters.P_57_COD_BIC));
		ai.setCcClear((String) output.get(Parameters.P_57_CC_CLEAR));
		ai.setBankName((String) output.get(Parameters.P_57_NOME_BANCO));
		if (output.get(Parameters.P_57_END_1) != null) {
			ai.addAddress((String) output.get(Parameters.P_57_END_1));
		}
		if (output.get(Parameters.P_57_END_2) != null) {
			ai.addAddress((String) output.get(Parameters.P_57_END_2));
		}
		if (output.get(Parameters.P_57_END_3) != null) {
			ai.addAddress((String) output.get(Parameters.P_57_END_3));
		}		
		return ai;
	}
	
	private BeneficiaryCustomer assembleBeneficiaryCustomer(Callback callback, Map<String, Object> output) { 
		BeneficiaryCustomer bc = callback.addNewBeneficiaryCustomer();
		bc.setBicAccount((String) output.get(Parameters.P_59_CONTA_BIC));
		bc.setBicCode((String) output.get(Parameters.P_59_COD_BIC));
		bc.setCcClear((String) output.get(Parameters.P_59_CC_CLEAR));
		bc.setBankName((String) output.get(Parameters.P_59_NOME_BANCO));
		if (output.get(Parameters.P_59_END_1) != null) {
			bc.addAddress((String) output.get(Parameters.P_59_END_1));
		}
		if (output.get(Parameters.P_59_END_2) != null) {
			bc.addAddress((String) output.get(Parameters.P_59_END_2));
		}
		if (output.get(Parameters.P_59_END_3) != null) {
			bc.addAddress((String) output.get(Parameters.P_59_END_3));
		}	
		return bc;
	}
	
	
	private JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	private static class Parameters { 
		public static final String P_MSG_ID_PARAM	=		"P_MSG_ID_PARAM";
		public static final String P_CNPJ_ORDENANTE	=	"P_CNPJ_ORDENANTE";
		public static final String P_CC_BENEFICIARIO	=	"P_CC_BENEFICIARIO";
		public static final String P_56_CONTA_BIC		=	"P_56_CONTA_BIC";
		public static final String P_56_COD_BIC		=	"P_56_COD_BIC";
		public static final String P_56_CC_CLEAR		=	"P_56_CC_CLEAR";
		public static final String P_56_NOME_BANCO		=	"P_56_NOME_BANCO";
		public static final String P_56_END_1			=	"P_56_END_1";
		public static final String P_56_END_2			=	"P_56_END_2";
		public static final String P_56_END_3			=	"P_56_END_3";
		public static final String P_57_CONTA_BIC		=	"P_57_CONTA_BIC";
		public static final String P_57_COD_BIC		=	"P_57_COD_BIC";
		public static final String P_57_CC_CLEAR		=	"P_57_CC_CLEAR";
		public static final String P_57_NOME_BANCO		=	"P_57_NOME_BANCO";
		public static final String P_57_END_1			=	"P_57_END_1";
		public static final String P_57_END_2			=	"P_57_END_2";
		public static final String P_57_END_3			=	"P_57_END_3";
		public static final String P_59_CONTA_BIC		=	"P_59_CONTA_BIC";
		public static final String P_59_COD_BIC		=	"P_59_COD_BIC";
		public static final String P_59_CC_CLEAR		=	"P_59_CC_CLEAR";
		public static final String P_59_NOME_BANCO		=	"P_59_NOME_BANCO";
		public static final String P_59_END_1			=	"P_59_END_1";
		public static final String P_59_END_2			=	"P_59_END_2";
		public static final String P_59_END_3			=	"P_59_END_3";
		public static final String P_DATA_ULTIMA_ORDEM	=	"P_DATA_ULTIMA_ORDEM";
		public static final String P_QUANTIDADE		=	"P_QUANTIDADE";
		public static final String P_CURSOR_ERRORS		=	"P_CURSOR_ERRORS";
	}

}

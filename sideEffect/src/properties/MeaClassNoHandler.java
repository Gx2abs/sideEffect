/**
 * 
 */
package properties;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.hibernate.Query;
import org.hibernate.Session;

import properties.item.SimpleItemHistory;

import kr.co.sgis.legacy.common.DBConnection;
import abstraction.IDValuePair;
import foreign.Mea_class_no;

/**
 * @author Adam Hun/현한영
 * @date 2013. 12. 2.
 * @description
 * TODO TODO
 */
public class MeaClassNoHandler {
	
	public static <Type_ extends IDValuePair>  Mea_class_no updateMeaClassNo(Mea_class_no target) {
		Mea_class_no found = null;
		System.out.println("Mea_class_no(Class t, int grade,String mea_class_no)");
		StringBuilder queryString = new StringBuilder();
		Connection conn = DBConnection.getConnection();
		int result = 0;
		queryString.append("UPDATE MEA_CLASS_NO SET ");
		queryString.append("CLASS_KOR_NAME = ?");
		queryString.append(", CLASS_ENG_NAME = ?");
		queryString.append(", CLASS_CONT = ?");
		queryString.append(", CODE_AGE= ?");
		queryString.append(", IS_IN_USE= ?");
		queryString.append(", TRACEABILITY= ?");
		queryString.append(", UDI_CODE= ?");
		queryString.append("\n WHERE MEA_CLASS_NO  = ? AND  GRADE = ?");
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(queryString.toString());
			pstmt.setString(1, target.getClass_kor_name());
			pstmt.setString(2, target.getClass_eng_name());
			pstmt.setString(3, target.getClass_cont());
			pstmt.setLong(4, target.getCode_age().getId());
			pstmt.setLong(5, target.getIsInUse().getId());
			pstmt.setLong(6, target.getTraceability().getId());
			pstmt.setString(7, target.getUdi_code());
			pstmt.setString(8, target.getMea_class_no());
			pstmt.setString(9, target.getGrade());
			
			
			System.out.println( target.getClass_kor_name());
			System.out.println( target.getClass_eng_name());
			System.out.println( target.getClass_cont());
			System.out.println( target.getCode_age().getId());
			System.out.println( target.getIsInUse().getId());
			System.out.println( target.getTraceability().getId());
			System.out.println( target.getUdi_code());
			System.out.println( target.getGrade());
			System.out.println( target.getMea_class_no());
			
			System.out.println(queryString.toString());
			result = pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("result of  updateMeaClassNo :=  " + result);
		return  found;
	}

	public static <Type_ extends IDValuePair>  Mea_class_no insert(Mea_class_no target) {
		Mea_class_no found = null;
		System.out.println("Mea_class_no(Class t, int grade,String mea_class_no)");
		StringBuilder queryString = new StringBuilder();
		Connection conn = DBConnection.getConnection();
		int result = 0;
		queryString.append("insert into MEA_CLASS_NO (MEA_CLASS_NO, GRADE, CLASS_KOR_NAME) values  ");
		queryString.append(" ( ?, ?, ?)");
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(queryString.toString());
			pstmt.setString(3, target.getClass_kor_name());
			pstmt.setString(2, target.getGrade());
			pstmt.setString(1, target.getMea_class_no());
			
			System.out.println( target.getClass_kor_name());
			System.out.println( target.getClass_eng_name());
			System.out.println( target.getClass_cont());
			System.out.println( target.getCode_age().getId());
			System.out.println( target.getIsInUse().getId());
			System.out.println( target.getTraceability().getId());
			System.out.println( target.getGrade());
			System.out.println( target.getMea_class_no());
			
			System.out.println(queryString.toString());
			result = pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("result of  updateMeaClassNo :=  " + result);
		return  found;
	}

	public static <Type_ extends IDValuePair>  Mea_class_no updateMeaClassNoBk(Mea_class_no target) {
		Mea_class_no found = null;
		System.out.println("Mea_class_no(Class t, int grade,String mea_class_no)");
		StringBuilder queryString = new StringBuilder();
		Connection conn = DBConnection.getConnection();
		int result = 0;
		queryString.append("UPDATE MEA_CLASS_NO_BK SET ");
		queryString.append("CLASS_KOR_NAME = ? ");
		queryString.append(", CLASS_ENG_NAME = ? ");
		queryString.append(", CLASS_CONT = ? ");
		queryString.append(" WHERE GRADE = ? AND MEA_CLASS_NO  = ?");
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(queryString.toString());
			pstmt.setString(1, target.getClass_kor_name());
			pstmt.setString(2, target.getClass_eng_name());
			pstmt.setString(3, target.getClass_cont());
			pstmt.setString(4, target.getGrade());
			pstmt.setString(5, target.getMea_class_no());
			
			System.out.println( target.getClass_kor_name());
			System.out.println( target.getClass_eng_name());
			System.out.println( target.getClass_cont());
			System.out.println( target.getGrade());
			System.out.println( target.getMea_class_no());
			
			System.out.println(queryString.toString());
			result = pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("result of  updateMeaClassNo :=  " + result);
		return  found;
	}

	public static <Type_ extends IDValuePair>  Mea_class_no insert2(Mea_class_no target) {
		Mea_class_no found = null;
		System.out.println("Mea_class_no(Class t, int grade,String mea_class_no)");
		StringBuilder queryString = new StringBuilder();
		Connection conn = DBConnection.getConnection();
		int result = 0;
		queryString.append("insert into MEA_CLASS_NO2 (MEA_CLASS_NO, GRADE, CLASS_ENG_NAME) values  ");
		queryString.append(" ( ?, ?, ?)");
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(queryString.toString());
			pstmt.setString(3, target.getClass_kor_name());
			pstmt.setString(2, target.getGrade());
			pstmt.setString(1, target.getMea_class_no());
			
			System.out.println( target.getClass_kor_name());
			System.out.println( target.getClass_eng_name());
			System.out.println( target.getClass_cont());
			System.out.println( target.getCode_age().getId());
			System.out.println( target.getIsInUse().getId());
			System.out.println( target.getTraceability().getId());
			System.out.println( target.getGrade());
			System.out.println( target.getMea_class_no());
			
			System.out.println(queryString.toString());
			result = pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("result of  updateMeaClassNo :=  " + result);
		return  found;
	}

	public static <Type_ extends IDValuePair>  Mea_class_no updateMeaClassNo2(Mea_class_no target) {
		Mea_class_no found = null;
		System.out.println("Mea_class_no(Class t, int grade,String mea_class_no)");
		StringBuilder queryString = new StringBuilder();
		Connection conn = DBConnection.getConnection();
		int result = 0;
		queryString.append("UPDATE MEA_CLASS_NO2 SET ");
		queryString.append(" CLASS_ENG_NAME = ? ");
		queryString.append(" WHERE  MEA_CLASS_NO  = ? AND GRADE = ?  ");
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(queryString.toString());
			pstmt.setString(1, target.getClass_eng_name());
			pstmt.setString(2, target.getGrade());
			pstmt.setString(3, target.getMea_class_no());
			
			System.out.println( target.getClass_kor_name());
			System.out.println( target.getClass_eng_name());
			System.out.println( target.getClass_cont());
			System.out.println( target.getGrade());
			System.out.println( target.getMea_class_no());
			
			System.out.println(queryString.toString());
			result = pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("result of  updateMeaClassNo :=  " + result);
		return  found;
	}

	public static boolean deleteMeaClassNo(Mea_class_no target){
		
		boolean jobStat = false;
		Connection conn = null;
		try{
		 	
			StringBuilder sb = new StringBuilder();
			sb.append("DELETE FROM MEA_CLASS_NO \n");
			sb.append("WHERE MEA_CLASS_NO= ?  \n");
			sb.append("AND GRADE= ? ");
			conn = DBConnection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			System.out.println("delete statement : " + sb.toString());
			int intGrade = target.getCompositeKey().getGrade();
			String stringGrade = Integer.toString(intGrade);
			pstmt.setString(1, target.getCompositeKey().getMea_class_no());
			pstmt.setString(2, stringGrade);
			
			System.out.println("deleting with mea_class_no : "+target.getCompositeKey().getMea_class_no());
			System.out.println("deleting with grade : "+stringGrade);
			
			 int affected =  pstmt.executeUpdate();
			  System.out.println("# of records affected := " + affected  );
			 if(affected>0){
				 conn.commit();
				 jobStat = true;
			 }
		 
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.out.println("deleteMeaClassNo().jobStat := " + jobStat);
		return jobStat;
	}

	public static boolean deleteMeaClassNoJunc(Mea_class_no target){
		
		boolean jobStat = false;
		Connection conn = null;
		try{
		 	
			StringBuilder sb = new StringBuilder();
			sb.append("DELETE FROM JUNC_ITEM_HISTORY \n");
			sb.append("WHERE MEA_CLASS_NO= ?  \n");
			sb.append("AND GRADE= ? ");
			conn = DBConnection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			System.out.println("delete statement : " + sb.toString());
			int intGrade = target.getCompositeKey().getGrade();
			String stringGrade = Integer.toString(intGrade);
			pstmt.setString(1, target.getCompositeKey().getMea_class_no());
			pstmt.setString(2, stringGrade);
			
			System.out.println("deleting with mea_class_no : "+target.getCompositeKey().getMea_class_no());
			System.out.println("deleting with grade : "+stringGrade);
			
			 int affected =  pstmt.executeUpdate();
			  System.out.println("# of records affected := " + affected  );
			 if(affected>0){
				 conn.commit();
				 jobStat = true;
			 }
		 
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.out.println("deleteMeaClassNo().jobStat := " + jobStat);
		return jobStat;
	}
}
